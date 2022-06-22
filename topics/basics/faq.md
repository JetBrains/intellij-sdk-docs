[//]: # (title: Plugin Development FAQ)

<!-- Copyright 2000-202 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This FAQ is a topical index of questions that have been asked (and answered) on our [IntelliJ IDEA Open API and Plugin Development forum](https://intellij-support.jetbrains.com/hc/en-us/community/topics/200366979-IntelliJ-IDEA-Open-API-and-Plugin-Development).

> See also [Explore the IntelliJ Platform API](explore_api.md) for more information and strategies.
>
{type="tip"}

## Unresolved Classes after Upgrading to 2019.2 or later
*  [Java functionality extracted as a plugin](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/)

## Action System
*  [How do I trigger actions programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206130119-Triggering-AnAction-instances-)
*  [Where do I get the list of built-in action IDs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206126699-List-of-built-in-action-ID-s-)

## Accessing and Modifying the Source Code

> See also [](psi.md) and [](psi_cookbook.md)

* [How do I find all subclasses of a class?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791895-finding-all-derived-class-given-parent-class)
* [How do I find all anonymous classes created in a class?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206792205-How-to-find-anonymous-classes-in-PsiClass-)
* [How do I calculate the value of a string literal token?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139829-How-to-evaluate-the-value-of-PsiJavaToken-of-STRING-LITERAL-type)
* [How do I rename or move a Java class?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791825-How-to-rename-a-class-)
* [How do I build the list of all classes used by a given class or package?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139469-Using-DependencyValidationManager-to-Get-Required-Classes)
* [How do I insert whitespace into the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143839-Adding-PsiElements-to-a-PsiFile)
* [How do I add properties to a .properties file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142279-Dynamically-add-new-properties-to-properties-files)
* [How do I find specific method calls inside a PsiMethod?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143579-finding-a-statement-within-a-PsiMethod)
* [What is the lifecycle of a PSI element?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206796015-What-is-the-lifecycle-of-a-PsiElement-)
* [How do I find a file given its name (but no path)?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206768795-How-to-search-file-by-file-name-in-project-s-root-directory-)
* [How do I create a new class in the given package?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206771665-Creating-a-new-class)
* [How do I make a PsiClass extend another PsiClass?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794255-How-to-make-a-PsiClass-derive-from-another-one-)
* [How do I find references to a class from non-Java files?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800695-How-to-obtain-the-references-to-a-class-from-non-java-files-)
* [How do I find the source file given the path to a .class file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800595-How-to-find-the-source-for-a-class-file)
* [How do I find classes with the specified non-qualified name?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146759-How-to-resolve-unqualified-name-to-possible-PsiClasses-)

## Working with XML and XML DOM
*  [How do I change the value of an XML attribute through the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139639-Change-xml-attribute-value)
*  [How do I programmatically register a DTD or schema?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795425-How-to-register-DTD-with-idea)

## Code Completion
*  [How do I determine what type of code completion was invoked?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206133529-How-to-determine-what-type-of-code-completion-was-invoked)
*  [How do I provide additional code completion in specific places of a Java file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139729-Custom-completion-in-editor)

## Refactoring
*  [How can I receive notifications about refactoring events?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795955-Refactoring-Listeners)
*  [How do I show a refactoring dialog programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800005-How-to-invoke-refactoring-dialog-not-refactoring-itself-)

## Make/Compile
*  [How do I get access to class files generated by javac?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800625-Implementing-a-ClassInstrumentingCompiler-how-to-get-the-generated-class-files)

## Version Control Integration
*  [Can I provide line status markers for files in a custom file system?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791585-Editor-diff-functionality-for-custom-file-system)
*  [How do I update the state of VCS actions depending on file status?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791975-VCS-context-menu)
*  [How can I find out the module of a deleted file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206792195-Module-for-deleted-file-)
*  [Can I provide additional decorations for changelists in the Changes view?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139549-Is-it-possible-to-decorate-change-lists-)
*  [How do I report out-of-date files?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791775-VCS-OpenAPI-what-to-do-with-files-detected-as-out-of-date-)

## Editors, Documents and Files
*  [Why doesn't the file change on disk after I changed it through the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791625-Action-doesn-t-see-changes-in-xml-file)
*  [Can I hook into the file save logic?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206790685-Can-you-tie-into-the-file-save-logic-)
*  [Can I mark a part of a file as read-only?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/207042355-Read-only-section-in-editor)
*  [How do I control what happens when the user tries to edit such a part?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791375-Using-locked-regions)
*  [How can I show several editors for a single file in tabs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795495-Alternative-Editors-ala-HTML-Preview)
*  [Can I open an editor which has no underlying file on disk?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206135449-Create-an-Editor-for-a-non-physical-file)
*  [How do I highlight elements in a source code editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143909-MarkupModel-navigate-highlighted-elements)
*  [How do I allow to navigate between highlighted elements using Find Next?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143879-HighlightManager-how-to-enable-F3-functionality)
*  [How do I get the active editor instance?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141119-how-to-get-the-Editor-from-PsiElement-)
*  [How do I get the cursor position in the current editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794335-How-to-get-cursor-position-in-the-current-editor-)
*  [How do I clear the read-only status of a file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142039-Clear-read-only-status)
*  [How can I show an editor with error highlighting in a tool window?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146679-Error-highlighting-in-Editors)

## Inspections
*  [How can I provide a quick fix that creates a method?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142769-Triggering-Create-Method-intention)
*  [Is it possible to inspect only the elements that have been modified after the last full inspection?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800645-How-to-inspect-only-the-elements-modified-since-the-last-class-inspection)
*  [ExternalAnnotator not in sync with current editor](https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000337510-Only-trigger-externalAnnotator-when-the-file-system-is-in-sync)

## Project Structure
*  [Can I add a new module dependency storage format?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206137859-Dependency-storage-formats-)
*  [What is the Pair to be passed to JavaModuleBuilder.setSourcePaths()?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143559-Usage-of-class-Pair-A-B-)

## Custom Languages
*  [How do I provide auto-popup code completion in my language?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139359-Autopopup-code-completion-in-custom-language)
*  [How to make a closing brace unindent?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206797085-Custom-Language-How-to-make-a-closing-brace-unindent-)
*  [How do I enable debugging for my custom language which is compiled into Java?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206786875-Debugging-custom-languages-)
*  [How do I generate virtual Java classes mirroring the classes of my language?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143749-Custom-languages-masquarding-as-a-java-source-file-within-IntelliJ)

## User Interface
*  [How do I provide animated status bar notifications?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791945-IDE-Notifications)
*  [How do I enable file name completion in a combo box?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139509-Combobox-with-Browse-Button-and-Autocompletion-)
*  [How do I show a popup with left-aligned and right-aligned parts for each item?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139049-popup-menu-with-left-and-right-aligned-items)
*  [How do I provide a custom icon for files/PSI elements?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143779-Is-it-possible-to-change-icon-of-file-in-Project-view-)
*  [Can I show a progress indicator for WriteActions?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139159-WriteActions-and-ProgressIndicator)
*  [How do I show a custom window or popup based on Structure View?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142679-Opening-a-customised-StructureView)
*  [How do I print messages in the console view?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141419-Putting-messages-into-console-window-)
*  [How do I show the package selector dialog programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794265-Package-selector-dialog)
*  [How do I provide syntax and error highlighting in a combo box editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800495-EditorTextField-in-3403-How-to-get-an-Editor-that-does-error-highlighting-)
*  [How can I provide Close and Rerun buttons in my Usage View window?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146779-How-to-get-a-Close-button-in-an-own-Usage-View-)
*  [How can I display the SDK list in a JComboBox?](https://stackoverflow.com/questions/51499884/how-to-display-the-sdk-list-in-a-jcombobox)

## General
*  [How can I implement a custom stack trace analyzer?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142959-Stack-Analyzer-extension)
*  [Where is the state of an ApplicationComponent stored?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794095-Where-is-ApplicationComponent-state-stored-in-)
*  [How do I open a project programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146969-how-to-open-a-project-)
*  [How do I get the folder of the currently selected file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206121889-How-to-get-the-folder-of-currenctly-selected-file)
