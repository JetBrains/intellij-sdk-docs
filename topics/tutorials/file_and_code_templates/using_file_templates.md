<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Using File Templates Programmatically

<link-summary>Using file templates programmatically from actions and other IDE features.</link-summary>

File templates provided by a plugin can be used during new file creation, in code intention actions, or other plugin features.
They can be accessed with the
[`FileTemplateManager`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/fileTemplates/FileTemplateManager.java)
service providing methods returning all or single file templates from a given category.
For example, to obtain a template from the <control>[Code](providing_file_templates.md#code)</control> category, pass its name to the `getCodeTemplate()` method (notice the lack of the <path>.ft</path> extension):

```java
FileTemplate template = FileTemplateManager.getInstance(project)
    .getCodeTemplate("Test Class.java");
```

To render a template content, prepare and pass `Properties` object to the `getText()` method:

```java
Properties properties = new Properties();
properties.setProperty("PROP1", value1);
properties.setProperty("PROP2", value2);
String renderedText = template.getText(properties);
```

## Creating New Files from Template

The common use case for file templates is creating new files with the initial content specific to a language or framework supported by the plugin.
File templates assigned to the <control>[Files](providing_file_templates.md#files)</control> category are automatically available in the <ui-path>File | New</ui-path> action group.
Sometimes, creating a file from a given template in a specific project place doesn't make sense, or a template requires some additional properties for its content.
It is possible to control a file template's visibility and its available properties using
[`CreateFromTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/fileTemplates/CreateFromTemplateHandler.java)
implementation registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.createFromTemplateHandler"/></include>.

**Example:**
[`JavaCreateFromTemplateHandler`](%gh-ic%/java/java-impl/src/com/intellij/ide/fileTemplates/JavaCreateFromTemplateHandler.java)

### Exposing File Templates from the Other Category

File templates from the <control>[Other](providing_file_templates.md#other)</control> category are not exposed by default.
To make them available in the UI, a plugin has to implement and register an action.
The easiest way to do it is by extending
[`CreateFileFromTemplateAction`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/CreateFileFromTemplateAction.java)
base class which contains standard implementations of necessary methods.
It allows customizing the action by providing an action name, icon, overriding methods creating dialog, and others.
Example action:

```java
public class CreateMyClassAction extends CreateFileFromTemplateAction {

  @Override
  protected void buildDialog(Project project, PsiDirectory directory,
      CreateFileFromTemplateDialog.Builder builder) {
    builder
    .setTitle("New My File")
    .addKind("Class", MyIcons.CLASS_ICON, "My Class");
  }

  @Override
  protected String getActionName(PsiDirectory directory,
      @NotNull String newName, String templateName) {
    return "Create My Class: " + newName;
  }
}
```

The new action should be registered under the `NewGroup` group, e.g:

```xml
<actions>
  <action id="Create.MyClass" class="com.example.CreateMyClassAction" icon="MyIcons.CLASS_ICON">
    <add-to-group group-id="NewGroup"/>
  </action>
</actions>
```

Action presentation texts should be added to the [resource bundle defined in plugin.xml](plugin_configuration_file.md#idea-plugin__resource-bundle) according to the rules described in [](action_system.md#localizing-actions-and-groups):

```
action.Create.MyClass.text=My Class
action.Create.MyClass.description=Creates new class
```

### Custom "Create File From Template" Actions

In some cases, the default mechanism for creating files from templates is insufficient.
Consider a language that defines multiple types of core entities, e.g., in the Java language, the following entities can be created: Class, Interface, Record, Enum, and Annotation.

Having all of those items in the <ui-path>File | New</ui-path> action group may overwhelm users with the number of options to choose.
It is more user-friendly to provide a single <ui-path>File | New | Java Class</ui-path> action and let users choose a specific entity type in the creation dialog:

![Create Java class](new_java_class_popup.png){width="296" border-effect="line"}

This can be achieved by placing templates in the <control>[Internal](providing_file_templates.md#internal)</control> category, so they are not picked up by the default mechanism, and then registering a custom
[`CreateFileFromTemplateAction`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/CreateFileFromTemplateAction.java)
implementation as described in the [](#exposing-file-templates-from-the-other-category) section.
To provide a list of selectable entity types, the action should provide a custom dialog with multiple file kinds for every template type, e.g.:

```java
@Override
protected void buildDialog(Project project, PsiDirectory directory,
    CreateFileFromTemplateDialog.Builder builder) {
  builder
    .setTitle("My File")
    .addKind("Class", MyIcons.CLASS_ICON, "My Class")
    .addKind("Record", MyIcons.RECORD_ICON, "My Record")
    .addKind("Enum", MyIcons.ENUM_ICON, "My Enum");
}
```

As file templates are placed in the <path>fileTemplates/internal</path> directory, they are not listed in the <ui-path>Settings | Editor | File and Code Templates</ui-path> settings page, and users can't adjust them to their needs.
Internal templates can be exposed in the <control>Files</control> category by additionally registering them via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.internalFileTemplate"/></include>, e.g.:

```xml
<internalFileTemplate name="My Record"/>
```

**Example:**
[`NewKotlinFileAction`](%gh-ic%/plugins/kotlin/kotlin.ide/src/org/jetbrains/kotlin/idea/actions/NewKotlinFileAction.kt) for Kotlin files creation action.

## Improving "Save File as Template…" Action

Some languages or frameworks may require creating many custom file templates directly in the IDE with <ui-path>File | Save File as Template…</ui-path> action, including plugin developers creating templates, e.g., for Java language to support a specific framework.
Adjusting created templates manually by replacing dynamic parts with properties can be tedious.
It is possible to speed up this process by replacing known elements like package or class names with template properties placeholder.
It can be achieved by implementing the
[`SaveFileAsTemplateHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/SaveFileAsTemplateHandler.java)
and registering it via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.saveFileAsTemplateHandler"/></include>.

**Example:**
[`SaveJavaAsTemplateHandler`](%gh-ic%/java/java-impl/src/com/intellij/ide/fileTemplates/SaveJavaAsTemplateHandler.java) replacing existing class and package names with `${NAME}` and `${PACKAGE_NAME}` properties placeholders respectively.
