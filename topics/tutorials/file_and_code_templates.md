<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# File and Code Templates

<link-summary>File templates allow generating files and code fragments containing repetitive text and patterns.</link-summary>

<tldr>

**Product Help:** [File and Code Templates](https://www.jetbrains.com/help/idea/settings-file-and-code-templates.html), [File templates](https://www.jetbrains.com/help/idea/using-file-and-code-templates.html), [Templates with multiple files](https://www.jetbrains.com/help/idea/templates-with-multiple-files.html)

</tldr>

The _File templates_ mechanism allows generating files and code fragments containing repetitive text and patterns.
Its main purpose is to relieve users from unnecessary manual work by generating boilerplate code automatically.

File templates can be used to create new project files populated with predefined content like code scaffolds or license headers that are specific to a certain file type and context.
For example, when a new Java class is created in IntelliJ IDEA, the file already contains a class declaration with the provided name and empty body.
File Templates are not limited to creating a single file.
It is possible to create [templates with child/multiple files](https://www.jetbrains.com/help/idea/templates-with-multiple-files.html) used for a set of related files, e.g., model, view and controller classes, at once in an MVC framework.

Another use case is generating code fragments from code intentions and fixes, e.g., adding a test method inside an existing test class or a quick fix for adding a missing interface method implementation.

As file templates are based on [Apache Velocity](https://velocity.apache.org/), their content is not static.
A template can contain dynamic parts based on the context, e.g., project or package name, created entity name, author data, and more.
It is also possible to create custom properties and populate them with the required values.

The contents of all listed file templates can be edited in the IDE settings so that users can adjust them to their specific needs.

These sections describe how to add File and Code Templates, and their associated building blocks, to plugins:
* [](providing_file_templates.md)
* [](using_file_templates.md)
