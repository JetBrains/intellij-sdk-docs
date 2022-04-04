// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.5.1"
  id("org.jetbrains.kotlin.jvm") version "1.6.20"
}

group = "org.intellij.sdk"
version = "2.0.0"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  version.set("2021.1.3")
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("211")
    untilBuild.set("213.*")
  }

  compileKotlin {
    kotlinOptions.jvmTarget = "11"
  }

  compileTestKotlin {
    kotlinOptions.jvmTarget = "11"
  }
}
