[//]: # (title: Documentation)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)
helps users by showing documentation, e.g., for classes, functions, or methods inside the editor.
Plugin authors implement
[`DocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
to show documentation for particular Psi elements.

`DocumentationProvider` can be used for existing languages as well as for a custom language implementation.
For an existing language, the implementation of `DocumentationProvider` is registered as `com.intellij.documentationProvider` extension point (EP),
while custom languages should register it as `com.intellij.lang.documentationProvider` EP.
The difference between both is that the latter requires the `language` attribute when registering the EP in the `plugin.xml` and is only called
for this particular language.

Several documentation providers can co-exist for the same language, and plugin authors can implement additional ones even for languages not under
their control.
If there is more than one provider for the same element, then the first one that returns a value different from `null` wins.
Although discouraged, the ordering of documentation providers can be influenced by using the `order` attribute when registering the extension.
For instance, [`python-core-common.xml`](upsource:///python/src/META-INF/python-core-common.xml) uses the following to call the external documentation
provider before the default one:

```xml
<lang.documentationProvider
  language="Python"
  implementationClass="com.jetbrains.python.documentation.PythonExternalDocumentationProvider"
  order="before pythonDocumentationProvider"
/>
```

For detailed instructions on how to implement a `DocumentationProvider`, please refer to the [Custom Language Support](documentation_provider.md)
section and the descriptions in the [Custom Language Support Tutorial](documentation.md).
