[//]: # (title: Documentation)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[Quick Documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)
helps users by showing documentation for symbols like method calls inside the editor.
Plugin authors implement
[`DocumentationProvider`](upsource:///platform/analysis-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
to show documentation for particular Psi elements.

`DocumentationProvider` can be used for existing languages as well as for a custom language implementation.
For an existing language, the implementation of `DocumentationProvider` is registered as `com.intellij.documentationProvider` extension point (EP),
while custom languages should register it as `com.intellij.lang.documentationProvider` EP.
For details on how to implement a `DocumentationProvider`, please refer to the [Custom Language Support](documentation_provider.md)
section and the descriptions in the [Custom Language Support Tutorial](documentation.md).
