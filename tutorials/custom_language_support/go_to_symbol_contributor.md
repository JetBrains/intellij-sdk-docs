---
title: 13. Go To Symbol Contributor
---

A go to symbol contributor helps the user to navigate to any PSI element by its name.

* bullet list
{:toc}

## 13.1. Define a Helper Method for Generated PSI Elements
To specify how a PSI element looks like in the *Go To Symbol* popup window, *Structure* tool window or another components, it should implement `getPresentation()`.
This means defining this method in the utility `com.intellij.sdk.language.psi.impl.SimplePsiImplUtil` and regenerate the parser and PSI classes.
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
            return element.getContainingFile().getName();
        }

        @Nullable
        @Override
        public Icon getIcon(boolean unused) {
            return SimpleIcons.FILE;
        }
    };
}
```

## 13.2. Update Grammar and Regenerate the Parser
Now add the `SimplePsiImplUtil.getPresentation()` to the `property` definition in the `Simple.bnf` grammar file by replacing the `property` definition with the lines below.
Don't forget to regenerate the parser after updating the file! 
Right click on the `Simple.bnf` file and select **Generate Parser Code**.
```java
property ::= (KEY? SEPARATOR VALUE?) | KEY {mixin="com.intellij.sdk.language.psi.impl.SimpleNamedElementImpl"
  implements="com.intellij.sdk.language.psi.SimpleNamedElement" methods=[getKey getValue getName setName getNameIdentifier getPresentation]}
```

## 13.3. Define a Go to Symbol Contributor
To enable the `simple_language` plugin to contribute items to "Navigate Class|File|Symbol" lists, subclass [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) to create `SimpleChooseNameContributor`:
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/SimpleChooseByNameContributor.java %}
```

## 13.4. Register the go to symbol contributor
The `SimpleChooseByNameContributor` implementation is registered with the IntelliJ Platform using the `gotoSymbolContributor` extension point.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <gotoSymbolContributor implementation="com.intellij.sdk.language.SimpleChooseByNameContributor"/>
  </extensions>
```

## 13.5. Run the project
Rebuild the project, and run `simple_language` in a Development Instance.
The IDE now supports navigating to a property definition by name pattern via **Navigate \| Symbol** action.

![Go To Symbol](img/go_to_symbol.png){:width="800px"}
