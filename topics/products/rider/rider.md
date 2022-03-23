[//]: # (title: Rider Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Rider plugins are generally used to expose the functionality of a [ReSharper](https://www.jetbrains.com/resharper/) plugin.
[Rider](https://www.jetbrains.com/rider/) uses the IntelliJ Platform somewhat [differently](intellij_platform.md#rider) than other Platform-based based IDEs.
Rider uses the IntelliJ Platform to provide the user interface for a C# and .NET IDE but uses ReSharper to provide the language-specific features.

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>
{type="tip"}

## IntelliJ IDEA Configuration for Rider Plugin Development

Although there is no dedicated Rider SDK, the [ReSharper DevGuide](https://www.jetbrains.com/help/resharper/sdk/Products/Rider.html) addresses the subject of plugins for Rider.
The documentation describes the [configuration](https://www.jetbrains.com/help/resharper/sdk/Products/Rider.html#plugin-project-jvm) of the Gradle build script and <path>settings.gradle</path> file to build a Rider plugin using the [Gradle project system](gradle_build_system.md) in IntelliJ IDEA.

> See [](rider_extension_point_list.md) for the complete list.
>
{type="note"}

## Developing Rider Plugins with the IDEA and ReSharper SDKs

Before starting a new Rider plugin project, review the article [Writing plugins for ReSharper and Rider](https://blog.jetbrains.com/dotnet/2019/02/14/writing-plugins-resharper-rider/).
In particular, this article discusses _One Template to Rule Them All_, a way to quickly get started writing plugins for both Rider and ReSharper.

More background information is available in the [Building a .NET IDE with JetBrains Rider](https://www.codemag.com/Article/1811091/Building-a-.NET-IDE-with-JetBrains-Rider) article, which includes a high-level description of the _Rider Protocol_ communication process between Rider and ReSharper.
The article is a good counterpoint to the ReSharper DevGuide content, which discusses the protocol at the code level.

## Including Custom Settings into a Plugin

Rider plugins may introduce their own <path>.DotSettings</path> files with customized [ReSharper settings](https://www.jetbrains.com/help/resharper/Sharing_Configuration_Options.html).
This is useful e.g. when a plugin brings its own file templates.

For the ReSharper part to pick these settings, the settings files should be available in the plugin JAR file under the path <path>dotnet/Extensions/$backend-plugin-id$/settings</path>, where `backend-plugin-id` is calculated according to the following rules:

- if the IntelliJ plugin id (the `<id>` element of the <path>plugin.xml</path>) includes a dot, then `backend-plugin-id` is the same as the IntelliJ plugin id;
- otherwise, the `backend-plugin-id` is a concatenation of the IntelliJ plugin vendor name (the `<vendor>` element of the <path>plugin.xml</path>) and the IntelliJ plugin id.

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

When learning new development configurations, it is helpful to have some existing plugins for reference.
This list is intended to provide some representative projects.
* [Unity support for both ReSharper and Rider](https://github.com/JetBrains/resharper-unity)
* [F# support in JetBrains Rider](https://github.com/JetBrains/fsharp-support)
* [AgentMulder](https://github.com/ERNICommunity/AgentMulder/)
* [ConfigureAwaitChecker](https://github.com/aelij/ConfigureAwaitChecker/)
* [CognitiveComplexity](https://github.com/matkoch/resharper-cognitivecomplexity/)
* [CyclomaticComplexity](https://github.com/JetBrains/resharper-cyclomatic-complexity/)
* [Exceptional](https://github.com/CSharpAnalyzers/ExceptionalReSharper/)
* [HeapAllocationsViewer](https://github.com/citizenmatt/resharper-heapview/)
* [ImplicitNullability](https://github.com/matkoch/SerializationInspections/)
* [InternalsVisibleTo](https://github.com/hmemcpy/ReSharper.InternalsVisibleTo/)
* [PresentationAssistant](https://github.com/JetBrains/resharper-presentation-assistant/)
* [SerializationInspections](https://github.com/matkoch/SerializationInspections/)
* [StyleCop](https://github.com/StyleCop/StyleCop.ReSharper/)
* [TestLinker](https://github.com/matkoch/TestLinker/)
* [Xao](https://github.com/hmemcpy/ReSharper.Xao/)
* [Azure Toolkit for Rider](https://github.com/JetBrains/azure-tools-for-intellij)
* [T4 language support for both ReSharper and Rider](https://github.com/JetBrains/ForTea)
