---
title: Documentation
---

To provide different kinds of documentation support (tooltips on **Ctrl-hover**, quick documentation popup etc.), the plugin needs to provide an implementation of the
[DocumentationProvider](upsource:///platform/lang-api/src/com/intellij/lang/documentation/DocumentationProvider.java)
interface and register it in the `lang.documentationProvider` extension point.
A standard base class for such implementations is available in the class
[AbstractDocumentationProvider](upsource:///platform/lang-api/src/com/intellij/lang/documentation/AbstractDocumentationProvider.java).

**Example**:
[DocumentationProvider](upsource:///plugins/properties/src/com/intellij/lang/properties/PropertiesDocumentationProvider.java)
for
[Properties language plugin](upsource:///plugins/properties/)


The `getQuickNavigateInfo()` method returns the text to be displayed when the user holds the mouse over an element with `Ctrl` pressed.
