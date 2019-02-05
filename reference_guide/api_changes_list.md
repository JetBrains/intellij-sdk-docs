---
title: Incompatible Changes in IntelliJ Platform and Plugins API
---

<!--
Before documenting a breaking API change, please, make sure that the change cannot be avoided 
in an alternative way as the following docs state:

API Compatibility policy: https://confluence.jetbrains.com/display/IDEA/IntelliJ+Platform+API+compatibility+policy
API Compatibility FAQ:    https://confluence.jetbrains.com/display/IDEA/IntelliJ+API+Compatibility+Policy+FAQ

To document a new incompatible change, you have to fill a row in a table so that
the first column is a problem pattern and the second column is a human-readable description.

The following problem patterns are supported:

<package name> package removed
<class name> class removed
<class name> renamed to <new class name>

<class name>.<method name> method removed
<class name>.<method name> method return type changed from <before> to <after>
<class name>.<method name> method parameter <type> removed
<class name>.<method name> method parameter type changed from <before> to <after>
<class name>.<method name> method visibility changed from <before> to <after>

<class name> constructor removed
<class name> constructor parameter <type> removed
<class name> constructor parameter type changed from <before> to <after>
<class name> constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<class name>.<method name> abstract method added
<class name> class moved to package <package name>

where <class name> is a fully-qualified name of the class, e.g. com.intellij.openapi.actionSystem.AnAction$InnerClass.

NOTE: If a change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack. 
An example of a ticket is https://youtrack.jetbrains.com/issue/PR-1218. Until supported, you may document the change as you prefer, and I will correct it later.

NOTE: You are allowed to prettify the pattern using markdown-features:
 1) code quotes: `org.example.Foo.methodName`
 2) links [org.example.Foo](upsource:///platform/core-api/src/org/example/Foo)
 3) both code quotes and links: [`org.example.Foo`](upsource:///platform/core-api/src/org/example/Foo)
-->

IntelliJ API may be occasionally changed between releases leading to 
incompatibilities of existing plugins with new IDEs' builds. 
The following pages list the breaking changes in IDE releases.  
  
* [**Changes in 2019.***](api_changes/api_changes_list_2019.md)
* [**Changes in 2018.***](api_changes/api_changes_list_2018.md)
* [**Changes in 2017.***](api_changes/api_changes_list_2017.md)
* [**Changes in 2016.***](api_changes/api_changes_list_2016.md)
