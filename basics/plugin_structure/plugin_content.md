---
title: Plugin Content
---

There are two possible ways of organizing plugin content:

1. A plugin consisting of one `.jar` file placed in the plugins directory:

    The archive should contain the configuration file (`META-INF/plugin.xml`) and classes that implement the plugin functionality. The configuration file specifies the plugin name, description, version, vendor, the supported IntelliJ IDEA version, plugin components, actions and action groups, and action user interface placement.

   ```
   .IntelliJIDEAx0/
     plugins/
       sample.jar/
         com/foo/...
         ...
         ...
         META-INF/
           plugin.xml
   ```
   

2. Plugin files located in a `.jar` file that is placed in the lib folder:

   ```
   .IntelliJIDEAx0/
     plugins/
       Sample/
         lib/
           libfoo.jar
           libbar.jar
           Sample.jar/
             com/foo/...
             ...
             ...
             META-INF/
               plugin.xml
   ```

   All jars from the `lib` folder are automatically added to the classpath.
