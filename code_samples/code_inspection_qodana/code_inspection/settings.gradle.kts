// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

rootProject.name = "code_inspection"

pluginManagement {
  plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.21"
    id("org.jetbrains.qodana") version "2026.2.1"
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
  id("org.jetbrains.intellij.platform.settings") version "2.19.0"
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    intellijPlatform {
      defaultRepositories()
    }
  }
}
