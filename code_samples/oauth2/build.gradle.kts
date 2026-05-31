// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.intellij.platform")
}

dependencies {
  implementation("org.kohsuke:github-api:1.330")

  intellijPlatform {
    intellijIdea("2025.2.6.1")
  }
}
