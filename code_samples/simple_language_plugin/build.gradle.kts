// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType.IntellijIdea
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType.IntellijIdeaCommunity
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease.Channel.RELEASE

plugins {
  id("java")
  id("org.jetbrains.intellij.platform") version "2.10.1"
}

group = "org.intellij.sdk"
version = "2.0.0"

// Include the generated files in the source set
sourceSets {
  main {
    java {
      srcDirs("src/main/gen")
    }
  }
}

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2024.3.6")
    bundledPlugin("com.intellij.java")

    testFramework(TestFrameworkType.Plugin.Java)
  }

  testImplementation("junit:junit:4.13.2")
  // workaround for <2024.3
  // https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-faq.html#missing-opentest4j-dependency-in-test-framework
  testImplementation("org.opentest4j:opentest4j:1.3.0")
}

intellijPlatform {
  buildSearchableOptions = false

  pluginConfiguration {
    ideaVersion {
      sinceBuild = "243"
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
