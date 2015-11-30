---
title: 1. Prerequisites
---


### 1.1. Download and install IntelliJ IDEA

Download and install either IntelliJ IDEA Ultimate or IntelliJ IDEA Community Edition.

### 1.2. Check out Community Edition source files

```
git clone git://git.jetbrains.org/idea/community.git idea
```

### 1.3. Install required plugins

Make sure that the bundled Plugin DevKit plugin is enabled.
Install and enable
[Grammar-Kit](http://plugins.intellij.net/plugin?pluginId=6606)
and
[PsiViewer](http://plugins.intellij.net/plugin/?pluginId=227)
plugins.

### 1.4. Configure SDK and source files

Configure a common JDK and an IntelliJ Platform SDK.
For the IntelliJ Platform SDK you may need to specify the directory containing the installed version IntelliJ IDEA.
By default it should offer the directory of the currently running version of IntelliJ IDEA.

To attach the Community Edition source files to the SDK, open the Sourcepath tab in the SDK configuration dialog, press the Add button and select the directory with the Community Edition source checkout.
IntelliJ IDEA will scan the directory for roots and offer the list of roots to add, with everything checked by default.
Accept the list of roots by pressing OK.

![Project SDK](img/project_sdk.png)

![Plugins](img/plugins.png)

### 1.5. Create a project

Create an IntelliJ Platform Plugin project and specify IntelliJ Platform SDK.

![New Project](img/new_project.png)

[Top](/tutorials/custom_language_support_tutorial.md)
[Next](language_and_filetype.md)

