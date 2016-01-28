---
title: Setting Up a Development Environment
---

### Preliminary Steps

Use the following check list to ensure that you are ready to develop your custom plugins.

- **IntelliJ IDEA** version 9.0 or later (either Community Edition or Ultimate) must be installed on your computer.
- **IntelliJ IDEA CE source code** should be checked out to your local computer. This is not a requirement but will make it much easier for you to debug your plugins. For detailed instructions, refer to [Check Out And Build Community Edition](/basics/checkout_and_build_community.md).
- **IntelliJ Platform SDK** must configured for your IDEA project. For more information, see [Configuring IntelliJ Platform SDK](#configuring-intellij-platform-sdk) below.
  
### Configuring IntelliJ Platform SDK
  
To set up your plugin development environment:

*  Check out the source code of IntelliJ IDEA Community Edition as described in
   [Check Out And Build Community Edition](/basics/checkout_and_build_community.md).

*  Create a new *IntelliJ Platform SDK* under **File \| Project Structure**:

   ![Create IntelliJ Platform SDK](img/create_intellij_idea_sdk.png)

*  Specify the installation folder of *IntelliJ IDEA Community Edition* as the home directory.

   ![Set Home Directory](img/set_home_directory.png)

*  Select the **IDEA jdk** created in [Check Out And Build Community Edition](/basics/checkout_and_build_community.md) as the default Java SDK:

   ![Set IDEA JDK](img/set_java_sdk.png)


> **warning** You may use IntelliJ IDEA Ultimate as an alternative, but debugging the core code will only work with the *Community Edition*.

*  In the Sourcepath tab of the SDK settings, click the *Add* button:

   ![Add Sourcepath](img/add_sourcepath.png)

*  Specify the directory into which you have checked out the sources of the *Community Edition*:

   ![Specify Source Paths](img/community_sources_directory.png)

*  Select **File \| New \| Module** and choose the *IntelliJ Platform Plugin* module type

   ![IntelliJ Platform Plugin Module](img/intellij_platform_plugin_module.png)

*  Enter your desired plugin name.

*  Go to **File \| Project Structure** and select the newly created *IntelliJ Platform SDK* as the default SDK for the plugin module:

   ![Set Plugin Module SDK](img/set_plugin_module_sdk.png)
