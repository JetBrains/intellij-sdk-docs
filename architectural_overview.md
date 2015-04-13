---
layout: editable
title: Architectural Overview
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Architectural+Overview
-->

The goal of this topic is to describe the architecture of IntelliJ IDEA from a plugin developer's point of view. It will be organized in a
task-based manner: rather than listing all the things that you can do with each object and describing how they are all implemented, it will try
to answer questions "what can I do with this object", "how do I get to this object" and so on.

This topic assumes that the reader is familiar with the basic concepts of IntelliJ IDEA plugin development. If you don't know anything at all about plugin development, you should start with the live demo and tutorials at 
[http://www.jetbrains.com/idea/plugins/index.html](http://www.jetbrains.com/idea/plugins/index.html), 
and then return to this document.

This topic covers the following subjects:

* [General Threading Rules](general_threading_rules.html)

* [Virtual Files](virtual_file.html)

* [Documents](documents.html)
 
* [PSI Files](psi_files.html)

* [File View Providers](file_view_providers.html)
 
* [Psi Elements](psi_elements.html)
