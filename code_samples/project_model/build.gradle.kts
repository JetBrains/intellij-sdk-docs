// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id 'java'
  id 'org.jetbrains.intellij' version '1.4.0'
}

group 'org.intellij.sdk'
version '2.0.0'

sourceCompatibility = 11

repositories {
  mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  version = '2021.1.3'
  plugins = ['com.intellij.java']
}

buildSearchableOptions {
  enabled = false
}

patchPluginXml {
  version = project.version
  sinceBuild = '211'
  untilBuild = '213.*'
}
