---
title: Notable Changes in IntelliJ Platform and Plugins API 2020.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

We've published our roadmap for the IntelliJ Platform for 2020: [Part I](https://blog.jetbrains.com/idea/2019/12/intellij-platform-roadmap-for-2020/) [Part II](https://blog.jetbrains.com/idea/2020/01/intellij-based-ide-features-roadmap-for-2020/)

# 2020.1 

## Notable Changes in IntelliJ Platform 2020.1
Dynamic Plugins
: [Compatible plugins](/basics/plugin_structure/dynamic_plugins.md) can be installed, updated and uninstalled without requiring IDE restart.

[`com.intellij.openapi.application.TransactionGuard`](upsource:///platform/core-api/src/com/intellij/openapi/application/TransactionGuard.java) deprecated
: Usage is deprecated and can be replaced with `com.intellij.openapi.application.Application.invokeLater()` in most cases, please consult Javadoc for more details.

`RecursionManager.assertOnMissedCache()` enabled by default in tests
: Please see `RecursionManager.CachingPreventedException` Javadoc and [this issue](https://youtrack.jetbrains.com/issue/IDEA-228809) for details. 

`ResolveCache` using `IdempotenceChecker` in tests
: Reports when the same reference resolves to non-equivalent results in different threads, see [`IdempotenceChecker`](upsource:///platform/core-impl/src/com/intellij/util/IdempotenceChecker.java).

Refactoring dialog: builtin "Open in editor" option
: Set `addOpenInEditorCheckbox` constructor parameter to enable it in custom `RefactoringDialog` implementation.

Configurable status bar widgets
: Use extension point `com.intellij.statusBarWidgetFactory` to provide widgets that can be disabled or reordered. 

JCEF Support (_Experimental Feature_) 
: Allows [embedding](/reference_guide/jcef.md) Chromium-based browser in the IDE.

Override text presentation for actions depending on menu context. 
: Set the `<override-text>` element within the `<action>` declaration in `plugin.xml`.

## Notable Changes in IntelliJ IDEA

EOL for JetBrains TFS Plugin
: Please use [Azure DevOps](https://plugins.jetbrains.com/plugin/7981-azure-devops) plugin instead, see [blog post](https://blog.jetbrains.com/idea/2020/01/end-of-support-for-tfs-2014-and-older/) for more details.

Unbundled plugins
: Several plugins (Cloud Foundry, Google App Engine) for no longer actively maintained technology have been unbundled. 
If your plugin depends on them, users will need to install them from the [JetBrains plugin repository](https://plugins.jetbrains.com).
