val codeInspectionPlugin = "code_inspection"

tasks.register<Copy>("stagePluginForQodana") {
  val qodanaDir = gradle.includedBuild("qodana-playground").projectDir.resolve(".qodana")
  dependsOn(gradle.includedBuild(codeInspectionPlugin).task(":buildPlugin"))
  from(gradle.includedBuild(codeInspectionPlugin)
    .projectDir.resolve("build/libs"))
  include("$codeInspectionPlugin-*.jar")
  exclude("$codeInspectionPlugin-*-base.jar")
  exclude("$codeInspectionPlugin-*-instrumented.jar")
  exclude("$codeInspectionPlugin-*-searchableOptions.jar")
  into(qodanaDir)
}

tasks.register<Copy>("buildPlugin") {
  group = "build"
  description = "Builds the Code Inspection plugin"

  val plugin = gradle.includedBuild(codeInspectionPlugin)
  dependsOn(plugin.task(":buildPlugin"))
  from(plugin.projectDir.resolve("build/distributions"))
  include("$codeInspectionPlugin-*.zip")
  into("build")
}
