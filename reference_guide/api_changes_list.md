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
<class name>.<method name> method return type changed from <before> to <after>
<class name>.<method name> method parameter type changed from <before> to <after>
<class name>.<method name> method visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<class name>.<method name> abstract method added
<class name> class moved to package <package name>

where <class name> is a fully-qualified name of the class, e.g. com.intellij.openapi.actionSystem.AnAction$InnerClass.

NOTE: If a change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack/Plugins Repository assigned to me (Sergey Patrikeev), or contact me directly. An example of such a task is https://youtrack.jetbrains.com/issue/PR-1218. Until supported, you may document the change as you prefer, and I will correct it later.

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

## Changes in IntelliJ Platform 2018.1

|  Change | How to deal with it |
|---------|---------------------|
| `com.google.common.base.Objects.ToStringHelper` class removed | Use `com.google.common.base.MoreObjects.ToStringHelper` instead |
| `com.google.common.util.concurrent.Futures.get` method removed | Use `com.google.common.util.concurrent.Futures.getChecked` instead |
| [`org.tmatesoft.svn`](https://svnkit.com/index.html) package removed | Use classes from `org.jetbrains.idea.svn` instead |
| `org.jetbrains.idea.svn.svnkit.SvnKitManager` class removed | Use `org.jetbrains.idea.svn.api.ClientFactory` instead |
| `com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI` constructor parameter `JTextField` removed | Use `com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI` default constructor instead |
| `com.intellij.ide.ui.laf.darcula.ui.DarculaTextFieldUI` constructor parameter `JTextField` removed | Use `com.intellij.ide.ui.laf.darcula.ui.DarculaTextFieldUI` default constructor instead |
| `com.intellij.ide.ui.laf.darcula.ui.DarculaComboBoxUI` constructor parameter `JComboBox` removed | Use `com.intellij.ide.ui.laf.darcula.ui.DarculaComboBoxUI` default constructor instead |
| `com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI.myTextField` field removed | Use `javax.swing.plaf.basic.BasicTextUI.getComponent()` instead |




## Changes in IntelliJ Platform 2017.3

|  Change | How to deal with it |
|---------|---------------------|
| `com.intellij.internal.statistic.AbstractApplicationUsagesCollector` class removed | This class isn't supposed to be used in regular plugins. Override `com.intellij.internal.statistic.AbstractProjectsUsagesCollector` instead if you're developing an IDE with its own statistics services. |
| `com.intellij.internal.statistic.UsagesCollector.doPersistProjectUsages` method removed | This method isn't supposed to be used in regular plugins. There is no need to call this method anymore. |
|`org.apache.sanselan` package removed | Use classes from `org.apache.commons.imaging` instead |
| `com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider.getLanguages` abstract method added | Implementation of this class are supposed to have several languages so you need to implement this method explicitly and return them all |
| `org.jetbrains.kotlin.idea.configuration.KotlinProjectConfigurator.changeCoroutineConfiguration` abstract method added | You need to implement this method and add the logic for updating the configuration in your build system |
| `org.jetbrains.kotlin.idea.configuration.KotlinProjectConfigurator.updateLanguageVersion` abstract method added | You need to implement this method and add the logic for updating the configuration in your build system |
| `org.jetbrains.kotlin.idea.configuration.KotlinProjectConfigurator.addLibraryDependency` abstract method added | You need to implement this method and add the logic for updating the configuration in your build system |

## Changes in DataGrip and Database Tools plugin 2017.3

|  Change | How to deal with it |
|---------|---------------------|
| `com.intellij.database.dataSource.DataSourceManager` class removed | Use `com.intellij.database.psi.DbPsiFacade` instead |
| `com.intellij.database.dataSource.DataSourceManagerEx` class removed | Use `com.intellij.database.psi.DbPsiFacade` instead |
| `com.intellij.database.dataSource.DataSource` class removed | Use `com.intellij.database.psi.DbDataSource` and `com.intellij.database.model.DatabaseSystem` instead. |
| `com.intellij.database.psi.DbDataSource.getModel` method return type changed from `DbElement`-based model to `DasObject`-based model | Use `com.intellij.database.psi.DbPsiFacade.findElement` to get the corresponding `com.intellij.database.psi.DbElement` wrapper when needed. |
| `com.intellij.database.view.DatabaseView.getTreeBuilder` method removed | Use `LangDataKeys.PSI_ELEMENT_ARRAY.get(event)` to get Database view selection |

## Changes in PhpStorm and PHP plugin 2017.3

|  Change | How to deal with it |
|---------|---------------------|
| `com.jetbrains.php.lang.psi.elements.Function.getReturnType()` method return type changed from `PsiElement` to `PhpReturnType` | Before method had been returning a `com.jetbrains.php.lang.psi.elements.ClassReference`. Now method returns `com.jetbrains.php.lang.psi.elements.PhpReturnType`. Method `ReturnType.getClassReference()` can be used if you need just a `ClassReference`. If you need to get the `PhpType`, use `com.jetbrains.php.lang.psi.elements.Function.getReturnType.getType()` method instead |

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
