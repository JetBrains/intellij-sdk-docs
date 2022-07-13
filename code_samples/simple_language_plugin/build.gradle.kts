// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.7.0"
}

group = "org.intellij.sdk"
version = "2.0.0"

repositories {
  mavenCentral()
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
  testImplementation("junit:junit:4.13.2")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  version.set("2021.3.3")
  plugins.set(listOf("com.intellij.java"))
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("213")
    untilBuild.set("222.*")
  }

  test {
    // This path value is a machine-specific placeholder text.
    // Set idea.home.path to the absolute path to the intellij-community source
    // on your local machine. For real world projects, use variants described in:
    // https://docs.gradle.org/current/userguide/build_environment.html
    systemProperty("idea.home.path", "/Users/jhake/Documents/source/comm")
  }
}
