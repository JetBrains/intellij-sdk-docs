---
title: Plugin Content
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The plugin `jar` file must contain:
- the configuration file (`META-INF/plugin.xml`) ([Plugin Configuration File](plugin_configuration_file.md))
- the classes that implement the plugin functionality 
- recommended: plugin logo file(s) (`META-INF/pluginIcon*.svg`) ([Plugin Logo](plugin_icon_file.md)) 


### Plugin Without Dependencies 
A plugin consisting of a single `.jar` file is placed in the `/plugins` directory.

```
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
The plugin `.jar` file is placed in the `/lib` folder under the plugin's "root" folder, together with all required bundled libraries.

All jars from the `/lib` folder are automatically added to the classpath (see also [Plugin Class Loaders](plugin_class_loaders.md)).
   
```
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
