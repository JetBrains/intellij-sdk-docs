<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Setting Up a Development Environment

<link-summary>Setting up a development environment required for developing a theme.</link-summary>

<procedure title="Preliminary Steps">

<include from="snippets.topic" element-id="pluginDevKitAvailability"/>

Use the following checklist to ensure that you are ready to develop your custom theme:

1. **[IntelliJ IDEA](https://www.jetbrains.com/idea/download/)** is installed.
2. **Plugin DevKit** plugin is installed and [enabled in IntelliJ IDEA](https://www.jetbrains.com/help/idea/managing-plugins.html).
3. **IntelliJ IDEA CE source code** is checked out. _(Optional)_

   This step is needed only when you plan to debug the IntelliJ Platform code.
   See [](#get-intellij-idea-ce-source-code) for more details.
4. **IntelliJ Platform SDK** is [configured](#configuring-intellij-platform-plugin-sdk).

</procedure>

## Get IntelliJ IDEA CE Source Code

Getting the IntelliJ IDEA CE source code is not a requirement for theme development as debugging the platform code while developing a theme is a rare situation.
In case you are developing a plugin extending IDE behavior, or you need to understand how some components work internally, having sources makes debugging much more straightforward.

For detailed instructions on how to check out the code efficiently, refer to the _Getting IntelliJ IDEA Source Code_ section of the [IntelliJ IDEA OSS README file](%gh-ic%/README.md).
Note that building the checked-out sources is not required in this case.

## Configuring IntelliJ Platform Plugin SDK

> For more information about SDKs, see the [SDKs section](https://www.jetbrains.com/help/idea/working-with-sdks.html) in the IntelliJ IDEA Web Help.

### Download IntelliJ-Based IDE

To see the effects of the developed plugin in a real IDE, it is required to run the plugin in an [IDE development instance](ide_development_instance.md).
In most cases, it is enough to download and use _[IntelliJ IDEA](https://www.jetbrains.com/idea/download/)_.
If it is required to style components used only in a specific IDE like _GoLand_ or _WebStorm_, they can also be used as SDK, but debugging the core code will only work with the _IntelliJ IDEA_.

### Add JDK and IntelliJ Platform Plugin SDK

The first step of configuring a theme plugin SDK is adding the JDK.

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

<procedure title="Add JDK" id="add-jdk">

1. Go to <ui-path>File | Project Structure | Platform Settings | SDKs</ui-path>.
2. Click the <control>Add</control> button (<control>+</control>).
3. If you have the required JDK installation on your machine, and it is detected, select it from the <control>Detected SDKs</control> list.
   If your JDK is not detected, select the <control>Add JDK...</control> option and choose the installation folder.

   If the required JDK is not installed on your machine, the simplest option is using <control>Download JDK...</control> and choosing the distribution options.
4. Click the <control>Apply</control> button.

</procedure>

The second step is adding IntelliJ Platform Plugin SDK that will use the JDK configured in the first step.

<procedure title="Add IntelliJ Platform Plugin SDK" id="add-intellij-platform-plugin-sdk">

1. Go to <ui-path>File | Project Structure | Platform Settings | SDKs</ui-path>.
2. Click the <control>Add</control> button (<control>+</control>).
3. Select the <control>Add IntelliJ Platform Plugin SDK from disk...</control> option.
4. Choose the installation folder of the IDE [downloaded previously](#download-intellij-based-ide).
   > On macOS, in the file chooser, select <path>/Applications/</path> and switch the view to <control>Show items as: List</control>.
   > Then, expand the IDE item and select the <path>Contents</path> folder.
   >
   {style="tip"}
5. In the <control>Select Internal Java Platform</control> dialog, select the JDK configured in the [previous step](#add-jdk) and click the <control>OK</control> button.
6. In the added SDK, specify the <control>Sandbox Home</control> directory.

   See [](ide_development_instance.md#the-development-instance-sandbox-directory) for details.
7. If debugging is required, select the <control>Sourcepath</control> tab, click the <control>Add</control> button (<control>+</control>) and select the root folder of [the checked-out sources](#get-intellij-idea-ce-source-code).
8. Click the <control>Apply</control> button.

</procedure>
