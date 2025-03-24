<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 13. Go To Symbol Contributor

<link-summary>Sample implementation of a "Go to Symbol" contributor allowing for searching and navigating to Simple language properties definitions.</link-summary>

<tldr>

**Reference**: [](go_to_class_and_go_to_symbol.md)

**Code**:
[`SimpleChooseByNameContributor`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleChooseByNameContributor.java),
[`SimplePsiImplUtil`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimplePsiImplUtil.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

A _Go to Symbol Contributor_ helps the user to navigate to any PSI element by its name.

## Define a Helper Method for Generated PSI Elements

To specify what a PSI element looks like in the <ui-path>Navigate | Symbol</ui-path> popup window, <control>Structure</control> tool window, or other components, it should implement `getPresentation()`.
This method gets defined in the utility class `SimplePsiImplUtil`, and the parser and PSI classes must be regenerated.
Add the following method to [`SimplePsiImplUtil`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimplePsiImplUtil.java):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimplePsiImplUtil.java" include-symbol="getPresentation"}

In addition, to provide an icon for the displayed items, extend [`IconProvider`](%gh-ic%/platform/core-api/src/com/intellij/ide/IconProvider.java) and register
it in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.iconProvider"/></include>. See [`SimplePropertyIconProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimplePropertyIconProvider.java):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimplePropertyIconProvider.java" include-symbol="SimplePropertyIconProvider"}

## Update Grammar and Regenerate the Parser

Now add the `SimplePsiImplUtil.getPresentation()` to the `property` methods definition in the <path>Simple.bnf</path> grammar file by replacing the `property` definition with the lines below.
Don't forget to regenerate the parser after updating the file!
Right-click on the <path>Simple.bnf</path> file and select <control>Generate Parser Code</control>.

```bnf
property ::= (KEY? SEPARATOR VALUE?) | KEY {
  mixin="org.intellij.sdk.language.psi.impl.SimpleNamedElementImpl"
  implements="org.intellij.sdk.language.psi.SimpleNamedElement"
  methods=[getKey getValue getName setName getNameIdentifier getPresentation]
}
```

## Define a Go To Symbol Contributor

To contribute items to <ui-path>Navigate | Symbol</ui-path> results, subclass [`ChooseByNameContributorEx`](%gh-ic%/platform/lang-impl/src/com/intellij/navigation/ChooseByNameContributorEx.java)
to create [`SimpleChooseByNameContributor`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleChooseByNameContributor.java):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleChooseByNameContributor.java" include-symbol="SimpleChooseByNameContributor"}

## Register the Go To Symbol Contributor

The `SimpleChooseByNameContributor` implementation is registered with the IntelliJ Platform in the plugin configuration file
using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.gotoSymbolContributor"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <gotoSymbolContributor
      implementation="org.intellij.sdk.language.SimpleChooseByNameContributor"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

The IDE now supports navigating to a property definition by name pattern via <ui-path>Navigate | Symbol</ui-path> action.

![Go To Symbol](go_to_symbol.png){width="800"}
