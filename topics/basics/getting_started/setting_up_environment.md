[//]: # (title: Setting Up a Development Environment)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

## Preliminary Steps

Use the following checklist to ensure that you are ready to develop your custom UI theme:

- **Plugin DevKit** plugin must be [enabled in IntelliJ IDEA](https://www.jetbrains.com/help/idea/managing-plugins.html).
- **Get IntelliJ IDEA CE source code** on your local computer.<br/>
  This step is optional and is needed only when you plan to debug the IntelliJ Platform code.
  See [](#get-intellij-idea-ce-source-code) for more details.
- **IntelliJ Platform SDK** must be [configured for your IDEA project](#configuring-intellij-platform-plugin-sdk).

## Get IntelliJ IDEA CE Source Code

Getting the IntelliJ IDEA CE source code is not a requirement for UI theme development as debugging the platform code while developing a UI theme is a rare situation.
In case you are developing a plugin extending IDE behavior, or you need to understand how some components work internally, having sources makes debugging much more straightforward.

For detailed instructions on how to check out the code efficiently, refer to the _Getting IntelliJ IDEA Community Edition Source Code_ section of [IntelliJ IDEA Community Edition README file](%gh-ic%/README.md).
Note that building the checked-out sources is not required in this case.

## Configuring IntelliJ Platform Plugin SDK

> For more information about SDKs, see [SDKs section](https://www.jetbrains.com/help/idea/working-with-sdks.html) in the IntelliJ IDEA Web Help.

### Download IntelliJ-based IDE

To see the effects of the developed plugin in real IDE, it is required to run the plugin in an [](ide_development_instance.md).
In most cases, it is enough to download and use [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) Community Edition.
If it is required to style components used only in a specific IDE like IntelliJ IDEA Ultimate Edition or WebStorm, they can also be used as SDK, but debugging the core code will only work with the *IntelliJ IDEA Community Edition*.

### Add JDK and IntelliJ Platform Plugin SDK

The first step of configuring a UI theme plugin SDK is adding the JDK.

<procedure title="Add JDK" id="add-jdk">

[//]: # (TODO: Add information about what JDK to choose: Set up a required Java SDK. See the IntelliJ Build Configuration section of Check Out And Build Community Edition for instructions about creating 1.8 &#40;11 when targeting 2020.3 or later&#41; Java SDK.)

[//]: # (TODO: what does the below message mean?)
> Do not use a more recent Java version than the one specified.
>
{type="warning"}

1. Go to <menupath>File | Project Structure | Platform Settings | SDKs</menupath>.
2. Click the <control>Add</control> button (<control>+</control>).
3. If you have the required JDK installation on your machine, and it is detected, please select it from the <control>Detected SDKs</control> list.
  If your JDK is not detected, select the <control>Add JDK...</control> option and choose the installation folder.<br/>
  If the required JDK is not installed on your machine, the simplest option is using <control>Download JDK...</control> and choosing the distribution options.
4. Click the <control>Apply</control> button.
</procedure>

The second step is adding IntelliJ Platform Plugin SDK that will use the JDK configured in the first step.

<procedure title="Add IntelliJ Platform Plugin SDK" id="create-ide-plugin">

1. Go to <menupath>File | Project Structure | Platform Settings | SDKs</menupath>.
2. Click the <control>Add</control> button (<control>+</control>).
3. Select the <control>Add IntelliJ Platform Plugin SDK...</control> option.
4. Choose the installation folder of the IDE [downloaded previously](#download-intellij-based-ide) (on macOS, select application icon in <path>/Applications/</path>).
5. In the <control>Select Internal Java Platform</control> dialog, select the JDK configured in the [previous step](#add-jdk) and click <control>OK</control> button.
6. In the added SDK, specify the <control>Sandbox Home</control> directory.<br/>
   See [](ide_development_instance.md#the-development-instance-sandbox-directory) for details.
7. If debugging is required, select the <control>Sourcepath</control> tab, click the <control>Add</control> button (<control>+</control>) and select the root folder of [the checked-out sources](#get-intellij-idea-ce-source-code).
8. Click the <control>Apply</control> button.

</procedure>
