[//]: # (title: Documentation)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)
helps users by showing documentation, e.g., for classes, functions, or methods inside the editor.
Plugin authors implement
[`DocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
to show documentation for particular PSI elements.

Implementations of `DocumentationProvider` can be registered either at the `com.intellij.documentationProvider` or the
`com.intellij.lang.documentationProvider` extension point (EP).
It is recommended to use the latter one when creating documentation that targets a specific language because providers registered
as `com.intellij.lang.documentationProvider` will only be called for elements from that language.
This is the reason they require the `language` attribute when registering the EP in the <path>plugin.xml</path>.
The bigger picture here is that documentation providers co-exist and if there is more than one provider for the same element,
the first one that returns a value different from `null` wins.

Although discouraged, the ordering of documentation providers can be influenced by using the `order` attribute when registering the extension.
For instance, [`python-core-common.xml`](upsource:///python/src/META-INF/python-core-common.xml) uses the following to call the external documentation
provider before the default one:

```xml
<lang.documentationProvider
  language="Python"
  implementationClass="com.jetbrains.python.documentation.PythonExternalDocumentationProvider"
  order="before pythonDocumentationProvider"/>
```

For detailed instructions on how to implement a `DocumentationProvider`, please refer to the [Custom Language Support](documentation_provider.md)
section and the descriptions in the [Custom Language Support Tutorial](documentation.md).

  implementationClass="com.jetbrains.python.documentation.PythonExternalDoc
