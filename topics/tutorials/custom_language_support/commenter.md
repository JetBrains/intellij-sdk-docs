[//]: # (title: 18. Commenter)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<tldr>

**Code**: [`SimpleCommenter`](%gh-sdk-samples%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCommenter.java)

**Testing**: [](commenter_test.md)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

A commenter enables the user to comment-out a line of code at the cursor or selected code automatically.
The [`Commenter`](%gh-ic%/platform/core-api/src/com/intellij/lang/Commenter.java) defines support for <ui-path>Code | Comment with Line Comment</ui-path> and <ui-path>Code | Comment with Block Comment</ui-path> actions.

## Define a Commenter

The [`SimpleCommenter`](%gh-sdk-samples%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCommenter.java) for Simple Language defines the line comment prefix as `#`.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCommenter.java"}

## Register the Commenter

The `SimpleCommenter` implementation is registered in the plugin configuration file using the `com.intellij.lang.commenter` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.commenter
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleCommenter"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the example Simple Language [properties file ](lexer_and_parser_definition.md#run-the-project) in the IDE Development Instance.
Place the cursor at the `website` line.
Select <ui-path>Code | Comment with Line Comment</ui-path>.
The line is converted to a comment.
Select <ui-path>Code | Comment with Line Comment</ui-path> again, and the comment is converted back to active code.

![Commenter](commenter.png)
