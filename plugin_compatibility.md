---
layout: editable
title: Plugin Compatibility with IntelliJ Platform Products
---

<!--INITIAL_SOURCE
https://confluence.jetbrains.com/display/IDEADEV/Plugin+Compatibility+with+IntelliJ+Platform+Products
-->

# {{page.title}}

All products based on the IntelliJ Platform (IntelliJ IDEA, RubyMine, WebStorm, PhpStorm, PyCharm and AppCode) share the same underlying platform API. 
Thus, a plugin which does not use any Java specific functionality can be marked as compatible with other products besides IntelliJ IDEA. 
This is done by specifying *module dependencies* in the 
<!--TODO link to sample_plugin file-->
[plugin.xml]() 
file.

A module dependency is a ```<depends>``` tag where the contents of the tag starts with *com.intellij.modules*.
 
For example:

```xml
<idea-plugin version="2">
  ...
  <depends>com.intellij.modules.lang</depends>
  ...
</idea-plugin>
```

If a plugin does not include any module dependency tags in its
<!--TODO link to sample_plugin file--> 
[plugin.xml](),
it's assumed to be a legacy plugin and it's loaded only in IntelliJ IDEA.

If the
<!--TODO link to sample_plugin file--> 
[plugin.xml]() 
includes one or more of such tags, the plugin is loaded if the product contains all of the modules on which the plugin depends.

The following modules are currently available in all products based on the IntelliJ platform:

* *com.intellij.modules.platform*

* *com.intellij.modules.lang*

* *com.intellij.modules.vcs*

* *com.intellij.modules.xml*

* *com.intellij.modules.xdebugger*

The following modules are only available in specific products:

* *com.intellij.modules.java* \- IntelliJ IDEA

* *com.intellij.modules.ultimate* \- IntelliJ IDEA Ultimate Edition

* *com.intellij.modules.ruby* \- RubyMine

* *com.intellij.modules.python* \- PyCharm

* *com.intellij.modules.appcode* \- AppCode

* *com.intellij.modules.clion* \- CLion

* *com.intellij.modules.cidr.lang* \- AppCode, CLion

* *com.intellij.modules.cidr.debugger* \- AppCode, CLion, RubyMotion

PhpStorm does not have any modules specific to it, but it includes the PHP plugin, which you can also use as a dependency: *com.jetbrains.php*

You can also specify optional module dependencies. 
If your plugin works with all products but provides some Java-specific functionality, you can use a dependency tag like this:

```xml
<depends optional="true" config-file="my-java-features.xml">
	com.intellij.modules.java
</depends>
```

Before marking a plugin as compatible with all products, you need to verify that it doesn't use any APIs which are specific to IntelliJ IDEA. 

In order to do that, you can create an IntelliJ Platform SDK pointing to an installation of RubyMine/PyCharm/..., compile your plugin against that SDK and verify that everything compiles.

The 
[IntelliJ plugin repository](http://plugins.intellij.net) 
automatically detects the products with which a plugin is compatible, according to these rules, and makes it available to users of those products.
