[//]: # (title: IDE Infrastructure)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

 > This page is _Work in Progress_ - more information will be added over time.
 >
 {type="note"}

### Logging

The IntelliJ platform uses [`Logger`](upsource:///platform/util/src/com/intellij/openapi/diagnostic/Logger.java) abstraction class to shield from underlying logging implementation and configuration.

Plugins should obtain a dedicated instance:

<tabs>
<tab title="Java">

```java
import com.intellij.openapi.diagnostic.Logger;

public class MyPluginFunctionality {

  private static final Logger LOG =
          Logger.getInstance(MyPluginFunctionality.class);

  public void someMethod() {
    LOG.info("someMethod() was called");
  }

}
```

</tab>

<tab title="Kotlin">

```kotlin

private val LOG = logger<MyPluginFunctionality>()

class MyPluginFunctionality {

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

## Error Reporting

Fatal errors and logging messages with `ERROR` level will be shown in the <control>IDE Fatal Errors</control> dialog for EAP releases or when running in [internal mode](enabling_internal.md).
For errors caused by third-party plugins, reporting is disabled by default - instead, there's an option to disable the plugin causing the exception.

To let users report such errors to the vendor, plugins can implement custom [`ErrorReportSubmitter`](upsource:///platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) registered in extension point `com.intellij.errorHandler`.
See [IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.errorHandler) for existing implementations - ranging from pre-filling web-based issue tracker forms to fully automated submission to log monitoring systems.

## Runtime Information

[`ApplicationInfo`](upsource:///platform/core-api/src/com/intellij/openapi/application/ApplicationInfo.java) provides information on the IDE version and vendor.
NOTE: to restrict compatibility, declare [IDEs](plugin_compatibility.md) and [versions](build_number_ranges.md) via <path>plugin.xml</path>.

To obtain information about OS and Java VM, use [`SystemInfo`](upsource:///platform/util/src/com/intellij/openapi/util/SystemInfo.java).

To access relevant configuration directories, see [`PathManager`](upsource:///platform/util/src/com/intellij/openapi/application/PathManager.java).
