[//]: # (title: IDE Infrastructure)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

### Logging

> If your plugin uses **log4j** library directly: it is removed from IntelliJ Platform in 2022.1; please see this [blog post](https://blog.jetbrains.com/platform/2022/02/removing-log4j-from-the-intellij-platform/) for migration instructions.
>
{type="note"}

The IntelliJ platform uses [`Logger`](upsource:///platform/util/src/com/intellij/openapi/diagnostic/Logger.java) abstraction class to shield from underlying logging implementation and configuration.

Plugins should obtain a dedicated instance:

<tabs>
<tab title="Java">

```java
import com.intellij.openapi.diagnostic.Logger;

public class MyPluginClass {

  private static final Logger LOG = Logger.getInstance(MyPluginClass.class);

  public void someMethod() {
    LOG.info("someMethod() was called");
  }

}
```

</tab>

<tab title="Kotlin">

```kotlin
private val LOG = logger<MyPluginClass>()

class MyPluginClass {

  fun someMethod() {
    LOG.info("someMethod() was called")
  }
}
```

If logging is used only to report exceptions, use convenience method `thisLogger()`:

```kotlin
try {
  // some code
}
catch (e: Throwable) {
  thisLogger().error("some code failed", e)
}
```

</tab>
</tabs>

By default, all messages with level `INFO` and higher are written to log output file <path>idea.log</path>.
To enable `DEBUG`/`TRACE` logging for specific categories, use <menupath>Help | Diagnostic Tools | Debug Log Settings</menupath>.

To locate the log file, choose the <menupath>Help | Show Log in Finder/Explorer</menupath> action.
When [internal mode](enabling_internal.md) is enabled, the currently running IDE log file can be opened using <menupath>Help | Open Log in Editor</menupath>.

To locate it for a specific installation, see this [Knowledge Base article](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519).
See [Development Instance Sandbox Directory](ide_development_instance.md#the-development-instance-sandbox-directory) on how to find it for development instances.

See [Testing FAQ](testing_faq.md) on how to enable `DEBUG`/`TRACE` level logging during tests, and obtain separate logs for failing tests.

To provide additional context for [reporting fatal errors](#error-reporting), use `Logger.error()` methods taking additional `Attachment` (see [`AttachmentFactory`](upsource:///platform/core-impl/src/com/intellij/diagnostic/AttachmentFactory.java)).

## Error Reporting

The IDE will show fatal errors caught by itself as well as logging messages with `ERROR` level in the <control>IDE Fatal Errors</control> dialog:
- for IDE platform: in EAP releases or when running in [internal mode](enabling_internal.md)
- for third-party plugins: always

For the latter, reporting is disabled by default — instead, there's an option to disable the plugin causing the exception.

To let users report such errors to the vendor, plugins can implement custom [`ErrorReportSubmitter`](upsource:///platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) registered in extension point `com.intellij.errorHandler`.
See [IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.errorHandler) for existing implementations — ranging from pre-filling web-based issue tracker forms to fully automated submission to log monitoring systems.
This [tutorial](https://www.plugin-dev.com/intellij/general/error-reporting/) also offers a working solution for using _Sentry_.

To disable red exclamation notification icon in status bar, invoke <menupath>Help | Edit Custom Properties...</menupath> and add `idea.fatal.error.notification=disabled` in opened <path>idea.properties</path>.

## Runtime Information

[`ApplicationInfo`](upsource:///platform/core-api/src/com/intellij/openapi/application/ApplicationInfo.java) provides information on the IDE version and vendor.
NOTE: to restrict compatibility, declare [IDEs](plugin_compatibility.md) and [versions](build_number_ranges.md) via <path>plugin.xml</path>.

To obtain information about OS and Java VM, use [`SystemInfo`](upsource:///platform/util/src/com/intellij/openapi/util/SystemInfo.java).

To access relevant configuration directories, see [`PathManager`](upsource:///platform/util/src/com/intellij/openapi/application/PathManager.java).

To obtain unique installation UUID, use [`PermanentInstallationID`](upsource:///platform/platform-impl/src/com/intellij/openapi/application/PermanentInstallationID.java).

## Context Help

To show custom context web-based help for your plugin's functionality (e.g., for [dialogs](dialog_wrapper.md)), provide [`WebHelpProvider`](upsource:///platform/platform-api/src/com/intellij/openapi/help/WebHelpProvider.java) registered in `com.intellij.webHelpProvider` extension point.

## Running Tasks Once

Use [`RunOnceUtil`](upsource:///platform/core-api/src/com/intellij/ide/util/RunOnceUtil.java) to run a task exactly once per project/application.

## Application Events

Application lifecycle events can be tracked via [`AppLifecycleListener`](upsource:///platform/platform-impl/src/com/intellij/ide/AppLifecycleListener.java) [listener](plugin_listeners.md).
See also [](plugin_components.md#application-startup) and [](plugin_components.md#project-and-application-close).

Register [`ApplicationActivationListener`](upsource:///platform/ide-core/src/com/intellij/openapi/application/ApplicationActivationListener.java) [listener](plugin_listeners.md) to be notified of "application focused/unfocused" events.

## Plugin Management

Currently, installed plugins can be checked via [`PluginManagerCore.isPluginInstalled()`](upsource:///platform/core-impl/src/com/intellij/ide/plugins/PluginManagerCore.java).

### Plugin Suggestions

For specific features (e.g., [File Type](registering_file_type.md), [Facet](facet.md), ...), the IDE will suggest installing matching plugins automatically.
See [Plugin Recommendations](https://plugins.jetbrains.com/docs/marketplace/intellij-plugin-recommendations.html) in Marketplace documentation for details.

To suggest other relevant plugins, use [`PluginsAdvertiser.installAndEnable()`](upsource:///platform/platform-impl/src/com/intellij/openapi/updateSettings/impl/pluginsAdvertisement/PluginsAdvertiser.kt).

### Deprecating a Plugin

To suggest replacing the currently installed deprecated plugin with the new one, implement [`PluginReplacement`](upsource:///platform/platform-api/src/com/intellij/ide/plugins/PluginReplacement.java) registered in `com.intellij.pluginReplacement` extension point.
