<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# New Project Wizard

<link-summary>Implementing a custom project and module creation wizard.</link-summary>

> This section describes the New Project Wizard API, which is also used to create modules within projects.
> The page uses "project wizard" wording for brevity.

The [New Project](https://www.jetbrains.com/help/idea/new-project-wizard.html) wizard items can be grouped into two main types:
- [](#language-project-generators)
- [](#framework-project-generators)

## Language Project Generators

Language project generators allow for creating general-purpose projects without specific frameworks (they can be added to the project by users later).
Examples:
- a Kotlin project
- a Python project

These items are displayed at the top in the left sidebar of the <control>New Project</control> dialog.

Language project generators implement
[`LanguageGeneratorNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/language/LanguageGeneratorNewProjectWizard.kt)
and are registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.newProjectWizard.languageGenerator"/></include>.

The generator implementation must provide:
- `name` and `icon` used in UI
- `ordinal` that is used to sort the generator in the generator list
- `isEnabled(WizardContext)` that allows controlling displaying the item depending on the current context
- `createStep(NewProjectWizardStep)` that creates the tree of steps that users go through during the project creation.
   Note the provided parent step, which exposes the wizard context, data holder, and other shared properties.
   See [](#wizard-steps) for details.

Examples:
- [`KotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/KotlinNewProjectWizard.kt) generating Kotlin projects
- [`PythonNewProjectWizard`](%gh-ic-master%/python/pluginJava/src/com/intellij/python/community/plugin/java/PythonNewProjectWizard.kt) generating Python projects

## Framework Project Generators

Framework project wizards allow for creating projects related to a specific technology, for example:
- a Spring Boot project
- a project generated from a Maven Archetype
- a React project

These items are displayed under language generator items in the left sidebar of the <control>New Project</control> dialog.

Framework project wizards implement
[`GeneratorNewProjectWizard`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/GeneratorNewProjectWizard.kt)
and are registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.newProjectWizard.generator"/></include>.

The interface exposes:
- unique `id`
- `name` and `icon` used in UI
- `ordinal` that is used to sort the generator in the generator list
- `isEnabled()` that allows controlling displaying the item depending on the current IDE context
- `createStep(WizardContext)` that creates the tree of steps that users go through during the project creation.
   See [](#wizard-steps) for details.

Example:
- [`MavenArchetypeNewProjectWizard`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/wizards/archetype/MavenArchetypeNewProjectWizard.kt) creating Maven projects from a selected archetype

## Wizard Steps

Every project wizard consists of one or more steps that users go through during project creation.
Each step contains UI elements for data necessary to create a project.
All the steps are displayed on a single screen in the project wizard dialog, which displays the <control>Create</control> button that creates the project.

> Wizards implemented with the old API (`ModuleBuilder`) can create multiple vertical steps that are navigable with the <control>Next</control> and <control>Previous</control> buttons in the wizard dialog.
> It caused many screens to be half-empty, and to improve the UX, new wizards display all steps on a single screen.

A project wizard step implements
[`NewProjectWizardStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectWizardStep.kt).
The most important methods are:
- `setupUI()` - defines the UI components for the step. Step UI is built with [](kotlin_ui_dsl_version_2.md).
- `setupProject()` - applies the parameters provided in UI to the generated project.

Steps build a tree structure (read the rest of this section for details), as some steps can be displayed depending on previously selected options.
`setupUI()` and `setupProject()` of steps building the tree are applied in the order from root to leaf.
Note that `setupProject()` won't be called for hidden steps.

For convenience, the platform provides
[`AbstractNewProjectWizardStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/AbstractNewProjectWizardStep.kt),
which is a base step class that takes a parent step and delegates common property accessors to the parent.
It allows sharing the root step's properties by all descendant steps.

As mentioned before, all steps are rendered on a single screen.
Merging multiple steps into a chain displayed as a single screen step is achieved with
[`NewProjectWizardChainStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectWizardChainStep.kt).
Its companion object exposes a helper method `NewProjectWizardChainStep.nextStep()`, which allows chaining steps in fluent form.

### Root Step

The root project wizard step initializes a data holder and other properties shared with all descendant steps in the wizard.

In the case of [language project generators](#language-project-generators), the `LanguageGeneratorNewProjectWizard.createStep(NewProjectWizardStep)` receives a parent step, and there is no need to initialize these values and create a root step.

In the case of [framework project generators](#framework-project-generators), the root step can be created with [`RootNewProjectWizardStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/RootNewProjectWizardStep.kt), which initializes shared properties.

### Common Steps

Almost every project requires providing the project name and the location where it should be created.
Also, projects are often shared in a Git repository.
The IntelliJ Platform provides:
- [`NewProjectWizardBaseStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectWizardBaseStep.kt),
which handles the project <control>Name</control> and <control>Location</control> fields
- [`GitNewProjectWizardStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/GitNewProjectWizardStep.kt), which is responsible for handling Git repository initialization if the user enables it

Note that [language project generators](#language-project-generators) already include these steps out of the box, and there is no need to create them in the wizard implementation.

### Asset Steps

It is often required to populate a created project with initial assets like:
- directory structure, for example, `src/main/java`, `src/main/resources`, `src/test/java`, `src/test/resources` in a Gradle Java project
- a build-tool-specific `.gitignore` file ignoring build and other directories and files
- sample code

This can be achieved by creating a step extending [`AssetsNewProjectWizardStep`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/generators/AssetsNewProjectWizardStep.kt).
See example implementations in the [`intellij-community`](https://github.com/JetBrains/intellij-community) repository.

> Note that `AssetsNewProjectWizardStep` **requires** a [dependency](plugin_dependencies.md) on the Java plugin.
> In addition, it is marked as an experimental API.
>
{style=warning}

### Steps Forking the Wizard Flow

Sometimes, a wizard requires displaying different options depending on the user's selection.
For example, depending on the selected build tool, different fields specific to the selected build tool are displayed.

Such a behavior can be implemented with
[`AbstractNewProjectWizardMultiStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/AbstractNewProjectWizardMultiStep.kt).

`AbstractNewProjectWizardMultiStep` internally handles steps forking the wizard flow.
It is responsible for:
- setting up the switcher
- setting up the selected step's UI
- applying the selected step's options to the created project

Forking steps are provided with an [extension point](plugin_extension_points.md) implementing
[`NewProjectWizardMultiStepFactory`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectWizardMultiStepFactory.kt).
Besides the parent step, `AbstractNewProjectWizardMultiStep`'s constructor receives the name of the extension point implementing `NewProjectWizardMultiStepFactory` and uses registered [extensions](plugin_extensions.md) to create steps.

> Note that a plugin must implement a custom extension point, which may allow for adding new forking steps (for example, build tools) supported by other plugins.

The [](#adding-support-for-custom-build-systems-in-language-project-wizards) section lists example implementations of `AbstractNewProjectWizardMultiStep`.

### Sharing Data Between Steps

Project wizards with multiple steps may require sharing data between them.
The recommended approach includes the following implementation steps:
1. Create an interface exposing step's shared properties, for example, `ExampleData`:
   ```kotlin
   interface ExampleData {
     val prop: String
   }
   ```
2. Create a companion object with the data key and a helper `NewProjectWizardStep.exampleData` property.
   ```kotlin
   interface ExampleData {
     val prop: Boolean

     companion object {
       val KEY: Key<ExampleData> = Key.create(ExampleData::class.java.name)
       @JvmStatic
       val NewProjectWizardStep.exampleData: ExampleData?
        get() = data.getUserData(KEY)
     }
   }
   ```
3. Make the step implement the created interface (see [](#property-graph) for implementation details) and implement the data property:
   ```kotlin
   class ExampleStep(parent: NewProjectWizardStep) :
     AbstractNewProjectWizardStep(parent), ExampleData {
     override val prop = propertyGraph.property(false)
     // ...
   }
   ```

4. During step instance initialization, store data in the data holder shared with all steps:
   ```kotlin
   class ExampleStep(parent: NewProjectWizardStep) :
     AbstractNewProjectWizardStep(parent), ExampleData {
     // ...
     init {
       data.putUserData(ExampleData.KEY, this)
     }
   }
   ```

Shared data can be accessed in another step in the following way:

```kotlin
class AnotherStep(parent: NewProjectWizardStep) :
   AbstractNewProjectWizardStep(parent) {

   override fun setupProject(project: Project) {
     // exampleData is available via this
     if (exampleData?.prop == true) {
       doSomething()
     }
     // ...
   }
   // ...
}
```

### Property Graph

[//]: # (TODO)

#### Persisting Default Settings

It is convenient for users to remember commonly used settings, so they don't need to fill them again.
Consider the checkbox initializing a Git repository in a created project.
When the user selects it for the first time, and later they create another project, the checkbox will be automatically selected.

This behavior can be implemented by binding properties to storage via methods from
[`BindUtil`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/observable/util/BindUtil.kt),
for example:
```kotlin
private val gitProperty = propertyGraph.property(false)
    .bindBooleanStorage(GIT_PROPERTY_NAME)
```

Under the hood, properties are stored at the application level via [PropertiesComponent](persisting_state_of_components.md#using-propertiescomponent-for-simple-non-roamable-persistence).

### Adding Support for Custom Build Systems in Language Project Wizards

Some language project generators contain the <control>Build system</control> field that allows for choosing a build system used by a project.
It is possible to add support for custom build systems by extensions dedicated for specific languages.
The table below shows the supported languages with corresponding interfaces and extension points:

| Language | Interface and Extension Point                                                                                                                                                                                                                                                                                  | Examples                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Java     | [`BuildSystemJavaNewProjectWizard`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/generators/BuildSystemJavaNewProjectWizard.kt)<br/><include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.java.buildSystem"/></include>                            | [`IntelliJJavaNewProjectWizard`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/generators/IntelliJJavaNewProjectWizard.kt)<br/>[`MavenJavaNewProjectWizard`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/wizards/MavenJavaNewProjectWizard.kt)<br/>[`GradleJavaNewProjectWizard`](%gh-ic%/plugins/gradle/java/src/service/project/wizard/GradleJavaNewProjectWizard.kt)                                                                                                      |
| Kotlin   | [`BuildSystemKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/BuildSystemKotlinNewProjectWizard.kt)<br/><include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.kotlin.buildSystem"/></include> | [`IntelliJKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/IntelliJKotlinNewProjectWizard.kt)<br/>[`MavenKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/maven/src/org/jetbrains/kotlin/tools/projectWizard/maven/MavenKotlinNewProjectWizard.kt)<br/>[`GradleKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/gradle/src/org/jetbrains/kotlin/tools/projectWizard/gradle/GradleKotlinNewProjectWizard.kt) |
| Groovy   | [`BuildSystemGroovyNewProjectWizard`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/config/wizard/BuildSystemGroovyNewProjectWizard.kt)<br/><include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.groovy.buildSystem"/></include>                   | [`IntelliJGroovyNewProjectWizard`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/config/wizard/IntelliJGroovyNewProjectWizard.kt)<br/>[`MavenGroovyNewProjectWizard`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/plugins/groovy/wizard/MavenGroovyNewProjectWizard.kt)<br/>[`GradleGroovyNewProjectWizard`](%gh-ic%/plugins/gradle/java/src/service/project/wizard/groovy/GradleGroovyNewProjectWizard.kt)                                                                  |

### Example Project Wizard Steps Structure

The following diagram presents steps of the Kotlin language project wizard implemented by [`KotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/KotlinNewProjectWizard.kt).

[//]: # (TODO: an example wizard tree diagram)

Despite being a language generator project wizard, its root step is created by the platform under the hood.

## FAQ

### How to determine whether the current context is a project or a module creation?

Use [`WizardContext.isCreatingNewProject()`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/WizardContext.java).
The wizard context is available in every step via [`NewProjectWizardStep.context`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/NewProjectWizardStep.kt).

### How to add a project wizard hint?

Sometimes, a project wizard requires an explanation about the purpose of the wizard or links to an alternative item.
To add a simple comment, use [`CommentNewProjectWizardStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/comment/CommentNewProjectWizardStep.kt), for example, [`EmptyProjectGeneratorNewProjectWizard.CommentStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/language/EmptyProjectGeneratorNewProjectWizard.kt)

To additionally render a clickable link to another wizard, use [`LinkNewProjectWizardStep`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/wizard/comment/LinkNewProjectWizardStep.kt), for example, [`MavenArchetypeNewProjectWizard.CommentStep`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/wizards/archetype/MavenArchetypeNewProjectWizard.kt).

### What is the status of `com.intellij.moduleBuilder`?

The [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) API is still supported but is not recommended to use in new projects.
