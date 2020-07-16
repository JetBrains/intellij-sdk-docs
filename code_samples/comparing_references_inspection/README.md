# Comparing References Inspection [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
Reference: [Code Inspections in SDK Docs][docs:code_inspections]

## Quickstart

Comparing References Inspection code sample project demonstrates the implementation
of the [Code Inspections][docs:code_inspections] feature for Java classes. 

The plugin inspects your Java code and highlights the fragments containing the comparison of the two `String` or `Date`
variables. If such check uses `==` or `!=` expression, an inspection provides a *quick fix* action that proposes
to replace it with `.equals()` instead. I.e., detected object references compared using `a == b` or `a != b` will be
converted using *Quick Fix* to `a.equals(b)` or `!a.equals(b)` respectively.

## Structure

Comparing References Inspection plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:pluginxml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name            | Implementation Class                                                | Interface                          |
| --------------- | ------------------------------------------------------------------- | ---------------------------------- |
| localInspection | [ComparingReferencesInspection][file:ComparingReferencesInspection] | [AbstractBJLIT][sdk:AbstractBJLIT] |

[Extension Points documentation][docs:ep]

[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:code_inspections]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/code_inspections.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:ComparingReferencesInspection]: ./src/main/java/org/intellij/sdk/codeInspection/ComparingReferencesInspection.java
[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml

[sdk:AbstractBJLIT]: https://github.com/JetBrains/intellij-community/blob/master/java/java-analysis-api/src/com/intellij/codeInspection/AbstractBJLIT.java

