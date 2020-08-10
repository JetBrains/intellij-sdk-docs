---
title: Glossary
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2 -->

## A

Abstract Syntax Tree _(AST)_
: The [Abstract Syntax Tree](/reference_guide/custom_language_support/implementing_parser_and_psi.md) represents the structure of source input files. 
&rarr; _Program Structure Interface_

Annotator
: Provides [semantic highlighting](/reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.md) based on underlying &rarr; _Program Structure Interface_ elements.
&rarr; _Inspection_

## D

Document Object Model _(DOM)_
: [DOM API](/reference_guide/frameworks_and_external_apis/xml_dom_api.md) abstracts working with XML files based on a custom semantic model.

## E
Event Dispatch Thread _(EDT)_
: The [Event Dispatch Thread](https://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html) handles all Swing events. See also [General Threading Rules](/basics/architectural_overview/general_threading_rules.md).

Extension Point _(EP)_
: Most functionality is provided by [Using Extension Points](/basics/plugin_structure/plugin_extensions.md) provided by the platform or plugins. Plugins can also [define their own](/basics/plugin_structure/plugin_extension_points.md) to allow extensibility.

External System _(ES)_
: [External System](/reference_guide/frameworks_and_external_apis/external_system_integration.md) allows integrating external project management systems.

## F

File Based Index _(FBI)_
: [File Based Index](/basics/indexing_and_psi_stubs/file_based_indexes.md) allows storing key-value information based on the project's files.


## I

Inspection
: Supports configurable [semantic highlighting](/reference_guide/custom_language_support/code_inspections_and_intentions.md).
&rarr; _Annotator_

Intention
: Provides an [automated fix](/reference_guide/custom_language_support/code_inspections_and_intentions.md) for commonly encountered code problems.

## L

Local History _(LVCS)_
: A builtin &rarr; _Version Control System_ tracking all changes in the project [locally](https://www.jetbrains.com/help/idea/local-history.html).

## J
                           
JetBrains Project System _(JPS)_
: Represents the project model in [External Build](/reference_guide/frameworks_and_external_apis/external_builder_api.md#accessing-project-model-and-configuration-from-external-build) process.

JetBrains Runtime _(JBR)_
: The [JetBrains Runtime](/basics/ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance) is the default bundled runtime for all IntelliJ Platform-based IDEs by JetBrains.


## P
Program Structure Interface _(PSI)_
: The [Program Structure Interface](/basics/architectural_overview/psi.md) represents a syntactic and semantic code model of the source input files. &rarr; _Abstract Syntax Tree_ &rarr; _Stubs_

## R

Read Action
: Allows accessing code-related data structures for reading purposes. See also [General Threading Rules](/basics/architectural_overview/general_threading_rules.md).
&rarr; _Write Action_

Run Configuration _(RC)_
: A [Run Configuration](/basics/run_configurations.md) allows running external processes from within the IDE.

## S

Stubs
: A subset of a &rarr; _Program Structure Interface_ tree in a binary serialized compact format, see [Stub Indexes](/basics/indexing_and_psi_stubs/stub_indexes.md).

## V
Version Control System _(VCS)_
: The API for [Version Control System](/reference_guide/vcs_integration_for_plugins.md) allows accessing builtin as well as adding custom implementations. 

Virtual File _(VF)_
: A [Virtual File](/basics/architectural_overview/virtual_file.md) represents a file in a &rarr; _Virtual File System_.

Virtual File System _(VFS)_
: A [Virtual File System](/basics/virtual_file_system.md) provides a unified API for working with files represented as &rarr; _Virtual File_. 


## W

Write Action
: Allows accessing code-related data structures for writing purposes. See also [General Threading Rules](/basics/architectural_overview/general_threading_rules.md).
&rarr; _Read Action_
