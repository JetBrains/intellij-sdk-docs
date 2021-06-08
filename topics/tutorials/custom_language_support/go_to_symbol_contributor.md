[//]: # (title: 13. Go To Symbol Contributor)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A _Go to Symbol Contributor_ helps the user to navigate to any PSI element by its name.

**Reference**: [Go to Class and Go to Symbol](go_to_class_and_go_to_symbol.md)

## Define a Helper Method for Generated PSI Elements
To specify how a PSI element looks like in the **Go To Symbol** popup window, **Structure** tool window, or other components, it should implement `getPresentation()`.
This method gets defined in the utility class `SimplePsiImplUtil`, and the parser and PSI classes must be regenerated.
Add the following method to `SimplePsiImplUtil`:

```java
public static ItemPresentation getPresentation(final SimpleProperty element) {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return element.getKey();
      }

      @Nullable
      @Override
      public String getLocationString() {
        PsiFile containingFile = element.getContainingFile();
        return containingFile == null ? null : containingFile.getName();
      }

      @Override
      public Icon getIcon(boolean unused) {
        return SimpleIcons.FILE;
      }
    };
  }
```

## Update Grammar and Regenerate the Parser
Now add the `SimplePsiImplUtil.getPresentation()` to the `property` methods definition in the `Simple.bnf` grammar file by replacing the `property` definition with the lines below.
Don't forget to regenerate the parser after updating the file!
Right-click on the `Simple.bnf` file and select **Generate Parser Code**.

```java
property ::= (KEY? SEPARATOR VALUE?) | KEY {
  mixin="org.intellij.sdk.language.psi.impl.SimpleNamedElementImpl"
  implements="org.intellij.sdk.language.psi.SimpleNamedElement"
  methods=[getKey getValue getName setName getNameIdentifier getPresentation]
}
```

## Define a Go to Symbol Contributor
To enable the `simple_language_plugin` to contribute items to **Navigate \| Class..., File..., Symbol...** lists, subclass [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) to create `SimpleChooseByNameContributor`:

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
Run the plugin by using the Gradle [runIde task](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin).

The IDE now supports navigating to a property definition by name pattern via **Navigate \| Symbol** action.

![Go To Symbol](go_to_symbol.png){width="800"}