---
layout: editable
title: Plugin Development FAQ
---


This FAQ is a topical index of questions that have been asked (and answered) on our
[OpenAPI forum](http://intellij.net/forums/forum.jspa?forumID=23)

## Open-Source Plugins
*  [How do I compile the Scala plugin?](http://intellij.net/forums/thread.jspa?threadID=266848)

## Version Differences
*  [How do I replace my usage of Project.getProjectFile() in 7.0?](http://intellij.net/forums/thread.jspa?threadID=269447)
*  [How do I replace my usage of DataConstants with DataKeys in 7.0?](http://intellij.net/forums/thread.jspa?threadID=269493)
*  [How do I replace my usage of ToolWindowManager.registerToolWindow() in 7.0?](http://intellij.net/forums/thread.jspa?threadID=269001)
*  [How do I replace my usage of PsiSearchHelper.findReferences() in 7.0?](http://intellij.net/forums/thread.jspa?threadID=268094)
*  [How do I replace my usage of DelayedFileStatusProvider in 6.0?](http://www.intellij.net/forums/thread.jspa?threadID=213530)

## Action System
*  [How do I trigger actions programmatically?](http://intellij.net/forums/thread.jspa?threadID=268927&tstart=0)
*  [How do I add a main menu item?](http://intellij.net/forums/thread.jspa?threadID=267076)
*  [How do I customize the "New..." menu?](http://www.intellij.net/forums/thread.jspa?threadID=247161)
*  [How do I customize the compiler output context menu?](http://www.intellij.net/forums/thread.jspa?threadID=257558)
*  [How do I get the context of an action (selected file, active project etc.)?](http://www.intellij.net/forums/thread.jspa?threadID=234000)
*  [How do I change the icon of an action dynamically?](http://www.intellij.net/forums/thread.jspa?threadID=233379)
*  [How do I add icons to the IDEA toolbar?](http://intellij.net/forums/thread.jspa?threadID=148491)
*  [Where do I get the list of built-in action IDs?](http://intellij.net/forums/thread.jspa?threadID=162503)

## Accessing and Modifying the Source Code
*  [How do I find all subclasses of a class?](http://intellij.net/forums/thread.jspa?threadID=268652)
*  [How do I find all anonymous classes created in a class?](http://intellij.net/forums/thread.jspa?threadID=267506)
*  [How do I calculate the value of a string literal token?](http://intellij.net/forums/thread.jspa?threadID=267171)
*  [How do I rename or move a Java class?](http://www.intellij.net/forums/thread.jspa?threadID=268809)
*  [How do I build the list of all classes used by a given class or package?](http://intellij.net/forums/thread.jspa?threadID=267757)
*  [How do I insert whitespace into the PSI?](http://www.intellij.net/forums/thread.jspa?threadID=216585)
*  [How do I add properties to a .properties file?](http://www.intellij.net/forums/thread.jspa?threadID=220800)
*  [How do I find specific method calls inside a PsiMethod?](http://www.intellij.net/forums/thread.jspa?threadID=222157)
*  [What is the lifecycle of a PSI element?](http://www.intellij.net/forums/thread.jspa?threadID=251579)
*  [How do I find a file given its name (but no path)?](http://www.intellij.net/forums/thread.jspa?threadID=124081)
*  [How do I create a new class in the given package?](http://www.intellij.net/forums/thread.jspa?threadID=265094)
*  [How do I make a PsiClass extend another PsiClass?](http://www.intellij.net/forums/thread.jspa?threadID=263617)
*  [How do I find references to a class from non-Java files?](http://intellij.net/forums/thread.jspa?threadID=143947)
*  [How do I find the source file given the path to a .class file?](http://intellij.net/forums/thread.jspa?threadID=148446)
*  [How do I find classes with the specified non-qualified name?](http://intellij.net/forums/thread.jspa?threadID=156040)
*  [How do I find the module in which a class is located?](http://intellij.net/forums/thread.jspa?threadID=159258)

## Working with XML and XML DOM
*  [How do I change the value of an XML attribute through the PSI?](http://intellij.net/forums/thread.jspa?threadID=267398)
*  [How do I add custom references to Java elements in XML files?](http://www.intellij.net/forums/thread.jspa?threadID=196522)
*  [How do I programmatically register a DTD or schema?](http://www.intellij.net/forums/thread.jspa?threadID=256637)
*  [What is the "strict" parameter in DomElement.getParentOfType()?](http://intellij.net/forums/thread.jspa?threadID=269542)

## Code Completion
*  [How do I determine what type of code completion was invoked?](http://intellij.net/forums/thread.jspa?threadID=265400)
*  [How do I provide additional code completion in specific places of a Java file?](http://intellij.net/forums/thread.jspa?threadID=266818)

## Refactoring
*  [How can I receive notifications about refactoring events?](http://www.intellij.net/forums/thread.jspa?threadID=248923)
*  [How do I show a refactoring dialog programmatically?](http://intellij.net/forums/thread.jspa?threadID=162502)

## Run/Debug
*  [How do I implement a custom run configuration?](http://www.intellij.net/forums/thread.jspa?threadID=251054)

## Make/Compile
*  [How do I get access to class files generated by javac?](http://intellij.net/forums/thread.jspa?threadID=146746)

## Version Control Integration
*  [Can I provide line status markers for files in a custom file system?](http://intellij.net/forums/thread.jspa?threadID=269503)
*  [How do I update the state of VCS actions depending on file status?](http://intellij.net/forums/thread.jspa?threadID=268542)
*  [How can I find out the module of a deleted file?](http://intellij.net/forums/thread.jspa?threadID=267779)
*  [Can I provide additional decorations for changelists in the Changes view?](http://intellij.net/forums/thread.jspa?threadID=267462)
*  [How do I report out-of-date files?](http://www.intellij.net/forums/thread.jspa?threadID=269156)

## Test Framework
*  [How can I use the new test framework?](http://www.intellij.net/forums/thread.jspa?threadID=247156)
*  [How do I create a library dependency in my test module?](http://intellij.net/forums/thread.jspa?threadID=269495)

## Plugin Architecture
*  [Why are the extension elements in my plugin.xml red?](http://intellij.net/forums/thread.jspa?threadID=269517)
*  [How can I read the plugin descriptor of my plugin from code?](http://www.intellij.net/forums/thread.jspa?threadID=225115)
*  [How do I provide a custom exception reporter for my plugin?](http://www.intellij.net/forums/thread.jspa?threadID=264619)
*  [How can I add the help topics of my plugin to the IntelliJ IDEA help system?](http://www.intellij.net/forums/thread.jspa?threadID=261416)
*  [How do I get the version of IntelliJ IDEA under which my plugin is running?](http://intellij.net/forums/thread.jspa?threadID=156440)

## Editors, Documents and Files
*  [Why doesn't the file change on disk after I changed it through the PSI?](http://intellij.net/forums/thread.jspa?threadID=269379)
*  [Can I hook into the file save logic?](http://intellij.net/forums/thread.jspa?threadID=269246)
*  [Can I mark a part of a file as read-only?](http://intellij.net/forums/thread.jspa?threadID=267895)
*  [How do I control what happens when the user tries to edit such a part?](http://www.intellij.net/forums/thread.jspa?threadID=269406)
*  [How do I implement a custom editor?](http://www.intellij.net/forums/thread.jspa?threadID=214836)
*  [How can I show several editors for a single file in tabs?](http://www.intellij.net/forums/thread.jspa?threadID=256436)
*  [Can I open an editor which has no underlying file on disk?](http://intellij.net/forums/thread.jspa?threadID=267233)
*  [How do I save the content of my custom editor when the user saves all documents?](http://intellij.net/forums/thread.jspa?threadID=268178)
*  [How do I highlight elements in a source code editor?](http://www.intellij.net/forums/thread.jspa?threadID=215366)
*  [How do I allow to navigate between highlighted elements using Find Next?](http://www.intellij.net/forums/thread.jspa?threadID=215724)
*  [How do I force code to be re-analyzed?](http://www.intellij.net/forums/thread.jspa?threadID=224999)
*  [How do I get the active editor instance?](http://www.intellij.net/forums/thread.jspa?threadID=264626)
*  [How do I get the cursor position in the current editor?](http://www.intellij.net/forums/thread.jspa?threadID=263035)
*  [How do I clear the read-only status of a file?](http://www.intellij.net/forums/thread.jspa?threadID=260112)
*  [How do I show a popup hint in an editor?](http://intellij.net/forums/thread.jspa?threadID=138488)
*  [How do I create live template-like red box edit regions in an editor?](http://intellij.net/forums/thread.jspa?threadID=158355)
*  [How can I show an editor with error highlighting in a toolwindow?](http://intellij.net/forums/thread.jspa?threadID=158409)

## Inspections
*  [Can I build an inspection that processes XML files?](http://intellij.net/forums/message.jspa?messageID=5188503#5188503)
*  [Why are the inspection results shown multiple times?](http://www.intellij.net/forums/thread.jspa?threadID=256665)
*  [How can I provide a quick fix that creates a method?](http://www.intellij.net/forums/thread.jspa?threadID=253735)
*  [Is it possible to inspect only the elements that have been modified after the last full inspection?](http://intellij.net/forums/thread.jspa?threadID=146143)

## Project Structure
*  [Can I add a new module dependency storage format?](http://intellij.net/forums/thread.jspa?threadID=269362&tstart=0)
*  [What is the Pair to be passed to JavaModuleBuilder.setSourcePaths()?](http://www.intellij.net/forums/thread.jspa?threadID=247157)
*  [How do I access all configured JDKs?](http://www.intellij.net/forums/thread.jspa?threadID=262185)

## Custom Languages
*  [How do I provide Parameter Info support for my language?](http://intellij.net/forums/thread.jspa?threadID=264574)
*  [How do I provide auto-popup code completion in my language?](http://intellij.net/forums/thread.jspa?threadID=268106)
*  [How to make a closing brace unindent?](http://www.intellij.net/forums/thread.jspa?threadID=213765)
*  [How to automatically insert closing quotes?](http://www.intellij.net/forums/thread.jspa?threadID=212228)
*  [How do I provide Ctrl+mouse popups for my language?](http://www.intellij.net/forums/thread.jspa?threadID=215354)
*  [How do I enable debugging for my custom language which is compiled into Java?](http://www.intellij.net/forums/thread.jspa?threadID=220451)
*  [How do I generate virtual Java classes mirroring the classes of my language?](http://www.intellij.net/forums/thread.jspa?threadID=220280)

## User Interface
*  [How do I provide animated status bar notifications?](http://intellij.net/forums/thread.jspa?threadID=268421)
*  [How do I enable file name completion in a combobox?](http://intellij.net/forums/thread.jspa?threadID=267558)
*  [How do I show a popup with left-aligned and right-aligned parts for each item?](http://intellij.net/forums/thread.jspa?threadID=269169)
*  [Can I use an embedded Web browser from my plugin?](http://intellij.net/forums/thread.jspa?threadID=267280)
*  [How do I provide a custom icon for files/PSI elements?](http://www.intellij.net/forums/thread.jspa?threadID=215468)
*  [Can I show a progress indicator for WriteActions?](http://www.intellij.net/forums/thread.jspa?threadID=245819)
*  [How do I make it possible to search the options of my plugin in the Settings dialog?](http://www.intellij.net/forums/thread.jspa?threadID=204909)
*  [How do I show a custom window or popup based on Structure View?](http://www.intellij.net/forums/thread.jspa?threadID=255255)
*  [Is it possible to extend the Project View tree?](http://www.intellij.net/forums/thread.jspa?threadID=263998)
*  [How do I show the "Project Structure" dialog programmatically?](http://www.intellij.net/forums/thread.jspa?threadID=264241)
*  [How do I print messages in the console view?](http://www.intellij.net/forums/thread.jspa?threadID=263776)
*  [How do I show the package selector dialog programmatically?](http://www.intellij.net/forums/thread.jspa?threadID=260537)
*  [How do I provide syntax and error highlighting in a combo box editor?](http://intellij.net/forums/thread.jspa?threadID=150616)
*  [How can I get notified when my toolwindow is activated?](http://intellij.net/forums/thread.jspa?threadID=153237)
*  [How can I provide Close and Rerun buttons in my Usage View window?](http://intellij.net/forums/thread.jspa?threadID=155452)

## General
*  [How do I get the currently active project outside of an AnAction?](http://intellij.net/forums/thread.jspa?threadID=267876)
*  [How do I detect when a project is closing?](http://intellij.net/forums/thread.jspa?threadID=267877)
*  [How can I implement a custom stack trace analyzer?](http://www.intellij.net/forums/thread.jspa?threadID=252590)
*  [Where is the state of an ApplicationComponent stored?](http://www.intellij.net/forums/thread.jspa?threadID=264364)
*  [How do I open a project programmatically?](http://intellij.net/forums/thread.jspa?threadID=150233)
*  [How do I get the folder of the currently selected file?](http://intellij.net/forums/thread.jspa?threadID=156790)
*  [How do I encrypt some values in the configuration data of my plugin?](http://intellij.net/forums/thread.jspa?threadID=148273)
*  [How can I track exceptions from my plugin?](https://devnet.jetbrains.com/message/5527276)