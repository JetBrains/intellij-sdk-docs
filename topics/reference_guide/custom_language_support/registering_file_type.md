[//]: # (title: Registering a File Type)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The first step in developing a custom language plugin is registering a file type associated with the language.

The IDE typically determines the type of a file by looking at its filename or extension.

A custom language file type is a class derived from [`LanguageFileType`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java), which passes a [`Language`](upsource:///platform/core-api/src/com/intellij/lang/Language.java) subclass to its base class constructor.

### Registration
<tabs>

<tab title="2019.2 and later">

When targeting 2019.2 or later *only*, use `com.intellij.fileType` extension point to register `LanguageFileType` implementation and instance via `implementationClass` and `fieldName` attributes.
Also, `name` and `language` must be declared matching `FileType.getName()` and ID of language returned from `LanguageFileType.getLanguage()`, respectively.

To associate the file type in the IDE, specify one or more associations as listed in the following table.

| Association type        | Attribute                                   | Attribute value                                                 |
|-------------------------|---------------------------------------------|-----------------------------------------------------------------|
| Filename extension(s)   | `extensions`                                | Semicolon-separated list of extensions, without `.` prefix      |
| Hard coded file name(s) | `fileNames`/<br/>`fileNamesCaseInsensitive` | Semicolon-separated list of exact (case-insensitive) file names |
| Filename pattern(s)     | `patterns`                                  | Semicolon-separated list of patterns (`*` and `?`)              |
| Hashbang _(2020.2+)_    | `hashBangs`                                 | Semicolon-separated list of hash bang patterns                  |

</tab>

<tab title="Pre-2019.2">

To register a file type, the plugin developer provides a subclass of [`FileTypeFactory`](upsource:///platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeFactory.java), which is registered via the `com.intellij.fileTypeFactory` extension point.

</tab>
</tabs>

**Examples**
- [Custom Language Support Tutorial: Language and File Type](language_and_filetype.md)
- [`LanguageFileType`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java) subclass in [Properties language plugin](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesFileType.java)

To verify that the file type is registered correctly, you can implement the [`LanguageFileType.getIcon()`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java) method and verify that the correct icon (see [Working with Icons and Images](work_with_icons_and_images.md)) is displayed for files associated with your file type.

### Additional Features

If you want IDEs to show a hint prompting users that your plugin supports a specific file type, see [Plugin Recommendations](https://plugins.jetbrains.com/docs/marketplace/intellij-plugin-recommendations.html).

To control file type association with the IDE in the operating system, implement [`OSFileIdeAssociation`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/OSFileIdeAssociation.java) (2020.3).
