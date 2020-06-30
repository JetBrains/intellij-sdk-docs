---
title: Plugin Development FAQ
redirect_from:
  - /faq.html
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This FAQ is a topical index of questions that have been asked (and answered) on our
[IntelliJ IDEA Open API and Plugin Development forum](https://intellij-support.jetbrains.com/hc/en-us/community/topics/200366979-IntelliJ-IDEA-Open-API-and-Plugin-Development).

## Open-Source Plugins
*  [How do I compile the Scala plugin?](https://github.com/jetbrains/intellij-scala#setting-up-the-project)

## Unresolved Classes after Upgrading to 2019.2 or later
*  [Java functionality extracted as a plugin](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/)

## Action System
*  [How do I trigger actions programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206130119-Triggering-AnAction-instances-)
*  [How do I add a main menu item?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206750335-Add-new-Main-menu-option-for-plugin)
*  [How do I customize the "New..." menu?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206765055-Overriding-the-New-context-menu-in-the-Project-view)
*  [How do I customize the compiler output context menu?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142169-How-to-add-a-menu-item-below-Exclude-From-Compile-)
*  [How do I get the context of an action (selected file, active project etc.)?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206756455-Question-about-Actions)
*  [How do I change the icon of an action dynamically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206763405-How-to-dynamically-change-icons-in-the-tool-bar-)
*  [How do I add icons to the IDEA toolbar?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206151289-How-to-add-icons-to-the-toolbar-)
*  [Where do I get the list of built-in action IDs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206126699-List-of-built-in-action-ID-s-)

## Accessing and Modifying the Source Code
*  [PSI Architectural Overview](/basics/architectural_overview/psi.md)
*  [How do I find all subclasses of a class?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791895-finding-all-derived-class-given-parent-class)
*  [How do I find all anonymous classes created in a class?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206792205-How-to-find-anonymous-classes-in-PsiClass-)
*  [How do I calculate the value of a string literal token?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139829-How-to-evaluate-the-value-of-PsiJavaToken-of-STRING-LITERAL-type)
*  [How do I rename or move a Java class?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791825-How-to-rename-a-class-)
*  [How do I build the list of all classes used by a given class or package?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139469-Using-DependencyValidationManager-to-Get-Required-Classes)
*  [How do I insert whitespace into the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143839-Adding-PsiElements-to-a-PsiFile)
*  [How do I add properties to a .properties file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142279-Dynamically-add-new-properties-to-properties-files)
*  [How do I find specific method calls inside a PsiMethod?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143579-finding-a-statement-within-a-PsiMethod)
*  [What is the lifecycle of a PSI element?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206796015-What-is-the-lifecycle-of-a-PsiElement-)
*  [How do I find a file given its name (but no path)?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206768795-How-to-search-file-by-file-name-in-project-s-root-directory-)
*  [How do I create a new class in the given package?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206771665-Creating-a-new-class)
*  [How do I make a PsiClass extend another PsiClass?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794255-How-to-make-a-PsiClass-derive-from-another-one-)
*  [How do I find references to a class from non-Java files?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800695-How-to-obtain-the-references-to-a-class-from-non-java-files-)
*  [How do I find the source file given the path to a .class file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800595-How-to-find-the-source-for-a-class-file)
*  [How do I find classes with the specified non-qualified name?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146759-How-to-resolve-unqualified-name-to-possible-PsiClasses-)
*  [How do I find the module in which a class is located?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206103859-How-to-get-Module-from-PsiClass-)
*  [PSI Cookbook](/basics/psi_cookbook.md)

## Working with XML and XML DOM
*  [How do I change the value of an XML attribute through the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139639-Change-xml-attribute-value)
*  [How do I add custom references to Java elements in XML files?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795875-XmlTagValue-reference-to-Java-methods)
*  [How do I programmatically register a DTD or schema?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795425-How-to-register-DTD-with-idea)
*  [What is the "strict" parameter in DomElement.getParentOfType()?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791535-DOM-DomElement-getParentOfType)

## Code Completion
*  [How do I determine what type of code completion was invoked?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206133529-How-to-determine-what-type-of-code-completion-was-invoked)
*  [How do I provide additional code completion in specific places of a Java file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139729-Custom-completion-in-editor)

## Refactoring
*  [How can I receive notifications about refactoring events?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795955-Refactoring-Listeners)
*  [How do I show a refactoring dialog programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800005-How-to-invoke-refactoring-dialog-not-refactoring-itself-)

## Run/Debug
*  [How do I implement a custom run configuration?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143009-Creating-a-Run-type-)

## Make/Compile
*  [How do I get access to class files generated by javac?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800625-Implementing-a-ClassInstrumentingCompiler-how-to-get-the-generated-class-files)

## Version Control Integration
*  [Can I provide line status markers for files in a custom file system?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791585-Editor-diff-functionality-for-custom-file-system)
*  [How do I update the state of VCS actions depending on file status?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791975-VCS-context-menu)
*  [How can I find out the module of a deleted file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206792195-Module-for-deleted-file-)
*  [Can I provide additional decorations for changelists in the Changes view?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139549-Is-it-possible-to-decorate-change-lists-)
*  [How do I report out-of-date files?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791775-VCS-OpenAPI-what-to-do-with-files-detected-as-out-of-date-)

## Test Framework
*  [How do I create a library dependency in my test module?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791555-library-depndency-in-InspectionTestCase)

## Plugin Architecture
*  [How do I provide a custom exception reporter for my plugin?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206793965-Custom-exception-reporting)
*  [How can I add the help topics of my plugin to the IntelliJ Platform help system?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206760095-how-do-i-plug-into-the-main-idea-help-system-)
*  [How do I get the version of IntelliJ Platform under which my plugin is running?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800275-How-to-get-the-idea-version)

## Editors, Documents and Files
*  [Why doesn't the file change on disk after I changed it through the PSI?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791625-Action-doesn-t-see-changes-in-xml-file)
*  [Can I hook into the file save logic?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206790685-Can-you-tie-into-the-file-save-logic-)
*  [Can I mark a part of a file as read-only?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/207042355-Read-only-section-in-editor)
*  [How do I control what happens when the user tries to edit such a part?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791375-Using-locked-regions)
*  [How do I implement a custom editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143969-Rough-guide-to-xml-gui-editor-type-plugin-)
*  [How can I show several editors for a single file in tabs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206795495-Alternative-Editors-ala-HTML-Preview)
*  [Can I open an editor which has no underlying file on disk?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206135449-Create-an-Editor-for-a-non-physical-file)
*  [How do I save the content of my custom editor when the user saves all documents?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206792085-Catching-the-Save-All-action)
*  [How do I highlight elements in a source code editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143909-MarkupModel-navigate-highlighted-elements)
*  [How do I allow to navigate between highlighted elements using Find Next?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143879-HighlightManager-how-to-enable-F3-functionality)
*  [How do I force code to be re-analyzed?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143679-Forcing-an-annotator-to-update-status-of-a-file)
*  [How do I get the active editor instance?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141119-how-to-get-the-Editor-from-PsiElement-)
*  [How do I get the cursor position in the current editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794335-How-to-get-cursor-position-in-the-current-editor-)
*  [How do I clear the read-only status of a file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142039-Clear-read-only-status)
*  [How do I show a popup hint in an editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146719-HintManager-API-question)
*  [How do I create live template-like red box edit regions in an editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800165-How-to-%C3%A7reate-live-template-like-red-box-edit-regions-in-an-editor)
*  [How can I show an editor with error highlighting in a tool window?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146679-Error-highlighting-in-Editors)

## Inspections
*  [Can I build an inspection that processes XML files?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139579-LocalInspectionTool-for-XML-files-/comments/206204765)
*  [Why are the inspection results shown multiple times?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142489-visitXmlAttribute-question)
*  [How can I provide a quick fix that creates a method?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142769-Triggering-Create-Method-intention)
*  [Is it possible to inspect only the elements that have been modified after the last full inspection?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800645-How-to-inspect-only-the-elements-modified-since-the-last-class-inspection)
*  [ExternalAnnotator not in sync with current editor](https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000337510-Only-trigger-externalAnnotator-when-the-file-system-is-in-sync)

## Project Structure
*  [Can I add a new module dependency storage format?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206137859-Dependency-storage-formats-)
*  [What is the Pair to be passed to JavaModuleBuilder.setSourcePaths()?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143559-Usage-of-class-Pair-A-B-)
*  [How do I access all configured JDKs?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141569-selecting-a-configured-jdk)

## Custom Languages
*  [How do I provide Parameter Info support for my language?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791995-Parameter-Info)
*  [How do I provide auto-popup code completion in my language?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139359-Autopopup-code-completion-in-custom-language)
*  [How to make a closing brace unindent?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206797085-Custom-Language-How-to-make-a-closing-brace-unindent-)
*  [How to automatically insert closing quotes?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206144059-How-the-insertion-of-closing-quote-works-in-Javascript-plugin-)
*  [How do I provide Ctrl+mouse popups for my language?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206797015-Ctrl-mouse)
*  [How do I enable debugging for my custom language which is compiled into Java?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206786875-Debugging-custom-languages-)
*  [How do I generate virtual Java classes mirroring the classes of my language?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143749-Custom-languages-masquarding-as-a-java-source-file-within-IntelliJ)

## User Interface
*  [How do I provide animated status bar notifications?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206791945-IDE-Notifications)
*  [How do I enable file name completion in a combobox?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139509-Combobox-with-Browse-Button-and-Autocompletion-)
*  [How do I show a popup with left-aligned and right-aligned parts for each item?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139049-popup-menu-with-left-and-right-aligned-items)
*  [Can I use an embedded Web browser from my plugin?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206351479-Using-Browser-Component)
*  [How do I provide a custom icon for files/PSI elements?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143779-Is-it-possible-to-change-icon-of-file-in-Project-view-)
*  [Can I show a progress indicator for WriteActions?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206139159-WriteActions-and-ProgressIndicator)
*  [How do I make it possible to search the options of my plugin in the Settings dialog?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206143999-DEMETRA-how-to-contribute-to-searchable-dialog-options-)
*  [How do I show a custom window or popup based on Structure View?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142679-Opening-a-customised-StructureView)
*  [Is it possible to extend the Project View tree?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141229-is-it-possible-to-extend-the-project-tree-)
*  [How do I show the "Project Structure" dialog programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141379-Showing-Project-Strucuture-dialog-programmatically)
*  [How do I print messages in the console view?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206141419-Putting-messages-into-console-window-)
*  [How do I show the package selector dialog programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794265-Package-selector-dialog)
*  [How do I provide syntax and error highlighting in a combo box editor?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800495-EditorTextField-in-3403-How-to-get-an-Editor-that-does-error-highlighting-)
*  [How can I get notified when my tool window is activated?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206800405-How-can-i-run-some-code-when-a-ToolWindow-activates)
*  [How can I provide Close and Rerun buttons in my Usage View window?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146779-How-to-get-a-Close-button-in-an-own-Usage-View-)
*  [How can I display the SDK list in a JComboBox?](https://stackoverflow.com/questions/51499884/how-to-display-the-sdk-list-in-a-jcombobox)

## General
*  [How do I get the currently active project outside of an AnAction?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206763335-Getting-active-project-)
*  [How do I detect when a project is closing?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206792155-Detecting-frame-project-closing)
*  [How can I implement a custom stack trace analyzer?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206142959-Stack-Analyzer-extension)
*  [Where is the state of an ApplicationComponent stored?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206794095-Where-is-ApplicationComponent-state-stored-in-)
*  [How do I open a project programmatically?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206146969-how-to-open-a-project-)
*  [How do I get the folder of the currently selected file?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206121889-How-to-get-the-folder-of-currenctly-selected-file)
*  [How do I encrypt some values in the configuration data of my plugin?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206147039-JDOMExternalizable-and-encrypting-)
*  [How can I track exceptions from my plugin?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206762245-How-can-I-track-plugin-s-exceptions-/comments/206112345)
