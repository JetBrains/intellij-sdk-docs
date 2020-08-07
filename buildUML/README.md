## Working with PlantUML
These are temporary instructions, pending a move of more [diagrams and utilities](https://github.com/JetBrains/intellij-community/tree/master/platform/docs) from the IntelliJ Platform codebase.

* If you are unfamiliar with PlantUML, review the [quick start](https://plantuml.com/starting) instructions. 
* Install [Graphviz](https://plantuml.com/graphviz-dot) on your machine. 
* Get and install the [PlantUML Integration plugin](https://plugins.jetbrains.com/plugin/7017-plantuml-integration) for IntelliJ IDEA: 
  * Set the `GRAPHVIZ_DOT` system or environment property to point to the directory containing the Graphviz executable on your machine. 
    For example, `export GRAPHVIZ_DOT="/usr/local/Cellar/graphviz/2.42.3/bin/dot"` 
  * In the PlantUML plugin Settings (**Preferences/Settings \| Other Settings \| PlantUML**) set: 
    * The "Additional 'plantuml.include.path'" to be the absolute path to the directory containing the `jb-plantuml-theme.puml` file on your machine. 
      For example, `/<path to sdk root>/buildUML` or, if you have the IntelliJ-Community source code, `/<path to intellij-community root>/platform/docs/`. 
    * The "PlantUML config" to `!include jb-plantuml-theme.puml`
    
When a *.puml file is open in the IDE editor, the PlantUML Integration plugin will show a preview.
To save a diagram in SVG format, change the file extension from the default PNG to SVG when saving the diagram from the preview window. 
