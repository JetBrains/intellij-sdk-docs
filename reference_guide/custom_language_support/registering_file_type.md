---
title: Registering a File Type
---

The first step in developing a custom language plugin is registering a file type the language will be associated with.
The IDE normally determines the type of a file by looking at its file name.

A custom language file type is a class derived from
[LanguageFileType](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java),
which passes a
[Language](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Language.java)
implementation class to its base class constructor.
To register a file type, the plugin developer provides an implementation of the
[FileTypeFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/fileTypes/FileTypeFactory.java)
interface, which is registered via the ```com.intellij.fileTypeFactory```
[platform extension point](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml).

**Example**:
[LanguageFileType](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java)
implementation in
[Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesFileType.java)

To verify that the file type is registered correctly, you can implement the
[LanguageFileType.getIcon()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java)
method and verify that the correct icon is displayed for files which have the extension(s) associated with your file type.
