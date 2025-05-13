<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Inspection Options

<link-summary>Implementing inspection options allowing to alter inspection behavior depending on user configuration.</link-summary>

Some code inspections provide configuration options that affect their behavior.
For example, <ui-path>Java | Code style issues | 'size() == 0' can be replaced with isEmpty()</ui-path>, allows ignoring classes from the defined list or expressions, which would be replaced with `!isEmpty()`.

Currently, there are two ways of providing the inspection options:
* [Declarative](#declarative-inspection-options)
* [UI-based](#ui-based-inspection-options)

## Declarative Inspection Options
<primary-label ref="2023.1"/>

Declarative API allows to:
* delegate component rendering to the platform and make all the inspection options UI consistent and compliant with the [](ui_guidelines_welcome.topic)
* optimize checking whether the inspection contains any options
* manipulate options in places other than inspection panels (e.g., in quick fixes)
* render options in contexts other than IntelliJ Platform-based IDEs

Providing the inspection options is achieved by implementing
[`InspectionProfileEntry.getOptionsPane()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionProfileEntry.java),
which returns an
[`OptPane`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/OptPane.java)
object describing available configuration possibilities.
Note that `InspectionProfileEntry` is a parent of inspection base classes like
[`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
and
[`GlobalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/GlobalInspectionTool.java).

Building the inspection options is achieved by using a DSL-like facade, which contains methods for creating option controls and binding them to the fields declared in an inspection class.

Building the options for <ui-path>Java | Code style issues | 'size() == 0' can be replaced with 'isEmpty()'</ui-path> is implemented as follows:

```java
public OrderedSet<String> ignoredTypes = new OrderedSet<>();
public boolean ignoreNegations = false;

@Override
public @NotNull OptPane getOptionsPane() {
  return pane(
    stringList(
      "ignoredTypes",
      message("options.label.ignored.classes"),
      new JavaClassValidator()),
    checkbox(
      "ignoreNegations",
      message("size.replaceable.by.isempty.negation.ignore"))
  );
}
```

The above example builds a form with two options (see
[`SizeReplaceableByIsEmptyInspection`](%gh-ic%/java/java-impl/src/com/siyeh/ig/style/SizeReplaceableByIsEmptyInspection.java)
for the full implementation context):
* List of strings, which are validated for being Java classes. The provided list is bound to the `ignoredTypes` field in the inspection class.
* Checkbox, which value is bound to the boolean `ignoreNegations` field in the inspection class.

The `OptPane` class exposes methods for building fields of other types, e.g., number or dropdown fields.

Note that the bind identifiers passed as a first string argument of methods creating form controls contain injected references that resolve to the bound fields.
It enables resolving and other resolve-related features available, making it easy to rename fields and minimizing the risk of introducing typos resulting in bugs, as unresolved references will be highlighted as errors.

### Custom Options Binding Protocol

The default way of binding option form values to fields may be insufficient in more advanced cases.
It is possible to customize the way of binding options by providing a custom
[`OptionController`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/OptionController.java)
from `InspectionProfileEntry.getOptionController()` method.

Consider the <ui-path>Properties files | Inconsistent resource bundle</ui-path> global inspection, from bundled **Properties** plugin in IntelliJ IDEA, which reports several types of inconsistencies in <path>.properties</path> files.
The inspection allows enabling or disabling reporting specific issue types, which are reported by providers implementing a dedicated interface.
Information about enabled providers is stored in a map where the key is a provider ID.
The options panel and value binding are implemented in the following way (see
[`InconsistentResourceBundleInspection`](%gh-ic%/plugins/java-i18n/src/com/intellij/codeInspection/i18n/inconsistentResourceBundle/InconsistentResourceBundleInspection.java)
for the full implementation context):

```java
private NotNullLazyValue<InconsistentResourceBundleInspectionProvider[]> myProviders = ...;
private Map<String, Boolean> mySettings = new LinkedHashMap<>();

@Override
public @NotNull OptPane getOptionsPane() {
  return new OptPane(ContainerUtil.map(
    myProviders.getValue(),
    provider -> checkbox(provider.getName(), provider.getPresentableName())));
}

@Override
public @NotNull OptionController getOptionController() {
  return OptionController.of(
    (bindId) -> ContainerUtil.getOrElse(mySettings, bindId, true),
    (bindId, value) -> {
      boolean boolValue = (Boolean)value;
      if (boolValue) {
        mySettings.remove(bindId);
      } else {
        mySettings.put(bindId, false);
      }
    });
}
```

Option controls panel is built based on providers’ IDs and presentable names.
This implementation doesn't need to be changed regardless of removing or adding new providers in the future.

Reading and writing options in the map is achieved by registering a custom controller with getter and setter logic provided to the `OptionController.of()` method.

It's possible to compose several option controllers into the hierarchy based on the `bindId` prefix.
It may be useful when some inspections have common configuration options and store the configuration in dedicated objects.
See
[`OptComponent.prefix()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/options/OptComponent.java)
and `OptionController.onPrefix()` methods for more details and example implementation:
[`MissingJavadocInspection`](%gh-ic%/java/java-impl/src/com/intellij/codeInspection/javaDoc/MissingJavadocInspection.java).

### Non-Profile Inspection Options

Sometimes, inspections use options that are rendered in a non-standard way, or are shared with other inspections or other IDE features.
Such a shared configuration can be implemented as a [persistent component](persisting_state_of_components.md) and not have a single owner.
It is still convenient to be able to configure these options from the inspection panel.

An example of such a case is the <ui-path>Java | Probable bugs | Nullability problems | @NotNull/@Nullable problems</ui-path> inspection, which contains the <control>Configure Annotations…</control> button that opens the <control>Nullable/NotNull Configuration</control> dialog.

Custom Swing controls can be provided by implementing
[`CustomComponentExtensionWithSwingRenderer`](%gh-ic%/platform/lang-api/src/com/intellij/codeInspection/ui/CustomComponentExtensionWithSwingRenderer.java)
and registering the implementation in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.inspectionCustomComponent"/></include>.
Please note that this API is still in experimental state and may be changed without preserving backward compatibility.

**Example:**
[`JavaInspectionButtons`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/options/JavaInspectionButtons.java)
providing buttons for configuring options in custom dialogs

## UI-Based Inspection Options

> If you target versions 2023.1+ only, it is highly recommended to implement [](#declarative-inspection-options).

UI-based inspection options are provided by implementing a configuration panel using Swing components and returning it from [`InspectionProfileEntry.createOptionsPanel()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionProfileEntry.java) method.
It returns the panel with option components that bind the provided values to the inspection class fields or other properties, similarly as in the [declarative](#declarative-inspection-options) approach.
Note that since version 2023.1, this method is ignored if `InspectionProfileEntry.getOptionPane()` returns a non-empty panel.

**Example:**
[`SizeReplaceableByIsEmptyInspection`](%gh-ic-223-master%/plugins/InspectionGadgets/src/com/siyeh/ig/style/SizeReplaceableByIsEmptyInspection.java)
in version 2022.3, implemented using the UI-approach

For simple customization requirements, see also:
* [`SingleCheckboxOptionsPanel`](%gh-ic%/platform/lang-api/src/com/intellij/codeInspection/ui/SingleCheckboxOptionsPanel.java) for single checkbox
* [`MultipleCheckboxOptionsPanel`](%gh-ic%/platform/lang-api/src/com/intellij/codeInspection/ui/MultipleCheckboxOptionsPanel.java) for multiple checkboxes
* [`SingleIntegerFieldOptionsPanel`](%gh-ic%/platform/lang-api/src/com/intellij/codeInspection/ui/SingleIntegerFieldOptionsPanel.java) for single Integer (text field)

> Be careful when you have a hierarchy of inspection classes.
> For example, if inspection superclass is converted to the declarative approach, any `createOptionsPanel()` methods in subclasses will be ignored.
> If you can't convert all of them at once, you may temporarily add `getOptionsPane()` returning `OptPane.EMPTY` to subclasses, where `createOptionsPanel()` is still used.
>
{style="warning"}
