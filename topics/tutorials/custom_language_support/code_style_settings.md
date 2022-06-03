[//]: # (title: 17. Code Style Settings)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

Code style settings enable defining formatting options.
A code style settings provider creates an instance of the settings and also creates an options page in settings/preferences.
This example creates a settings/preferences page that uses the default language code style settings, customized by a language code style settings provider.

**Reference**: [Code Style Settings](code_formatting.md#code-style-settings)

## Define Code Style Settings

Define a code style settings for Simple Language by subclassing [`CustomCodeStyleSettings`](upsource:///platform/code-style-api/src/com/intellij/psi/codeStyle/CustomCodeStyleSettings.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettings.java"}

## Define Code Style Settings Provider

The code style settings provider gives the IntelliJ Platform a standard way to instantiate `CustomCodeStyleSettings` for the Simple Language.
Define a code style settings provider for Simple Language by subclassing [`CodeStyleSettingsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/CodeStyleSettingsProvider.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCodeStyleSettingsProvider.java"}

## Register the Code Style Settings Provider

The `SimpleCodeStyleSettingsProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `com.intellij.codeStyleSettingsProvider` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <codeStyleSettingsProvider
      implementation="org.intellij.sdk.language.SimpleCodeStyleSettingsProvider"/>
</extensions>
```

## Define the Language Code Style Settings Provider

Define a code style settings provider for Simple Language by subclassing [`LanguageCodeStyleSettingsProvider`](upsource:///platform/lang-api/src/com/intellij/psi/codeStyle/LanguageCodeStyleSettingsProvider.java), which provides common code style settings for a specific language.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguageCodeStyleSettingsProvider.java"}

## Register the Language Code Style Settings Provider

The `SimpleLanguageCodeStyleSettingsProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `com.intellij.langCodeStyleSettingsProvider` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <langCodeStyleSettingsProvider
      implementation="org.intellij.sdk.language.SimpleLanguageCodeStyleSettingsProvider"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

In the IDE Development Instance, open the Simple Language code formatting page: <menupath>Settings/Preferences | Editor | Code Style | Simple</menupath>.

![Code Style Settings](code_style_settings.png)
