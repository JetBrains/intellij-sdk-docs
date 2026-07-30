// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
  id("java")
  id("org.jetbrains.intellij.platform")
}

// Include the generated files in the source set
sourceSets {
  main {
    java {
      srcDirs("src/main/gen")
    }
  }
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
