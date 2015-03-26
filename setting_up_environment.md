---
title: Setting Up Development Environment
---

# {{ page.title }}
In order to set up the plugin development environment, you should follow these steps:

*  Check out the sources of IntelliJ IDEA Community Edition as described in
   [Check Out And Build Community Edition](checkout_and_build_community.html)

*  Create a new *IntelliJ IDEA SDK*

   ![Create IntelliJ IDEA SDK](/img/getting_started/setting_up_environment/create_intellij_idea_sdk.png)

*  Set *IDEA jdk* created in
   [Check Out And Build Community Edition](checkout_and_build_community.html)
   as a default Java SDK

   ![Set IDEA JDK](/img/getting_started/setting_up_environment/set_idea_jdk.png)

*  Specify your installation of *IntelliJ IDEA Community Edition* as the home path

   **Note:**
   You can use IDEA Ultimate as well, but debugging the core code will only work with the *Community Edition*

*  In the Sourcepath tab of the SDK settings, press ```Add``` button

   ![Add Sourcepath](/img/getting_started/setting_up_environment/add_sourcepath.png)

*  Specify the directory into which you have checked out the sources of the *Community Edition*

   ![Specify Source Paths](/img/getting_started/setting_up_environment/community_sources_directory.png)

*  Go to **File \| New \| Module**

*  Choose *IntelliJ Platform Plugin* module type

   ![IntelliJ Platform Plugin Module](/img/getting_started/setting_up_environment/intellij_platform_plugin_module.png)

*  Set desired plugin name

*  Go to **File \| Project Structure**

*  Select the newly created *IntelliJ Platform SDK* as a default SDK for the plugin module

   ![Set Plugin Module SDK](/img/getting_started/setting_up_environment/set_plugin_module_sdk.png)
