import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType.IntellijIdea
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.intellij.platform")
}

dependencies {
  intellijPlatform {
    intellijIdea("2025.3")
  }
  implementation(project(":shared"))
  implementation(project(":css"))
}

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.intellij.platform.module")
}

val runIde261 by intellijPlatformTesting.runIde.registering {
  type = IntellijIdea
  version = "2026.1"
}
