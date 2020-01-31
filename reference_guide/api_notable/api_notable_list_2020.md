---
title: Notable Changes in IntelliJ Platform and Plugins API 2020.*
---

# 2020.1 

## Notable Changes in IntelliJ Platform 2020.1
Dynamic Plugins
: [Compatible plugins](/basics/plugin_structure/dynamic_plugins.md) can be installed, updated and uninstalled without requiring IDE restart.

[`com.intellij.openapi.application.TransactionGuard`](upsource:///platform/core-api/src/com/intellij/openapi/application/TransactionGuard.java) deprecated
: Usage is deprecated and can be replaced with `com.intellij.openapi.application.Application.invokeLater()` in most cases, please consult Javadoc for more details.

`RecursionManager.assertOnMissedCache()` enabled by default in tests
: Please see `RecursionManager.CachingPreventedException` Javadoc and [this issue](https://youtrack.jetbrains.com/issue/IDEA-228809) for details. 

## Notable Changes in IntelliJ IDEA

EOL for JetBrains TFS Plugin
: Please use [Azure DevOps](https://plugins.jetbrains.com/plugin/7981-azure-devops) plugin instead, see [blog post](https://blog.jetbrains.com/idea/2020/01/end-of-support-for-tfs-2014-and-older/) for more details.