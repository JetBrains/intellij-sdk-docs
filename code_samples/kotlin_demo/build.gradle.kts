// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.15.0"
  id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

group = "org.intellij.sdk"
version = "2.0.0"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2022.2.5")
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("222")
    untilBuild.set("231.*")
  }

  compileKotlin {
    kotlinOptions.jvmTarget = "17"
  }

  compileTestKotlin {
    kotlinOptions.jvmTarget = "17"
  }
}
