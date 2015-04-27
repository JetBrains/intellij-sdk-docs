---
layout: editable
title: Plugin Content
---

*There are 3 ways how to organize plugin content:*

1. A plugin consists of one .jar file placed in the plugins directory:

   The archive should contain the configuration file (META-INF/plugin.xml) and classes that implement the plugin functionality.
   The configuration file specifies the plugin name, description, version, vendor, the supported IDEA version, plugin components, actions and action groups, action user interface placement.

   ```
.IntelliJIDEAx0
	plugins
		sample.jar/
			com/foo/.....
				...
				...
			META-INF
				plugin.xml
   ```

2. Plugin files are located in a folder:

   ```
.IntelliJIDEAx0
	plugins
		Sample
			lib
				libfoo.jar
				libbar.jar
			classes
				com/foo/.....
				...
				...

			META-INF
				plugin.xml
   ```

   The 'classes' folder and all jars located in the 'lib' folder are automatically added to the classpath.

3. Plugin files are located in a jar-file that is placed to the lib folder:

   ```
.IntelliJIDEAx0
	plugins
		Sample
			lib
				libfoo.jar
				libbar.jar
				Sample.jar/
    				com/foo/.....
	    			...
					...
					META-INF
						plugin.xml
   ```

   All the jars from the 'lib' folder are automatically added to the classpath.