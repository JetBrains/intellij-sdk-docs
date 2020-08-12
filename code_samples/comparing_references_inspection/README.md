# Comparing References Inspection Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Inspections in IntelliJ SDK Docs][docs:code_inspections]*

## Quickstart

Comparing References Inspection Sample demonstrates the implementation
of the [Code Inspections][docs:code_inspections] feature for Java classes. 

The plugin inspects your Java code and highlights any fragments containing the comparison of two `String` or `Date`
variables. If such a check finds a comparison using the `==` or !`=` operators instead of the `.equals()` method,
the plugin proposes a *quick-fix* action.

### Extension Points

| Name                           | Implementation                                                      | Extension Point Class                                    |
| ------------------------------ | ------------------------------------------------------------------- | -------------------------------------------------------- |
| `com.intellij.localInspection` | [ComparingReferencesInspection][file:ComparingReferencesInspection] | [AbstractBaseJavaLocalInspectionTool][sdk:AbstractBJLIT] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:code_inspections]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/code_inspections.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:ComparingReferencesInspection]: ./src/main/java/org/intellij/sdk/codeInspection/ComparingReferencesInspection.java

[sdk:AbstractBJLIT]: upsource:///java/java-analysis-api/src/com/intellij/codeInspection/AbstractBaseJavaLocalInspectionTool.java
