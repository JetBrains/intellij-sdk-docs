[//]: # (title: Facet)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<microformat>

**Product Help Topics:** [Facets](https://www.jetbrains.com/help/idea/facet-page.html), [Adding frameworks (facet)](https://www.jetbrains.com/help/idea/adding-support-for-frameworks-and-technologies.html)

</microformat>

A facet represents configuration specific for a particular framework/technology, associated with a module.
A module can have multiple facets.
E.g. Spring Framework specific configuration is stored in a Spring facet.

## Facet Basics Sample
Please see [Facet Basics](%gh-sdk-samples%/facet_basics) sample plugin project.

## Working with Facets

### Managing Facets
To create, search and access the list of facets for a module use [`FacetManager`](%gh-ic%/platform/lang-core/src/com/intellij/facet/FacetManager.java).

### Facet-Based Tool Window
A [tool window](tool_windows.md) dependent on the existence of given facet(s) can be registered via `com.intellij.facet.toolWindow` extension point.
