<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 20. Documentation

<link-summary>Sample implementation of documentation provider showing definitions of the Simple language properties.</link-summary>

<tldr>

**Reference**: [](documentation.md)

**Code**: [`SimpleDocumentationProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleDocumentationProvider.java),
[`SimpleUtil`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleUtil.java)

**Testing**: [](documentation_test.md)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

> For plugins targeting IntelliJ Platform version 2023.1 or later, it is recommended to use the
> [](documentation.md#documentation-target-api).
>
{style="note"}

A [`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
helps users by showing documentation for symbols like method calls inside the editor.
For the custom language tutorial, we're implementing a version of this extension point (EP) for the Simple Language that shows the key/value,
the file where it is defined, and any related documentation comment.

## Implement a Documentation Provider and Register the EP

In the first step, we create an empty class that extends
[`AbstractDocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/AbstractDocumentationProvider.java)
and register it in the <path>[plugin.xml](plugin_configuration_file.md)</path>.

```java
final class SimpleDocumentationProvider extends AbstractDocumentationProvider { }
```

Make sure the extension is registered in the <path>plugin.xml</path> as shown below:

```xml
<extensions defaultExtensionNs="com.intellij">
  <!-- Other extensions… -->
  <lang.documentationProvider
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleDocumentationProvider"/>
</extensions>
```

## Ensure That the Correct PSI Element Is Used

For the Simple Language, we consider two use-cases:

1. A Simple key is [used inside a Java string literal](reference_contributor.md),
   and we would like to show documentation for the key/value right from the reference inside the Java file.
2. The cursor is already over a key/value definition inside a Simple file, in which case we would also like to show its documentation.

To ensure that the IntelliJ Platform chooses the correct element of type `SimpleProperty` when <ui-path>View | Quick Documentation</ui-path> is called,
we create a dummy implementation of `generateDoc()`:

```java
@Override
public @Nullable String generateDoc(PsiElement element,
                                    @Nullable PsiElement originalElement) {
  return super.generateDoc(element, originalElement);
}
```

Now, we set a breakpoint in our dummy implementation, debug the plugin, and call <ui-path>View | Quick Documentation</ui-path>
for the Simple property both in the Java file and the Simple file.
We do this by placing the cursor over the key and invoking <ui-path>View | Quick Documentation</ui-path> to show the documentation.

In both cases, we find that the element provided is `SimplePropertyImpl`, which is exactly what we hoped for.
However, there are two drawbacks: inside a Java string, your cursor needs to be directly over `key` in the string `"simple:key"` to make <emphasis>Quick Documentation</emphasis> work.
Since the Simple Language only allows for one property per string,
it would be nice if <emphasis>Quick Documentation</emphasis> worked no matter where your cursor was positioned in the string as long as the string contained a Simple property.
Inside a Simple file, the situation is similar, and calling <ui-path>View | Quick Documentation</ui-path> only works when the cursor is positioned on the key.

Refer to the [Addendum](#addendum) below, which explains how to improve on this situation by additionally overriding the `getCustomDocumentationElement()` method.

## Extract Documentation Comments from Key/Value Definitions

While `SimpleProperty` elements will provide us with their key and value, we have no direct access to a possible comment that is preceding the key/value definition.
Since we would like to show this comment in the documentation as well, we need a small helper function that extracts the text from the comment.
This function will reside in the `SimpleUtil` class and will find, for instance, the comment preceding `apikey` in the following short example:

```properties
# An application programming interface key (API key) is a unique
# identifier used to authenticate a user, developer, or calling
# program to an API.
apikey=ph342m91337h4xX0r5k!11Zz!
```

The following implementation will check if there is any comment preceding a `SimpleProperty`, and if there is,
it will collect all comment lines until it reaches either the previous key/value definition or the beginning of the file.
One caveat is that since we're collecting the comment lines backwards, we need to reverse the list before joining them into a single string.
A simple regex is used to remove the leading hash characters and whitespaces from each line.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleUtil.java" include-symbol="findDocumentationComment"}


## Render the Documentation

With easy ways to access the key, the value, the file, and a possible documentation comment,
we now have everything in place to provide a useful implementation of `generateDoc()`.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleDocumentationProvider.java" include-symbol="generateDoc"}


The creation of the rendered documentation is done in a separate method for clarity.
It uses `DocumentationMarkup` to align and format the contents.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleDocumentationProvider.java" include-symbol="renderFullDoc"}


The `addKeyValueSection()` method used is just a small helper function to reduce repetition.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleDocumentationProvider.java" include-symbol="addKeyValueSection"}


After implementing all the steps above, the IDE will show the rendered documentation for a Simple key when called with <ui-path>View | Quick Documentation</ui-path>.


## Implement Additional Functionality

We can provide implementations for additional functionality that comes with a `DocumentationProvider`.
For instance, when simply hovering the mouse over the code, it also shows documentation after a short delay.
It's not necessary that this popup shows the exact same information as when calling <ui-path>View | Quick Documentation</ui-path>, but for this tutorial, we'll do just that.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleDocumentationProvider.java" include-symbol="generateHoverDoc"}


When the mouse hovers over code with <shortcut>Ctrl</shortcut>/<shortcut>Cmd</shortcut> pressed, the IDE shows navigation information of the symbol under the cursor,
such as its namespace or package.
The implementation below will show the Simple key and the file where it is defined.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleDocumentationProvider.java" include-symbol="getQuickNavigateInfo"}


Finally, <ui-path>View | Quick Documentation</ui-path> can also be called from a selected entry within the autocompletion popup.
In that case, language developers need to ensure that the correct PSI element for generating the documentation is provided.
In the case of Simple Language, the lookup element is already a `SimpleProperty` and no additional work needs to be done.
In other circumstances, you can override `getDocumentationElementForLookupItem() `and return the correct PSI element.

## Addendum: Choosing a Better Target Element
{id="addendum"}

To be able to call <ui-path>View | Quick Documentation</ui-path> for Simple properties in all places of a Java string literal, two steps are required:

1. The extension point needs to be changed from `com.intellij.lang.documentationProvider` to `com.intellij.documentationProvider` because only then
   the Simple DocumentationProvider is called for PSI elements with a different language.
2. The `getCustomDocumentationElement()` method needs to be implemented to find the correct target PSI element for creating the documentation.

Therefore, the current version of the code could be extended to check whether <ui-path>View | Quick Documentation</ui-path> was called from inside a Java string or a Simple file.
It then uses PSI and `PsiReference` functionalities to determine the correct target element.
This allows getting documentation for a Simple property no matter where it was called inside a Java string literal or a Simple property definition.

```java
@Override
public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor,
                                                          @NotNull PsiFile file,
                                                          @Nullable PsiElement context,
                                                          int targetOffset) {
  if (context != null) {
    // In this part the SimpleProperty element
    // is extracted from inside a Java string
    if (context instanceof PsiJavaToken &&
        ((PsiJavaToken) context).getTokenType().equals(JavaTokenType.STRING_LITERAL)) {
      PsiElement parent = context.getParent();
      PsiReference[] references = parent.getReferences();
      for (PsiReference ref : references) {
        if (ref instanceof SimpleReference) {
          PsiElement property = ref.resolve();
          if (property instanceof SimpleProperty) {
            return property;
          }
        }
      }
    }
    // In this part the SimpleProperty element is extracted
    // when inside of a .simple file
    else if (context.getLanguage() == SimpleLanguage.INSTANCE) {
      PsiElement property =
        PsiTreeUtil.getParentOfType(context, SimpleProperty.class);
      if (property != null) {
        return property;
      }
    }
  }
  return super.getCustomDocumentationElement(
      editor, file, context, targetOffset);
}
```
