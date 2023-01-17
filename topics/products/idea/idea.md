# IntelliJ IDEA Plugin Development

<!-- Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[IntelliJ IDEA](https://www.jetbrains.com/idea/) is available in two editions: IntelliJ Community Edition and IntelliJ IDEA Ultimate.
<snippet id="idea_editions">
See [Choose your edition](https://www.jetbrains.com/idea/features/#choose-your-edition) and [Feature Comparison](https://www.jetbrains.com/products/compare/?product=idea&product=idea-ce) for a detailed comparison.
</snippet>

## Configuring Plugin Projects Targeting IntelliJ IDEA

The configuration of IntelliJ IDEA plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute).

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                         |
|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IC` for IntelliJ IDEA Community Edition (default)<br/> `IU` for IntelliJ IDEA Ultimate |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | IDE version, e.g. `2022.2`                                                              |

## Available API

See [](extension_point_list.md#intellij-community-plugins) for API from bundled plugins.

## Java

See "Java" entry in table [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) on how to use Java specific functionality.

[PSI Cookbook](psi_cookbook.md#java-specific) lists a number of common operations for working with Java PSI.

Depending on exact functionality, a plugin can also target [UAST (Unified Abstract Syntax Tree)](uast.md) to support multiple JVM languages, including Java and Kotlin.

Relevant Extension Points:

- [](extension_point_list.md#javaanalysispluginxml)
- [](extension_point_list.md#javaindexingpluginxml)
- [](extension_point_list.md#javapluginxml)
- [](extension_point_list.md#javapsipluginxml)

## IntelliJ IDEA Ultimate

IntelliJ IDEA Ultimate comes with a number of additional features and bundled plugins.
<include from="idea.md" element-id="idea_editions"/>

<include from="snippets.md" element-id="jetbrainsProductOpenSourceLicense"/>

The following pages describe targeting API available only in IntelliJ IDEA Ultimate:

* [Tomcat Integration](tomcat_integration.md)
* [Spring API](spring_api.md)
