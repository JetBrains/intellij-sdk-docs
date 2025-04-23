<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IDE Infrastructure

<web-summary>
How to log messages, report errors, access runtime info, help users report fatal errors, and manage plugins in your IDE.
</web-summary>

<link-summary>General IDE-level functionality (logging, error reporting, environment/installation info).</link-summary>

### Logging

> If your plugin uses the **log4j** library directly: it is removed from IntelliJ Platform in 2022.1; please see this [blog post](https://blog.jetbrains.com/platform/2022/02/removing-log4j-from-the-intellij-platform/) for migration instructions.
>
{style="note"}

The IntelliJ Platform uses [`Logger`](%gh-ic%/platform/util/src/com/intellij/openapi/diagnostic/Logger.java) abstraction class to shield from underlying logging implementation and configuration.

Plugins should get a dedicated instance:

<tabs>
<tab title="Java">

```java
import com.intellij.openapi.diagnostic.Logger;

public class MyClass {
  private static final Logger LOG = Logger.getInstance(MyClass.class);

  public void someMethod() {
    LOG.info("someMethod() was called");
  }
}
```

</tab>

<tab title="Kotlin">

```kotlin
import com.intellij.openapi.diagnostic.logger

private val LOG = logger<MyClass>()

class MyClass {
  fun someMethod() {
    LOG.info("someMethod() was called")
  }
}
```

If logging is used only to report exceptions, use convenience method `thisLogger()` instead of a dedicated instance:

```kotlin
try {
  // some code
} catch (e: Throwable) {
  thisLogger().error("some code failed", e)
}
```

</tab>
</tabs>

By default, all messages with level `INFO` and higher are written to log output file <path>idea.log</path>.
To enable `DEBUG`/`TRACE` logging for specific categories, use <ui-path>Help | Diagnostic Tools | Debug Log Settings</ui-path>.

To locate the log file, choose the <ui-path>Help | Show Log in Finder/Explorer</ui-path> action.
When [internal mode](enabling_internal.md) is enabled, the currently running IDE log file can be opened using <ui-path>Help | Open Log in Editor</ui-path>.

To locate it for a specific installation, see this [Knowledge Base article](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519).
See [Development Instance Sandbox Directory](ide_development_instance.md#the-development-instance-sandbox-directory) on how to find it for development instances.

See [Testing FAQ](testing_faq.md) on how to enable `DEBUG`/`TRACE` level logging during tests and get separate logs for failing tests.

To provide additional context for [reporting fatal errors](#error-reporting), use `Logger.error()` methods taking additional `Attachment` (see [`CoreAttachmentFactory`](%gh-ic%/platform/core-impl/src/com/intellij/diagnostic/CoreAttachmentFactory.java) and [`AttachmentFactory`](%gh-ic%/platform/util/src/com/intellij/openapi/diagnostic/AttachmentFactory.java)).

## Error Reporting

The IDE will show fatal errors caught by itself as well as logging messages with `ERROR` level in the <control>IDE Fatal Errors</control> dialog:
- for IDE platform: in EAP releases or when running in [internal mode](enabling_internal.md)
- for third-party plugins: always

For the latter, reporting is disabled by default — instead, there's an option to disable the plugin causing the exception.

To let users report such errors to the vendor, plugins can use one of the solutions:
- Use [JetBrains Exception Analyzer (EA)](https://plugins.jetbrains.com/docs/marketplace/exception-analyzer.html) that sends errors to the backend provided by JetBrains (2023.3+).
- Implement custom [`ErrorReportSubmitter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.errorHandler"/></include>.
Existing implementations range from pre-filling web-based issue tracker forms to fully automated submission to log monitoring systems.
This [tutorial](https://www.plugin-dev.com/intellij/general/error-reporting/) also offers a working solution for using _Sentry_.

The red exclamation notification icon in the status bar is controlled with the `idea.fatal.error.notification` system property.
The property can be edited in <path>idea.properties</path> file opened with <ui-path>Help | Edit Custom Properties...</ui-path>:
- `idea.fatal.error.notification=disabled` hides the component
- any other value or the lack of the property enables the component

## Runtime Information

[`ApplicationInfo`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ApplicationInfo.java) provides information on the IDE version and vendor.
NOTE: to restrict compatibility, declare [IDEs](plugin_compatibility.md) and [versions](build_number_ranges.md) via <path>[plugin.xml](plugin_configuration_file.md)</path>.

To get information about OS and Java VM, use [`SystemInfo`](%gh-ic%/platform/util/src/com/intellij/openapi/util/SystemInfo.java).

To access relevant configuration directories, see [`PathManager`](%gh-ic%/platform/util/src/com/intellij/openapi/application/PathManager.java).

To obtain unique installation UUID, use [`PermanentInstallationID`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/application/PermanentInstallationID.java).
For paid plugins, see also [Marketplace docs](https://plugins.jetbrains.com/docs/marketplace/identify-user-of-paid-plugin.html).

## Context Help

To show custom context web-based help for your plugin's functionality (e.g., for [dialogs](dialog_wrapper.md)), provide [`WebHelpProvider`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/help/WebHelpProvider.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.webHelpProvider"/></include>.

> To [localize](providing_translations.md) the help page URL, retrieve the current locale and language tag from the [dynamic bundle](providing_translations.md#getting-the-current-locale-programmatically).

## Running Tasks Once

Use [`RunOnceUtil`](%gh-ic%/platform/ide-core/src/com/intellij/ide/util/RunOnceUtil.kt) to run a task exactly once per project/application.

## Application Events

Application lifecycle events can be tracked via the [`AppLifecycleListener`](%gh-ic%/platform/ide-core/src/com/intellij/ide/AppLifecycleListener.java) [listener](plugin_listeners.md).
See also [](plugin_components.md#application-startup) and [](plugin_components.md#project-and-application-close).

Register the [`ApplicationActivationListener`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/application/ApplicationActivationListener.java) [listener](plugin_listeners.md) to be notified of "application focused/unfocused" events.

To request restart of the IDE, use [`Application.restart()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java)

## Launching Browser

Use [`BrowserLauncher`](%gh-ic%/platform/platform-api/src/com/intellij/ide/browsers/BrowserLauncher.kt).

## Open File in System File Manager

Use [`RevealFileAction.openFile()`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/actions/RevealFileAction.java) or `openDirectory()`.

## Theme Change

Use the [`LafManagerListener`](%gh-ic%/platform/platform-api/src/com/intellij/ide/ui/LafManagerListener.java) topic to receive change notifications (e.g., to refresh UI).

## Power Save Mode

<ui-path>File | Power Save Mode</ui-path> can be enabled to limit power-consuming features on laptops.
Use [`PowerSaveMode`](%gh-ic%/platform/core-api/src/com/intellij/ide/PowerSaveMode.java) service and `PowerSaveMode.Listener` topic to disable such features in your plugin accordingly.

## Plugin Management

Installed plugins can be checked via [`PluginManagerCore.isPluginInstalled()`](%gh-ic%/platform/core-impl/src/com/intellij/ide/plugins/PluginManagerCore.kt).

See also [](dynamic_plugins.md#pluginLoadUnloadEvents).

### Plugin Suggestions

For specific features (e.g., [File Type](registering_file_type.md), [Facet](facet.md), ...), the IDE will suggest installing matching plugins automatically.
See [Plugin Recommendations](https://plugins.jetbrains.com/docs/marketplace/intellij-plugin-recommendations.html) in Marketplace documentation for details.

To suggest other relevant plugins, use [`PluginsAdvertiser.installAndEnable()`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/pluginsAdvertisement/PluginsAdvertiser.kt).

### Deprecating a Plugin

To suggest replacing the currently installed deprecated plugin with the new one, implement [`PluginReplacement`](%gh-ic%/platform/platform-api/src/com/intellij/ide/plugins/PluginReplacement.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.pluginReplacement"/></include>.

## Network

Use [`HttpConnectionUtils`](%gh-ic%/platform/platform-api/src/com/intellij/util/net/HttpConnectionUtils.kt)
to use platform network settings (e.g., proxy) (2024.3).
For earlier versions, see [`HttpConfigurable`](%gh-ic%/platform/platform-api/src/com/intellij/util/net/HttpConfigurable.java).
