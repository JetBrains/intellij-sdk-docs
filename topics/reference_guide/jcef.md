<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

#  Embedded Browser (JCEF)

<link-summary>Embedding Chromium-based browser in IDE.</link-summary>

> JCEF is available since 2020.1 as an experimental feature and is enabled by default since 2020.2.

JCEF (Java Chromium Embedded Framework) is a Java port of [CEF](https://bitbucket.org/chromiumembedded/cef/wiki/Home).
It allows for embedding [Chromium-based browsers](https://www.chromium.org/Home) in Swing applications.

Embedding of the browser component inside the IDE can be used for:

- rendering HTML content
- previewing generated HTML (e.g., from Markdown)
- creating custom web-based components (e.g., diagrams preview, image browser, etc.)

It is recommended to implement UI in the default IntelliJ Platform UI framework, which is Swing.
Consider using JCEF approach only in cases, when a plugin needs to display HTML documents or the standard approach for creating UI is insufficient.

JCEF replaces JavaFX, which was used to render web content in IDEs in the past.

> Using JavaFX in 3rd party plugins has been deprecated since 2020.2.
> To continue using JavaFX in 2020.2 or later, add an explicit [dependency](plugin_dependencies.md) on [JavaFX Runtime for Plugins](https://plugins.jetbrains.com/plugin/14250-javafx-runtime-for-plugins) (not recommended).
>
> See [JavaFX and JCEF in the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/07/javafx-and-jcef-in-the-intellij-platform/) blog post for the details.
>
{style="warning" title="Using JavaFX"}

## Enabling JCEF

<tabs>
<tab title="2020.2 and later">

JCEF is available and enabled by default since 2020.2.
No additional actions are required.

</tab>
<tab title="Earlier versions">

Using JCEF requires using a dedicated JetBrains Runtime and enabling JCEF in the IDE Registry.

<procedure>

1. Go to the [JetBrains Runtime releases list](https://github.com/JetBrains/JetBrainsRuntime/releases).
2. Download "Binaries for launching IntelliJ IDEA" matching your operating system, e.g., <path>jbr_jcef-17.0.9-osx-x64-b1087.7.tar.gz</path> for macOS.
3. Unpack the archive.
4. Follow the steps described in the [IDEA Web Help](https://www.jetbrains.com/help/idea/2020.2/switching-boot-jdk.html) and choose the downloaded JBR.
5. Invoke <ui-path>Help | Find Action...</ui-path>, type "Registry", and press enter to open the <control>Registry</control> dialog.
6. Enable the `ide.browser.jcef.enabled` flag.
7. Restart the IDE for changes to take effect.

</procedure>

</tab>
</tabs>

## Using JCEF In a Plugin

The core JCEF class exposed by IntelliJ Platform API is [`JBCefApp`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefApp.java).
It is responsible for initializing JCEF context and managing its lifecycle.

There is no need for initializing `JBCefApp` explicitly.
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

JCEF browser is represented by [`JBCefBrowser`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefBrowser.java) class.
It is responsible for loading and rendering requested documents in the actual Chromium-based browser.

JCEF browsers can be created either by using the `JBCefBrowser` class' constructors, or via [`JBCefBrowserBuilder`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefBrowserBuilder.java).
Use constructors in the cases when a browser with the default [client](#browser-client) and default options is enough.
The builder approach allows using custom clients and configuring other options.

#### Adding Browser to UI

[`JBCefBrowser.getComponent()`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefBrowser.java) exposes the UI component embedding the actual browser.
The component is an instance of Swing `JComponent`, which can be added to the plugin UI:

```java
// assume 'JPanel myPanel' is a part of a tool window UI
JBCefBrowser browser = new JBCefBrowser();
myPanel.add(browser.getComponent());
```

#### Loading Documents

To load a document in the browser, use one of [`JBCefBrowserBase.load*()`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefBrowserBase.java) methods.
Methods loading documents can be called from both UI and non-UI threads.
It is possible to set an initial URL (passed to constructor or builder) that will be loaded when browser is created and initialized.

### Browser Client

Browser client provides an interface for setting up [handlers](#event-handlers) related to various browser events, e.g.:
- HTML document loaded
- console message printed
- browser gained focus

Handlers allow reacting to these events in plugin code and change browser's behavior.
Each browser is tied to a single client and a single client can be shared with multiple browser instances.

Browser client is represented by [`JBCefClient`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefClient.java), which is a wrapper for JCEF's [`CefClient`](%gh-jcef%/org/cef/CefClient.java).
`JBCefClient` allows registering multiple handlers of the same type, which is not possible with `CefClient`.
To access the underlying `CefClient` and its API, call `JBCefClient.getCefClient()`.

#### Creating and Accessing Client

If a `JBCefBrowser` instance is created without passing a specific client, it is tied to a default client created implicitly.
Implicit clients are [disposed](#disposing-resources) automatically, following the associated browser instance disposal.

For more advanced use cases, create a custom client by calling `JBCefApp.createClient()` and register required [handlers](#event-handlers).
Custom clients must be disposed explicitly in the plugin code.

To access the client associated with a browser, call `JBCefBrowser.getJBCefClient()`.

### Event Handlers

JCEF API provides various event handler interfaces that allows handling a wide set of events emitted by the browser.
Example handlers:
- [`CefLoadHandler`](%gh-jcef%/org/cef/handler/CefLoadHandler.java) - handles browser loading events.<br/>
  **Example**: Implement `CefLoadHandler.onLoadEnd()` to [execute scripts](#executing-javascript) after document is loaded.

- [`CefDisplayHandler`](%gh-jcef%/org/cef/handler/CefDisplayHandler.java) - handles events related to browser display state.<br/>
  **Example**: Implement `CefDisplayHandler.onAddressChange()` to load project files in the browser when a local file link is clicked, or opening an external browser if an external link is clicked.

- [`CefContextMenuHandler`](%gh-jcef%/org/cef/handler/CefContextMenuHandler.java) - handles context menu events.<br/>
  **Example**: Implement `CefContextMenuHandler.onBeforeContextMenu()` to change the items of the browser context menu.

- [`CefDownloadHandler`](%gh-jcef%/org/cef/handler/CefDownloadHandler.java) - file download events.<br/>
  **Example**: Implement `CefDownloadHandler.onBeforeDownload()` to enable downloading files in the embedded browser.

See [org.cef.handler](%gh-jcef%/org/cef/handler) package for all available handlers.

> For each handler interface, JCEF API provides an adapter class, which can be extended to avoid implementing unused methods, e.g., [`CefLoadHandlerAdapter`](%gh-jcef%/org/cef/handler/CefLoadHandlerAdapter.java).

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

The above snippet will be executed in the embedded browser and will display alert box with the "Hello World!" message.
The `url` and `lineNumber` parameters are used in the error report in the browser, if the script throws an error.
Their purpose is to help debugging in case of errors, and they are not crucial for the script execution.
It is common to pass `browser.getCefBrowser().getUrl()` or null/empty string, and `0` as these parameters.

### Executing Plugin Code From JavaScript

JCEF doesn't provide direct access to DOM from the plugin code (it may [change](https://youtrack.jetbrains.com/issue/JBR-2046) in the future) and asynchronous communication with JavaScript is achieved with the callback mechanism.
It allows executing plugin code from the embedded browser via JavaScript, e.g., when a button or link is clicked, a shortcut is pressed, a JavaScript function is called, etc.

JavaScript query callback is represented by [`JBCefJSQuery`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefJSQuery.java).
It is an object bound to a specific browser, and it holds a set of handlers that implement the required plugin behavior.

Consider a case, which requires opening local files links in the editor and external links in an external browser.
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

1. Create `JBCefQuery` instance. Make sure that the passed browser instance is of type `JBCefBrowserBase` (casting may be needed).
2. Add a handler implementing a plugin code to be executed.
   Example implementation opens a link in the editor or an external browser depending on whether the link is local or external.
3. Handlers can optionally return `JBCefJSQuery.Response` object, which holds information about success or error occurred on the plugin code side.
   It can be handled in the browser if needed.
4. Execute JavaScript, which creates a custom `openLink` function.
5. Inject JavaScript code responsible for invoking plugin code implemented in step 2.
   The handler added to `openLinkQuery` will be invoked on each `openLink` function call.

   Note the `"link"` parameter of the `JBCefJSQuery.inject()` method.
   It is the name of the `openLink` function's `link` parameter.
   This value is injected to the query function call, and can be any value that is required by handler, e.g., `"myJsObject.prop"`, `"'JavaScript string'"`, etc.
6. Execute JavaScript, which registers a click event listener in the browser.
   Whenever an `a` element is clicked in the browser, the listener will invoke the `openLink` function defined in step 4 with the `href` value of the clicked link.

### Loading Resources From Plugin Distribution

In cases when a plugin feature implements a web-based UI, the plugin may provide HTML, CSS, and JavaScript files in its [distribution](plugin_content.md) or build them on the fly depending on some configuration.
Such resources cannot be easily accessed by the browser.
They can be made accessible by implementing proper request [handlers](#event-handlers), which make them available to the browser at predefined URLs.

This approach requires implementing [`CefRequestHandler`](%gh-jcef%/org/cef/handler/CefRequestHandler.java), and [`CefResourceRequestHandler`](%gh-jcef%/org/cef/handler/CefResourceRequestHandler.java), which map resource paths to resource providers.

Serving such resources is implemented by the Image Viewer component responsible for displaying SVG files in IntelliJ Platform-based IDEs.
See [`JCefImageViewer`](%gh-ic%/images/src/org/intellij/images/editor/impl/jcef/JCefImageViewer.kt) and related classes for the implementation details.

### Scrollbars Look and Feel

Default browser scrollbars may be insufficient, e.g. when they stand out of the IDE scrollbars look, or specific look and behavior is required.

In JCEF browsers, scrollbars look and feel can be customized by CSS and JavaScript.
IntelliJ Platform provides [`JBCefScrollbarsHelper`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefScrollbarsHelper.java) that allows to customize scrollbars in two ways:
1. Using `JBCefScrollbarsHelper.getOverlayScrollbarStyle()`, which provides the styles adapted to the IDE scrollbars.
2. Using [OverlayScrollbars](https://kingsora.github.io/OverlayScrollbars/) library adapted to the IDE look and feel.
   For the details, see `getOverlayScrollbarsSourceCSS()`, `getOverlayScrollbarsSourceJS()`, and `buildScrollbarsStyle()` Javadocs.
   It should be used when transparent scrollbars or other advanced options are required.

### Disposing Resources

`JBCefBrowser`, `JBCefClient`, and `JBCefJSQuery` classes implement [`JBCefDisposable`](%gh-ic%/platform/platform-api/src/com/intellij/ui/jcef/JBCefDisposable.java), which extends [`Disposable`](%gh-ic%/platform/util/src/com/intellij/openapi/Disposable.java).
It means that these classes should clean up their resources according to the rules described on the [](disposers.md) page.

For example, a custom `JBCefClient` with registered handlers should remove them in the `dispose()` method implementation.

## Testing

See [`JBCefTestHelper`](%gh-ic%/platform/platform-tests/testSrc/com/intellij/ui/jcef/JBCefTestHelper.java) and tests in that package.

## Debugging

The [Chrome DevTools](https://developers.google.com/web/tools/chrome-devtools/), embedded into JCEF, can be used as a debugging and profiling tool.
It is active by default, so that a Chrome DevTools client can attach to it via the default 9222 port.
Default port can be changed via the registry key `ide.browser.jcef.debug.port` (go to <ui-path>Help | Find Action...</ui-path> and type "Registry").

JavaScript debugger in IntelliJ IDEA Ultimate can thus be used to debug JavaScript code running in the IDE via the Chrome DevTools.
Use the [<control>Attach to Node.js/Chrome</control>](https://www.jetbrains.com/help/idea/run-debug-configuration-node-js-remote-debug.html) configuration with a proper port number.

Also, JCEF provides a default Chrome DevTools frontend (similar to the one in the Chrome browser) that can be opened from the JCEF's browser component context menu via <ui-path>Open DevTools</ui-path>.
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

In order to open DevTools in a separate window, call `JBCefBrowser.openDevtools()`.

## JCEF Usage Examples

- [Markdown preview panel](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/jcef/MarkdownJCEFHtmlPanel.kt)
- [SVG Image Viewer](%gh-ic%/images/src/org/intellij/images/editor/impl/jcef/JCefImageViewer.kt)
- [PDF Viewer](https://github.com/FirstTimeInForever/intellij-pdf-viewer) plugin
- [CodeStream](https://github.com/TeamCodeStream/codestream) plugin
- [Excalidraw Integration](https://github.com/bric3/excalidraw-jetbrains-plugin) plugin
- [Creating IntelliJ plugin with WebView](https://medium.com/virtuslab/creating-intellij-plugin-with-webview-3b27c3f87aea) blog post

<include from="snippets.md" element-id="missingContent"/>
