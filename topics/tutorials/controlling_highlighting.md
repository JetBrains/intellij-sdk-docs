# Controlling Highlighting

<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<link-summary>Suppressing highlighting in the editor programmatically.</link-summary>

The results of analyzing code by several mechanisms provided by the IntelliJ Platform ([Syntax errors](syntax_errors.md), Annotators, [Inspections](code_inspections.md)) are converted to highlighting information used to highlight the code in the editor.
However, in some contexts, the provided highlighting information is invalid or unnecessary.

Consider a tool that allows changing Java language's syntax by implicitly generating getters and setters for annotated fields during the build so that they can be omitted in class implementation:

```java
class Person {
    @GetterSetter
    private int age;
}

// usage:
person.setAge(47); // valid at runtime
```

Java support in IntelliJ IDEA would report the above setter usage as an unresolved code symbol.
The resulting error annotation would be valid from the Java language point of view but invalid in a project using such a tool.

Another case where highlighting code issues is unnecessary is old file revisions from VCS.
For example, the old version of a file could be created in a different project context, with other libraries configured.
If the old file version used the library that is not used by the project currently, it would cause reporting false-positive code issues.

The IntelliJ Platform exposes the extension point allowing a plugin to decide which highlighting information will be visible in the editor.
To do that, a plugin has to provide an implementation of [`HighlightInfoFilter`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/HighlightInfoFilter.java) and register it
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.daemon.highlightInfoFilter"/></include>.
It contains a single method `accept()`, which should return `true` if a given `HighlightInfo` should be visible in the editor and `false` to ignore it.

**Examples:**
- [`DebuggerHighlightFilter`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/evaluation/DebuggerHighlightFilter.java) disabling reporting unhandled exceptions in the debugger code editor
- [`LombokHighlightErrorFilter`](%gh-ic%/plugins/lombok/src/main/java/de/plushnikov/intellij/plugin/extension/LombokHighlightErrorFilter.java) disabling false-positive error reports in a project using Lombok

**See also:**
- [Controlling Syntax Errors Highlighting](syntax_errors.md#controlling-syntax-errors-highlighting)
