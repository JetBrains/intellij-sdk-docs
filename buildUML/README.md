## Working with PlantUML

If you are unfamiliar with PlantUML, review the [quick start](https://plantuml.com/starting) instructions.

### Editing

#### Online

Use [https://kroki.io/](https://kroki.io/).

#### IDE

* Install [Graphviz](https://plantuml.com/graphviz-dot) on your machine.
* Get and install the [PlantUML Integration plugin](https://plugins.jetbrains.com/plugin/7017-plantuml-integration) for IntelliJ IDEA:
    * Set the `GRAPHVIZ_DOT` system or environment property to point to the directory containing the Graphviz executable on your machine. For example, `export GRAPHVIZ_DOT="/usr/local/Cellar/graphviz/2.42.3/bin/dot"`
    * In the PlantUML plugin Settings (**Settings/Preferences \| Other Settings \| PlantUML**) set:
        * The "Additional 'plantuml.include.path'" to be the absolute path to the directory containing the `jb-plantuml-theme.puml` file on your machine. For example, `/<path to sdk root>/buildUML` or, if you have the IntelliJ-Community source code, `/<path to intellij-community root>/platform/docs/`.
        * The "PlantUML config" to `!include jb-plantuml-theme.puml`

### Generating SVG

> Temporary workflow until embedding inside Markdown sources is available.

#### IDE

When a `*.puml` file is open in the IDE editor, the PlantUML Integration plugin will show a preview. To save a diagram in SVG format, change the file extension from the default PNG to SVG when saving the diagram from the preview window.

#### Online

Use [https://kroki.io/](https://kroki.io/) to generate SVG files by pasting:

```
@startuml

[contents of jb-plantuml-theme.puml]

[contents of your_diagram.puml (without `@startuml`)]

@enduml
```

and then c/p generated SVG output.

### Post Process

In SVG file `<defs>`, insert `<style>@import url('https://fonts.googleapis.com/css?family=Roboto|Roboto+Mono&amp;display=swap');</style>` tag.