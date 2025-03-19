<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Facet

<link-summary>Introduction to facets representing a framework/technology configuration for a module.</link-summary>

<tldr>

**Product Help:** [Facets](https://www.jetbrains.com/help/idea/facet-page.html), [Adding frameworks (facet)](https://www.jetbrains.com/help/idea/adding-support-for-frameworks-and-technologies.html)

</tldr>

A facet represents configuration specific for a particular framework/technology, associated with a module.
A module can have multiple facets.
For example, Spring Framework-specific configuration is stored in a dedicated [Spring facet](https://www.jetbrains.com/help/idea/spring-projects.html).

## Working with Facets

<include from="project.md" element-id="useWorkspaceModelAPI"/>

### Managing Facets

To create, search, and access the list of facets for a module, use [`FacetManager`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetManager.java).

### Facet-Based Tool Window

A [tool window](tool_windows.md) dependent on the existence of given facet(s) can be registered
via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.facet.toolWindow"/></include>.

## Facet Basics Sample

See [Facet Basics](%gh-sdk-samples-master%/facet_basics) sample plugin project.
