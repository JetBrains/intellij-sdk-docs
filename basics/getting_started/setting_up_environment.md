---
title: Setting Up a Development Environment
---

### Preliminary Steps

Use the following checklist to ensure that you are ready to develop your custom plugins.

- **Get IntelliJ IDEA CE source code** on your local computer. Getting IntelliJ IDEA CE source code is not a requirement for plugin development, but having it makes debugging your plugins much easier. For detailed instructions refer to the _Getting IntelliJ IDEA Community Edition Source Code_ section of [Check Out And Build Community Edition](https://github.com/JetBrains/intellij-community/blob/master/README.md). Note that building IntelliJ IDEA CE from source code is not required for plugin development.
- **Plugin DevKit** plugin must be [enabled in IntelliJ IDEA](https://www.jetbrains.com/help/idea/managing-plugins.html)
- **IntelliJ Platform SDK** must be configured for your IDEA project. For more information, see below.
  
### Configuring IntelliJ Platform SDK
  
To set up your plugin development environment:

*  Create a new *IntelliJ Platform SDK* under **File \| Project Structure**:

   ![Create IntelliJ Platform SDK](img/create_intellij_idea_sdk.png)
<br/>
<br/>
*  Specify the installation folder of *IntelliJ IDEA Community Edition* as the home directory.
> **warning** You may use IntelliJ IDEA Ultimate as an alternative, but debugging the core code will only work with the *Community Edition*.

   ![Set Home Directory](img/set_home_directory.png)
<br/>
<br/>      
*  Select the **IDEA jdk** as the default Java SDK. See the _IntelliJ Build Configuration_ section of [Check Out And Build Community Edition](https://github.com/JetBrains/intellij-community/blob/master/README.md) for instructions about creating the **IDEA jdk**.

   ![Set IDEA JDK](img/set_java_sdk.png)
<br/>
<br/>
*  In the Sourcepath tab of the SDK settings, click the *Add* button:

   ![Add Sourcepath](img/add_sourcepath.png)
<br/>
<br/>
*  Specify the source code directory for the *IntelliJ IDEA Community Edition*:

   ![Specify Source Paths](img/community_sources_directory.png)
<br/>
<br/>
*  Select **File \| New \| Module** and choose the *IntelliJ Platform Plugin* module type

   ![IntelliJ Platform Plugin Module](img/intellij_platform_plugin_module.png)
<br/>
<br/>
*  Enter your desired plugin name.

*  Go to **File \| Project Structure** and select the newly created *IntelliJ Platform SDK* as the default SDK for the plugin module:

   ![Set Plugin Module SDK](img/set_plugin_module_sdk.png)
