[//]: # (title: Plugin Content)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Plugin distribution will be built using [Gradle](deployment.md) or [Plugin DevKit](deploying_plugin.md).

The plugin <path>jar</path> file must contain:
- the configuration file (<path>META-INF/plugin.xml</path>) ([Plugin Configuration File](plugin_configuration_file.md))
- the classes that implement the plugin functionality
- recommended: plugin logo file(s) (<path>META-INF/pluginIcon*.svg</path>) ([Plugin Logo](plugin_icon_file.md))

### Plugin Without Dependencies
A plugin consisting of a single <path>.jar</path> file is placed in the <path>/plugins</path> directory.

```text
.IntelliJIDEAx0/
└── plugins
    └── sample.jar
        ├── com/foo/...
        │   ...
        │   ...
        └── META-INF
            ├── plugin.xml
            ├── pluginIcon.svg
            └── pluginIcon_dark.svg
```

### Plugin With Dependencies
The plugin <path>.jar</path> file is placed in the <path>/lib</path> folder under the plugin's "root" folder, together with all required bundled libraries.

All jars from the <path>/lib</path> folder are automatically added to the classpath (see also [Plugin Class Loaders](plugin_class_loaders.md)).

```text
   .IntelliJIDEAx0/
   └── plugins
       └── sample
           └── lib
               ├── lib_foo.jar
               ├── lib_bar.jar
               │   ...
               │   ...
               └── sample.jar
                   ├── com/foo/...
                   │   ...
                   │   ...
                   └── META-INF
                       ├── plugin.xml
                       ├── pluginIcon.svg
                       └── pluginIcon_dark.svg
```
