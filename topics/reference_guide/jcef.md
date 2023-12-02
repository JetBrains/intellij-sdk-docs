<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# JCEF — Java Chromium Embedded Framework

<link-summary>Embedding Chromium-based browser in IDE.</link-summary>

> JCEF is available since 2020.1 as an **experimental feature**.
> We plan to deprecate using JavaFX in 3rd party plugins and switch to JCEF in 2020.2.
> To continue using JavaFX in 2020.2 or later, an explicit dependency on [JavaFX Runtime for Plugins](https://plugins.jetbrains.com/plugin/14250-javafx-runtime-for-plugins) must be added.
> Please see also blog post [JavaFX and JCEF in the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/07/javafx-and-jcef-in-the-intellij-platform/) for summary of plans.
>
{style="warning"}

JCEF is a Java port of [CEF](https://bitbucket.org/chromiumembedded/cef/wiki/Home) framework for embedding [Chromium-based browsers](https://www.chromium.org/Home) in applications using Swing.

Embedding of the browser component inside the IDE allows amongst others:

- rendering HTML content
- previewing generated HTML (e.g., from Markdown)

> Please see also [Creating IntelliJ plugin with WebView](https://medium.com/virtuslab/creating-intellij-plugin-with-webview-3b27c3f87aea) tutorial.

## Enabling JCEF

<tabs>
<tab title="2020.2 and later">

> JCEF is available and enabled by default since 2020.2
>
{style="note"}

</tab>
<tab title="Earlier versions">

Using JCEF requires using a dedicated JetBrains Runtime.
See [installation instructions](https://youtrack.jetbrains.com/issue/IDEA-231833#focus=streamItem-27-3993099.0-0) on how to obtain and activate it in your IDE.
Enable `ide.browser.jcef.enabled` in <control>Registry</control> dialog (invoke <ui-path>Help | Find Action...</ui-path> and type "Registry") and restart the IDE for changes to take effect.

</tab>
</tabs>

## Debugging

The [Chrome DevTools](https://developers.google.com/web/tools/chrome-devtools/), embedded into JCEF, can be used as a debugging and profiling tool.
It's active by default, so that a Chrome DevTools client can attach to it via the default port number (9222).
The port number can be configured with the following registry key:

```
ide.browser.jcef.debug.port=9222
```

JavaScript debugger in IntelliJ IDEA Ultimate can thus be used to debug JavaScript code running in the IDE via the Chrome DevTools.
Use the <control>Attach to Node.js/Chrome</control> configurations with a proper port number.

Also, JCEF provides a default Chrome DevTools front-end (similar to the one in the Chrome browser) that can be opened from the JCEF's browser component context menu via <ui-path>Open DevTools</ui-path>.
The menu item is available in [internal mode](enabling_internal.md) only.
Starting with version 2021.3, the registry key `ide.browser.jcef.contextMenu.devTools.enabled` must be set to `true` explicitly.

To access the Chrome DevTools in plugin code, use the following API:

```java
JBCefBrowser browser = new JBCefBrowser(myUrl);
CefBrowser devTools = browser.getCefBrowser().getDevTools();
JBCefBrowser devToolsBrowser = JBCefBrowser.createBuilder()
    .setCefBrowser(devTools)
    .setClient(browser.getJBCefClient())
    .build();
```

Or in order to just open it in a separate window:

```java
JBCefBrowser browser = new JBCefBrowser(myUrl);
browser.openDevtools();
```

## Testing

See [`JBCefTestHelper`](%gh-ic%/platform/platform-tests/testSrc/com/intellij/ui/jcef/JBCefTestHelper.java) and tests in that package.

## File Download

Downloading files can be achieved by implementing the [`CefDownloadHandler`](https://github.com/JetBrains/jcef/blob/7560ce68418f8d8d1ac55a4fd318141053be8fea/java/org/cef/handler/CefDownloadHandler.java) and adding the download handler to the `JBCefClient`, as follows:

```java
JBCefBrowser browser = new JBCefBrowser();
browser.getJBCefClient()
       .addDownloadHandler(new SimpleDownloadHandler(), browser.getCefBrowser());
```

The `SimpleDownloadHandler` implements the `CefDownloadHandler`:

```java
public class SimpleDownloadHandler implements CefDownloadHandler {

  @Override
  public void onBeforeDownload(
      CefBrowser browser,
      CefDownloadItem downloadItem,
      String suggestedName,
      CefBeforeDownloadCallback callback) {
    callback.Continue("", true);
  }
...
}
```

Using the callback it opens the browser file download dialog.

## API

### JBCefApp

[`JBCefApp`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefApp.java) performs JCEF auto-initialization, manages its lifecycle, and provides `JBCefClient` instances.

Before using JCEF, `JBCefApp.isSupported()` check must be called:

```java
if (!JBCefApp.isSupported()) {
  // Fallback to an alternative browser-less solution
  return;
}
// Use JCEF
```

JCEF can be unsupported when:
- It's not available in the IDE runtime (the IDE is started with an alternative OpenJDK).
- Its version is not compatible with the running IDE.

To avoid the above problems, the IDE should be run with the bundled JetBrains Runtime (JBR) (see also [](ide_development_instance.md)).

### JBCefClient

[`JBCefClient`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefClient.java) is tied to every browser component explicitly or implicitly.
It is used for adding handlers to the associated browser.
The same instance can be shared among multiple browsers.
It is up to the developer to use a shared or per-browser instance, depending on the handlers' logic.
If a client was created explicitly, it should be [disposed](disposers.md) by the developer.
Otherwise, it is disposed automatically following the associated browser instance disposal.

### JBCefBrowser

[`JBCefBrowser`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefBrowser.java) provides the browser UI component:

```java
JComponent getComponent();
```

It also provides the load methods (callable from non-EDT thread as well):

```java
void loadURL(String);
void loadHTML(String);
```

For executing JS code and callbacks (see [](#jbcefjsquery)), use the wrapped `CefBrowser` instance directly:

```java
getCefBrowser().executeJavaScript(myCode, myUrl, myLine);
```

By default, `JBCefBrowser` is created with implicit `JBCefClient` (disposed automatically).
It is possible to pass your own `JBCefClient` (disposed by the developer).

For accessing the browser use `JBCefClient`:

```java
JBCefClient getJBCefClient();
```

The simplest way to add a browser component to the plugin UI:

```java
JPanel myPanel = ...;
myPanel.add(new JBCefBrowser("https://example.com").getComponent());
```

### JBCefJSQuery

[`JBCefJSQuery`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefJSQuery.java) provides JS query callback mechanism.

There is no direct access to JS DOM from Java (like in JavaFX WebView, see also [this issue](https://youtrack.jetbrains.com/issue/JBR-2046)).
Still, JCEF provides an asynchronous way to communicate to JS.

The example below shows opening a link in an external browser, and handling it:

```java
JBCefBrowser browser = new JBCefBrowser();
CefBrowser cefBrowser = browser.getCefBrowser();

// Create a JS query instance
JBCefJSQuery openInBrowserJsQuery =
    JBCefJSQuery.create((JBCefBrowserBase)browser);

// Add a query handler
openInBrowserJsQuery.addHandler((link) -> {
      // handle link here
      return null; // can respond back to JS with JBCefJSQuery.Response
    });

// Inject the query callback into JS
cefBrowser.executeJavaScript(
    "window.JavaPanelBridge = {" +
        "openInExternalBrowser : function(link) {" +
            openInBrowserJsQuery.inject("link") +
        "}" +
    "};",
    cefBrowser.getURL(), 0);

// Dispose the query when necessary
Disposer.dispose(openInBrowserJsQuery);
```
