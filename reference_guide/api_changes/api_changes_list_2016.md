---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2016.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
-->

Please see [Incompatible API Changes](/reference_guide/api_changes_list.md) on how to verify compatibility.

## 2016.3 

### Changes in IntelliJ Platform 2016.3

[`com.intellij.openapi.application.ApplicationListener.afterWriteActionFinished`](upsource:///platform/core-api/src/com/intellij/openapi/application/ApplicationListener.java?nav=1481:1505:focused&line=45) abstract method added
: Implement this method or extend [`com.intellij.openapi.application.ApplicationAdapter`](upsource:////platform/core-api/src/com/intellij/openapi/application/ApplicationAdapter.java) class instead of implementing the interface.


## 2016.2 

### Changes in IntelliJ Platform 2016.2

`com.intellij.util.net.HttpConfigurable.PROXY_LOGIN` field removed
: Use [`com.intellij.util.net.HttpConfigurable.getProxyLogin()`](upsource:///platform/platform-api/src/com/intellij/util/net/HttpConfigurable.java) instead.

`com.intellij.util.net.HttpConfigurable.PROXY_PASSWORD_CRYPT` field removed
: [`com.intellij.util.net.HttpConfigurable.getPlainProxyPassword()`](upsource:///platform/platform-api/src/com/intellij/util/net/HttpConfigurable.java) instead.

`org.jetbrains.asm4` package removed
: Use classes from `org.jetbrains.org.objectweb.asm` package instead.