[//]: # (title: Notable Changes in IntelliJ Platform and Plugins API 2021.*)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## 2021.2

### Notable Changes in IntelliJ Platform 2021.2

## 2021.1

### Notable Changes in IntelliJ Platform 2021.1

_Add unambiguous imports on the fly_ for custom languages
: Override [`ReferenceImporter#isAddUnambiguousImportsOnTheFlyEnabled()`](upsource:///platform/analysis-impl/src/com/intellij/codeInsight/daemon/ReferenceImporter.java) and provide corresponding user setting. Implement `HintAction` with `fixSilently()` and hook it up to highlighting as a quick fix for unresolved reference.

### Notable Changes in Java Plugin 2021.1

Testframework: JUnit4 variant for `LightJavaCodeInsightFixtureTestCase`
: Use `com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase4`.