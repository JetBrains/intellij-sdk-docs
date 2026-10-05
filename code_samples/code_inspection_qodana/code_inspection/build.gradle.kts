// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.intellij.platform")
  id("org.jetbrains.qodana")
}

dependencies {
  intellijPlatform {
    intellijIdea("2026.1.5")
    bundledPlugin("org.jetbrains.kotlin")
  }
}
