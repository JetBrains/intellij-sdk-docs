// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

// Composite Build for all SDK Code Sample projects (excluding those under /product_specific/ to reduce dependencies)

rootProject.name = "SDK Code Samples"

includeBuild("../action_basics")
includeBuild("../comparing_string_references_inspection")
includeBuild("../conditional_operator_intention")
includeBuild("../editor_basics")
includeBuild("../facet_basics")
includeBuild("../framework_basics")
includeBuild("../kotlin_demo")
includeBuild("../live_templates")
includeBuild("../max_opened_projects")
includeBuild("../module")
includeBuild("../project_model")
includeBuild("../project_view_pane")
includeBuild("../project_wizard")
includeBuild("../psi_demo")
includeBuild("../run_configuration")
includeBuild("../settings")
includeBuild("../simple_language_plugin")
includeBuild("../tool_window")
includeBuild("../tree_structure_provider")
