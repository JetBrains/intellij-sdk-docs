<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Integration Tests

<primary-label ref="2023.2"/>

<link-summary>Tutorial on how to create UI and functional integration tests</link-summary>

There are several important reasons to create integration tests, including:

* Testing complex scenarios: Some scenarios, particularly UI interactions, cannot be effectively covered by unit tests alone.
* Full product testing: Integration tests run against the complete product rather than isolated components. This helps identify issues that unit tests might miss, such as module interaction problems, classpath conflicts, and plugin declaration issues.
* User story validation: Integration tests typically mirror real user scenarios, ensuring the plugin works reliably from start to finish.


1. [](integration_tests_intro.md)
2. [](integration_tests_ui.md)
3. [](integration_tests_api.md)

