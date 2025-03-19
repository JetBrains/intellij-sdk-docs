<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 17. Code Style Settings

<link-summary>Sample implementation of code style settings allowing to configure the Simple language formatter.</link-summary>

<tldr>

**Reference**: [](code_formatting.md#code-style-settings)

**Code**: [`SimpleCodeStyleSettings`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettings.java),
[`SimpleCodeStyleSettingsProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettingsProvider.java),
[`SimpleLanguageCodeStyleSettingsProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguageCodeStyleSettingsProvider.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

Code style settings enable defining formatting options.
A code style settings provider creates an instance of the settings and also creates an options page in Settings.
This example creates a Settings page that uses the default language code style settings, customized by a language code style settings provider.

## Define Code Style Settings

Define [`SimpleCodeStyleSettings`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettings.java)
for Simple Language by subclassing [`CustomCodeStyleSettings`](%gh-ic%/platform/code-style-api/src/com/intellij/psi/codeStyle/CustomCodeStyleSettings.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettings.java" include-symbol="SimpleCodeStyleSettings"}

## Define Code Style Settings Provider

The code style settings provider gives the IntelliJ Platform a standard way to instantiate `CustomCodeStyleSettings` for the Simple Language.

Define [`SimpleCodeStyleSettingsProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettingsProvider.java)
for Simple Language by subclassing [`CodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettingsProvider.java" include-symbol="SimpleCodeStyleSettingsProvider"}

## Register the Code Style Settings Provider

The `SimpleCodeStyleSettingsProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file
using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeStyleSettingsProvider"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <codeStyleSettingsProvider
      implementation="org.intellij.sdk.language.SimpleCodeStyleSettingsProvider"/>
</extensions>
```

## Define the Language Code Style Settings Provider

Define [`SimpleLanguageCodeStyleSettingsProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguageCodeStyleSettingsProvider.java) for Simple Language by subclassing [`LanguageCodeStyleSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java), which provides common code style settings for a specific language.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguageCodeStyleSettingsProvider.java" include-symbol="SimpleLanguageCodeStyleSettingsProvider"}

## Register the Language Code Style Settings Provider

The ``SimpleLanguageCodeStyleSettingsProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file
using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.langCodeStyleSettingsProvider"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <langCodeStyleSettingsProvider
      implementation="org.intellij.sdk.language.SimpleLanguageCodeStyleSettingsProvider"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

In the IDE Development Instance, open the Simple Language code formatting page: <ui-path>Settings | Editor | Code Style | Simple</ui-path>.

![Code Style Settings](code_style_settings.png)
