---
title: Rider Plugin Development
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction 
Rider plugins are generally used to expose the functionality of a ReSharper plugin.
[Rider](https://www.jetbrains.com/rider/) uses the IntelliJ Platform somewhat [differently](/intro/intellij_platform.md#rider) than other Platform-based based IDEs. 
Rider uses the IntelliJ Platform to provide the user interface for a C# and .NET IDE but uses ReSharper to provide the language-specific features.

## IntelliJ IDEA Configuration for Rider Plugin Development
Although there is no dedicated Rider SDK, the [ReSharper DevGuide](https://www.jetbrains.com/help/resharper/sdk/Products/Rider.html)  addresses the subject of plugins for Rider. 
The documentation describes the [configuration](https://www.jetbrains.com/help/resharper/sdk/Products/Rider.html#plugin-project-jvm) of `build.gradle` and `settings.gradle` files to build a Rider plugin using the [Gradle project system](/tutorials/build_system.md) in IntelliJ IDEA.

## Developing Rider Plugins with the IDEA and ReSharper SDKs
Before starting a new Rider plugin project, review the article [Writing plugins for ReSharper and Rider](https://blog.jetbrains.com/dotnet/2019/02/14/writing-plugins-resharper-rider/). 
In particular, this article discusses _One Template to Rule Them All_, a way to quickly get started writing plugins for both Rider and ReSharper. 

More background information is available in the [Building a .NET IDE with JetBrains Rider](https://www.codemag.com/Article/1811091/Building-a-.NET-IDE-with-JetBrains-Rider) article, which includes a high-level description of the _Rider Protocol_ communication process between Rider and ReSharper. 
The article is a good counterpoint to the ReSharper DevGuide content, which discusses the protocol at the code level.
 
## Open Source Rider Plugins
When learning new development configurations it is helpful to have some existing plugins for reference. 
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