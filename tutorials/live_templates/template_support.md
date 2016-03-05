---
title: 1. Adding Live Template Support
---

## Template Creation

Initially, you will need to [create a new Live Template](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html#d1476224e158) from scratch. Add a new Template Group, "Markdown" and create a new Live Template under this group. Then give the template an abbreviation (ex. "[") and a description (ex. "New markdown link"). Paste the following snippet into the *Template text*:

```
[$TEXT$]($LINK$)$END$
```

The variables `$TEXT$` and `$LINK$` may be further configured in the *Edit variables* dialogue, to reorder their precedence and bind to functions that will invoke auto-completion at the appropriate time, among many other [useful functions](https://www.jetbrains.com/idea/help/creating-and-editing-template-variables.html). Developers should become familiar with the provided functions before implementing any special functionality in a plugin, in case the desired feature is available as a [predefined function](https://www.jetbrains.com/idea/help/creating-and-editing-template-variables.html#predefined_functions).

Finally, give your new Live Template an applicable context (ie. "Everywhere" or "Other").

## Export the Live Template

Once confident the Live Template produces the expected result (consider testing it inside the current editor to minimize debugging later), export the Live Template (**File \| Export Settings \| ☑ Live Templates**). Unpack the resulting archive, and inside a directory `./templates/` there will be a file  called `Markdown.xml` with the following contents:

```xml
<templateSet group="Markdown">
  <template name="[" value="[$TEXT$]($LINK$)$END$" description="New link reference." toReformat="false" toShortenFQNames="false">
    <variable name="TEXT" expression="" defaultValue="" alwaysStopAt="true" />
    <variable name="LINK" expression="complete()" defaultValue="" alwaysStopAt="true" />
    <context>
      <option name="OTHER" value="true" />
    </context>
  </template>
</templateSet>
```

Copy this file into your plugin's resources, (eg. `project/resource/liveTemplates/Markdown.xml`.

## Implement DefaultLiveTemplatesProvider

The [`DefaultLiveTemplatesProvider`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/template/impl/DefaultLiveTemplatesProvider.java) tells us where to find the Live Template settings file. Make sure to include the full path to the file, relative to the resources directory, excluding the file name.

```java
{% include /code_samples/live_templates/src/MarkdownTemplateProvider.java %}
```

## Implement TemplateContextType

A [`TemplateContextType`](upsource:///platform/lang-api/src/com/intellij/codeInsight/template/TemplateContextType.java) tells us where the live template is applicable.

```java
{% include /code_samples/live_templates/src/MarkdownContext.java %}
```

Once you define the `TemplateContextType`*, be sure to add the assigned context type to the previously created Live Template settings file. Under `<template>...</template>` add the following context:

```xml
<context>
  <option name="MARKDOWN" value=true />
</context>
```

*It is not always necessary to define your own `TemplateContextType`, as there are many existing template contexts already defined in the IntelliJ Platform. Consider reusing one of the [many existing template contexts](https://upsource.jetbrains.com/idea-community/file/d190fa2f2741067587fd14c6f771302dda6fcc18/platform/lang-api/src/com/intellij/codeInsight/template/TemplateContextType.java?hierarchy=/platform/lang-api/src/com/intellij/codeInsight/template/TemplateContextType.java:46&nav=0:0:focused) if you are augmenting language support to an existing area.

## Register Extension Points

```xml
{% include /code_samples/live_templates/resources/META-INF/plugin.xml %}
```

## Check Plugin

Now check that the plugin is working correctly. Run the plugin and verify there is a new entry under *File \| Settings \| Live Templates \| Markdown \| \[*. Finally, create a new file `Test.md` and confirm that the Live Template works.