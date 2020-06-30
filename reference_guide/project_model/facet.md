---
title: Facet
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A facet represents configuration specific for a particular framework/technology, associated with a module.
A module can have multiple facets. E.g. Spring Framework specific configuration is stored in a Spring facet.

For more information about facets see [Adding Support for Frameworks and Technologies](https://www.jetbrains.com/help/idea/adding-support-for-frameworks-and-technologies.html) and the help page for the [Facets dialog](https://www.jetbrains.com/help/idea/facet-page.html) in the IntelliJ IDEA Web Help.

## Facet Basics Sample
Please see [Facet Basics](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/facet_basics) sample plugin project.

## Working with Facets

### Managing Facets
To create, search and access the list of facets for a module use [`FacetManager`](upsource:///platform/lang-api/src/com/intellij/facet/FacetManager.java).

### Facet-Based Tool Window
A [tool window](/user_interface_components/tool_windows.md) dependent on the existence of given facet(s) can be registered via `com.intellij.facet.toolWindow` extension point.
