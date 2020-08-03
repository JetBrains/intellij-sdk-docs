# Live Templates Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Live Templates in IntelliJ SDK Docs][docs:live_templates]*

## Quickstart

Live Templates Sample Project implements two example live templates for the Markdown language:

- New link reference - by typing the `{<TAB>`, following template will be inserted: `[$TEXT$]($LINK$)$END$`
- Convert to title case - retrieves the text from the macro or selection, if any is available.

## Structure

Live Templates Sample Project
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                 | Implementation Class                    | Interface                                      |
| -------------------- | --------------------------------------- | ---------------------------------------------- |
| defaultLiveTemplates | [Markdown][file:Markdown]               |                                                |
| liveTemplateContext  | [MarkdownContext][file:MarkdownContext] | [TemplateContextType][sdk:TemplateContextType] |
| liveTemplateMacro    | [TitleCaseMacro][file:TitleCaseMacro]   | [MacroBase][sdk:MacroBase]                     |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:live_templates]: https://jetbrains.org/intellij/sdk/docs/tutorials/live_templates.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:Markdown]: ./src/main/resources/liveTemplates/Markdown.xml
[file:MarkdownContext]: ./src/main/java/org/intellij/sdk/liveTemplates/MarkdownContext.java
[file:TitleCaseMacro]: ./src/main/java/org/intellij/sdk/liveTemplates/TitleCaseMacro.java

[sdk:TemplateContextType]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/template/TemplateContextType.java
[sdk:MacroBase]: https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/codeInsight/template/macro/MacroBase.java
