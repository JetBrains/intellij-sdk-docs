[//]: # (title: Spell Checking)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[Spell Checking](https://www.jetbrains.com/help/idea/spellchecking.html)
widely used to check the correctness of natural languages within code.

The starting point for the spell checking is
[`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)
class, which is registered in the `com.intellij.spellchecker.support` extension point.

**Examples:**
- [`JavaSpellcheckingStrategy`](%gh-ic%/java/java-impl/src/com/intellij/spellchecker/JavaSpellcheckingStrategy.java)
- [`HtmlSpellcheckingStrategy`](%gh-ic%/xml/impl/src/com/intellij/spellchecker/xml/HtmlSpellcheckingStrategy.java)

## SpellcheckingStrategy

`SpellcheckingStrategy` implements a strategy for tokenizing the text of PSI elements, which will then be checked for spelling.

The class already implements strategy for spell checking of basic parts such as comments, identifiers and plain text, so if you don't need anything else, you can just inherit from this class and register it.

If you need to check spelling for some specific elements in your language, then override `getTokenizer()`.

This method returns an instance of the
[Tokenizer](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/Tokenizer.java)
class.

### Tokenizer

The `Tokenizer` is responsible for splitting the element into words for spell checking.

The `SpellcheckingStrategy` class already contains two predefined tokenizers.

Through the field `myCommentTokenizer` you can get a tokenizer for comments, and through `myXmlAttributeTokenizer` for XML attributes.

You can also get a tokenizer for text through the `SpellcheckingStrategy.TEXT_TOKENIZER` static field.

If some element doesn't need spell checking then return `SpellcheckingStrategy.EMPTY_TOKENIZER`

If your language requires special handling, then you can define your tokenizer by deriving from the `Tokenizer` class.
In it, override the `tokenize()` in which describe the logic for obtaining tokens from the passed element.

**Example:**
[`MethodNameTokenizerJava`](%gh-ic%/java/java-impl/src/com/intellij/spellchecker/MethodNameTokenizerJava.java)

In `Tokenizer.tokenize()` the `consumeToken()` method can take [`Splitter`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/inspections/Splitter.java) as the second argument.

#### Splitter

Splitter allow define the logic for splitting text into words.

For example,
[`IdentifierSplitter`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/inspections/IdentifierSplitter.java),
splits text into separate identifiers.

If your language requires special handling, then you can define your splitter by deriving from the `Splitter` class.
In it, override the `split()` in which describe the logic for obtaining words from the passed text.

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
