---
title: Registering a File Type
---

The first step in developing a custom language plugin is registering a file type associated with the language.
The IDE normally determines the type of a file by looking at its file name.

A custom language file type is a class derived from
[LanguageFileType](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java),
which passes a
[Language](upsource:///platform/core-api/src/com/intellij/lang/Language.java)
subclass to its base class constructor.
To register a file type, the plugin developer provides a subclass of
[FileTypeFactory](upsource:///platform/platform-api/src/com/intellij/openapi/fileTypes/FileTypeFactory.java), which is registered via the `com.intellij.fileTypeFactory`
[platform extension point](upsource:///platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml). 
> **NOTE** When targeting 2019.2 or later only, using `com.intellij.fileType` extension point is preferred to using dedicated `FileTypeFactory`.

**Example**:
[LanguageFileType](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java)
subclass in
[Properties language plugin](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesFileType.java)

To verify that the file type is registered correctly, you can implement the
[LanguageFileType.getIcon()](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java)
method and verify that the correct icon is displayed for files which have the extension(s) associated with your file type.

If you want IDEs to show a hint prompting users that your plugin supports some extensions, look at the information about [plugin recommendations](https://plugins.jetbrains.com/docs/marketplace/intellij-plugin-recommendations.html).
