Project wizard. Adding support for creating new project types.
===============

#Project Wizard
Working with the project wizard can be excessively illustrated with the following
[plugin] (https://github.com/bulenkov/RedlineSmalltalk.git)

##Implementing new module type
Additional support for specific tools and technologies is usually done via implementing some certain module type which is attached to the project.
New module type should be derived from the class
[ModuleType.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/openapi/module/ModuleType.java).
[Code sample] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)

#Facet
Facets in IntelliJ are the way to store multiple kinds of module-specific settings, for instance to make a language support or framework available in some given module.
To understand facets better from the point of view of an end-user, please see
[this page] (http://confluence.jetbrains.com/display/IntelliJIDEA/Facets).



