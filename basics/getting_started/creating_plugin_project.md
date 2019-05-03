---
title: Creating a Plugin Project
---

> **NOTE** For new projects, it is recommend to use [Gradle](/tutorials/build_system.md).

This section explains how you can create a new plugin project from scratch using the New Project wizard.  Optionally, you can import an existing project or import a project from external models. You can also add a new plugin module to an existing *IntelliJ Platform* project.
For more information, refer to the [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/new-project-wizard.html).

### To Create an IntelliJ Platform Plugin Project:

*  On the main menu, choose **File \| New \| Project**. The *New Project*
   wizard starts.

   ![New Project Wizard](img/new_project_wizard.png)

*  Set *IntelliJ Platform Plugin* project type
*  Click **Next**
*  Set desired project name
*  Click **Finish** to generate project structure files
*  Go to **File \| Project Structure** to customize project settings if required

### To Create an IntelliJ Platform Plugin Module
*  Select **File \| New \| Module** and choose the *IntelliJ Platform Plugin* module type

   ![IntelliJ Platform Plugin Module](img/intellij_platform_plugin_module.png)
<br/>
<br/>
*  Enter your desired plugin name.

*  Go to **File \| Project Structure** and select the newly created *IntelliJ Platform SDK* as the default SDK for the plugin module:

   ![Set Plugin Module SDK](img/set_plugin_module_sdk.png)

