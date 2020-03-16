---
title: JCEF - Java Chromium Embedded Framework
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** JCEF is available since 2020.1 as an **experimental feature**. We plan to deprecate using JavaFX in 3rd party plugins and switch to JCEF.

JCEF is a Java port of [CEF](https://bitbucket.org/chromiumembedded/cef/wiki/Home) framework for embedding [Chromium-based browsers](https://www.chromium.org/Home) in applications using Swing.

Embedding of the browser component inside the IDE allows amongst others:

- rendering HTML content
- previewing generated HTML (e.g., from Markdown) 

## Enabling JCEF                                                     
Using JCEF requires using a dedicated JetBrains Runtime, please follow these [installation instructions](https://youtrack.jetbrains.com/issue/IDEA-231833#focus=streamItem-27-3993099.0-0) on how to obtain and activate it in your IDE.
Enable `ide.browser.jcef.enabled` in Registry dialog (invoke **Help \| Find Action** and type "Registry") and restart the IDE for changes to take effect.

## Debugging
When the IDE is running in [internal mode](/reference_guide/internal_actions/enabling_internal.md), the JCEF process is started in debug mode (with the [`--inspect` flag](https://nodejs.org/en/docs/guides/debugging-getting-started/)). 

JavaScript debugger in IntelliJ IDEA Ultimate can thus be used to debug JavaScript code running in it.
Use the _Attach to Node.js/Chrome_ configurations with the default port `9229`.

Alternatively, Chrome's DevTools can be used by invoking [internal action](/reference_guide/internal_actions/enabling_internal.md) _Show Web Browser_ and then choose **Tools \| Dev Tools \| Show Dev Tools** in browser frame menu.

## API

### `com.intellij.ui.jcef.JBCefApp`
Performs JCEF auto-initialization, manages its lifecycle, and provides `JBCefClient` instances.

### `com.intellij.ui.jcef.JBCefClient`
Is tied to every browser component explicitly or implicitly. Used for adding handlers to the associated browser.
The same instance can be shared among multiple browsers. It is up to the developer to use a shared or per-browser instance, depending on the handlers' logic.
If a client was created explicitly, it should be disposed by the developer; otherwise, it is disposed automatically following the associated browser instance disposal.

### `com.intellij.ui.jcef.JBCefBrowser`
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

### `com.intellij.ui.jcef.JBCefJSQuery`

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
