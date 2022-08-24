[//]: # (title: Plugin Configuration File)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

The <path>plugin.xml</path> configuration file contains all the information about the plugin and all registered extensions, actions, listeners, etc.
Sections below describe all the elements in detail.

## Additional Plugin Configuration Files

A plugin can contain additional configuration files beside the main <path>plugin.xml</path>.
They have the same format, and they are included with the `config-file` attribute of [`<depends>`](#idea-plugin__depends) elements.
However, some elements and attributes required in <path>plugin.xml</path> are ignored in additional configuration files.
If the requirements differ, the documentation below will state it explicitly.
One use case for additional configuration files is when a plugin provides optional features that are only available in some IDEs and require [certain modules](plugin_compatibility.md#modules-specific-to-functionality).

## Useful Resources

Please make sure to follow the guidelines from [Plugin Overview page](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html) for an optimal presentation of your plugin on JetBrains Marketplace.
The _Busy Plugin Developers.
Episode 2_ discusses [5 tips for optimizing JetBrains Marketplace plugin page](https://youtu.be/oB1GA9JeeiY?t=52) in more detail.

See also [](marketing.md) about widgets and badges.

## Configuration Structure Overview

- [`<idea-plugin>`](#idea-plugin)
  - [`<id>`](#idea-plugin__id)
  - [`<name>`](#idea-plugin__name)
  - [`<version>`](#idea-plugin__version)
  - [`<product-descriptor>`](#idea-plugin__product-descriptor)
  - [`<idea-version>`](#idea-plugin__idea-version)
  - [`<vendor>`](#idea-plugin__vendor)
  - [`<description>`](#idea-plugin__description)
  - [`<change-notes>`](#idea-plugin__change-notes)
  - [`<depends>`](#idea-plugin__depends)
  - [`<actions>`](#idea-plugin__actions)
  - [`<extensionPoints>`](#idea-plugin__extensionPoints)
    - [`<extensionPoint>`](#idea-plugin__extensionPoints__extensionPoint)
      - [`<with>`](#idea-plugin__extensionPoints__extensionPoint__with)
  - [`<extensions>`](#idea-plugin__extensions)
  - [`<applicationListeners>`](#idea-plugin__applicationListeners)
    - [`<listener>`](#idea-plugin__listeners__listener)
  - [`<projectListeners>`](#idea-plugin__projectListeners)
    - [`<listener>`](#idea-plugin__listeners__listener)
  - [`<resource-bundle>`](#idea-plugin__resource-bundle)

Deprecated elements are omitted in the list above.

> If an element or an attribute is not documented on this page, please consider them as configuration items intended to be used only by JetBrains teams internally. They should never be used by 3rd-party plugins.
>
{type="note"}

## `idea-plugin`
{id="idea-plugin"}

The <path>plugin.xml</path> file root element.

{style="narrow"}
Required
: **yes**

Attributes
:
- `url` _(optional; ignored in [additional configuration](#additional-plugin-configuration-files))_<br/>
  The link to the plugin homepage displayed on the plugin page in the [JetBrains Marketplace](https://plugins.jetbrains.com).

Children
:
  - [`<actions>`](#idea-plugin__actions)
  - [`<applicationListeners>`](#idea-plugin__applicationListeners)
  - [`<change-notes>`](#idea-plugin__change-notes)
  - [`<depends>`](#idea-plugin__depends)
  - [`<description>`](#idea-plugin__description)
  - [`<extensions>`](#idea-plugin__extensions)
  - [`<extensionPoints>`](#idea-plugin__extensionPoints)
  - [`<id>`](#idea-plugin__id)
  - [`<idea-version>`](#idea-plugin__idea-version)
  - [`<name>`](#idea-plugin__name)
  - [`<product-descriptor>`](#idea-plugin__product-descriptor)
  - [`<projectListeners>`](#idea-plugin__projectListeners)
  - [`<resource-bundle>`](#idea-plugin__resource-bundle)
  - [`<vendor>`](#idea-plugin__vendor)
  - [`<version>`](#idea-plugin__version)<br/><br/>

  Deprecated:
  - [`<application-components>`](#idea-plugin__application-components)
  - [`<module-components>`](#idea-plugin__module-components)
  - [`<project-components>`](#idea-plugin__project-components)

### `id`
{id="idea-plugin__id"}

A unique identifier of the plugin.
It should be a fully qualified name similar to Java packages and must not collide with the ID of existing plugins.

**The identifier value cannot be changed between the plugin versions.**

{style="narrow"}
Required
: no; ignored in [additional config file](#additional-plugin-configuration-files)<br/>
**It is highly recommended to set in <path>plugin.xml</path> file.**<br/>

Default value
: Value of the [`<name>`](#idea-plugin__name) element.<br/>

Example
:
```xml
<id>com.example.myframeworksupport</id>
```

### `name`
{id="idea-plugin__name"}

The public plugin name using Title Cases.

See the [naming guidelines](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#plugin-name) in the JetBrains Marketplace documentation.

{style="narrow"}
Required
: **yes**; ignored in [additional config file](#additional-plugin-configuration-files)

Example
:
```xml
<name>My Framework Support</name>
```

### `version`
{id="idea-plugin__version"}

The plugin version displayed in the <control>Plugins</control> settings dialog and in the JetBrains Marketplace plugin page.
Plugins uploaded to the JetBrains Marketplace must follow [semantic versioning](https://plugins.jetbrains.com/docs/marketplace/semver.html).

{style="narrow"}
Required
: **yes**; ignored in [additional config file](#additional-plugin-configuration-files)

Example
:
```xml
<version>1.3.18</version>
```

### `product-descriptor`
{id="idea-plugin__product-descriptor"}

[Paid](https://plugins.jetbrains.com/build-and-market) or [Freemium](https://plugins.jetbrains.com/docs/marketplace/freemium.html) plugin descriptor.
See the [required parameters](https://plugins.jetbrains.com/docs/marketplace/add-required-parameters.html) for more details.<br/>

{style="narrow"}
Required
: only for paid or freemium plugins; ignored in [additional config file](#additional-plugin-configuration-files)<br/>
**Do not add `<product-descriptor>` element in a free plugin.**

### `idea-version`
{id="idea-plugin__idea-version"}

The plugin's range of compatible IntelliJ-based IDE versions.

See how to correctly specify [version ranges](build_number_ranges.md).

{style="narrow"}
Required
: **yes**; ignored in [additional config file](#additional-plugin-configuration-files)<br/>
The element can be skipped in the source <path>plugin.xml</path> file if the Gradle [patchPluginXml](tools_gradle_intellij_plugin.md#patchpluginxml-task) task is enabled.

Attributes
:
- `since-build` _(required)_<br/>
  The lowest IDE version compatible with the plugin.<br/>
  Examples: `213`, `213.56`, `IU-213.94.11`
- `until-build` _(optional)_<br/>
  The highest IDE version compatible with the plugin.
  Undefined value declares compatibility with all the IDEs since the version specified by the `since-build` (also with the future builds what may cause incompatibility errors).<br/>
  Examples: `221.*`, `221.56.*`, `IU-221.*`

Examples
:
- Compatibility with a specific build number (2021.3.3) and higher versions:
    ```xml
    <idea-version since-build="213.7172.25"/>
    ```
- Compatibility with versions from any of `213` branches to any of `221` branches:
    ```xml
    <idea-version
        since-build="213" until-build="221.*"/>
    ```

### `vendor`
{id="idea-plugin__vendor"}

The vendor name or organization ID (if created) in the <control>Plugins</control> settings dialog and in the JetBrains Marketplace plugin page.

{style="narrow"}
Required
: **yes**; ignored in [additional config file](#additional-plugin-configuration-files)

Attributes
:
- `url` _(optional)_<br/>
  The link to the vendor's homepage.
- `email` _(optional)_<br/>
  The vendor's email address.

Examples
:
- Personal vendor with an email address provided:
    ```xml
    <vendor email="joe@example.com">Joe Doe</vendor>
    ```
- Organizational vendor with a website URL and email address provided:
    ```xml
    <vendor
        url="https://mycompany.example.com"
        email="contact@example.com">
      My Company
    </vendor>
    ```

See also: [Contacts and resources
](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#contacts-and-resources) in the JetBrains Marketplace documentation.

### `description`
{id="idea-plugin__description"}

The plugin description displayed on the JetBrains Marketplace plugin page and in the IDE Plugin Manager.

Simple HTML elements, like text formatting, paragraphs, lists, etc., are allowed and must be wrapped into `<![CDATA[` ... `]]>` section.

See the [description guidelines](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#plugin-description) in the JetBrains Marketplace documentation.

{style="narrow"}
Required
: **yes**; ignored in [additional config file](#additional-plugin-configuration-files)<br/>
TODO: can be skipped if Gradle handles it

Example
:
  ```xml
  <description><![CDATA[
  Provides support for My Framework.
  The support includes:
  <ul>
  <li>code completion</li>
  <li>references</li>
  </ul>
  For more information visit the
  <a href="https://example.com">project site</a>.
  ]]></description>
  ```

### `change-notes`
{id="idea-plugin__change-notes"}

A short summary of new features, bugfixes, and changes provided with the latest plugin version.
Change notes are displayed on the Marketplace plugin page and in the IDE Plugin Manager.

Simple HTML elements, like text formatting, paragraphs, lists, etc., are allowed and must be wrapped into `<![CDATA[` ... `]]>` section.

See also: [Change notes](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#change-notes) in the JetBrains Marketplace documentation.

TODO: how gradle plugin helps

{style="narrow"}
Required
: no; ignored in [additional config file](#additional-plugin-configuration-files)

Example
:
  ```xml
  <change-notes><![CDATA[
  <h2>New Features</h2>
  <ul>
  <li>Feature 1</li>
  <li>Feature 2</li>
  </ul>
  <h2>Bug Fixes</h2>
  <ul>
  <li>Fixed issue 1</li>
  <li>Fixed issue 2</li>
  </ul>
  ]]></change-notes>
  ```


### `depends`
{id="idea-plugin__depends"}

Specifies a [dependency](plugin_dependencies.md) on another plugin or a module of
an [IntelliJ Platform-based product](plugin_compatibility.md#modules-specific-to-functionality).
A single [`<idea-plugin>`](#idea-plugin) element can contain multiple `<depends>` elements.

{style="narrow"}
Required
: no

Attributes
:
- `optional` _(optional)_<br/>
  Boolean value defining whether the dependency is optional to load the plugin in the IDE.
  Default value: `false`.<br/>
  If the dependency plugin is not installed in the current IDE, and:
    - `optional="true"` - the plugin will be loaded.
    - `optional="false"` - the plugin will not be loaded.
- `config-file` _(optional)_<br/>
  Relative path to an [additional configuration file](#additional-plugin-configuration-files), loaded only if the dependency plugin is installed in the current IDE.

Examples
:
- Required plugin dependency:
    ```xml
    <depends>com.example.dependency-plugin</depends>
    ```
- Required dependency on the IntelliJ Java module:
    ```xml
    <depends>com.intellij.modules.java</depends>
    ```
- Optional plugin dependency:
    ```xml
    <depends optional="true">
      com.example.dependency-plugin
    </depends>
    ```
- Required plugin dependency with additional configuration:
    ```xml
    <depends config-file="depconfig.xml">
      com.example.dependency-plugin
    </depends>
    ```
- Optional plugin dependency with additional configuration:<br/>
    ```xml
    <depends
        optional="true"
        config-file="subdir/depconfig.xml">
      com.example.dependency-plugin
    </depends>
    ```

### `resource-bundle`
{id="idea-plugin__resource-bundle"}

A resource bundle to be used with message key attributes in extension declarations and for [action and group localization](basic_action_system.md#localizing-actions-and-groups).
A single [`<idea-plugin>`](#idea-plugin) element can contain multiple `<resource-bundle>` elements.

{style="narrow"}
Required
: no

Example
: To load the content of <path>messages/Bundle.properties</path> bundle, declare:
```xml
<resource-bundle>messages.Bundle</resource-bundle>
```

### `applicationListeners`
{id="idea-plugin__applicationListeners"}

Defines the [application-level listeners](plugin_listeners.md#defining-application-level-listeners).

{style="narrow"}
Required
: no

Children
: [`<listener>`](#idea-plugin__listeners__listener)

### `projectListeners`
{id="idea-plugin__projectListeners"}

Defines the [project-level listeners](plugin_listeners.md#defining-project-level-listeners).

{style="narrow"}
Required
: no

Children
: [`<listener>`](#idea-plugin__listeners__listener)

#### `listener`
{id="idea-plugin__listeners__listener"}

Defines a single application or project-level listener.
A single [`<applicationListeners>`](#idea-plugin__applicationListeners) or [`<projectListeners>`](#idea-plugin__projectListeners) can contain multiple `<listener>` elements.

{style="narrow"}
Required
: no

Attributes
:
- `topic` _(required)_<br/>
  The fully qualified name of the listener interface corresponding to the type of received events.
- `class` _(required)_<br/>
  The fully qualified name of the class implementing the listener interface that receives and handles the events.
- `os` _(optional; supported since 2020.1)_<br/>
  Restricts listener instantiation to a specific operating system.
  Allowed values:
    - `freebsd`
    - `mac`
    - `linux`
    - `unix`
    - `windows`
- `activeInTestMode` _(optional)_<br/>
  Boolean flag defining whether the listener should be instantiated in test mode.<br/>
  Default value: `true`.
- `activeInHeadlessMode` _(optional)_<br/>
  Boolean flag defining whether the listener should be instantiated in headless mode.<br/>
  Default value: `true`.

Example
:
  ```xml
  <listener
      topic="com.intellij.ide.AppLifecycleListener"
      class="com.example.MyListener"
      os="mac"
      activeInTestMode="false"/>
  ```

### `actions`
{id="idea-plugin__actions"}

TODO

Additional information about configuring `<actions>` is available in the [Actions](basic_action_system.md#registering-actions) section.

{style="narrow"}
Required
: no

Children
:
- TODO
- TODO
- TODO

### `extensions`
{id="idea-plugin__extensions"}

[Plugin extensions](plugin_extensions.md).

{style="narrow"}
Required
: no

Attributes
: - `defaultExtensionNs` _(optional)_<br/>
  Default extensions namespace.
  It allows skipping the common prefix in fully qualified extension point names.<br/>
  Example: if `defaultExtensionNs` is `com.example.vcs` and extension point FQN is `com.example.vcs.myExtension`, it can be defined as simple `<myExtension>` element instead of `<com.example.vcs.myExtension>`.

Children
: The children elements are registrations of the extension points defined by [`<extensionPoint>`](#idea-plugin__extensionPoints__extensionPoint) elements. Extension elements names follow the EPs names defined by `name` or `qualifiedName` attributes.

Example
:
  ```xml
  <extensions defaultExtensionNs="com.example.vcs">
    <myExtension
      implementation="com.example.impl.MyExtension"/>
  </extensions>
  ```

### `extensionPoints`
{id="idea-plugin__extensionPoints"}

[Extension points](plugin_extension_points.md) defined by the plugin.

{style="narrow"}
Required
: no

Children
: [`<extensionPoint>`](#idea-plugin__extensionPoints__extensionPoint)

#### `extensionPoint`
{id="idea-plugin__extensionPoints__extensionPoint"}

A single extension point entry of the [`<extensionPoints>`](#idea-plugin__extensionPoints) defined by the plugin.
A single [`<extensionPoints>`](#idea-plugin__extensionPoints) element can contain multiple `<extensionPoint>` elements.

{style="narrow"}
Required
: no

Attributes
:
- `name` _(`name` or `qualifiedName` is required)_<br/>
  The extension point name that should be unique in the scope of the plugin, e.g., `myExtension`.
  The fully qualified name of the extension point is built at runtime by prepending the value of the `name` attribute with the plugin [`<id>`](#idea-plugin__id) + `.` prefix.
  Only one of the `name` and `qualifiedName` attributes can be specified.<br/>
  Example: when the `name` is  `myExtension` and plugin ID is `com.example.myplugin`, the fully qualified name of the EP will be `com.example.myplugin.myExtension`.
- `qualifiedName` _(`name` or `qualifiedName` is required)_<br/>
  The fully qualified name of the extension point.
  It should be unique between different plugins, and it is recommended to include a plugin ID to guarantee uniqueness, e.g., `com.example.myplugin.myExtension`.
  Only one of the `name` and `qualifiedName` attributes can be specified.
- `interface` _(`interface` or `beanClass` is required)_<br/>
  The fully qualified name of the interface to be implemented for extending plugin's functionality.
  Only one of the `interface` and `beanClass` attributes can be specified.
  See [](plugin_extension_points.md) for more information.
- `beanClass` _(`interface` or `beanClass` is required)_<br/>
  The fully qualified name of the extension point bean class providing additional information to the plugin.
  Only one of the `interface` and `beanClass` attributes can be specified.
  See [](plugin_extension_points.md) for more information.
- `dynamic` _(optional)_<br/>
  Boolean value defining whether the extension point meets the requirements to be [dynamic](plugin_extension_points.md#dynamic-extension-points), which is a prerequisite for [dynamic plugins](dynamic_plugins.md).
  Default value: `false`.
- `area` _(optional)_<br/>
  The scope in which the [extension](plugin_extensions.md) is instantiated.
  Allowed values:
    - `IDEA_APPLICATION` _(default)_
    - `IDEA_PROJECT`
    - `IDEA_MODULE`

    It is not recommended to use non-default values.

Children
: [`<with>`](#idea-plugin__extensionPoints__extensionPoint__with)

##### `with`
{id="idea-plugin__extensionPoints__extensionPoint__with"}

Specifies the required parent type for class names provided in extension point tags or attributes.
A single [`<extensionPoint>`](#idea-plugin__extensionPoints__extensionPoint) element can contain multiple `<with>` elements.

[//]: # (TODO: additional table entry proposal)
[//]: # (Path)
[//]: # (:)
[//]: # ([`idea-plugin`]&#40;#idea-plugin&#41; / [`extensionPoints`]&#40;#idea-plugin__extensionPoints&#41; / [`extensionPoint`]&#40;#idea-plugin__extensionPoints__extensionPoint&#41; / `with`)

{style="narrow"}
Required
: no

Attributes
:
- `tag` _(`tag` or `attribute` is required)_<br/>
  The name of the tag holding the fully qualified name of the class which parent type will be limited by the type provided in the `implements` attribute.
  Only one of the `tag` and `attribute` attributes can be specified.
- `attribute` _(`tag` or `attribute` is required)_<br/>
  The name of the attribute holding the fully qualified name of the class which parent type will be limited by the type provided in the `implements` attribute.
  Only one of the `tag` and `attribute` attributes can be specified.
- `implements` _(required)_<br/>
  The fully qualified name of the parent type limiting the type provided in the place specified by `tag` or `attribute`.

Example
:
An extension point which restricts the type provided in a `myClass` attribute to be an instance of `com.example.ParentType`, and the type provided in a `someClass` element to be an instance of `java.lang.Comparable`:
  ```xml
  <extensionPoint
      name="myExtension"
      beanClass="com.example.MyExtension">
    <with
        attribute="myClass"
        implements="com.example.ParentType"/>
    <with
        tag="someClass"
        implements="java.lang.Comparable"/>
  </extensionPoint>
  ```
  When using the above extension point, an implementation could be registered as follows:
  ```xml
  <myExtension ...
      myClass="com.example.MyCustomType">
    <someClass>com.example.MyComparable</someClass>
  </myExtension>
  ```
  where:
  - `com.example.MyCustomType` must be a subtype of `com.example.ParentType`
  - `com.example.MyComparable` must be a subtype of `java.lang.Comparable`

### `application-components`
{id="idea-plugin__application-components"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

Defines a list of application [components](plugin_components.md).

{style="narrow"}
Required
: no

Children
: [`<component>`](#idea-plugin__components__component)

### `project-components`
{id="idea-plugin__project-components"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

Defines a list of project [components](plugin_components.md).

{style="narrow"}
Required
: no

Children
: [`<component>`](#idea-plugin__components__component)

### `module-components`
{id="idea-plugin__module-components"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

Defines a list of module [components](plugin_components.md).

{style="narrow"}
Required
: no

Children
: [`<component>`](#idea-plugin__components__component)

#### `component`
{id="idea-plugin__components__component"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

Defines a single application, project, or module [component](plugin_components.md).
A single [`<application-components>`](#idea-plugin__application-components), [`<project-components>`](#idea-plugin__project-components), or [`<module-components>`](#idea-plugin__module-components) element can contain multiple `<component>` elements.

{style="narrow"}
Required
: no

Children
:
- [`<headless-implementation-class>`](#idea-plugin__components__component__headless-implementation-class)
- [`<implementation-class>`](#idea-plugin__components__component__implementation-class)
- [`<interface-class>`](#idea-plugin__components__component__interface-class)
- [`<loadForDefaultProject>`](#idea-plugin__components__component__loadForDefaultProject)
- [`<option>`](#idea-plugin__components__component__option)

##### `implementation-class`
{id="idea-plugin__components__component__implementation-class"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

The fully qualified name of the component implementation class.

{style="narrow"}
Required
: **yes**

##### `interface-class`
{id="idea-plugin__components__component__interface-class"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

The fully qualified name of the component interface class. If not specified, the interface will be the same as defined by [`<implementation-class>`](#idea-plugin__components__component__interface-class) element.

{style="narrow"}
Required
: no

##### `headless-implementation-class`
{id="idea-plugin__components__component__headless-implementation-class"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

The fully qualified name of the component implementation class to be used when the IDE runs in headless mode.

{style="narrow"}
Required
: no

##### `option`
{id="idea-plugin__components__component__option"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

Allows to provide additional component options.
A single [`<component>`](#idea-plugin__components__component) element can contain multiple `<option>` elements.

{style="narrow"}
Required
: no

Attributes
:
- `name` _(required)_<br/>
  Option name.
- `value` _(required)_<br/>
  Option value.

##### `loadForDefaultProject`
{id="idea-plugin__components__component__loadForDefaultProject"}

> Element is deprecated. Do not use it in new plugins.
>
{type="warning"}

If present, the component is instantiated also for the default project. It takes effect only when used inside of [`<project-components>`](#idea-plugin__project-components) element.

{style="narrow"}
Required
: no

## TODO:

- add information about what can be patched by Gradle IntelliJ Plugin (+ links to its docs)
- add missing stuff:
  - paid/freemium plugin attributes
  - idea-plugin@require-restart
  - category element
- add information that if some element or attribute is missing, it shouldn't be used in 3rd-party plugins
- links to code sample plugin.xml files?
- resolve content duplication, e.g., extensions point attributes are described in the [](plugin_extension_points.md#declaring-extension-points) section

[//]: # (TODO: when we backlink to related topic&#40;s&#41; maybe we could use same format as we have in custom language tutorial "Reference: [link]" always in same position for element and separated from textual description)
