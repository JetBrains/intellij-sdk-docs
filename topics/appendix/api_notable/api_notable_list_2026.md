<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2026.*

<link-summary>List of known Notable API Changes in 2026.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2026.1

### IntelliJ Platform 2026.1

New LSP API features
: Language Server Protocol support gains Range Formatting (`textDocument/rangeFormatting`), Code Lens (`textDocument/codeLens`), and Optimize Imports (`textDocument/codeAction` with `source.organizeImports`). ``Also includes a major rewrite of LSP highlighting for improved performance. See [Language Server Protocol (LSP)](language_server_protocol.md).
