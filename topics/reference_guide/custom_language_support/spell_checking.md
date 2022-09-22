[//]: # (title: Spell Checking)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[Spell Checking](https://www.jetbrains.com/help/idea/spellchecking.html)
is used to check the correctness of natural languages within code.
Language plugins can implement customized spell checking by implementing
[`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)
and registering it in the `com.intellij.spellchecker.support` extension point.

**Examples:**
- [`JavaSpellcheckingStrategy`](%gh-ic%/java/java-impl/src/com/intellij/spellchecker/JavaSpellcheckingStrategy.java)
- [`HtmlSpellcheckingStrategy`](%gh-ic%/xml/impl/src/com/intellij/spellchecker/xml/HtmlSpellcheckingStrategy.java)

## SpellcheckingStrategy

`SpellcheckingStrategy` adjusts the spell checking behavior for PSI elements of a custom language
by providing methods to define:

1. Which PSI elements should be checked by this strategy.
2. How to extract the text from PSI elements.
3. How the text is broken into single words.

The class already contains a default strategy for spell checking of basic parts such as comments,
identifiers and plain text.
If you don't need anything else, you can just inherit from this class and register it.

If you need to check spelling for some specific elements in your language, then override `getTokenizer()`.
and use `isMyContext()` to determine if a PSI element should be checked by your strategy.
The `getTokenizer()` method returns an instance of
[Tokenizer](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/Tokenizer.java)
and is explained below.

### Tokenizer

The `tokenize()` method of `Tokenizer` defines which portions of a PSI element
need to be spell-checked by feeding them into the
[`TokenConsumer`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/TokenConsumer.java).
In the simplest case, the whole PSI element is consumed and its entire text is split into words and
checked for spelling.
For these simple cases, `SpellcheckingStrategy` already contains predefined tokenizers:

- `SpellcheckingStrategy.TEXT_TOKENIZER` for simple text elements.
- `SpellcheckingStrategy.EMPTY_TOKENIZER` for elements that don't require checking.
- `myCommentTokenizer` field for comments.
- `myXmlAttributeTokenizer` field for XML attributes.

However, there are situations where only fragments of the PSI element are textual content.
In these cases, `tokenize()` can take care of extracting the correct text-ranges and feed them
sequentially into the `TokenConsumer`.
If elements in your language require such special handling, then define a tokenizer by deriving from `Tokenizer`
and implement `tokenize()` which describes the logic you need.

**Example:**
[`MethodNameTokenizerJava`](%gh-ic%/java/java-impl/src/com/intellij/spellchecker/MethodNameTokenizerJava.java)

#### Splitter

In `Tokenizer.tokenize()` the `consumeToken()` method can take an instance of
[`Splitter`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/inspections/Splitter.java) as the second argument.
The `Splitter` defines how exactly the text is broken into words which is important for, e.g. identifiers or
variables that follow camel-case or snake-case naming.
As an example, please see how
[`IdentifierSplitter`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/inspections/IdentifierSplitter.java),
splits identifiers into separate words.

A custom language can define special splitting rules for elements by deriving from `Splitter`,
implementing the `split()` method and describing the logic for obtaining words from the passed text.

## SuppressibleSpellcheckingStrategy

If your language supports suppression annotations, then you can derive from the
[`SuppressibleSpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SuppressibleSpellcheckingStrategy.java)
class, which allows check if the spelling warning is suppressed.

Override `isSuppressedFor()` to check if the warning is suppressed for the passed element and `getSuppressActions()` to add a quick fix for element to suppress warning.

**Example:**
[`XmlSpellcheckingStrategy`](%gh-ic%/xml/impl/src/com/intellij/spellchecker/xml/XmlSpellcheckingStrategy.java)

## BundledDictionaryProvider

If the custom language contains words that are not known, then inherit from
[BundledDictionaryProvider](%gh-ic%/spellchecker/src/com/intellij/spellchecker/BundledDictionaryProvider.java).
Implement `getBundledDictionaries()` where return path to the word dictionary.

Register it with the `com.intellij.spellchecker.bundledDictionaryProvider` extension point.
