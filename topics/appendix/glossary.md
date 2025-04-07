<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Glossary

<link-summary>Glossary of IntelliJ Platform specific terms.</link-summary>

See also [Oracle's Java Technology Glossary](https://www.oracle.com/java/technologies/glossary.html).

## A

Abstract Syntax Tree _(AST)_ {#abstract-syntax-tree}
: The [Abstract Syntax Tree](implementing_parser_and_psi.md) represents the structure of source input files.
<br/>&rarr;&nbsp;[_Program Structure Interface_](#program-structure-interface)

Annotator {#annotator}
: Provides [semantic highlighting](syntax_highlighting_and_error_highlighting.md) based on underlying &rarr;&nbsp;[_Program Structure Interface_](#program-structure-interface) elements.
<br/>&rarr;&nbsp;[_Inspection_](#inspection)

## B

Blocking Context {#blocking-context}
: Executing in the [Blocking Context](execution_contexts.topic#blocking-context) means executing tasks on a thread without access to a coroutine context.
<br/>&rarr;&nbsp;[_Suspending Context_](#suspending-context)
<br/>&rarr;&nbsp;[_Coroutine Execution Context_](#coroutine-execution-context)
<br/>&rarr;&nbsp;[_Coroutine Scope_](#coroutine-scope)
<br/>&rarr;&nbsp;[_Coroutine_](#coroutine)

## C

`CancellationException` _(CE)_ {#cancellationexception}
: `java.util.concurrent.CancellationException`, `kotlin.coroutines.cancellation.CancellationException` (typealias in stdlib), `kotlinx.coroutines.CancellationException` (typealias in kotlinx-coroutines)
<br/>&rarr;&nbsp;[_ProcessCanceledException_](#processcanceledexception)

Code Insight {#code-insight}
: Code insight is a common name used for auto-completion, inspections, intention actions, type inference, and other features related to the code analysis.

Coroutine {#coroutine}
: A [Kotlin coroutines](kotlin_coroutines.md) execution unit allowing for handling concurrency and asynchronous tasks efficiently with a sequential/imperative code style.

Coroutine Dispatcher {#coroutine-dispatcher}
: Part of the coroutine context.
Determines a thread or a thread pool the corresponding coroutine is executed on.
See [](coroutine_dispatchers.md) for more details.
<br/>&rarr;&nbsp;[_Coroutine_](#coroutine)

Coroutine Execution Context {#coroutine-execution-context}
: Executing in the [Coroutine Execution Context](execution_contexts.topic#blocking-context) means executing code (suspending or non-suspending/blocking) from a coroutine.

Coroutine Scope {#coroutine-scope}
: [Coroutine scopes](coroutine_scopes.md) define the lifetime of coroutines and ensure proper handling of coroutine cancellations and structured concurrency.
<br/>&rarr;&nbsp;[_Coroutine_](#coroutine)

## D

Document Object Model _(DOM)_ {#document-object-model}
: [](xml_dom_api.md) abstracts working with XML files based on a custom semantic model.

## E

Event Dispatch Thread _(EDT)_ {#event-dispatch-thread}
: The [Event Dispatch Thread](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html) handles all Swing events. See also [](threading_model.md) and [](coroutine_dispatchers.md#edt-dispatcher).

Extension Point _(EP)_ {#extension-point}
: Most functionality is provided by [Using Extension Points](plugin_extensions.md) provided by the platform or plugins. Plugins can also [define their own](plugin_extension_points.md) to allow extensibility.

External System _(ES)_ {#external-system}
: [](external_system_integration.md) allows integrating external project management systems.

## F

Feature Usage Statistics _(FUS)_ {#feature-usage-statistics}
: JetBrains internal API to track feature usage in the IDE.

File Based Index _(FBI)_ {#file-based-index}
: [File Based Index](file_based_indexes.md) allows storing key-value information based on the project's files.

## G

Gradle Build Script {#gradle-build-script}
: A configuration file written in Kotlin (<path>build.gradle.kts</path>) or Groovy (<path>build.gradle</path>) that describes the build process and dependencies of a plugin.

## I

Inspection {#inspection}
: Supports configurable [semantic highlighting](code_inspections_and_intentions.md).
<br/>&rarr;&nbsp;[_Annotator_](#annotator)

Intention {#intention}
: Provides an [automated fix](code_inspections_and_intentions.md) for commonly encountered code problems.

## L

Language Server Protocol _(LSP)_ {#language-server-protocol}
: Communication standard between development tools and Language Servers, see [](language_server_protocol.md).

Local History _(LVCS)_ {#local-history}
: A builtin [&rarr;&nbsp;_Version Control System_](#version-control-system) tracking all changes in the project [locally](https://www.jetbrains.com/help/idea/local-history.html).

Look and Feel _(LaF)_ {#}
: Defines the visual appearance and behavior of the user interface; see [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html).

## J

JetBrains Project System _(JPS)_ {#jetbrains-project-system}
: Represents the project model in [External Build](external_builder_api.md#accessing-project-model-and-configuration-from-external-build) process.

JetBrains Runtime _(JBR)_ {#jetbrains-runtime}
: The [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) is the default bundled runtime for all IntelliJ Platform-based IDEs by JetBrains.

## N

Non-Blocking Read Action _(NBRA)_ {#non-blocking-read-action}
: A &rarr;&nbsp;[_Read Action_](#read-action) that is canceled by a &rarr;&nbsp;[_Write Action_](#write-action). See also [](threading_model.md#read-action-cancellability).

## P

`ProcessCanceledException` _(PCE)_ {#processcanceledexception}
: [`ProcessCanceledException`](%gh-ic%/platform/util/base/src/com/intellij/openapi/progress/ProcessCanceledException.java) An exception indicating that the currently running operation was terminated and should finish as soon as possible.
<br/>&rarr;&nbsp;[_CancellationException_](#cancellationexception)

Program Structure Interface _(PSI)_ {#program-structure-interface}
: The [Program Structure Interface](psi.md) represents a syntactic and semantic code model of the source input files.
<br/>&rarr;&nbsp;[_Abstract Syntax Tree_](#abstract-syntax-tree)
<br/>&rarr;&nbsp;[_Stubs_](#stubs)

## R

Read Action _(RA)_ {#read-action}
: Allows accessing code-related data structures for reading purposes. See also [](threading_model.md).
<br/>&rarr;&nbsp;[_Non-Blocking Read Action_](#non-blocking-read-action)
<br/>&rarr;&nbsp;[_Write Action_](#write-action)

Run Configuration _(RC)_ {#run-configuration}
: A [Run Configuration](run_configurations.md) allows running external processes from within the IDE.

## S

Structural Search and Replace _(SSR)_ {#structural-search-and-replace}
: Allows searching and replacing code by defining the structure of the searched code fragments, see [](plugin_alternatives.md#structural-search-and-replace-inspections).

Stubs {#stubs}
: A subset of a &rarr;&nbsp;[_Program Structure Interface_](#program-structure-interface) tree in a binary serialized compact format, see [](stub_indexes.md).

Suspending Context {#suspending-context}
: Executing in the [Suspending Context](execution_contexts.topic#suspending-context-coroutines) means executing tasks in Kotlin coroutines.
<br/>&rarr;&nbsp;[_Blocking Context_](#blocking-context)
<br/>&rarr;&nbsp;[_Coroutine Execution Context_](#coroutine-execution-context)
<br/>&rarr;&nbsp;[_Coroutine_](#coroutine)

Symbol {#symbol}
: A semantic element in some model, e.g., language or framework model, see [](symbols.md).

## U

Unified Abstract Syntax Tree _(UAST)_ {#unified-abstract-syntax-tree}
: An [abstraction layer](uast.md) on the &rarr;&nbsp;[_Program Structure Interface_](#program-structure-interface) of different JVM languages.

## V

Version Control System _(VCS)_ {#version-control-system}
: The API for [Version Control System](vcs_integration_for_plugins.md) allows accessing builtin as well as adding custom implementations.

Virtual File _(VF)_ {#virtual-file}
: A [Virtual File](virtual_file.md) represents a file in a &rarr;&nbsp;[_Virtual File System_](#virtual-file-system).

Virtual File System _(VFS)_ {#virtual-file-system}
: A [Virtual File System](virtual_file_system.md) provides a unified API for working with files represented as &rarr;&nbsp;[_Virtual File_](#virtual-file).

## W

Write Action _(WA)_ {#write-action}
: Allows accessing code-related data structures for writing purposes. See also [](threading_model.md).
<br/>&rarr;&nbsp;[_Read Action_](#read-action)

Write Allowing Read Action _(WARA)_ {#write-allowing-read-action}
: A coroutine &rarr;&nbsp;[_Read Action_](#read-action) that is canceled by an incoming &rarr;&nbsp;[_Write Action_](#write-action).
See [](coroutine_read_actions.topic#coroutine-read-actions-api) for details.
<br/>&rarr;&nbsp;[_Suspending Context_](#suspending-context)
<br/>&rarr;&nbsp;[_Coroutine_](#coroutine)

Write Blocking Read Action _(WBRA)_ {#write-blocking-read-action}
: A coroutine &rarr;&nbsp;[_Read Action_](#read-action) that blocks incoming &rarr;&nbsp;[_Write Action_](#write-action).
See [](coroutine_read_actions.topic#coroutine-read-actions-api) for details.
<br/>&rarr;&nbsp;[_Suspending Context_](#suspending-context)
<br/>&rarr;&nbsp;[_Coroutine_](#coroutine)

<include from="snippets.topic" element-id="missingContent"/>
