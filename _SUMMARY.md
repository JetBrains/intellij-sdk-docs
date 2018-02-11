# Summary

* [介绍](welcome.md)
* [IntelliJ Platform ](intro/intellij_platform.md)
* [关于本指南](intro/about.md)
    * [关键主题](intro/key_topics.md)
    * [特约](CONTRIBUTING.md)
* [获得帮助](intro/getting_help.md)
* [最近更新](recently_updated.md)

## Part I - Plugins
* [介绍](basics.md)
    * [插件的类型](basics/types_of_plugins.md)
* [入门](basics/getting_started.md)
    * [建立一个开发环境](basics/getting_started/setting_up_environment.md)
    * [检出并建立社区版](basics/checkout_and_build_community.md)
    * [创建一个插件项目](basics/getting_started/creating_plugin_project.md)
    * [编号范围](basics/getting_started/build_number_ranges.md)
    * [创建一个动作](basics/getting_started/creating_an_action.md)
    * [运行和调试一个插件](basics/getting_started/running_and_debugging_a_plugin.md)
    * [部署一个插件](basics/getting_started/deploying_plugin.md)
    * [发布一个插件](basics/getting_started/publishing_plugin.md)
    * [插件结构](basics/plugin_structure.md)
        * [插件内容](basics/plugin_structure/plugin_content.md)
        * [插件类装载机](basics/plugin_structure/plugin_class_loaders.md)
        * [插件组件](basics/plugin_structure/plugin_components.md)
        * [插件扩展和扩展点](basics/plugin_structure/plugin_extensions_and_extension_points.md)
        * [插件操作](basics/plugin_structure/plugin_actions.md)
        * [插件服务](basics/plugin_structure/plugin_services.md)
        * [插件配置文件](basics/plugin_structure/plugin_configuration_file.md)
        * [插件依赖关系](basics/plugin_structure/plugin_dependencies.md)
* [项目结构](basics/project_structure.md)
    * [行动系统](basics/action_system.md)
    * [组件持久状态](basics/persisting_state_of_components.md)
    * [PSI 食谱](basics/psi_cookbook.md)
    * [虚拟文件系统](basics/virtual_file_system.md)
* [测试插件](basics/testing_plugins.md)
    * [测试和夹具](basics/testing_plugins/tests_and_fixtures.md)
    * [轻和重的测试](basics/testing_plugins/light_and_heavy_tests.md)
    * [测试项目和测试数据目录](basics/testing_plugins/test_project_and_testdata_directories.md)
    * [编写测试](basics/testing_plugins/writing_tests.md)
    * [测试突出显示](basics/testing_plugins/testing_highlighting.md)
* [版本和兼容性](basics/getting_started/plugin_compatibility.md)
    * [打破API的变化](reference_guide/api_changes_list.md)
* 所需的技术
    * Kotlin
    * Gradle
    * 非JVM开发人员的入门
* [故障排除](basics/settings_caches_logs.md)
* [插件开发常见问题](faq.md)

## Part II - Architecture
* [介绍](basics/architectural_overview.md)
* 基础平台
    * 组件
    * 扩展点
* 项目模型
* [PSI](basics/architectural_overview/psi.md)
    * [PSI 文件](basics/architectural_overview/psi_files.md)
    * [文件视图提供者](basics/architectural_overview/file_view_providers.md)
    * [PSI 元素](basics/architectural_overview/psi_elements.md)
* 特征
* 产品
* [为 IntelliJ 平台做出贡献](basics/platform_contributions.md)
    * [IntelliJ 编码指南](basics/intellij_coding_guidelines.md)

## Part III - Base Platform
* [基本面](platform/fundamentals.md)
    * 组件模型
    * [穿线](basics/architectural_overview/general_threading_rules.md)
        * 背景任务
    * [消息传递基础结构](reference_guide/messaging_infrastructure.md)
* [用户界面组件](user_interface_components/user_interface_components.md)
    * [工具窗口](user_interface_components/tool_windows.md)
    * [对话框](user_interface_components/dialog_wrapper.md)
    * [弹出窗口](user_interface_components/popups.md)
    * [通知](user_interface_components/notifications.md)
    * [文件和类选择器](user_interface_components/file_and_class_choosers.md)
    * [编辑器组件](user_interface_components/editor_components.md)
    * [列表和树控件](user_interface_components/lists_and_trees.md)
    * [其他 Swing 组件](user_interface_components/misc_swing_components.md)
    * [图标和图像](reference_guide/work_with_icons_and_images.md)
    * [配色方案管理](reference_guide/color_scheme_management.md)
* 操作
* 设置
* [文件](basics/architectural_overview/files.md)
    * [虚拟文件](basics/architectural_overview/virtual_file.md)
    * 临时文件
* [文档](basics/architectural_overview/documents.md)
* [编者](reference_guide/editors.md)
    * [多意见](reference_guide/multiple_carets.md)
* [运行配置](basics/run_configurations.md)
    * [运行配置管理](basics/run_configurations/run_configuration_management.md)
    * [执行](basics/run_configurations/run_configuration_execution.md)
