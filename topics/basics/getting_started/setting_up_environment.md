[//]: # (title: Setting Up a Development Environment)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> For new projects, it is highly recommended to use [Gradle-based setup](getting_started.md).
>
{type="note"}

### Preliminary Steps

Use the following checklist to ensure that you are ready to develop your custom plugins.
- **Plugin DevKit** plugin must be [enabled in IntelliJ IDEA](https://www.jetbrains.com/help/idea/managing-plugins.html).
- **IntelliJ Platform SDK** must be configured for your IDEA project.
  For more information, see below.
- (_Optional_) **Get IntelliJ IDEA CE source code** on your local computer.
  Getting IntelliJ IDEA CE source code is not a requirement for plugin development, but having it makes debugging your plugins much more straightforward.
  For detailed instructions, refer to the _Getting IntelliJ IDEA Community Edition Source Code_ section of [Check Out And Build Community Edition](upsource:///README.md).
  Note that building IntelliJ IDEA CE from source code is not required for plugin development.

### Configuring IntelliJ Platform SDK

> For more information about SDKs, see [SDKs section](https://www.jetbrains.com/help/idea/working-with-sdks.html) in the IntelliJ IDEA Web Help.

To set up your plugin development environment:

* Set up a required Java SDK.
  See the _IntelliJ Build Configuration_ section of [Check Out And Build Community Edition](upsource:///README.md) for instructions about creating **1.8** (**11** when targeting 2020.3 or later) Java SDK.

> Do not use a more recent Java version than the one specified.
>
{type="warning"}

* Create a new <control>IntelliJ Platform Plugin SDK</control> under <menupath>File | Project Structure</menupath>:
  ![Create IntelliJ Platform SDK](create_intellij_idea_sdk.png)
  <br/>
  <br/>
* Specify the installation folder of the *IntelliJ IDEA Community Edition* as the home directory (on Mac, select application icon in <path>/Applications/</path>).
  You can use the installation package built from sources or download it from the [Download IntelliJ IDEA page](https://www.jetbrains.com/idea/download/).
  > You may use IntelliJ IDEA Ultimate as an alternative, but debugging the core code will only work with the *Community Edition*.
  ![Set Home Directory](set_home_directory.png)
  <br/>
  <br/>
* Select Java SDK from the first step as the default Java SDK.
  ![Set IDEA JDK](set_java_sdk.png)
  <br/>
  <br/>
* In the <control>Sourcepath</control> tab of the SDK settings, click the <control>Add</control> button (_this step can be skipped if your plugin doesn't require debugging_):
  ![Add Sourcepath](add_sourcepath.png)
  <br/>
  <br/>
* Specify the source code directory for the *IntelliJ IDEA Community Edition*:
  ![Specify Source Paths](community_sources_directory.png)
  <br/>
  <br/>
* Specify the <control>Sandbox Home</control> directory.
  The <control>Sandbox Home</control> directory stores the settings of the IDE development instance launched from a Plugin Project's run configuration.
  Shown below is the default <control>Sandbox Home</control> directory for a user on macOS.
  Any directory can be chosen as the <control>Sandbox Home</control> location.
  Use the ellipsis button (shown below) to define a custom location.

  See the [IDE Development Instances](ide_development_instance.md) page for more information about the default <control>Sandbox Home</control> directory locations and contents.

  ![Specify Sandbox Path](plugins-sandbox.png)
