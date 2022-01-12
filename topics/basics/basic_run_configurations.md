[//]: # (title: Run Configurations)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

*Run Configurations* allow users to run specific external processes from within the IDE, e.g., a script, an application, a server, etc. 
You can provide the UI for the user to specify execution options, and an option to create a run configuration based on a specific location in the source code.

## Architectural Overview

Classes used to manipulate run configurations can be split into the following groups:

* [Run configuration management](run_configuration_management.md).
  This includes creation, persistence, and modification.
* [Execution](run_configuration_execution.md).

This diagram shows the main classes:

![Architecture](classes.png)

See [Run Configurations Tutorial](run_configurations.md) for a fully working sample.