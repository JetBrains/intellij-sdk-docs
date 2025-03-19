<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 10. Reference Contributor

<link-summary>Sample implementation of reference contributor adding Simple language references in Java strings.</link-summary>

<tldr>

**Reference**: [](references_and_resolve.md), [](psi_references.md)

**Code**: [`SimpleNamedElement`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleNamedElement.java),
[`SimpleNamedElementImpl`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimpleNamedElementImpl.java),
[`SimpleReference.java`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleReference.java),
[`SimpleReferenceContributor`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleReferenceContributor.java),
[`SimpleRefactoringSupportProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleRefactoringSupportProvider.java)

**Testing**: [](completion_test.md), [](rename_test.md), [](reference_test.md),

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

The references functionality is one of the most important parts in the implementation of custom language support.
Resolving references means the ability to go from the usage of an element to its declaration, completion, rename refactoring, find usages, etc.

> Every PSI element that can be renamed or referenced needs to implement [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) interface.
>
{style="note"}

## Define a Named Element Class

The classes below show how the Simple Language fulfills the need to implement `PsiNamedElement`.

The [`SimpleNamedElement`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleNamedElement.java) interface is subclassed from [`PsiNameIdentifierOwner`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNameIdentifierOwner.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleNamedElement.java" include-symbol="SimpleNamedElement"}

The [`SimpleNamedElementImpl`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimpleNamedElementImpl.java) class implements the `SimpleNamedElement` interface and extends [`ASTWrapperPsiElement`](%gh-ic%/platform/core-impl/src/com/intellij/extapi/psi/ASTWrapperPsiElement.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimpleNamedElementImpl.java" include-symbol="SimpleNamedElementImpl"}

## Define Helper Methods for Generated PSI Elements

Modify `SimplePsiImplUtil` to support new methods that get added to the PSI class for Simple Language.
Note that `SimpleElementFactory` isn't defined until the [next step](#define-an-element-factory), so for now it shows as an error.

```java
public class SimplePsiImplUtil {

  // ...

  public static String getName(SimpleProperty element) {
    return getKey(element);
  }

  public static PsiElement setName(SimpleProperty element, String newName) {
    ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
    if (keyNode != null) {
      SimpleProperty property =
          SimpleElementFactory.createProperty(element.getProject(), newName);
      ASTNode newKeyNode = property.getFirstChild().getNode();
      element.getNode().replaceChild(keyNode, newKeyNode);
    }
    return element;
  }

  public static PsiElement getNameIdentifier(SimpleProperty element) {
    ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
    return keyNode != null ? keyNode.getPsi() : null;
  }

  // ...

}
```

## Define an Element Factory

The `SimpleElementFactory` provides methods for creating `SimpleFile`.

```java
package org.intellij.sdk.language.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.intellij.sdk.language.SimpleFileType;

public class SimpleElementFactory {

  public static SimpleProperty createProperty(Project project, String name) {
    SimpleFile file = createFile(project, name);
    return (SimpleProperty) file.getFirstChild();
  }

  public static SimpleFile createFile(Project project, String text) {
    String name = "dummy.simple";
    return (SimpleFile) PsiFileFactory.getInstance(project).
        createFileFromText(name, SimpleFileType.INSTANCE, text);
  }
}
```

## Update Grammar and Regenerate the Parser

Now make corresponding changes to the <path>Simple.bnf</path> grammar file by replacing the `property` definition with the lines below.
Remember to regenerate the parser after updating the file!
Right-click on the <path>Simple.bnf</path> file and select **Generate Parser Code**.

```bnf
property ::= (KEY? SEPARATOR VALUE?) | KEY {
  mixin="org.intellij.sdk.language.psi.impl.SimpleNamedElementImpl"
  implements="org.intellij.sdk.language.psi.SimpleNamedElement"
  methods=[getKey getValue getName setName getNameIdentifier]
}
```

## Define a Reference

Now define a reference class [`SimpleReference.java`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleReference.java) to resolve a property from its usage.
As a single reference can [resolve to multiple](references_and_resolve.md#resolving-to-multiple-targets) Simple properties, it implements [`PsiPolyVariantReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java).
To simplify the implementation, [`PsiPolyVariantReferenceBase`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiPolyVariantReferenceBase.java) is used as a base class.


```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleReference.java" include-symbol="SimpleReference"}

## Define a Reference Contributor

A reference contributor allows the `simple_language_plugin` to provide references to Simple Language from elements in other languages such as Java.
Create [`SimpleReferenceContributor`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleReferenceContributor.java) by subclassing [`PsiReferenceContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java).
Contribute a reference to each usage of a property:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleReferenceContributor.java" include-symbol="SimpleReferenceContributor"}

## Register the Reference Contributor

The `SimpleReferenceContributor` implementation is registered using
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.psi.referenceContributor"/></include> and specifying `language="JAVA"`.

```xml
<extensions defaultExtensionNs="com.intellij">
  <psi.referenceContributor
      language="JAVA"
      implementation="org.intellij.sdk.language.SimpleReferenceContributor"/>
</extensions>
```

## Run the Project with the Reference Contributor

Run the project by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

The IDE now resolves the property and provides [completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#basic_completion) suggestions:

![Reference Contributor](reference_contributor.png){width="800"}

The [Rename refactoring](https://www.jetbrains.com/help/idea/rename-refactorings.html#invoke-rename-refactoring) functionality is now available from definition and usages.

![Rename](rename.png){width="800"}

## Define a Refactoring Support Provider

Support for in-place refactoring is specified explicitly in a refactoring support provider.
Create [`SimpleRefactoringSupportProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleRefactoringSupportProvider.java) by subclassing [`RefactoringSupportProvider`](%gh-ic%/platform/refactoring/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java)
As long as an element is a `SimpleProperty` it is allowed to be refactored:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleRefactoringSupportProvider.java" include-symbol="SimpleRefactoringSupportProvider"}

## Register the Refactoring Support Provider

The `SimpleRefactoringSupportProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file
using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.refactoringSupport"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.refactoringSupport
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleRefactoringSupportProvider"/>
</extensions>
```

## Run the Project

Run the project by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

The IDE now supports [refactoring](https://www.jetbrains.com/help/idea/rename-refactorings.html) suggestions:

![In Place Rename](in_place_rename.png){width="800"}
