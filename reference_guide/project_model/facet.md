---
title: Facet
---

A facet represents configuration specific for a particular framework/technology, associated with a module.
A module can have multiple facets. E.g. Spring specific configuration is stored in a Spring facet.
For more information about facets see [Adding Support for Frameworks and Technologies](https://www.jetbrains.com/help/idea/configuring-projects.html#add-support-for-frameworks-technologies) and the help page for the [Facets dialog](https://www.jetbrains.com/help/idea/facets.html) in the IntelliJ IDEA Web Help.

## Working with Facets

To access the list of facets for a module, use the [FacetManager](upsource:///platform/lang-api/src/com/intellij/facet/FacetManager.java)
and [Facet](upsource:///platform/lang-api/src/com/intellij/facet/Facet.java) classes.
