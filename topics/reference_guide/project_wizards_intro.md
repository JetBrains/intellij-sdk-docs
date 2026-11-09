<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Project Wizards

<link-summary>APIs for implementing project wizards that collect project settings and generate new projects.</link-summary>

Creating a project in IntelliJ-based IDEs is achieved with [project wizards](https://www.jetbrains.com/help/idea/new-project-wizard.html) gathering information about the created project and generating it.

IntelliJ Platform provides APIs to implement wizards:
- [](new_project_wizard.md) — available since 2024.2
- [](legacy_project_wizard.md) — an old API, not recommended to implement in plugins targeting versions 2024.2 and newer
