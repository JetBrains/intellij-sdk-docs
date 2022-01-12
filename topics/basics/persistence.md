[//]: # (title: Persistence Model)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform Persistence Model is used to store a variety of information.
For example, [Run Configurations](basic_run_configurations.md) and [Settings](settings.md) are stored using the Persistence Model.

There are two distinct approaches, depending on the type of data being persisted:
* [Persisting State of Components](persisting_state_of_components.md)
* [Persisting Sensitive Data](persisting_sensitive_data.md)