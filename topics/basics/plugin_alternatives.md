[//]: # (title: Alternatives to Implementing a Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

In some cases, implementing an actual IntelliJ Platform plugin can be overkill, and using one of the alternative approaches listed below may provide you with the required value in a much shorter time.
If you need a functionality that is specific to your project domain, conventions, or practices, you can avoid all the steps that are required to implement and publish a plugin and provide these features as a part of your project or IDE configuration files.

Before you start the IntelliJ Platform plugin development, define your requirements and verify if they can be covered with the alternatives described below.
Consider implementing an actual plugin only when the described solutions are insufficient in your case and there is a significant number of developers who can benefit from it.

## Structural Search and Replace Inspections

The [Structural Search and Replace (SSR)](https://www.jetbrains.com/help/idea/structural-search-and-replace.html) functionality allows defining search patterns which are based not only on textual information but also on the structure of the searched code fragments, no matter how it is formatted or commented.
The SSR templates can be used for [creating custom inspections](https://www.jetbrains.com/help/idea/creating-custom-inspections.html), which can be an alternative for programmatic [code inspections](code_inspections.md).
Depending on requirements, an inspection can report an issue for a code fragment matching a given template, but also provide a quick fix replacing the reported fragment with the configured replacement template.
All inspection metadata like name, problem tooltip, and description are configurable.
A single inspection can use multiple search and replacement templates.

Once SSR inspections are created and configured, they can be shared with other team members via [inspection profiles](https://www.jetbrains.com/help/idea/customizing-profiles.html).

> See the [I(J)nspector](https://ijnspector.wordpress.com/) blog for practical SSR templates examples.
>
{type="note"}

## IDE Scripting Console

The [IDE scripting console](https://www.jetbrains.com/help/idea/ide-scripting-console.html) can be used for automating IDE's features and extracting required information, e.g., about a current project.
Scripts can access the IntelliJ Platform APIs and can be implemented in Kotlin, JavaScript, or Groovy by default, but it is also possible to use other languages compliant with the [JCR-223 specification](https://www.jcp.org/en/jsr/detail?id=223).

Created scripts are stored in the [IDE configuration directory](https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#config-directory) and can't be shared as part of project files or configuration.

## Flora Plugin

The [Flora](https://plugins.jetbrains.com/plugin/17669-flora-beta-) plugin allows for developing project-specific extensions as Kotlin Script (<path>\*.kts</path>) or JavaScript (<path>\*.js</path>) files.
Flora extensions have access to all available IntelliJ Platform APIs, just like a regular plugin.

Every extension is represented by a single file and stored directly in a project's <path>.plugins</path> directory.
Extensions can be easily shared with other team members by adding the <path>.plugins</path> directory to VCS.
Also, adding the Flora plugin in the <menupath>Settings/Preferences | Build, Execution, Deployment | Required Plugins</menupath> and sharing this configuration as part of a project makes it effortless to deliver additional IDE functionalities to your team without any manual setup.

## LivePlugin

The [LivePlugin](https://plugins.jetbrains.com/plugin/7282-liveplugin) allows for extending IntelliJ-based IDEs functionalities the runtime, without the need of restarting IDE.
It adds a new <control>Plugins</control> tool window that lists all available extensions and allows managing them.
Extensions can be implemented in Kotlin or Groovy and edited directly in the IDE.
Extensions can use all IntelliJ Platform APIs and additional LivePlugin API that shorten common use cases.

Created extensions are stored on the IDE level and can be shared with other team members as plain files, GitHub gists, or repositories.
Additionally, if they are stored in a project's <path>.live-plugins</path> directory and LivePlugin's <control>Run Project Specific Plugins</control> option is enabled, all extensions from this directory will be loaded automatically when the project is opened and unloaded when the project is closed.

> See the LivePlugin author's [article](https://dmitrykandalov.com/liveplugin) and [presentation](https://www.youtube.com/watch?v=GcYa4lMRta0) for more information.
>
{type="note"}
