// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

plugins {
  id 'java'
  id 'org.jetbrains.intellij' version '1.4.0'
}

group 'com.intellij.sdk'
version '0.1.0'

sourceCompatibility = 11

repositories {
  mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  version = '2021.2'
  type = 'PY'
  plugins = ["Pythonid"]
  downloadSources = false
}

buildSearchableOptions {
  enabled = false
}

patchPluginXml {
  version = project.version
  sinceBuild = '212'
  untilBuild = '213.*'
}
