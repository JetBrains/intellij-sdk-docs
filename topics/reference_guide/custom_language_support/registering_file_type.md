[//]: # (title: Registering a File Type)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The first step in developing a custom language plugin is registering a file type associated with the language.

The IDE typically determines the type of a file by looking at its filename or extension.
In 2020.2, support for mapping via _hashbang_ is available via `hashBangs` attribute in `com.intellij.fileType` extension point.

A custom language file type is a class derived from [`LanguageFileType`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java), which passes a [`Language`](upsource:///platform/core-api/src/com/intellij/lang/Language.java) subclass to its base class constructor.
                                             
<tabs>

<tab title="2019.2 and later">

When targeting 2019.2 or later only, use `com.intellij.fileType` extension point to associate `LanguageFileType` with corresponding filename extensions and patterns.

</tab>

<tab title="Pre-2019.2">

To register a file type, the plugin developer provides a subclass of [`FileTypeFactory`](upsource:///platform/platform-api/src/com/intellij/openapi/fileTypes/FileTypeFactory.java), which is registered via the `com.intellij.fileTypeFactory` extension point.

</tab>
</tabs>

**Examples**:
- [`LanguageFileType`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java) subclass in [Properties language plugin](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesFileType.java)
- [Custom Language Support Tutorial: Language and File Type](language_and_filetype.md)

To verify that the file type is registered correctly, you can implement the [`LanguageFileType.getIcon()`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java) method and verify that the correct icon (see [Working with Icons and Images](work_with_icons_and_images.md)) is displayed for files associated with your file type.
                                         
If you want IDEs to show a hint prompting users that your plugin supports a specific file type, see [Plugin Recommendations](https://plugins.jetbrains.com/docs/marketplace/intellij-plugin-recommendations.html).

To control file type association with the IDE in the operating system, implement [`OSFileIdeAssociation`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/OSFileIdeAssociation.java) (2020.3).