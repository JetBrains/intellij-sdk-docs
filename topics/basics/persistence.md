<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Persistence Model

<link-summary>Introduction to the IntelliJ Platform Persistence Model.</link-summary>

The IntelliJ Platform Persistence Model is used to store a variety of information.
For example, [Run Configurations](run_configurations.md) and [Settings](settings.md) are stored using the Persistence Model.

There are two distinct approaches, depending on the type of data being persisted:
* [Persisting State of Components](persisting_state_of_components.md)
* [Persisting Sensitive Data](persisting_sensitive_data.md)

To share application-level settings between different IDE installations, see [](roaming_settings.md).

[Split plugins](split_mode_and_remote_development.md) may also need explicit synchronization metadata for persisted settings.
See [](persistent_state_in_split_mode.md).
