---
title: Setting Up Development Environment
---

# {{ page.title }}
In order to set up the plugin development environment, you should follow these steps:

* Check out the sources of IntelliJ IDEA Community Edition as described in 
[Check Out & Build Community Edition](http://www.jetbrains.org/pages/viewpage.action?pageId=983225)

* Create an SDK of type "IntelliJ IDEA SDK" and specify your installation of IntelliJ IDEA Community Edition as the home path. (You can use IDEA Ultimate as well, but debugging the core code will only work with the Community Edition.)
* In the Sourcepath tab of the SDK settings, press the "Add..." button and specify the directory into which you have checked out the sources of the Community Edition
* Create a new module of type "Plugin" and select the created SDK as the SDK to be used for this module.
