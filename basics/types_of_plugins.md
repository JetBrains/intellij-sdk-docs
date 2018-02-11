---
title: 插件的主要类型
---

基于对产品 *的IntelliJ平台* 可以通过添加插件进行修改和调整定制的目的。所有下载插件都可以在[JetBrains的插件库](https://plugins.jetbrains.com/)。

最常见的类型的插件包括：

* 自定义语言支持
* 框架集成
* 工具集成
* 用户界面加载项



## 自定义语言支持[](http://127.0.0.1:4000/intellij/sdk/docs/basics/types_of_plugins.html#custom-language-support)

自定义语言支持提供基本功能与特定的编程语言工作。这包括：

* 文件类型识别
* 词法分析
* 语法高亮
* 格式化
* 代码洞察和代码完成
* 检查和快速修复
* 意向行为

请参阅[自定义语言支持教程](http://127.0.0.1:4000/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html)，以了解更多的话题。



## 框架集成[](http://127.0.0.1:4000/intellij/sdk/docs/basics/types_of_plugins.html#framework-integration)

框架集成包括改进的代码洞察功能，这是典型的给定的框架，以及直接从IDE中使用框架特定功能的选项。有时，它也包括一个自定义的语法或DSL语言支持的元素。

* 具体代码洞察
* 直接访问特定于框架的功能

请参考[Struts 2的插件](https://plugins.jetbrains.com/plugin/1698)作为框架集成的例子。



## 工具集成[](http://127.0.0.1:4000/intellij/sdk/docs/basics/types_of_plugins.html#tool-integration)

工具集成能够直接从IDE操纵第三方工具和组件，而无需切换上下文。

这意味着：

* 额外行动的实施
* 相关的UI组件
* 访问外部资源

请参阅[格里特集成](https://plugins.jetbrains.com/plugin/7272?pr=idea)插件作为一个例子。



## 用户界面加载项[](http://127.0.0.1:4000/intellij/sdk/docs/basics/types_of_plugins.html#user-interface-add-ons)

这一类插件适用于IDE的标准用户界面的各种变化。一些新加入的成分是互动的，并提供新的功能，而另一些则仅限于视觉修改。所述[背景图像](https://plugins.jetbrains.com/plugin/72)的插件可以作为一个例子。


