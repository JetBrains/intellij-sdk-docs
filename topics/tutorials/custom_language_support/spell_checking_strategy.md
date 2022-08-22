[//]: # (title: 21. Spell Checking)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

Spell Checker allows users to see spelling errors while editing code.

**Reference**: [](spell_checking.md)

## Define a SimpleSpellcheckingStrategy

The simple spell checking strategy extends
[`SpellcheckingStrategy`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSpellcheckingStrategy.java"}

## Register the SimpleSpellcheckingStrategy

The `SimpleSpellcheckingStrategy` implementation is registered with the IntelliJ Platform in the plugin
configuration file using the `com.intellij.spellchecker.support` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <spellchecker.support language="Simple" implementationClass="org.intellij.sdk.language.SimpleSpellcheckingStrategy"/>
</extensions>
```

## Run the Project

Run the project by using the Gradle
[runIde task](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin).

Open the <path>test.simple</path> file and make an intentional spelling mistake.
The IDE will highlight the error and suggest a quick fix.

![Spellchecking](spell_checking.png)
