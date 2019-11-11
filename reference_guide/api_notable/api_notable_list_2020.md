---
title: Notable Changes in IntelliJ Platform and Plugins API 2020.*
---

# 2020.1 

## Notable Changes in IntelliJ Platform 2020.1
[`com.intellij.openapi.application.TransactionGuard`](upsource:///platform/core-api/src/com/intellij/openapi/application/TransactionGuard.java) deprecated
: Usage is deprecated and can be replaced with `com.intellij.openapi.application.Application.invokeLater()` in most cases, please consult Javadoc for more details.
                          
## Notable Changes in IntelliJ IDEA
