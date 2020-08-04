---
title: Adding Live Templates to a Plugin
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This tutorial illustrates how to add default Custom Live Templates to an IntelliJ Platform plugin, and assign valid contexts for these templates based on the surrounding code and file type. 
In addition, the tutorial discusses how to export existing Live Templates, and bundle them within a plugin.
Any Live Template that can be created and exported can be added to a plugin by following the Template Creation, Export, and Extension Point Registration processes.

This tutorial uses the SDK code sample [`live_templates`](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/live_templates).

* bullet
{:toc}

## Template Creation
Get started by [creating a new Live Template](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html) within the IntelliJ Platform-based IDE:
* Add a new Template Group, "Markdown" and create a new Live Template under this group. 
* Assign the template the abbreviation "**{**".
* Assign the description "**SDK: New link reference**". 
* Paste the following snippet into the *Template text* field:

```text
[$TEXT$]($LINK$)$END$
```

The variables `$TEXT$` and `$LINK$` may be further configured in the *Edit variables* dialogue to reorder their precedence and bind to functions that invoke auto-completion at the appropriate time.
In the *Edit variables* dialog, set the `Expression` for the `LINK` to `complete()` using the combobox.

There are many other [predefined functions](https://www.jetbrains.com/idea/help/creating-and-editing-template-variables.html) that developers should become familiar with before implementing any unique functionality in a plugin.

> **TIP** Consider iteratively testing the Live Template using the current editor and a markdown file to minimize debugging later.

## Export the Live Template
Once the Live Template produces the expected result, [export the Live Template](https://www.jetbrains.com/help/idea/sharing-live-templates.html). 
The export produces a file called `Markdown.xml` with the following contents:

```xml
<templateSet group="Markdown">
  <template name="{" 
            value="[$TEXT$]($LINK$)$END$" 
            description="SDK: New link reference" 
            toReformat="false" 
            toShortenFQNames="false">
    <variable name="TEXT" expression="" defaultValue="" alwaysStopAt="true" />
    <variable name="LINK" expression="complete()" defaultValue="" alwaysStopAt="true" />
  </template>
</templateSet>
```

Copy this file into the [plugin's resources folder](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/live_templates/src/main/resources/liveTemplates).

## Implement TemplateContextType
A [`TemplateContextType`](upsource:///platform/lang-api/src/com/intellij/codeInsight/template/TemplateContextType.java) tells the IntelliJ Platform where the Live Template is applicable: Markdown files.
Every context must have a unique `TemplateContextType` defined for it, and many context types are defined by the Platform.
The `MarkdownContext` class defines it for Markdown files.
Ultimately, a file's extension determines the applicable Markdown context.

```java
{% include /code_samples/live_templates/src/main/java/org/intellij/sdk/liveTemplates/MarkdownContext.java%}
```

> **NOTE** Once the `MarkdownContext` is defined, be sure to add the new context type to the previously created Live Template settings file. 

Within the `<template>...</template>` elements in the `Markdown.xml` [Live Template definition file](#export-the-live-template), add the following context elements:

```xml
    <variable.../>
    <context>
      <option name="MARKDOWN" value="true"/>
    </context>
  </template>
```

It is not always necessary to define your own `TemplateContextType`, as there are many existing template contexts already defined in the IntelliJ Platform. 
Consider reusing one of the many existing template context types that inherit from `TemplateContextType` if you are augmenting language support to an existing area.

## Completing the Live Template Implementation 
Depending on the version of the IntelliJ Platform, different steps are used to complete the implementation of the feature.

### Versions 2020.1 and Later
For 2020.1 and later, follow this section to register the extension points and then proceed to the [Check Plugin](#check-plugin) section.

#### Register Extension Points
Using the `com.intellij.defaultLiveTemplates` and `com.intellij.liveTemplateContext` extension points, register the implementations with the IntelliJ Platform.
The `file` attribute in the `defaultLiveTemplates` element specifies `path/filename` under the `src/main/resources` folder.

```xml
  <extensions defaultExtensionNs="com.intellij">
    <defaultLiveTemplates file="/liveTemplates/Markdown.xml"/>
    <liveTemplateContext implementation="org.intellij.sdk.liveTemplates.MarkdownContext"/>
  </extensions>
```

Now go to the [Check Plugin](#check-plugin) section to test the template.

### Versions 2019.3 and Earlier
For older versions of the IntelliJ Platform follow this section to complete the implementation and register the extension points.
Then proceed to the [Check Plugin](#check-plugin) section.
 
#### Implement DefaultLiveTemplatesProvider
The `MarkdownTemplateProvider` tells the Platform where to find the Live Template settings file. 
Make sure to include the full path to the file, relative to the `src/main/resources` directory, excluding the file extension.

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

#### Register Extension Points
Using the `com.intellij.defaultLiveTemplatesProvider` and `com.intellij.liveTemplateContext` extension points, register the implementations with the IntelliJ Platform.

```xml
  <extensions defaultExtensionNs="com.intellij">
    <defaultLiveTemplatesProvider implementation="org.intellij.sdk.liveTemplates.MarkdownTemplateProvider"/>
    <liveTemplateContext implementation="org.intellij.sdk.liveTemplates.MarkdownContext"/>
  </extensions>
```

## Check Plugin
Now verify the plugin is working correctly. 
Run the plugin in a Development Instance and verify there is a new entry under **Settings/Preferenes \| Live Templates \| Markdown \| \{ (SDK: New link reference)**. 
Finally, create a new file `Test.md` and confirm that the Live Template works by entering a <kbd>{</kbd> character and then pressing <kbd>Tab</kbd>.