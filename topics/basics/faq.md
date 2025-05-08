<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Development FAQ

<link-summary>List of plugin development forum topics with frequently asked questions.</link-summary>

This FAQ is a topical index of questions that have been asked (and answered) on the archived
[IntelliJ IDEA Open API and Plugin Development forum](https://intellij-support.jetbrains.com/hc/en-us/community/topics/200366979-IntelliJ-IDEA-Open-API-and-Plugin-Development).
See [](getting_help.topic#problems-with-code-support-issues) on how to post new questions.

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

* [Why doesn't the file change on disk after I changed it through the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791625-Action-doesn-t-see-changes-in-xml-file)
* [Can I hook into the file save logic?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206790685-Can-you-tie-into-the-file-save-logic-)
* [How do I control what happens when the user tries to edit such a part?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791375-Using-locked-regions)
* [How can I show several editors for a single file in tabs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795495-Alternative-Editors-ala-HTML-Preview)
* [Can I open an editor which has no underlying file on disk?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206135449-Create-an-Editor-for-a-non-physical-file)
* [How do I highlight elements in a source code editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143909-MarkupModel-navigate-highlighted-elements)
* [How do I allow to navigate between highlighted elements using Find Next?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143879-HighlightManager-how-to-enable-F3-functionality)
* [How do I get the active editor instance?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141119-how-to-get-the-Editor-from-PsiElement-)
* [How do I clear the read-only status of a file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142039-Clear-read-only-status)
* [How can I show an editor with error highlighting in a tool window?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146679-Error-highlighting-in-Editors)

## General

* [How can I implement a custom stack trace analyzer?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142959-Stack-Analyzer-extension)
* [How do I open a project programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146969-how-to-open-a-project-)
* [How do I get the folder of the currently selected file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206121889-How-to-get-the-folder-of-currenctly-selected-file)
