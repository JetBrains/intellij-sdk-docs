// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

rootProject.name = "code_inspection"

pluginManagement {
  plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.changelog") version "2.5.0"
    id("org.jetbrains.qodana") version "2026.1.3"
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
  id("org.jetbrains.intellij.platform.settings") version "2.18.1"
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    intellijPlatform {
      defaultRepositories()
    }
  }
}
