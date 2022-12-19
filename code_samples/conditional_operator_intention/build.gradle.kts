// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.11.0"
}

group = "org.intellij.sdk"
version = "2.0.0"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("junit:junit:4.13.2")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  version.set("2022.1.4")
  plugins.set(listOf("com.intellij.java"))
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("221")
    untilBuild.set("223.*")
  }

  test {
    // Set idea.home.path to the absolute path to the intellij-community source
    // on your local machine.
    systemProperty("idea.home.path", "/Users/jhake/Documents/source/comm")
  }
}
