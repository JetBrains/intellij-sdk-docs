<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Rider Plugin Development

<link-summary>Introduction to developing plugins for Rider.</link-summary>

<var name="productID" value="rider"/>
<var name="marketplaceProductID" value="rider"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

Rider plugins are generally used to expose the functionality of a [ReSharper](https://www.jetbrains.com/resharper/) plugin.
[Rider](https://www.jetbrains.com/rider/) uses the IntelliJ Platform somewhat [differently](intellij_platform.md#rider) than other Platform-based based IDEs.
Rider uses the IntelliJ Platform to provide the user interface for a C# and .NET IDE but uses ReSharper to provide the language-specific features.

> Rider is free for non-commercial use

## IntelliJ IDEA Configuration for Rider Plugin Development

Although there is no dedicated Rider SDK, the [ReSharper DevGuide](https://www.jetbrains.com/help/resharper/sdk/getting_started.html) addresses the subject of plugins for Rider.
The documentation describes the [configuration](https://www.jetbrains.com/help/resharper/sdk/creating_plugin.html) of the Gradle build script and <path>settings.gradle</path> file to build a Rider plugin using the [Gradle build system](creating_plugin_project.md) in IntelliJ IDEA.

> See [](rider_extension_point_list.md) for the complete list.
>
{style="note"}

## Developing Rider Plugins with the IDEA and ReSharper SDKs

Before starting a new Rider plugin project, review the article [Writing plugins for ReSharper and Rider](https://blog.jetbrains.com/dotnet/2019/02/14/writing-plugins-resharper-rider/).
In particular, this article discusses _One Template to Rule Them All_, a way to quickly get started writing plugins for both Rider and ReSharper.

More background information is available in the [Building a .NET IDE with JetBrains Rider](https://www.codemag.com/Article/1811091/Building-a-.NET-IDE-with-JetBrains-Rider) article, which includes a high-level description of the _Rider Protocol_ communication process between Rider and ReSharper.
The article is a good counterpoint to the ReSharper DevGuide content, which discusses the protocol at the code level.

## Including Custom Settings into a Plugin

Rider plugins may introduce their own <path>.DotSettings</path> files with customized [ReSharper settings](https://www.jetbrains.com/help/resharper/Sharing_Configuration_Options.html).
This is useful e.g., when a plugin brings its own file templates.

For the ReSharper part to pick these settings, the settings files should be available in the plugin JAR file under the path <path>dotnet/Extensions/\$backend-plugin-id\$/settings</path>, where `backend-plugin-id` is calculated according to the following rules:

- if the IntelliJ plugin id (the [`<id>`](plugin_configuration_file.md#idea-plugin__id) element of the <path>[plugin.xml](plugin_configuration_file.md)</path>) includes a dot, then `backend-plugin-id` is the same as the IntelliJ plugin id;
- otherwise, the `backend-plugin-id` is a concatenation of the IntelliJ plugin vendor name (the [`<vendor>`](plugin_configuration_file.md#idea-plugin__vendor) element of the <path>plugin.xml</path>) and the IntelliJ plugin id.

For example, for a plugin with the following <path>plugin.xml</path> contents, the file <path>dotnet/Extensions/com.example.awesomeplugin/settings/templates.DotSettings</path> would be picked up:

```xml
<idea-plugin>
  <id>com.example.awesomeplugin</id>
  <!-- ... -->
</idea-plugin>
```

And the following <path>plugin.xml</path> would require placing the file under <path>dotnet/Extensions/Jean-Luc Picard.Enterprise/settings/templates.DotSettings</path> path:

```xml
<idea-plugin>
  <id>Enterprise</id>
  <vendor>Jean-Luc Picard</vendor>
  <!-- ... -->
</idea-plugin>
```

## Open Source Rider Plugins

It can be useful to refer to existing projects to help understand how to build plugins for Rider.
The following list of plugins is all open source and can demonstrate how to implement different functionality.
Please note that the list includes ReSharper plugins as well as Rider plugins.
Since a lot of Rider's language features are shared with the ReSharper engine, and since the Rider SDK includes the ReSharper SDK, then it can be useful to look at ReSharper plugins too.
Also note that these plugins might not be up to date with the current SDK.

* [Unity support for both ReSharper and Rider](https://github.com/JetBrains/resharper-unity)
* [F# support in JetBrains Rider](https://github.com/JetBrains/fsharp-support)
* [AgentMulder](https://github.com/ERNICommunity/AgentMulder/)
* [CognitiveComplexity](https://github.com/matkoch/resharper-cognitivecomplexity/)
* [CyclomaticComplexity](https://github.com/JetBrains/resharper-cyclomatic-complexity/)
* [Exceptional](https://github.com/CSharpAnalyzers/ExceptionalReSharper/)
* [HeapAllocationsViewer](https://github.com/citizenmatt/resharper-heapview/)
* [InternalsVisibleTo](https://github.com/hmemcpy/ReSharper.InternalsVisibleTo/)
* [PresentationAssistant](https://github.com/JetBrains/resharper-presentation-assistant/)
* [StyleCop](https://github.com/StyleCop/StyleCop.ReSharper/)
* [Xao](https://github.com/hmemcpy/ReSharper.Xao/)
* [Azure Toolkit for Rider](https://github.com/JetBrains/azure-tools-for-intellij)
* [T4 language support for both ReSharper and Rider](https://github.com/JetBrains/ForTea)
