---
title: Incompatible Changes in IntelliJ Platform and Plugins API
---

<!--
To document a new incompatible change you have to fill a row in a table so that
the first column is a problem pattern and the second column is a human-readable description.

The following problem patterns are supported:
<package name> package removed
<class name> class removed
<class name>.<method name> method removed
<class name>.<field name> field removed
<class name>.<method name> abstract method added
<class name> class moved to package <package name>

where <class name> is a fully-qualified name of the class, e.g. com.intellij.openapi.actionSystem.AnAction.

NOTE: You are allowed to prettify the pattern using markdown-features:
 1) code quotes: `org.example.Foo.methodName`
 2) links [org.example.Foo](upsource:///platform/core-api/src/org/example/Foo)
 3) both code quotes and links: [`org.example.Foo`](upsource:///platform/core-api/src/org/example/Foo)
-->

<style>
  table {
    width:100%;
  }
  th, tr, td {
    width:50%;
  }
</style>

## Changes in IntelliJ Platform 2017.3

|  Change | How to deal with it |
|---------|---------------------|
| `com.intellij.internal.statistic.AbstractApplicationUsagesCollector` class removed | This class isn't supposed to be used in regular plugins. Override `com.intellij.internal.statistic.AbstractProjectsUsagesCollector` instead if you're developing an IDE with its own statistics services. |
| `com.intellij.internal.statistic.UsagesCollector.doPersistProjectUsages` method removed | This method isn't supposed to be used in regular plugins. There is no need to call this method anymore. |
|`org.apache.sanselan` package removed | Use classes from `org.apache.commons.imaging` instead |

## Changes in IntelliJ Platform 2016.3

|  Change | How to deal with it |
|---------|---------------------|
| [`ApplicationListener.afterWriteActionFinished`](upsource:///platform/core-api/src/com/intellij/openapi/application/ApplicationListener.java?nav=1481:1505:focused&line=45) abstract method added | Implement this method or extend [`com.intellij.openapi.application.ApplicationAdapter`](upsource:////platform/core-api/src/com/intellij/openapi/application/ApplicationAdapter.java) class instead of implementing the interface |


## Changes in IntelliJ Platform 2016.2 

|  Change | How to deal with it |
|---------|---------------------|
| `com.intellij.util.net.HttpConfigurable.PROXY_LOGIN` field removed | Use [`com.intellij.util.net.HttpConfigurable.getProxyLogin()`](upsource:///platform/platform-api/src/com/intellij/util/net/HttpConfigurable.java) instead |
| `com.intellij.util.net.HttpConfigurable.PROXY_PASSWORD_CRYPT` field removed | Use [`com.intellij.util.net.HttpConfigurable.getPlainProxyPassword()`](upsource:///platform/platform-api/src/com/intellij/util/net/HttpConfigurable.java) instead |
| `org.jetbrains.asm4` package removed | Use classes from `org.jetbrains.org.objectweb.asm` package instead |
