<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Providing Translations

<link-summary>Translating IDE and plugin texts used in UI, inspections, file templates, etc.</link-summary>

Translations for IntelliJ Platform products and plugins can be provided in two ways:
- [](#language-packs)
- [](#bundled-translations)

## Language Packs

Localizing IDEs is achieved by providing language packs (see available [language packs](https://plugins.jetbrains.com/search?tags=Language%20Pack)).
Language packs are IntelliJ Platform plugins containing translations of UI texts.
Official language packs contain translations of all the UI texts used in the IDE and in plugins developed by JetBrains.

Please note that language packs aim for full IDE localization.
If it is required to translate a plugin, see the [](#bundled-translations) section.

Language packs must define their language.
The language definition is provided in the [`plugin.xml`](plugin_configuration_file.md) file with `com.intellij.languageBundle` extension point (EP), e.g.:
```xml
<extensions defaultExtensionNs="com.intellij">
  <languageBundle locale="zh-CN"/>
</extensions>
```

The `locale` attribute defines the translation language on two possible levels:
- region level, e.g.: `zh-CN` - Chinese (Simplified), `zh-TW` - Chinese (Taiwan)
- language level, e.g., `ja` - Japanese

### Language Selection

It is important to note that there is no language chooser available in the IDE and language packs serve as the IDE "language switcher".
Installing a language pack changes the IDE language to the one defined by the `languageBundle` EP.
Only a single language pack can be installed at the same time, and restart is required for the translations to take effect.

### Language Pack Translations Structure

See the [translated elements](#translated-elements) list for the elements possible to translate.
All the elements should be located in exactly the same paths as in original locations in their JAR files.

For example, if the original location of a message bundle is <path>$PLUGIN_JAR$/messages/AbcBundle.properties</path>, it must be located in <path>$LANGUAGE_PACK_JAR$/messages/AbcBundle.properties</path>.

It is allowed to organize them within [localization directories or with file name language suffixes](#bundled-translations-structure), but it is unnecessary as language pack can define only a single language.

In case of doubts, it is recommended to inspect the contents of existing language packs.

## Bundled Translations

Since 2024.1, IntelliJ Platform partially supports providing translations directly bundled in the IDE or plugins.
See the [translated elements](#translated-elements) list for the elements possible to translate.

An IDE module or a plugin can provide multiple language translations in a single distribution, e.g., `zh-CN` and `ja`.
Proper localization files will be used at runtime depending on the [IDE language](#language-selection).

### Bundled Translations Structure

Translations for a specific language can be organized in two ways:
- Language directory: <path>/localization/$LANGUAGE_CODE$/$REGION_CODE$</path> (`$REGION_CODE$` level is optional).
  Example:
  - Original template description:

    <path>/fileTemplates/code/JavaDoc Class.java.html</path>
  - Translated template description: <path></path>

    <path>/localization/zh/CN/fileTemplates/code/JavaDoc Class.java.html</path>
- Localization suffix in file name: <path>/intentionDescriptions/QuickEditAction/description_$LANGUAGE_CODE$_$REGION_CODE$.html</path>.
  Example:
  - Original template description:

    <path>/intentionDescriptions/QuickEditAction/description.html</path>
  - Translated template description: <path></path>

    <path>/intentionDescriptions/QuickEditAction/description_zh_CN.html</path>

The proper directory layout/file name suffixes is the only thing needed for the translations to work.
No additional actions like registering EPs are needed.

## Translated Elements

The following table contains the possible translated elements and information about their support in language packs and IDE/plugins.

| Element                                                                                                                                                                     | Language Pack | IDE/Plugin       |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|------------------|
| Message bundles<p>(<path>*.properties</path> files)</p>                                                                                                                     | Yes           | Since 2024.1     |
| [Inspection descriptions](code_inspections.md#inspection-description)<p>(<path>*.html</path> files in <path>/inspectionDescriptions</path> directory)</p>                   | Yes           | Since 2024.1     |
| [Intention descriptions](code_intentions.md#about-intention-actions)<p>(<path>*.html</path> files in <path>/intentionDescriptions</path> directory)</p>                     | Yes           | Since 2024.1     |
| tips of the day<p><path>*.html</path> files in <path>tips</path> directory</p>                                                                                              | Yes           | Since 2024.1     |
| [searchable options](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions)<p>(<path>*.xml</path> file in <path>/search</path>)</p>                         | Yes           | 2024.2 (planned) |
| [File template descriptions](providing_file_templates.md#creating-file-template-description)<p>(<path>*.html</path> files in the <path>/fileTemplates</path> directory)</p> | Yes           | 2024.2 (planned) |
| [Postfix template descriptions](postfix_templates.md#postfix-template-description)<p>(<path>*.xml</path> file in <path>/postfixTemplates</path> directory)</p>              | Yes           | 2024.2 (planned) |

## Translation Priority

Translations can be provided on three different levels:
- region-specific translation
- language-specific translation
- default translation (English)

In addition, translations can be [organized in directories or with file suffixes](#bundled-translations-structure), and the same translation can be provided by a [language pack](#language-packs) or [IDE/plugin](#bundled-translations).

All these conditions determine how a single translation is resolved at runtime.
The priority is as follows:
1. Region level localization file:
    1. located within the <path>localization</path> directory of the language pack
    2. located within the <path>localization</path> directory of the IDE or plugin
    3. via suffix within the language pack
    4. via suffix within the IDE or plugin
2. Language level localization file:
    1. located within the <path>localization</path> directory of the language pack
    2. located within the <path>localization</path> directory of the IDE or plugin
    3. via suffix within the language pack
    4. via suffix within the IDE or plugin
3. Default file within:
    1. the language pack
    2. the IDE or plugin

<include from="snippets.md" element-id="missingContent"/>
