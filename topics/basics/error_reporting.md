<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Error Reporting

<web-summary>
How to let users report plugin errors from the IDE, how to receive automatic error reports in the background, and how to handle freeze reports safely.
</web-summary>

<link-summary>User-facing and automatic reporting APIs for plugin-attributed exceptions and freezes.</link-summary>

The IntelliJ Platform can detect many errors caused by plugin code and attribute them to the responsible plugin.
Plugin authors can integrate with that flow in two different ways:

- [`ErrorReportSubmitter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java) handles the user-facing reporting flow from the IDE Fatal Errors UI.
- [`ErrorReportSink`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSink.kt) receives automatic background reports about unhandled exceptions and UI freezes attributed to the plugin.

These APIs complement each other.
`ErrorReportSubmitter` is the user-facing reporting API.
It is triggered when the user chooses to submit a report from the IDE UI, so the user is explicitly involved in the process.
It is typically used for issue tracker integration, custom backend submission, privacy notices, and account-aware reporting.
The submitter receives `IdeaLoggingEvent` instances together with the user comment, any included attachments,
and a callback used to complete the submission flow.

`ErrorReportSink` is the automatic background reporting API.
It is triggered when the platform automatically forwards a plugin-attributed exception or freeze, so no user action is required.
The sink receives either [`UnhandledExceptionReport`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSink.kt)
or [`UnhandledFreezeReport`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSink.kt),
which makes it suitable for observability scenarios even when the user never clicks a reporting action.

<video src="https://www.youtube.com/watch?v=F4ZQe3AU3v4"/>

## Manual Reporting

### JetBrains Marketplace Exception Analyzer

If the plugin is published on JetBrains Marketplace and the built-in reporting flow is sufficient, this is the simplest supported setup.

Register [`JetBrainsMarketplaceErrorReportSubmitter`](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/JetBrainsMarketplaceErrorReportSubmitter.kt)
as an implementation of the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.errorHandler"/></include>
in <path>plugin.xml</path>:

```xml
<extensions defaultExtensionNs="com.intellij">
  <errorHandler implementation="com.intellij.diagnostic.JetBrainsMarketplaceErrorReportSubmitter"/>
</extensions>
```

This approach requires no custom `ErrorReportSubmitter` implementation in the plugin.
The IDE uses the built-in submitter to send reports to the backend provided by JetBrains.

See [JetBrains Exception Analyzer](https://plugins.jetbrains.com/docs/marketplace/exception-analyzer.html)
for setup details and current product-specific behavior.

### Custom Implementation of `ErrorReportSubmitter`

Use a custom submitter when the plugin needs control over the backend, payload, privacy notice, or the post-submission workflow.

Register the submitter in <path>plugin.xml</path>:

```xml
<extensions defaultExtensionNs="com.intellij">
  <errorHandler implementation="my.plugin.MyErrorReportSubmitter"/>
</extensions>
```

The public contract is defined by [`ErrorReportSubmitter`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSubmitter.java).
Reports are delivered as [`IdeaLoggingEvent`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/IdeaLoggingEvent.java) instances,
and the final result is reported back to the IDE using
[`SubmittedReportInfo`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/SubmittedReportInfo.java).

```kotlin
class MyErrorReportSubmitter : ErrorReportSubmitter() {
  override fun getReportActionText(): String = "Report to Vendor"

  override fun getPrivacyNoticeText(): String =
    "The report may contain stack traces, plugin version," +
            " IDE version, and attachments selected by the user."

  override fun submit(
    events: Array<IdeaLoggingEvent>,
    additionalInfo: String?,
    parentComponent: Component,
    consumer: Consumer<in SubmittedReportInfo>
  ): Boolean {
    // Start background work, then complete consumer later.
    return true
  }
}
```

Where:

- `getReportActionText()` defines the text shown on the report button in the reporting UI
- `getPrivacyNoticeText()` must explain to users what data is sent and how it is used
- `submit()` performs the actual submission

The `submit()` method receives the [`IdeaLoggingEvent`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/IdeaLoggingEvent.java) data selected for the report:

- events - `IdeaLoggingEvent` instances give access to the core report data, such as the `Throwable`, message text, and attachments included in the report.
- `additionalInfo` - the free-form text entered by the user in the comment box as
- `parentComponent` - for any necessary interaction
- `consumer` - callback that must receive the final [`SubmittedReportInfo`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/SubmittedReportInfo.java).
This is the main integration point for building a custom payload for an issue tracker or backend service.

If `submit()` returns `true`, the submitter is declaring that the submission has started and the callback will be completed later.
The callback must eventually receive a `SubmittedReportInfo` with the proper `SubmissionStatus`:
- `NEW_ISSUE` - if the issue has been successfully filed
- `DUPLICATE` - if the issue is actually a duplicate of the existing one
- `FAILED` - if the submission failed

If submission cannot even start, `submit()` must return `false`.

> Do not leave the callback unfinished after returning `true`.
> The IDE uses that callback to update the reporting UI and final submission state.
>
{style="warning"}

### Threading and Coroutines

`ErrorReportSubmitter.submit()` should return quickly and avoid expensive blocking work as it runs on the EDT.
Therefore, the general threading rules from [](threading_model.md) apply here as well.
That means, operations on EDT must be as fast as possible to avoid UI freezes, while
network, file, and other expensive operations belong on background threads.

For plugins targeting 2024.1+, [Kotlin coroutines](kotlin_coroutines.md) are the recommended approach for asynchronous work.

To keep `submit()` lightweight, a typical implementation first extracts the information needed from the incoming events, then starts the expensive work in the background, returns `true`, and finally completes the callback when submission finishes.

When using coroutines, prefer a [service scope](launching_coroutines.md#launching-coroutine-from-service-scope) for submission work.
Use [`Dispatchers.IO`](coroutine_dispatchers.md) for network and file I/O.

```kotlin
@Service
class ReportingService(
  private val cs: CoroutineScope
) {
  fun submitReport(
      report: ReportData,
      consumer: Consumer<in SubmittedReportInfo>)
  {
    cs.launch {
      val result = withContext(Dispatchers.IO) {
        sendToBackend(report)
      }
      consumer.consume(result)
    }
  }
}
```

This pattern keeps the reporting UI responsive while still integrating cleanly with the IDE callback contract.

Also, do not perform long-running non-cancellable read actions while collecting data for a report.
Long-running read actions on background threads can block write actions and cause UI freezes.
If additional project or PSI data is needed for a report, keep access short and use the modern threading
and coroutine APIs described in [](threading_model.md#accessing-data) and [](launching_coroutines.md).

## Automatic Reporting with `ErrorReportSink`

[`ErrorReportSink`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/diagnostic/ErrorReportSink.kt)
is an experimental API for automatic background reporting.

Register it via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.errorReportSink"/></include>
in <path>plugin.xml</path>:

```xml
<extensions defaultExtensionNs="com.intellij">
  <errorReportSink implementation="com.example.MyErrorReportSink"/>
</extensions>
```

Example:

```kotlin
class MyErrorReportSink : ErrorReportSink {
  override suspend fun submit(report: UnhandledErrorReport) {
    when (report) {
      is UnhandledExceptionReport -> {
        // collect metrics or forward to backend
      }
      is UnhandledFreezeReport -> {
        // inspect duration, attachments, and thread dumps
      }
    }
  }
}
```

`ErrorReportSink` currently delivers two report types:
- `UnhandledExceptionReport` contains the exception class and stack trace
- `UnhandledFreezeReport` contains a message, the freeze duration, attachments, and thread dumps

`ErrorReportSink` is intended for plugin observability, not guaranteed report delivery and at most 10,000 exception
reports per plugin per IDE session are forwarded.
Note, that the platform does not deduplicate or debounce repeated exceptions, and exception reports contain less information
than user-submitted reports.

The current platform implementation delivers sink reports asynchronously on a background dispatcher.
Plugins should still keep sink processing short, deduplicate repeated failures, and rate-limit any backend traffic on their side.

## Attachments and Freeze Reports

An exception report is often centered around a `Throwable` and its stack trace.
However, you can provide additional context for such reports and log exceptions with attachments when useful:

```kotlin
try {
  // some code
}
catch (e: Throwable) {
  thisLogger().error(
      "some code failed",
      e,
      Attachment("details.txt", "extra diagnostics")
  )
}
```

Those attachments may later appear in the reporting UI and become part of the report delivered to the plugin's submitter.

Freeze reports are different from ordinary exception reports because they describe a stalled UI and usually need more evidence to explain what the IDE was waiting for.
Therefore, when the platform records a plugin-attributed UI freeze, the report may contain one or more thread dumps and additional attachments collected during freeze reporting.

For `ErrorReportSubmitter`, those artifacts may appear as attachments in the `IdeaLoggingEvent`.
For `ErrorReportSink`, they are represented explicitly by `UnhandledFreezeReport.attachments` and `UnhandledFreezeReport.threadDumps`.

Freeze reports are often caused by lock contention, blocking I/O, or long-running work that prevents EDT from progressing.
In these cases, a simple exception stack trace is not enough and thread dumps plus freeze-specific attachments help answer:

- what EDT was waiting for
- which thread was holding the relevant lock
- whether the plugin was doing background work that blocked a write action
- whether the stall was transient, repeated, or part of a larger problem.

This is why freeze reports are usually much more diagnostically valuable than a plain stack trace.

## Choosing Between the APIs

Use `ErrorReportSubmitter` when:
- the user should explicitly submit the report
- the plugin needs the user comment
- a custom privacy notice or account-aware reporting is required
- the backend expects a richer user-facing reporting flow

Use `ErrorReportSink` when:
- the plugin should observe failures even if the user does nothing
- the goal is telemetry, counting, alerting, or sending reports to your backend when possible, without guaranteeing delivery
- automatic freeze visibility is important

Use both APIs when the plugin needs background observability during normal usage and also a richer user-driven reporting path for manual submission.

## Example Implementations

- [ITNReporter](%gh-ic%/platform/platform-impl/src/com/intellij/diagnostic/ITNReporter.kt)
  A full production implementation that submits fatal error reports, shows progress and notifications, and completes the `SubmittedReportInfo` callback.
- [`UnhandledReportSinkServiceTest.TestSink`](%gh-ic%/platform/platform-tests/testSrc/com/intellij/diagnostic/UnhandledReportSinkServiceTest.kt)
  A tiny sink used in tests that simply receives an UnhandledErrorReport in submit(report) and stores it for verification.
- [Third party tutorial](https://www.plugin-dev.com/intellij/general/error-reporting/) for error reporting using _Sentry_
