<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Documentation

<tldr>

**Product Help:** [Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)

</tldr>

<link-summary>Documentation for code elements inside the editor.</link-summary>

Quick Documentation helps users by showing documentation, e.g., for classes, functions, or methods inside the editor.
Plugin authors implement
[`DocumentationProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
to show documentation for particular [PSI elements](psi_elements.md).

Implementations of `DocumentationProvider` can be registered either at the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.documentationProvider"/></include> or
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.documentationProvider"/></include>.
It is recommended to use the latter one when creating documentation that targets a specific language because providers registered
as `com.intellij.lang.documentationProvider` will only be called for elements from that language.
This is the reason they require the `language` attribute when registering the EP in the <path>[plugin.xml](plugin_configuration_file.md)</path>.
The bigger picture here is that documentation providers co-exist and if there is more than one provider for the same element,
the first one that returns a value different from `null` wins.

Although discouraged, the ordering of documentation providers can be influenced by using the `order` attribute when registering the extension.
For instance, PythonCore [`plugin.xml`](%gh-ic%/python/pluginCore/resources/META-INF/plugin.xml) uses the following to call the external documentation
provider before the default one (registered with `id="pythonDocumentationProvider"`):

```xml
<lang.documentationProvider
  language="Python"
  implementationClass="com.jetbrains.python.documentation.PythonExternalDocumentationProvider"
  order="before pythonDocumentationProvider"/>
```

For detailed instructions on how to implement a `DocumentationProvider`, please refer to the [Custom Language Support](documentation.md)
section and the descriptions in the [Custom Language Support Tutorial](documentation_provider.md).
