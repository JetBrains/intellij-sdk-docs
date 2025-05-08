<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Development FAQ

<link-summary>Miscellaneous frequently asked questions.</link-summary>

This page contains miscellaneous FAQs. See [](getting_help.topic#problems-with-code-support-issues) on how to post new questions.

> Other common FAQs:
> - [](ide_infrastructure.md)
> - [](ui_faq.md)
> - [](testing_faq.md)
> - [](tools_intellij_platform_gradle_plugin_faq.md)
>
> See also [](explore_api.md) for more information and strategies.
>

## Working with XML and XML DOM

> See also [](xml_dom_api.md).

### How do I register a DTD or XSD?

To register a bundled DTD/XSD file, use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.standardResource"/></include>
for static
or <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.standardResourceProvider"/></include>
for programmatic registration.

## Refactoring

### How can I receive notifications about refactoring events?

[`RefactoringEventListener`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/listeners/RefactoringEventListener.java)

### How do I show a refactoring dialog programmatically?

[`RefactoringActionHandlerFactory`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/RefactoringActionHandlerFactory.java)

## Editors, Documents and Files

* [How can I show several editors for a single file in tabs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795495-Alternative-Editors-ala-HTML-Preview)
