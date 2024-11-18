<!-- Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Element Patterns

<link-summary rel="excerpt"/>
<p id="excerpt">
Element patterns provide a generic way to specify conditions on objects.
</p>
Plugin authors use them to check whether PSI elements match a particular structure.
Just as regular expressions for strings test whether a (sub-)string matches a particular pattern, element patterns are used to put conditions on the nested structure of PSI elements.
Their two main applications inside the IntelliJ Platform are:

1. Specifying where auto-completion should occur when implementing [a completion contributor](completion_contributor.md) for a custom language.
2. Specifying PSI elements that provide further references via [a PSI reference contributor](psi_references.md#contributed-references).

However, plugin authors rarely implement the [`ElementPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/ElementPattern.java) interface directly.
Instead, we recommend using the high-level pattern classes provided by the IntelliJ Platform:

| Class                                                                                               | Main Contents                                                                                                             | Notable Examples                                                                                                                                                                                                                                                                          |
|-----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`StandardPatterns`](%gh-ic%/platform/core-api/src/com/intellij/patterns/StandardPatterns.java)     | Factory for string and char pattern (see below); Logical operations like and, or, not                                     | [`LogbackReferenceContributor`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/ext/logback/LogbackReferenceContributor.kt), [`RegExpCompletionContributor`](%gh-ic%/RegExpSupport/src/org/intellij/lang/regexp/RegExpCompletionContributor.java)                                 |
| [`PlatformPatterns`](%gh-ic%/platform/core-api/src/com/intellij/patterns/PlatformPatterns.java)     | Factory for PSI-, IElement-, and VirtualFile-patterns                                                                     | [`FxmlReferencesContributor`](%gh-ic%/plugins/javaFX/src/org/jetbrains/plugins/javaFX/fxml/refs/FxmlReferencesContributor.java), [`PyDataclassCompletionContributor`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/completion/PyDataclassCompletionContributor.kt) |
| [`PsiElementPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/PsiElementPattern.java)   | Patterns for PSI; Checks for children, parents, or neighboring leaves                                                     | [`XmlCompletionContributor`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/completion/XmlCompletionContributor.java)                                                                                                                                                                      |
| [`CollectionPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/CollectionPattern.java)   | Filter and check pattern collections; Mainly used to provide functionality for other high-level pattern classes           | [`PsiElementPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/PsiElementPattern.java)                                                                                                                                                                                         |
| [`TreeElementPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/TreeElementPattern.java) | Patterns specifically for checking (PSI) tree structure                                                                   | [`PyMetaClassCompletionContributor`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/completion/PyMetaClassCompletionContributor.java)                                                                                                                                |
| [`StringPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/StringPattern.java)           | Check if strings match, have a certain length, have a specific beginning or ending, or are one of a collection of strings | [`AbstractGradleCompletionContributor`](%gh-ic%/plugins/gradle/java/src/codeInsight/AbstractGradleCompletionContributor.kt)                                                                                                                                                               |
| [`CharPattern`](%gh-ic%/platform/core-api/src/com/intellij/patterns/CharPattern.java)               | Check if characters are whitespace, digits, or Java identifier parts                                                      | [`CompletionUtil`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/completion/CompletionUtil.java)                                                                                                                                                                            |

Some built-in languages in the IntelliJ Platform implement their own pattern classes and can provide additional examples:

- [`XmlPatterns`](%gh-ic%/xml/xml-psi-api/src/com/intellij/patterns/XmlPatterns.java) provides patterns for XML attributes, values, entities, and texts.
- [`PsiJavaPatterns`](%gh-ic%/java/java-psi-api/src/com/intellij/patterns/PsiJavaPatterns.java) provides patterns for literals, strings, arguments, and function/method arguments for Java.
- [`DomPatterns`](%gh-ic%/xml/dom-openapi/src/com/intellij/patterns/DomPatterns.java) builds upon `XmlPatterns` and acts as a wrapper to provide further patterns for [DOM-API](xml_dom_api.md).

## Examples

A good starting point for element patterns is the [Custom Language Support Tutorial](custom_language_support_tutorial.md).
They are used in the [completion](completion_contributor.md#define-a-completion-contributor) and [reference](reference_contributor.md#define-a-reference-contributor) contributor section of the tutorial.
However, the IntelliJ Platform source code provides many more examples of element patterns for built-in languages like JSON, XML, Groovy, Markdown, and so on.
Checking the references in the table above or searching for usages of the high-level pattern classes will provide a comprehensive list that shows how element patterns are used in production code.

For instance, an example can be found in the JavaFX plugin [`FxmlReferencesContributor`](%gh-ic%/plugins/javaFX/src/org/jetbrains/plugins/javaFX/fxml/refs/FxmlReferencesContributor.java) that tests if the given PSI element is an XML attribute value inside a <path>*.fxml</path> file.

```java
XmlAttributeValuePattern attributeValueInFxml =
    XmlPatterns.xmlAttributeValue().inVirtualFile(
        virtualFile().withExtension(JavaFxFileTypeFactory.FXML_EXTENSION)
    );
```

As shown in the code above, element patterns can be stacked and combined to create more complex conditions.
[`JsonCompletionContributor`](%gh-ic%/json/src/com/intellij/json/codeinsight/JsonCompletionContributor.java) contains another example with more requirements on the PSI element.

```java
PsiElementPattern.Capture<PsiElement> AFTER_COMMA_OR_BRACKET_IN_ARRAY =
    psiElement()
        .afterLeaf("[", ",")
        .withSuperParent(2, JsonArray.class)
        .andNot(
            psiElement().withParent(JsonStringLiteral.class)
        );
```

The above pattern makes sure that the PSI element:

1. Appears after either an open bracket or a comma, which is expressed by putting a restriction on the neighboring leaf element.
2. Has `JsonArray` as a level-two parent, which indicates that the PSI element must be inside a JSON array.
3. Does not have a `JsonStringLiteral` as a parent, which prevents situations where a string with a bracket or comma inside an array would give a false-positive match.

This last example shows that corner cases need to be considered carefully even for simple patterns.

## Tools and Debugging

Working with element patterns can be tricky, and plugin authors need a solid understanding of the underlying PSI structure to get it right.
Therefore, it is recommended to use the [PsiViewer plugin or built-in PSI viewer](explore_api.md#internalMode) and verify that elements indeed have the expected structure and properties.

### Debugging

For this section, it is assumed that plugin authors have a basic understanding of how to work with [a debugger](https://www.jetbrains.com/help/idea/debugging-code.html), how to [set breakpoints](https://www.jetbrains.com/help/idea/using-breakpoints.html#set-breakpoints), and how to set [conditions on breakpoints](https://www.jetbrains.com/help/idea/using-breakpoints.html#properties).

When debugging element patterns, plugin authors need to keep in mind that the places where element patterns are instantiated are unrelated to where they are actually used.
For instance, while patterns for completion contributors are instantiated when registering the contributor, the patterns are checked during completion while typing.
Therefore, finding the correct locations in the IntelliJ Platform for debugging element patterns is the first important step.

However, setting breakpoints inside `ElementPattern` will result in many false-positives since element patterns are used extensively throughout the IDE.
One way to filter out these false-positives is to use a condition on the breakpoints.
The following steps can help you investigate where patterns are checked:

1. Set breakpoints at the `ElementPattern.accepts()` methods.
2. Set a condition on the breakpoints that checks whether the string representation of the pattern contains an _identifiable part_ of the pattern.
3. Debug, and when the breakpoint triggers, make sure it is the right pattern and investigate the call stack to find relevant methods that use the pattern check.
4. Debug the relevant methods, e.g. methods that fill completion variants or find references.

Note that finding an identifiable part of a pattern can be achieved by setting a breakpoint where the pattern is **instantiated** and checking its string representation.

### Debugging Example

Using the Markdown code example from above, we note that the `MarkdownLinkDestinationImpl` class is used in the element pattern.
Now, set a breakpoint at:

```text
com.intellij.patterns.ElementPattern#accepts(
  java.lang.Object,
  com.intellij.util.ProcessingContext
)
```

Right-click on the breakpoint and set the following as a condition:

```java
toString().contains("MarkdownLinkDestinationImpl")
```

Now start a debug session and open a Markdown file.
When the breakpoint hits, the call stack in the [debug tool window](https://www.jetbrains.com/help/idea/debug-tool-window.html) shows that reference-providers are checked in the method `doGetReferencesFromProviders` within [`ReferenceProvidersRegistryImpl`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/resolve/reference/ReferenceProvidersRegistryImpl.java).
This provides a good starting point for further investigation.
