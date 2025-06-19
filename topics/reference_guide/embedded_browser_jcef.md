<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

#  Embedded Browser (JCEF)

<primary-label ref="2020.2"/>

<link-summary>Embedding Chromium-based browser in IDE.</link-summary>

<web-summary>
Embededding a Chromium-based browser in IntelliJ IDEA using JCEF for rendering HTML, previewing content, and creating custom web-based components.
</web-summary>

JCEF (Java Chromium Embedded Framework) is a Java port of [CEF](https://bitbucket.org/chromiumembedded/cef/wiki/Home).
It allows for embedding [Chromium-based browsers](https://www.chromium.org/Home) in Swing applications.

Embedding of the browser component inside the IDE can be used for:

- rendering HTML content
- previewing generated HTML (e.g., from Markdown)
- creating custom web-based components (e.g., diagram preview, image browser, etc.)

It is recommended to implement UI in the default IntelliJ Platform [UI framework](user_interface_components.md), which is Swing.
Consider using the JCEF approach only in cases when a plugin needs to display HTML documents or the standard approach for creating UI is not enough.

## JavaFX
{collapsible="true" default-state="collapsed"}

<primary-label ref="Deprecated"/>

JCEF replaces JavaFX, which was used to render web content in IDEs in the past.

Using JavaFX in 3rd party plugins has been deprecated since 2020.2.
To continue using JavaFX in 2020.2 or later, add an explicit [dependency](plugin_dependencies.md) on [JavaFX Runtime for Plugins](https://plugins.jetbrains.com/plugin/14250-javafx-runtime-for-plugins) (not recommended).
See [JavaFX and JCEF in the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/07/javafx-and-jcef-in-the-intellij-platform/) blog post for the details.

> This plugin is no longer available starting with 2025.1, see this [announcement](https://platform.jetbrains.com/t/javafx-runtime-for-plugins-is-deprecated-in-2025-1/944).
>
{style="warning"}

## Using JCEF In a Plugin

The core JCEF class exposed by IntelliJ Platform API is [`JBCefApp`](%gh-ic%/platform/ui.jcef/jcef/JBCefApp.java).
It is responsible for initializing JCEF context and managing its lifecycle.

There is no need to initialize `JBCefApp` explicitly.
It is done when `JBCefApp.getInstance()` is called, or when [browser](#browser) or [client](#browser-client) objects are created.

Before using JCEF API, it is required to check whether JCEF is supported in the running IDE.
It is done by calling `JBCefApp.isSupported()`:

```java
if (JBCefApp.isSupported()) {
  // use JCEF
} else {
  // optional fallback to an alternative browser-less solution
}
```

JCEF can be unsupported when:
- The IDE is started with an alternative JDK that does not include JCEF.
- Its version is not compatible with the running IDE.

### Browser

JCEF browser is represented by the [`JBCefBrowser`](%gh-ic%/platform/ui.jcef/jcef/JBCefBrowser.java) class.
It is responsible for loading and rendering requested documents in the actual Chromium-based browser.

JCEF browsers can be created either by using the `JBCefBrowser` class' constructors or via [`JBCefBrowserBuilder`](%gh-ic%/platform/ui.jcef/jcef/JBCefBrowserBuilder.java).
Use constructors in the cases when a browser with the default [client](#browser-client) and default options is enough.
The builder approach allows using custom clients and configuring other options.

#### Adding Browser to UI

[`JBCefBrowser.getComponent()`](%gh-ic%/platform/ui.jcef/jcef/JBCefBrowser.java) exposes the UI component embedding the actual browser.
The component is an instance of Swing `JComponent`, which can be added to the plugin UI:

```java
// assume 'JPanel myPanel' is a part of a tool window UI
JBCefBrowser browser = new JBCefBrowser();
myPanel.add(browser.getComponent());
```

#### Loading Documents

To load a document in the browser, use one of [`JBCefBrowserBase.load*()`](%gh-ic%/platform/ui.jcef/jcef/JBCefBrowserBase.java) methods.
Methods loading documents can be called from both [EDT and background threads](threading_model.md).
It is possible to set an initial URL (passed to constructor or builder) that will be loaded when the browser is created and initialized.

### Browser Client

Browser client provides an interface for setting up [handlers](#event-handlers) related to various browser events, e.g.:
- HTML document loaded
- console message printed
- the browser gained focus

Handlers allow reacting to these events in plugin code and changing the browser's behavior.
Each browser is tied to a single client, and a single client can be shared with multiple browser instances.

Browser client is represented by [`JBCefClient`](%gh-ic%/platform/ui.jcef/jcef/JBCefClient.java), which is a wrapper for the JCEF [`CefClient`](%gh-jcef-master%/org/cef/CefClient.java).
`JBCefClient` allows registering multiple handlers of the same type, which is not possible with `CefClient`.
To access the underlying `CefClient` and its API, call `JBCefClient.getCefClient()`.

#### Creating and Accessing Client

If a `JBCefBrowser` instance is created without passing a specific client, it is tied to a default client created implicitly.
Implicit clients are [disposed](#disposing-resources) automatically, following the associated browser instance disposal.

For more advanced use cases, create a custom client by calling `JBCefApp.createClient()` and register required [handlers](#event-handlers).
Custom clients must be disposed explicitly in the plugin code.

To access the client associated with a browser, call `JBCefBrowser.getJBCefClient()`.

### Event Handlers

The JCEF API provides various event handler interfaces that allow handling a wide set of events emitted by the browser.
Example handlers:
- [`CefLoadHandler`](%gh-jcef-master%/org/cef/handler/CefLoadHandler.java) - handles browser loading events.<br/>
  **Example:** Implement `CefLoadHandler.onLoadEnd()` to [execute scripts](#executing-javascript) after a document is loaded.

- [`CefDisplayHandler`](%gh-jcef-master%/org/cef/handler/CefDisplayHandler.java) - handles events related to the browser display state.<br/>
  **Example:** Implement `CefDisplayHandler.onAddressChange()` to load project files in the browser when a local file link is clicked, or opening an external browser if an external link is clicked.

- [`CefContextMenuHandler`](%gh-jcef-master%/org/cef/handler/CefContextMenuHandler.java) - handles context menu events.<br/>
  **Example:** Implement `CefContextMenuHandler.onBeforeContextMenu()` to change the items of the browser context menu.

- [`CefDownloadHandler`](%gh-jcef-master%/org/cef/handler/CefDownloadHandler.java) - file download events.<br/>
  **Example:** Implement `CefDownloadHandler.onBeforeDownload()` to enable downloading files in the embedded browser.

See the [org.cef.handler](%gh-jcef-master%/org/cef/handler) package for all available handlers.

> For each handler interface, JCEF API provides an adapter class, which can be extended to avoid implementing unused methods, e.g., [`CefLoadHandlerAdapter`](%gh-jcef-master%/org/cef/handler/CefLoadHandlerAdapter.java).

Handlers should be registered with `JBCefClient.getCefClient().add*Handler()` methods.

> Please note that `JBCefClient` exposes methods for adding handlers, but it is not recommended to use them.
>
{style="warning"}

### Executing JavaScript

JCEF API allows executing JavaScript code in the embedded browser from the plugin code.
JavaScript can be used for manipulating DOM, creating functions required in implemented features, injecting styles, etc.

In the simplest case, JavaScript code can be executed by using `JBCefBrowser.getCefBrowser().executeJavaScript()`, e.g.:

```java
browser.getCefBrowser().executeJavaScript(
    "alert('Hello World!')",
    url, lineNumber
);
```

The above snippet will be executed in the embedded browser and will display an alert box with the "Hello World!" message.
The `url` and `lineNumber` parameters are used in the error report in the browser if the script throws an error.
Their purpose is to help debugging in case of errors, and they are not crucial for the script execution.
It is common to pass `browser.getCefBrowser().getUrl()` or null/empty string, and `0` as these parameters.

### Executing Plugin Code From JavaScript

JCEF doesn't provide direct access to DOM from the plugin code (it may [change](https://youtrack.jetbrains.com/issue/JBR-2046) in the future), and asynchronous communication with JavaScript is achieved with the callback mechanism.
It allows executing plugin code from the embedded browser via JavaScript, e.g., when a button or link is clicked, a shortcut is pressed, a JavaScript function is called, etc.

JavaScript query callback is represented by [`JBCefJSQuery`](%gh-ic%/platform/ui.jcef/jcef/JBCefJSQuery.java).
It is an object bound to a specific browser, and it holds a set of handlers that implement the required plugin behavior.

Consider a case which requires opening local files links in the editor and external links in an external browser.
Such a requirement could be implemented as follows (each step is explained under the code snippet):

```java
JBCefJSQuery openLinkQuery = JBCefJSQuery.create(browser); // 1
openLinkQuery.addHandler((link) -> { // 2
    if (LinkUtil.isExternal(link)) {
      BrowserUtil.browse(link);
    } else {
      EditorUtil.openFileInEditor(link);
    }
    return null; // 3
});

browser.getCefBrowser().executeJavaScript( // 4
    "window.openLink = function(link) {" +
      openLinkQuery.inject("link") + // 5
      "};",
    browser.getCefBrowser().getURL(), 0
);

browser.getCefBrowser().executeJavaScript( // 6
    """
    document.addEventListener('click', function (e) {
      const link = e.target.closest('a').href;
      if (link) {
        window.openLink(link);
      }
    });""",
    browser.getCefBrowser().getURL(), 0
);
```

1. Create a ` JBCefQuery ` instance. Make sure that the passed browser instance is of the type `JBCefBrowserBase` (casting may be necessary).
2. Add a handler implementing a plugin code to be executed.
   The example implementation opens a link in the editor or an external browser depending on whether the link is local or external.
3. Handlers can optionally return a `JBCefJSQuery.Response` object, which holds information about success or error occurred on the plugin code side.
   It can be [handled](#handling-query-response) in the browser if needed.
4. Execute JavaScript, which creates a custom `openLink` function.
5. Inject JavaScript code responsible for invoking plugin code implemented in step 2.
   The handler added to `openLinkQuery` will be invoked on each `openLink` function call.

   Note the `"link"` parameter of the `JBCefJSQuery.inject()` method.
   It is the name of the `openLink` function's `link` parameter.
   This value is injected to the query function call and can be any value required by the handler, e.g., `"myJsObject.prop"`, `"'JavaScript string'"`, etc.
6. Execute JavaScript, which registers a click event listener in the browser.
   Whenever an `a` element is clicked in the browser, the listener will invoke the `openLink` function defined in step 4 with the `href` value of the clicked link.

#### Handling Query Response

In the example above, there is no need to return the response value to the browser from a query handler.
If it is required to handle response:
1. Instead of returning `null` as in 3., return a `JBCefJSQuery.Response` instance, for example, `new JBCefJSQuery.Response("OK")`.
2. Instead of injecting code without callbacks with `JBCefJSQuery.inject("link")` as in 5., use `JBCefJSQuery.inject(queryResult, onSuccessCallback, onFailureCallback)`, for example:
   ```java
   openLinkQuery.inject(
     "link",
     "function(response) { /* success handler code */ }",
     "function(error_code, error_message) { /* error handler code */ }"
   );
   ```

### Loading Resources From Plugin Distribution

In cases when a plugin feature implements a web-based UI, the plugin may provide HTML, CSS, and JavaScript files in its [distribution](plugin_content.md) or build them on the fly depending on some configuration.
The browser cannot easily access such resources.
They can be made accessible by implementing proper request [handlers](#event-handlers), which make them available to the browser at predefined URLs.

This approach requires implementing [`CefRequestHandler`](%gh-jcef-master%/org/cef/handler/CefRequestHandler.java), and [`CefResourceRequestHandler`](%gh-jcef-master%/org/cef/handler/CefResourceRequestHandler.java), which map resource paths to resource providers.

Serving such resources is implemented by the Image Viewer component responsible for displaying SVG files in IntelliJ Platform-based IDEs.
See [`JCefImageViewer`](%gh-ic%/images/src/org/intellij/images/editor/impl/jcef/JCefImageViewer.kt) and related classes for the implementation details.

### Scrollbars Look and Feel

Default browser scrollbars may be not enough, for example, when they stand out of the IDE scrollbars look, or specific look and behavior is required.

In JCEF browsers, scrollbars look and feel can be customized by CSS and JavaScript.
IntelliJ Platform provides [`JBCefScrollbarsHelper`](%gh-ic%/platform/ui.jcef/jcef/JBCefScrollbarsHelper.java) that allows customizing scrollbars in two ways:
1. Using `JBCefScrollbarsHelper.buildScrollbarsStyle()`, which provides the styles adapted to the IDE scrollbars (recommended).
2. Using [OverlayScrollbars](https://kingsora.github.io/OverlayScrollbars/) library adapted to the IDE look and feel.
   For the details, see `getOverlayScrollbarsSourceCSS()`, `getOverlayScrollbarsSourceJS()`, and `getOverlayScrollbarStyle()` Javadocs.
   It should be used when transparent scrollbars or other advanced options are required.

### Disposing Resources
`JBCefBrowser`, `JBCefClient`, and `JBCefJSQuery` classes implement [`JBCefDisposable`](%gh-ic%/platform/ui.jcef/jcef/JBCefDisposable.java), which extends [`Disposable`](%gh-ic%/platform/util/src/com/intellij/openapi/Disposable.java).
It means that these classes should clean up their resources according to the rules described on the [](disposers.md) page.

For example, a custom `JBCefClient` with registered handlers should remove them in the `dispose()` method implementation.

## Testing

See [`JBCefTestHelper`](%gh-ic%/platform/platform-tests/testSrc/com/intellij/ui/jcef/JBCefTestHelper.java) and tests in that package.

## Debugging

The [Chrome DevTools](https://developers.google.com/web/tools/chrome-devtools/), embedded into JCEF, can be used as a debugging and profiling tool.
It is active by default, so that a Chrome DevTools client can attach to it via the default port 9222.
The default port can be changed via the registry key `ide.browser.jcef.debug.port` (go to <ui-path>Help | Find Action...</ui-path> and type "Registry").

JavaScript debugger in IntelliJ IDEA Ultimate can thus be used to debug JavaScript code running in the IDE via the Chrome DevTools.
Use the [<control>Attach to Node.js/Chrome</control>](https://www.jetbrains.com/help/idea/run-debug-configuration-node-js-remote-debug.html) configuration with a proper port number.

Also, JCEF provides a default Chrome DevTools frontend (similar to the one in the Chrome browser) that can be opened from the JCEF browser component context menu via <ui-path>Open DevTools</ui-path>.
The menu item is available in the [internal mode](enabling_internal.md) only, and since version 2021.3, the registry key `ide.browser.jcef.contextMenu.devTools.enabled` must be set to `true` explicitly.

### Accessing DevTools Programmatically

To access the Chrome DevTools in the plugin code, use the following API:

```java
CefBrowser devTools = browser.getCefBrowser().getDevTools();
JBCefBrowser devToolsBrowser = JBCefBrowser.createBuilder()
    .setCefBrowser(devTools)
    .setClient(browser.getJBCefClient())
    .build();
```

To open DevTools in a separate window, call `JBCefBrowser.openDevtools()`.

## JCEF Usage Examples

- [Markdown preview panel](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/jcef/MarkdownJCEFHtmlPanel.kt)
- [SVG Image Viewer](%gh-ic%/images/src/org/intellij/images/editor/impl/jcef/JCefImageViewer.kt)
- [PDF Viewer](https://github.com/FirstTimeInForever/intellij-pdf-viewer) plugin
- [Excalidraw Integration](https://github.com/bric3/excalidraw-jetbrains-plugin) plugin
- [Creating IntelliJ plugin with WebView](https://medium.com/virtuslab/creating-intellij-plugin-with-webview-3b27c3f87aea) blog post

<include from="snippets.topic" element-id="missingContent"/>
