// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("java")
  id("org.jetbrains.intellij.platform") version "2.5.0"
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
    intellijIdeaCommunity("2024.2")
  }
}

intellijPlatform {
  buildSearchableOptions = false
  pluginVerification  {
    ides {
      recommended()
    }
  }
}
