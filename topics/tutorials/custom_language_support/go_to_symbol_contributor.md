[//]: # (title: 13. Go To Symbol Contributor)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

A _Go to Symbol Contributor_ helps the user to navigate to any PSI element by its name.

**Reference**: [](go_to_class_and_go_to_symbol.md)

## Define a Helper Method for Generated PSI Elements

To specify how a PSI element looks like in the <menupath>Navigate | Symbol</menupath> popup window, <control>Structure</control> tool window, or other components, it should implement `getPresentation()`.
This method gets defined in the utility class `SimplePsiImplUtil`, and the parser and PSI classes must be regenerated.
Add the following method to `SimplePsiImplUtil`:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/impl/SimplePsiImplUtil.java" include-symbol="getPresentation"}

## Update Grammar and Regenerate the Parser

Now add the `SimplePsiImplUtil.getPresentation()` to the `property` methods definition in the <path>Simple.bnf</path> grammar file by replacing the `property` definition with the lines below.
Don't forget to regenerate the parser after updating the file!
Right-click on the <path>Simple.bnf</path> file and select <control>Generate Parser Code</control>.

```java
property ::= (KEY? SEPARATOR VALUE?) | KEY {
  mixin="org.intellij.sdk.language.psi.impl.SimpleNamedElementImpl"
  implements="org.intellij.sdk.language.psi.SimpleNamedElement"
  methods=[getKey getValue getName setName getNameIdentifier getPresentation]
}
```

## Define a Go To Symbol Contributor

To contribute items to <menupath>Navigate | Symbol</menupath> results, subclass [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) to create `SimpleChooseByNameContributor`:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleChooseByNameContributor.java"}

## Register the Go To Symbol Contributor

The `SimpleChooseByNameContributor` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `com.intellij.gotoSymbolContributor` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <gotoSymbolContributor
      implementation="org.intellij.sdk.language.SimpleChooseByNameContributor"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

The IDE now supports navigating to a property definition by name pattern via <menupath>Navigate | Symbol</menupath> action.

![Go To Symbol](go_to_symbol.png){width="800"}
