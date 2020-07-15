---
title: JCEF - Java Chromium Embedded Framework
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** JCEF is available since 2020.1 as an **experimental feature**. We plan to deprecate using JavaFX in 3rd party plugins and switch to JCEF in 2020.2. 
> To continue using JavaFX in 2020.2 or later, an explicit dependency on [JavaFX Runtime for Plugins](https://plugins.jetbrains.com/plugin/14250-javafx-runtime-for-plugins) must be added.
> Please see also blog post [JavaFX and JCEF in the IntelliJ Platform](https://blog.jetbrains.com/platform/2020/07/javafx-and-jcef-in-the-intellij-platform/) for summary of plans.

JCEF is a Java port of [CEF](https://bitbucket.org/chromiumembedded/cef/wiki/Home) framework for embedding [Chromium-based browsers](https://www.chromium.org/Home) in applications using Swing.

Embedding of the browser component inside the IDE allows amongst others:

- rendering HTML content
- previewing generated HTML (e.g., from Markdown) 

## Enabling JCEF       
> **NOTE** JCEF is available and enabled by default in 2020.2.
                                              
Using JCEF requires using a dedicated JetBrains Runtime, please follow these [installation instructions](https://youtrack.jetbrains.com/issue/IDEA-231833#focus=streamItem-27-3993099.0-0) on how to obtain and activate it in your IDE.
Enable `ide.browser.jcef.enabled` in Registry dialog (invoke **Help \| Find Action** and type "Registry") and restart the IDE for changes to take effect.

## Debugging
The [Chrome DevTools](https://developers.google.com/web/tools/chrome-devtools/), embedded into JCEF, can be used as a debugging and profiling tool. It’s active by default, so that a Chrome DevTools client can attach to it via the default port number - `9222`.
The port number can be configured with the following registry key:

```
ide.browser.jcef.debug.port=9222
```

JavaScript debugger in IntelliJ IDEA Ultimate can thus be used to debug JavaScript code running in the IDE via the Chrome DevTools. Use the _Attach to Node.js/Chrome_ configurations with a proper port number.

Also, JCEF provides a default Chrome DevTools front-end (similar to the one in the Chrome browser) that can be opened from the JCEF’s browser component context menu via **Open DevTools** (the menu item is available in [internal mode](/reference_guide/internal_actions/enabling_internal.md) only).

To access the Chrome DevTools in plugin code, use the following API:

```java
  JBCefBrowser myBrowser = new JBCefBrowser(myUrl);
  CefBrowser myDevTools = myBrowser.getCefBrowser().getDevTools();
  JBCefBrowser myDevToolsBrowser = new JBCefBrowser(myDevTools, myBrowser.getJBCefClient());
```

Or in order to just open it in a separate window:

```java
  JBCefBrowser myBrowser = new JBCefBrowser(myUrl);
  myBrowser.openDevTools();
```

## API

### [`com.intellij.ui.jcef.JBCefApp`](upsource:///platform/platform-api/src/com/intellij/ui/jcef/JBCefApp.java)
Performs JCEF auto-initialization, manages its lifecycle, and provides `JBCefClient` instances.

Before using JCEF, `JBCefApp.isSupported()` check must be called:

```java
    if (!JBCefApp.isSupported()) {
      // Fallback to an alternative browser-less solution
      return;
    }                 

    // Use JCEF
```                               

JCEF can be unsupported when:
- It’s not available in the IDE runtime (the IDE is started with an alternative OpenJDK).
- Its version is not compatible with the running IDE.

To avoid the above problems, the IDE should be run with the bundled JBR.


### [`com.intellij.ui.jcef.JBCefClient`](upsource:///platform/platform-api/src/com/intellij/ui/jcef/JBCefClient.java)
Is tied to every browser component explicitly or implicitly. Used for adding handlers to the associated browser.
The same instance can be shared among multiple browsers. It is up to the developer to use a shared or per-browser instance, depending on the handlers' logic.
If a client was created explicitly, it should be disposed by the developer; otherwise, it is disposed automatically following the associated browser instance disposal.

### [`com.intellij.ui.jcef.JBCefBrowser`](upsource:///platform/platform-api/src/com/intellij/ui/jcef/JBCefBrowser.java)
Provides the browser UI component:

```java
  JComponent getComponent();
```

Provides the load methods (callable from non-EDT thread as well):

```java
  void loadURL(String);
  void loadHTML(String);
```

For executing JS code and callbacks (see below), use the wrapped `CefBrowser` instance directly:

```java
  getCefBrowser().executeJavaScript(String code, String url, int line);
```

By default, `JBCefBrowser` is created with implicit `JBCefClient` (disposed automatically). It is possible to pass your own `JBCefClient` (disposed by the developer). 

For accessing:

```java
  JBCefClient getJBCefClient();
```

The simplest way to add a browser component to your UI:

```java
  JPanel myPanel = ...
  myPanel.add(new JBCefBrowser(“https://www.jetbrains.com”).getComponent());
```

### [`com.intellij.ui.jcef.JBCefJSQuery`](upsource:///platform/platform-api/src/com/intellij/ui/jcef/JBCefJSQuery.java)

Provides JS query callback mechanism.

There’s no direct access to JS DOM from Java (like in JavaFX WebView, see also [this issue](https://youtrack.jetbrains.com/issue/JBR-2046)). Still, JCEF provides an asynchronous way to communicate to JS.

It’s simpler to illustrate it by an example. Say, we want to open a link in an external browser (see it in [`MarkdownJCEFHtmlPanel`](upsource:///plugins/markdown/src/org/intellij/plugins/markdown/ui/preview/jcef/MarkdownJCEFHtmlPanel.java)):

```java
  // Create a JS query instance
  final JBCefJSQuery myJSQueryOpenInBrowser =
  JBCefJSQuery.create(myJBCefBrowser);
  
  // Add a query handler
  myJSQueryOpenInBrowser.addHandler((link) -> {
            MarkdownAccessor.getSafeOpenerAccessor().openLink(link);
          return null; // can respond back to JS with JBCefJSQuery.Response
        });
  
  // Inject the query callback into JS
  myCefBrowser.executeJavaScript(
  "window.JavaPanelBridge = {" +
            "openInExternalBrowser : function(link) {" +
                  myJSQueryOpenInBrowser.inject("link") +
              "}" +
        "};",
        getCefBrowser().getURL(), 0);
  
  // Dispose the query when necessary
  Disposer.dispose(myJSQueryOpenInBrowser);
```
