---
title: 13. Go To Symbol Contributor
---

*A go to symbol contributor helps user to navigate to any PSI element by its name.*

### 13.1. Define helper method for generated PSI elements

To specify how a PSI element looks like in the *Go To Symbol* popup window, *Structure* tool window or another components, it should implement *getPresentation* method.

This means we need to define this method in our utility *com.simpleplugin.psi.impl.SimplePsiImplUtil* and regenerate the parser and PSI classes.

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

### 13.2. Update grammar and regenerate the parser

```java
property ::= (KEY? SEPARATOR VALUE?) | KEY {mixin="com.simpleplugin.psi.impl.SimpleNamedElementImpl"
  implements="com.simpleplugin.psi.SimpleNamedElement" methods=[getKey getValue getName setName getNameIdentifier getPresentation]}
```

Regenerate the parser by right clicking on the `Simple.bnf` file and selecting _Generate Parser Code_.

### 13.3. Define a go to symbol contributor

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleChooseByNameContributor.java %}
```

### 13.4. Register the go to symbol contributor

```xml
<gotoSymbolContributor implementation="com.simpleplugin.SimpleChooseByNameContributor"/>
```

### 13.5. Run the project

Now we can navigate to a property definition by name pattern via **Navigate \| Symbol** action.

![Go To Symbol](img/go_to_symbol.png)
