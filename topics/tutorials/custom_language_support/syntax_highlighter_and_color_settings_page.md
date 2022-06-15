[//]: # (title: 5. Syntax Highlighter and Color Settings Page)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

The first level of syntax highlighting is based on the lexer output, and is provided by `SyntaxHighlighter`.
A plugin can also define color settings based on `ColorSettingPage` so the user can configure highlight colors.
The `SimpleSyntaxHighlighter`, `SimpleSyntaxHighlighterFactory`, and `SimpleColorSettingsPage` discussed on this page are demonstrated in the `simple_language_plugin` code sample.

**Reference**: [](syntax_highlighting_and_error_highlighting.md)

## Define a Syntax Highlighter

The Simple Language syntax highlighter class extends [`SyntaxHighlighterBase`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterBase.java).
As recommended in [Color Scheme Management](color_scheme_management.md#text-attribute-key-dependency), the Simple Language highlighting text attributes are specified as a dependency on one of standard Intellij Platform keys.
For the Simple Language, define only one scheme.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighter.java"}

### Define a Syntax Highlighter Factory

The factory provides a standard way for the IntelliJ Platform to instantiate the syntax highlighter for Simple Language files.
Here, `SimpleSyntaxHighlighterFactory` subclasses [`SyntaxHighlighterFactory`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighterFactory.java"}

### Register the Syntax Highlighter Factory

Register the factory with the IntelliJ Platform in the plugin configuration file using the `com.intellij.lang.syntaxHighlighterFactory` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.syntaxHighlighterFactory
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleSyntaxHighlighterFactory"/>
</extensions>
```

### Run the Project with Default Colors

Open the example Simple Language [properties file ](lexer_and_parser_definition.md#run-the-project) (`test.simple`) in the IDE Development Instance.
The colors for Simple Language Key, Separator, and Value highlighting default to the IDE _Language Defaults_ for Keyword, Braces, Operators, and Strings, respectively.

![Syntax highlighter](syntax_highlighter.png)

## Define a Color Settings Page

The color settings page adds the ability for users to customize color settings for the highlighting in Simple Language files.
The `SimpleColorSettingsPage` implements [`ColorSettingsPage`](upsource:///platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleColorSettingsPage.java"}

It is supported to group related attributes like operators or braces by separating the nodes with `//`, e.g.:

```java
AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
    new AttributesDescriptor("Operators//Plus", MySyntaxHighlighter.PLUS),
    new AttributesDescriptor("Operators//Minus", MySyntaxHighlighter.MINUS),
    new AttributesDescriptor("Operators//Advanced//Sigma", MySyntaxHighlighter.SIGMA),
    new AttributesDescriptor("Operators//Advanced//Pi", MySyntaxHighlighter.PI),
    //...
};
```

### Register the Color Settings Page

Register the Simple Language color settings page with the IntelliJ Platform in the plugin configuration file using the `com.intellij.colorSettingsPage` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettingsPage
      implementation="org.intellij.sdk.language.SimpleColorSettingsPage"/>
</extensions>
```

### Run the Project

Run the project by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

In the IDE Development Instance, open the Simple Language highlight settings page: <menupath>Settings/Preferences | Editor | Color Scheme | Simple</menupath>.
Each color initially inherits from a <control>Language Defaults</control> value.

![Color Settings Page](color_settings_page.png)
