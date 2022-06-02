[//]: # (title: 18. Commenter)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

A commenter enables the user to comment-out a line of code at the cursor or selected code automatically.
The [`Commenter`](upsource:///platform/core-api/src/com/intellij/lang/Commenter.java) defines support for <menupath>Code | Comment with Line Comment</menupath> and <menupath>Code | Comment with Block Comment</menupath> actions.

## Define a Commenter

The commenter for Simple Language defines the line comment prefix as `#`.

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

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

Open the example Simple Language [properties file ](lexer_and_parser_definition.md#run-the-project) in the IDE Development Instance.
Place the cursor at the `website` line.
Select <menupath>Code | Comment with Line Comment</menupath>.
The line is converted to a comment.
Select <menupath>Code | Comment with Line Comment</menupath> again, and the comment is converted back to active code.

![Commenter](commenter.png)
