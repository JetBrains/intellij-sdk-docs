# Live Templates Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Live Templates in IntelliJ SDK Docs][docs:live_templates]*

## Quickstart

Live Templates Sample Project implements two example live templates for the Markdown language:

- New link reference - by typing the `{<TAB>`, the following template will be inserted: `[$TEXT$]($LINK$)$END$`
- Convert to title case - retrieves the text from the macro or selection, if available.

### Extension Points

| Name                                | Implementation                          | Extension Point Class |
|-------------------------------------|-----------------------------------------|-----------------------|
| `com.intellij.defaultLiveTemplates` | [Markdown][file:Markdown]               | n/a                   |
| `com.intellij.liveTemplateContext`  | [MarkdownContext][file:MarkdownContext] | `TemplateContextType` |
| `com.intellij.liveTemplateMacro`    | [TitleCaseMacro][file:TitleCaseMacro]   | `MacroBase`           |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:live_templates]: https://plugins.jetbrains.com/docs/intellij/live-templates.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:Markdown]: ./src/main/resources/liveTemplates/Markdown.xml
[file:MarkdownContext]: ./src/main/java/org/intellij/sdk/liveTemplates/MarkdownContext.java
[file:TitleCaseMacro]: ./src/main/java/org/intellij/sdk/liveTemplates/TitleCaseMacro.java

