---
layout: editable
title: Run Configurations
---


Run configurations allow users to run a certain type of external processes from within the IDE, i.e a script, an application, a server, etc.
You can provide UI for the user to specify execution options, as well as an option to create run configuration based on a specific location in the source code.


# Architectural overview

![Architecture](img/run_configurations/classes.png)

We can separate all entities into two parts:

*  [Run configuration management](run_configuration_management.html)

   This includes creation, persistence, and modification.

*  [Execution](run_configuration_execution.html)