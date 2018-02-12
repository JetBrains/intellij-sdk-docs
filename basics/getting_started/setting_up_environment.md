---
title: 建立开发环境
---

### 预备步骤

使用下面的检查表，以确保您准备开发自己的定制插件。

* **IntelliJ IDEA的** 版本9.0或更高版本（无论是社区版或旗舰版）必须安装在您的计算机上。
* **IntelliJ IDEA的CE源代码** 应该被检出到本地计算机。这不是一个要求，但将使它更容易为你调试你的插件。有关详细说明，请参阅 [检查，并建立社区版](basics/checkout_and_build_community.md).
* **插件的devkit** 插件必须在IDE中启用
* **的IntelliJ平台SDK** 必须配置为您的IDEA项目。欲了解更多信息，请参阅 [配置的IntelliJ平台SDK](#配置的IntelliJ平台SDK).
  
### 配置的IntelliJ平台SDK
  
要设置您的插件开发环境：

*  检查出的IntelliJ IDEA社区版的源代码中的说明
   [检查，并建立社区版](basics/checkout_and_build_community.md).

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


