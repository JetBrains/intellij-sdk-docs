<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2024.*

<link-summary>List of known Notable API Changes in 2024.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).


<include from="tools_gradle_intellij_plugin.md" element-id="gradle_plugin_223_problem"/>

## 2024.1

### IntelliJ Platform 2024.1

### IntelliJ IDEA 2024.1

Unbundled plugins
: Several plugins (Cucumber Groovy, Cucumber Java) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).
