<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Providing File and Code Templates

<link-summary>Creating file templates and assigning them to categories.</link-summary>

The IntelliJ Platform allows plugins to provide custom file templates specific to the plugin's functionalities.
In order to include custom templates in the plugin, a template file has to be created and placed in the specific place of plugin resources, depending on the template's purpose.

## Creating File Templates

There are multiple ways of creating file templates:
* [Use "File | Save File as Template…" action](https://www.jetbrains.com/help/idea/using-file-and-code-templates.html#save-file-as-template)
* [Create a new file template](https://www.jetbrains.com/help/idea/using-file-and-code-templates.html#create-new-template)
* [Copy an existing file template](https://www.jetbrains.com/help/idea/using-file-and-code-templates.html#copy-existing-template)

Once the file templates are created and saved, they should be copied to the plugin project's <path>resources</path> directory.
The created template can be found in the [IDE configuration directory](https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#config-directory) in the <path>fileTemplates</path> directory, or they can be exported via <ui-path>File | Manage IDE Settings | Export Settings</ui-path> by selecting the <control>File Templates (schemes)</control> checkbox.
The exported ZIP file will contain the <path>fileTemplates</path> directory with the created templates.
The <path>fileTemplates</path> directory should be moved to the plugin project's <path>resources</path> folder, and the <path>.ft</path> extension must be added to the template files, e.g., <path>My Class.java</path> must be renamed to <path>My Class.java.ft</path>.

The template name and extension displayed in the IDE settings will automatically be extracted from the file name.
The <control>Enable Live Templates</control> option will be enabled if the template's content includes a `#[[$` fragment.
Note that [live templates](live_templates.md) expressions should be surrounded with `#[[` and `]]#` tokens to avoid being parsed by the Apache Velocity template engine.

> Please note that adding templates with child/multiple files in custom plugins is currently not supported.
>
{style="note"}

### File Templates Categories

File templates can be assigned to one of the predefined categories depending on their purpose and type.

#### Files

The <control>Files</control> category contains templates used for creating new files.
This is the main category, and it includes all templates placed directly in the <path>fileTemplates</path> directory.
It should contain only templates for given language core entities, e.g., a class, an interface, or other items that users very frequently create.
If the language defines a lot of core entities, see [](using_file_templates.md#custom-create-file-from-template-actions) for a more user-friendly solution.

#### Includes

The <control>Includes</control> category contains reusable fragments used for including in other file templates using the Apache Velocity `#parse()` directive, e.g., license header or documentation comment skeleton.
It includes templates located in the <path>fileTemplates/includes</path> directory.

#### Code

The <control>Code</control> category contains templates used for inserting in existing files, e.g., a code fragment used in an [intention action](code_intentions.md) or [quick fix](code_inspections.md#quick-fix-implementation).
It includes templates located in the <path>fileTemplates/code</path> directory.

#### Internal

The <control>Internal</control> category contains templates that are not visible in the IDE settings by default and cannot be edited by users.
It includes templates located in the <path>fileTemplates/internal</path> directory.

#### Other

The <control>Other</control> category contains other templates organized in groups.
It includes templates located in the <path>fileTemplates/j2ee</path> directory and registered via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileTemplateGroup"/></include>.
Note that the <path>j2ee</path> directory name is historical and unrelated to the J2EE technology.
This category is intended for templates that are not used for creating core language entities or are used less frequently by users, e.g., a specific XML configuration file, a framework-specific class in Java language, etc.
To include file templates in the <control>Other</control> section of the <ui-path>Settings | Editor | File and Code Templates</ui-path> settings page, provide an implementation of the
[`FileTemplateGroupDescriptorFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/FileTemplateGroupDescriptorFactory.java)
and register it via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileTemplateGroup"/></include>.

**Example:**
[`MavenFileTemplateGroupFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/utils/MavenFileTemplateGroupFactory.java)

Note that
[`FileTemplateGroupDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/FileTemplateGroupDescriptor.java)
is a subclass of
[`FileTemplateDescriptor`](%gh-ic%/platform/core-api/src/com/intellij/ide/fileTemplates/FileTemplateDescriptor.java),
which allows creating nested groups.

## Creating File Template Description

By default, the description contains generic text about the syntax and properties of the given template.
It is highly recommended to provide a custom description explaining its purpose and any available properties.
Overriding the default description is achieved by creating an HTML file with the name matching template's name but with the <path>.html</path> extension.
Example:
* Template file name: <path>My Class.java.ft</path>
* Description file name: <path>My Class.java.html</path>

The description file must be located in the same directory as the template file.
It is recommended to follow the convention from the
[`default.html`](%gh-ic%/platform/platform-resources-en/src/fileTemplates/default.html)
file.

> If a plugin project is multi-module, and it combines resources into a single JAR, make sure that all template description files have unique names or paths.
> Otherwise, only the last packed description file will exist in the distribution package.
>
{style="warning"}

> See the [](providing_translations.md#bundled-translations) section for information about how to provide file template description translations in plugins.

## Providing Default File Template Properties

A file template body can use a set of [predefined properties](https://www.jetbrains.com/help/idea/file-template-variables.html#predefined_template_variables) exposed by the IntelliJ Platform out of the box.
Some languages or frameworks may require additional properties exposed to existing templates.
To provide custom properties, implement
[`DefaultTemplatePropertiesProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/fileTemplates/DefaultTemplatePropertiesProvider.java)
and register it via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.defaultTemplatePropertiesProvider"/></include>.

**Example:** Java Plugin's
[`TemplatePackagePropertyProvider`](%gh-ic%/java/java-impl/src/com/intellij/ide/fileTemplates/TemplatePackagePropertyProvider.java)
providing `PACKAGE_NAME` property based on the directory a file is created in.
