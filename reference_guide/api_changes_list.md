---
title: Incompatible Changes in IntelliJ Platform and Plugins API
---

## Changes in IntelliJ Platform 2016.3

|  Change | How to deal with it |
|---------|---------------------|
| com.intellij.openapi.application.ApplicationListener#afterWriteActionFinished method added | Implement this method or extend com.intellij.openapi.application.ApplicationAdapter class instead of implementing the interface |


## Changes in IntelliJ Platform 2016.2 

|  Change | How to deal with it |
|---------|---------------------|
| com.intellij.util.net.HttpConfigurable#PROXY_LOGIN field removed | Use com.intellij.util.net.HttpConfigurable#getProxyLogin() instead |
| com.intellij.util.net.HttpConfigurable#PROXY_PASSWORD_CRYPT field removed | Use com.intellij.util.net.HttpConfigurable#getPlainProxyPassword() instead |
| org.jetbrains.asm4 package removed | use classes from org.jetbrains.org.objectweb.asm package instead |

