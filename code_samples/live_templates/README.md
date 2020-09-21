# Live Templates Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Live Templates in IntelliJ SDK Docs][docs:live_templates]*

## Quickstart

Live Templates Sample Project implements two example live templates for the Markdown language:

- New link reference - by typing the `{<TAB>`, the following template will be inserted: `[$TEXT$]($LINK$)$END$`
- Convert to title case - retrieves the text from the macro or selection, if available.

### Extension Points

| Name                                | Implementation                          | Extension Point Class                          |
| ----------------------------------- | --------------------------------------- | ---------------------------------------------- |
| `com.intellij.defaultLiveTemplates` | [Markdown][file:Markdown]               |                                                |
| `com.intellij.liveTemplateContext`  | [MarkdownContext][file:MarkdownContext] | [TemplateContextType][sdk:TemplateContextType] |
| `com.intellij.liveTemplateMacro`    | [TitleCaseMacro][file:TitleCaseMacro]   | [MacroBase][sdk:MacroBase]                     |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:live_templates]: https://jetbrains.org/intellij/sdk/docs/tutorials/live_templates.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:Markdown]: ./src/main/resources/liveTemplates/Markdown.xml
[file:MarkdownContext]: ./src/main/java/org/intellij/sdk/liveTemplates/MarkdownContext.java
[file:TitleCaseMacro]: ./src/main/java/org/intellij/sdk/liveTemplates/TitleCaseMacro.java

[sdk:TemplateContextType]: upsource:///platform/lang-api/src/com/intellij/codeInsight/template/TemplateContextType.java
[sdk:MacroBase]: upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/macro/MacroBase.java