* [版本控制](reference_guide/vcs_integration_for_plugins.md)
    * 比较
    * 本地文件历史
* 索引
* 任务和上下文
* [本地化指南](reference_guide/localization_guide.md)
* 图
* 数据库工具

## Part IV - Project Model
* [Introduction](reference_guide/project_model.md)
* [Project](reference_guide/project_model/project.md)
    * [Project Wizard](reference_guide/project_wizard.md)
    * Frameworks
* [Module](reference_guide/project_model/module.md)
* [SDK](reference_guide/project_model/sdk.md)
* [Library](reference_guide/project_model/library.md)
* [Facet](reference_guide/project_model/facet.md)
* [Build System](reference_guide/project_model/build_system.md)
    * [External Builder API and Plugins](reference_guide/frameworks_and_external_apis/external_builder_api.md)
    * [External system integration](reference_guide/frameworks_and_external_apis/external_system_integration.md)

## Part V - PSI
* What is the PSI?
* PSI Elements
* Navigating the PSI
* Modifying the PSI
* [Indexing and PSI Stubs](basics/indexing_and_psi_stubs.md)
    * [File-based indexes](basics/indexing_and_psi_stubs/file_based_indexes.md)
    * [Stub indexes](basics/indexing_and_psi_stubs/stub_indexes.md)
* References
* Unified AST
* [XML DOM API](reference_guide/frameworks_and_external_apis/xml_dom_api.md)

## Part VI - Features
* Navigation
    * Go To Symbol
* Editing
    * Code Completion
    * Templates
        * Live Templates
        * File Templates
        * Surround Templates
    * QuickDoc
    * Intentions
* Analysing
    * Annotator
    * Inspections
        * Profiles
        * Scopes
        * Suppressing Highlights
        * Structural Search
* Refactoring
* Unit Testing

## Part VII - Product Specific
* Compatibility with Multiple Products
* [IntelliJ IDEA](products/idea.md)
    * [Tomcat Integration](reference_guide/tomcat_integration.md)
    * [Spring API](reference_guide/frameworks_and_external_apis/spring_api.md)
* [PhpStorm](phpstorm/phpstorm.md)
    * [Setting-up the Environment](phpstorm/setting_up_environment.md)
    * [PHP Open API](phpstorm/php_open_api.md)
    * [Existing Third Party Plugins](phpstorm/existing_plugins.md)
* [PyCharm](products/pycharm.md)
* DataGrip
* Android Studio
* Rider

## Part VIII - Custom Languages
* [Custom Language Support](reference_guide/custom_language_support.md)
    * [Registering File Type](reference_guide/custom_language_support/registering_file_type.md)
    * [Implementing Lexer](reference_guide/custom_language_support/implementing_lexer.md)
    * [Implementing Parser and PSI](reference_guide/custom_language_support/implementing_parser_and_psi.md)
    * [Syntax Highlighting and Error Highlighting](reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.md)
    * [References and Resolve](reference_guide/custom_language_support/references_and_resolve.md)
    * [Code Completion](reference_guide/custom_language_support/code_completion.md)
    * [Find Usages](reference_guide/custom_language_support/find_usages.md)
    * [Rename Refactoring](reference_guide/custom_language_support/rename_refactoring.md)
    * [Safe Delete Refactoring](reference_guide/custom_language_support/safe_delete_refactoring.md)
    * [Code Formatter](reference_guide/custom_language_support/code_formatting.md)
    * [Code Inspections and Intentions](reference_guide/custom_language_support/code_inspections_and_intentions.md)
    * [Structure View](reference_guide/custom_language_support/structure_view.md)
    * [Surround With](reference_guide/custom_language_support/surround_with.md)
    * [Go to Class and Go to Symbol](reference_guide/custom_language_support/go_to_class_and_go_to_symbol.md)
    * [Documentation](reference_guide/custom_language_support/documentation.md)
    * [Additional Minor Features](reference_guide/custom_language_support/additional_minor_features.md)
    * To do Explorer
    * Parameter Info
    * Parameter Hints
* Injected Languages
* Project Model
* Build System
* Compiler
* Debugger

## Part IX - Custom IDEs
* Build Your Own IDE
* Licensing

## Part X - Plugin Repository API
* [Introduction](plugin_repository/index.md)
* [API Reference](plugin_repository/api/api_reference.md)
    * [Plugin Upload](plugin_repository/api/plugin_upload.md)
    * [Plugins List](plugin_repository/api/plugins_list.md)
    * [Plugin Details](plugin_repository/api/plugin_details.md)
    * [Plugin Update Download](plugin_repository/api/plugin_download_update.md)
    * [Maven Interface](plugin_repository/api/maven_interface.md)
    * [Plugin Developers List](plugin_repository/api/plugin_developers.md)
* [Custom Release Channels](plugin_repository/custom_channels.md)

## Appendix I - Tutorials
* [Tutorials](tutorials.md)
* [Gradle Support](tutorials/build_system.md)
    * [1. Prerequisites](tutorials/build_system/prerequisites.md)
    * [2. Deployment](tutorials/build_system/deployment.md)
