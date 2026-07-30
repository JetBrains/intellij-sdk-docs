// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
  id("java")
  id("org.jetbrains.intellij.platform")
}

dependencies {
  intellijPlatform {
    intellijIdea("2025.3.6.1")
    bundledPlugin("com.intellij.java")

    testFramework(TestFrameworkType.Platform)
    testFramework(TestFrameworkType.Plugin.Java)
  }

  testImplementation("junit:junit:4.13.2")
}

// workaround for the Vue plugin causing test failure due to NoClassDefFoundError
// when fixed, delete this and `if` in workflow code-samples.yml
val testWithoutVue by intellijPlatformTesting.testIde.register("testWithoutVue") {
  type = IntelliJPlatformType.IntellijIdea

  testFramework(TestFrameworkType.Platform)
  testFramework(TestFrameworkType.Plugin.Java)
  plugins {
    disablePlugin("org.jetbrains.plugins.vue")
  }
}
