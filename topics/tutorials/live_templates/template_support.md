[//]: # (title: Providing Live Templates)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This tutorial illustrates how to add default Custom Live Templates to an IntelliJ Platform plugin, and assign valid contexts for these templates based on the surrounding code and file type.
In addition, the tutorial discusses how to export existing Live Templates, and bundle them within a plugin.
Any Live Template that can be created and exported can be added to a plugin by following the Template Creation, Export, and Extension Point Registration processes.

This tutorial uses the SDK code sample [`live_templates`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/live_templates).

## Template Creation

Get started by [creating a new Live Template](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html) within the IntelliJ Platform-based IDE:
* Add a new Template Group, "Markdown" and create a new Live Template under this group.
* Assign the template the abbreviation "**{**".
* Assign the description "**SDK: New link reference**".
* Paste the following snippet into the <control>Template text</control> field:

```text
[$TEXT$]($LINK$)$END$
```

The variables `$TEXT$` and `$LINK$` may be further configured in the <control>Edit variables</control> dialogue to reorder their precedence and bind to the functions that invoke auto-completion at the appropriate time.
In the <control>Edit variables</control> dialog, set the <control>Expression</control> for the `LINK` to `complete()` using the combo box.

There are many [predefined functions](https://www.jetbrains.com/help/idea/template-variables.html#predefined_functions) that developers should become familiar with before implementing any unique functionality in a plugin.

> Consider iteratively testing the Live Template using the current editor and a markdown file to minimize debugging later.
>
{type="tip"}

## Export the Live Template

Once the Live Template produces the expected result, [export the Live Template](https://www.jetbrains.com/help/idea/sharing-live-templates.html).
The export produces a file called <path>Markdown.xml</path> with the following contents:

```xml
<templateSet group="Markdown">
  <template
      name="{"
      value="[$TEXT$]($LINK$)$END$"
      description="SDK: New link reference"
      toReformat="false"
      toShortenFQNames="false">
    <variable
        name="TEXT"
        expression=""
        defaultValue=""
        alwaysStopAt="true"/>
    <variable
        name="LINK"
        expression="complete()"
        defaultValue=""
        alwaysStopAt="true"/>
  </template>
</templateSet>
```

The display `name` can also provide localized variants by specifying `key` and `resource-bundle` attributes additionally (2020.3 and later).

Copy this file into the [plugin's resources folder](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/live_templates/src/main/resources/liveTemplates).

## Implement TemplateContextType

A [`TemplateContextType`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/template/TemplateContextType.java) tells the IntelliJ Platform where the Live Template is applicable: Markdown files.
Every context must have a unique `TemplateContextType` defined for it, and the Platform defines many context types out of the box.
The `MarkdownContext` class defines it for Markdown files.
Ultimately, a file's extension determines the applicable Markdown context.

```java
```
{src="live_templates/src/main/java/org/intellij/sdk/liveTemplates/MarkdownContext.java"}

> Once the `MarkdownContext` is defined, be sure to add the new context type to the previously created Live Template settings file.
>
{type="note"}

Within the `<template>...</template>` elements in the <path>Markdown.xml</path> [Live Template definition file](#export-the-live-template), add the following context elements:

```xml
<template>
  <variable ... />
  <context>
    <option name="MARKDOWN" value="true"/>
  </context>
</template>
```

It is not always necessary to define your own `TemplateContextType`, as there are many existing template contexts already defined in the IntelliJ Platform.
Consider reusing one of the many existing template context types that inherit from `TemplateContextType` if you are augmenting language support to an existing area.

## Completing the Live Template Implementation

Depending on the version of the IntelliJ Platform, different steps are used to complete the implementation of the feature.

<tabs>
<tab title="2020.1 and later">

Using the `com.intellij.defaultLiveTemplates` and `com.intellij.liveTemplateContext` extension points, register the implementations with the IntelliJ Platform.
The `file` attribute in the `defaultLiveTemplates` element specifies `path/filename` under the <path>src/main/resources</path> folder.

```xml
<extensions defaultExtensionNs="com.intellij">
  <defaultLiveTemplates file="/liveTemplates/Markdown.xml"/>
  <liveTemplateContext
      implementation="org.intellij.sdk.liveTemplates.MarkdownContext"/>
</extensions>
```

</tab>

<tab title="Versions 2019.3 and Earlier">

The `MarkdownTemplateProvider` implementing [`DefaultLiveTemplatesProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/impl/DefaultLiveTemplatesProvider.java) tells the Platform where to find the Live Template settings file.
Make sure to include the full path to the file, relative to the <path>src/main/resources</path> directory, excluding the file extension.

```java
package org.intellij.sdk.liveTemplates;

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;
import org.jetbrains.annotations.Nullable;

public class MarkdownTemplateProvider implements DefaultLiveTemplatesProvider {
  @Override
  public String[] getDefaultLiveTemplateFiles() {
    return new String[]{"liveTemplates/Markdown"};
  }

  @Nullable
  @Override
  public String[] getHiddenLiveTemplateFiles() {
    return null;
  }
}
```

Using the `com.intellij.defaultLiveTemplatesProvider` and `com.intellij.liveTemplateContext` extension points, register the implementations with the IntelliJ Platform.

```xml
<extensions defaultExtensionNs="com.intellij">
  <defaultLiveTemplatesProvider
      implementation="org.intellij.sdk.liveTemplates.MarkdownTemplateProvider"/>
  <liveTemplateContext
      implementation="org.intellij.sdk.liveTemplates.MarkdownContext"/>
</extensions>
```

</tab>
</tabs>

## Check Plugin

Now verify the plugin is working correctly.
Run the plugin in a Development Instance and verify there is a new entry under <menupath>Settings/Preferences | Live Templates | Markdown | \{ (SDK: New link reference)</menupath>.

Finally, create a new file <path>test.md</path> and confirm that the Live Template works by entering a <shortcut>{</shortcut> character and then pressing <shortcut>Tab</shortcut>.