* [Custom Language Support](tutorials/custom_language_support_tutorial.md)
    * [1. Prerequisites](tutorials/custom_language_support/prerequisites.md)
    * [2. Language and File Type](tutorials/custom_language_support/language_and_filetype.md)
    * [3. Grammar and Parser](tutorials/custom_language_support/grammar_and_parser.md)
    * [4. Lexer and Parser Definition](tutorials/custom_language_support/lexer_and_parser_definition.md)
    * [5. Syntax Highlighter and Color Settings Page](tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.md)
    * [6. PSI Helpers and Utilities](tutorials/custom_language_support/psi_helper_and_utilities.md)
    * [7. Annotator](tutorials/custom_language_support/annotator.md)
    * [8. Line Marker Provider](tutorials/custom_language_support/line_marker_provider.md)
    * [9. Completion Contributor](tutorials/custom_language_support/completion_contributor.md)
    * [10. Reference Contributor](tutorials/custom_language_support/reference_contributor.md)
    * [11. Find Usages Provider](tutorials/custom_language_support/find_usages_provider.md)
    * [12. Folding Builder](tutorials/custom_language_support/folding_builder.md)
    * [13. Go To Symbol Contributor](tutorials/custom_language_support/go_to_symbol_contributor.md)
    * [14. Structure View Factory](tutorials/custom_language_support/structure_view_factory.md)
    * [15. Formatter](tutorials/custom_language_support/formatter.md)
    * [16. Code Style Settings](tutorials/custom_language_support/code_style_settings.md)
    * [17. Commenter](tutorials/custom_language_support/commenter.md)
    * [18. Quick Fix](tutorials/custom_language_support/quick_fix.md)
* [Writing Tests For Plugins](tutorials/writing_tests_for_plugins.md)
    * [1. Tests Prerequisites](tutorials/writing_tests_for_plugins/tests_prerequisites.md)
    * [2. Parsing Test](tutorials/writing_tests_for_plugins/parsing_test.md)
    * [3. Completion Test](tutorials/writing_tests_for_plugins/completion_test.md)
    * [4. Annotator Test](tutorials/writing_tests_for_plugins/annotator_test.md)
    * [5. Formatter Test](tutorials/writing_tests_for_plugins/formatter_test.md)
    * [6. Rename Test](tutorials/writing_tests_for_plugins/rename_test.md)
    * [7. Folding Test](tutorials/writing_tests_for_plugins/folding_test.md)
    * [8. Find Usages Test](tutorials/writing_tests_for_plugins/find_usages_test.md)
    * [9. Commenter Test](tutorials/writing_tests_for_plugins/commenter_test.md)
    * [10. Reference Test](tutorials/writing_tests_for_plugins/reference_test.md)
* [Action System](tutorials/action_system.md)
    * [1. Working With Custom Actions](tutorials/action_system/working_with_custom_actions.md)
    * [2. Grouping Actions](tutorials/action_system/grouping_action.md)
* [Editor Basics](tutorials/editor_basics.md)
    * [1. Working With Text](tutorials/editor_basics/working_with_text.md)
    * [2. Editor Coordinates System. Positions And Offsets](tutorials/editor_basics/coordinates_system.md)
    * [3. Handling Editor Events](tutorials/editor_basics/editor_events.md)
* [Project Wizard](tutorials/project_wizard.md)
    * [Adding New Steps to Project Wizard](tutorials/project_wizard/adding_new_steps.md)
    * [Supporting Module Types](tutorials/project_wizard/module_types.md)
* [Code Inspections](tutorials/code_inspections.md)
* [Code Intentions](tutorials/code_intentions.md)
* [Live Templates](tutorials/live_templates.md)
    * [1. Adding Live Template Support](tutorials/live_templates/template_support.md)\
* [Run Configurations](tutorials/run_configurations.md)
* [Supporting Frameworks](tutorials/framework.md)
* [Tree Structure View](tutorials/tree_structure_view.md)
* [Kotlin for Plugin Developers](tutorials/kotlin.md)

## Appendix II - Resources
* [Introduction](resources.md)
* [IntelliJ Community Edition on GitHub](https://github.com/JetBrains/intellij-community)
* [IntelliJ Plugins](https://github.com/JetBrains/intellij-plugins)
* [IntelliJ Scala Plugin](https://github.com/JetBrains/intellij-scala)
* [Gradle IntelliJ Plugin](https://github.com/JetBrains/gradle-intellij-plugin)
* [IntelliJ SDK Documentation](https://github.com/JetBrains/intellij-sdk-docs)
* [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier)
* [IntelliJ Plugin Developers Gitter](https://gitter.im/IntelliJ-Plugin-Developers/Lobby)
* [Community SDK Forum](https://intellij-support.jetbrains.com/hc/en-us/community/topics/200366979-IntelliJ-IDEA-Open-API-and-Plugin-Development)
* [Kotlin Reference](https://kotlinlang.org)
