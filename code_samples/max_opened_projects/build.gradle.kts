// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType.IntellijIdea
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType.IntellijIdeaCommunity
import org.jetbrains.intellij.platform.gradle.models.ProductRelease.Channel.RELEASE

plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.3.0"
  id("org.jetbrains.intellij.platform") version "2.10.4"
}

group = "org.intellij.sdk"
version = "2.0.0"

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdea("2025.1.5.1")
  }
}

intellijPlatform {
  buildSearchableOptions = false

  pluginConfiguration {
    ideaVersion {
      sinceBuild = "251"
    }
  }
  pluginVerification  {
    ides {
      // since 253, IntelliJ IDEA Community and Ultimate have been merged into IntelliJ IDEA
      select {
        types = listOf(IntellijIdeaCommunity)
        untilBuild = "252.*"
      }
      select {
        types = listOf(IntellijIdea)
        sinceBuild = "253"
        channels = listOf(RELEASE)
      }
    }
  }
}
