<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 21. Spell Checking

<link-summary>Sample implementation of spellchecking strategy allowing to analyze spell correctness in Simple language elements.</link-summary>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

<tldr>

**Reference**: [](spell_checking.md)

**Code**: [`SimpleSpellcheckingStrategy`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSpellcheckingStrategy.java)

</tldr>

Spell checking allows users to see spelling errors while editing code.

## Define a SimpleSpellcheckingStrategy

The [`SimpleSpellcheckingStrategy`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSpellcheckingStrategy.java) extends
[`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSpellcheckingStrategy.java" include-symbol="SimpleSpellcheckingStrategy"}

## Register the SimpleSpellcheckingStrategy

The implementation is registered with the IntelliJ Platform in the plugin
configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.spellchecker.support"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <spellchecker.support language="Simple" implementationClass="org.intellij.sdk.language.SimpleSpellcheckingStrategy"/>
</extensions>
```

## Run the Project

Run the project by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the <path>test.simple</path> file and make an intentional spelling mistake.
The IDE will highlight the error and suggest a quick fix.

![Spellchecking](spell_checking.png)
