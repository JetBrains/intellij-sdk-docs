<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Marketing

> Make sure to follow the guidelines from the [Plugin Overview page](https://plugins.jetbrains.com/docs/marketplace/best-practices-for-listing.html) for an optimal presentation of your plugin on JetBrains Marketplace.
>
> The _Busy Plugin Developers. Episode 2_ discusses [5 tips for optimizing JetBrains Marketplace plugin page](https://youtu.be/oB1GA9JeeiY?t=52) in more detail.
>
> Plugin vendors can also submit a blog post for the JetBrains Platform blog, see [Contributing to the Blog](https://plugins.jetbrains.com/docs/marketplace/contributing-to-the-blog.html) for details.

<link-summary>Widgets and badges for marketing material</link-summary>

## Embeddable Widgets

[JetBrains Marketplace](https://plugins.jetbrains.com) provides embeddable widgets that you can place on your website using a simple code snippet:

- Embeddable **Plugin Card** – renders an information card with the plugin name, icon, description, last update, and download counter,
- Embeddable **Install Plugin** Button – provides a button that allows installing your plugin right in the user's IDE, if it is currently open.

For more details, follow the [Embeddable Content](https://plugins.jetbrains.com/docs/marketplace/embeddable-content.html) section in the JetBrains Marketplace documentation.

## Readme Badges

Adding badges to the <path>README</path> files in open-source projects is common for providing additional information for users.

Below are listed a few badges related to the IntelliJ SDK and plugins development provided by [shields.io](https://shields.io):

> The following code snippets contain `:pluginId` and `:packageName` placeholders.
>
> `:pluginId` can be obtained from your plugin page URL, like: `https://plugins.jetbrains.com/plugin/6954-kotlin` – in this case `6954`.
>
> `:pluginId` also accepts a string ID that can be found in the <control>Versions</control> tab, like `https://plugins.jetbrains.com/plugin/6954-kotlin/versions`.
>
> `:packageName` for ReSharper accepts only string ID.
>
{style="note"}

### Downloads

**IntelliJ Plugins**

![Downloads](https://img.shields.io/badge/downloads-10M-brightgreen)
```
![Downloads](https://img.shields.io/jetbrains/plugin/d/:pluginId)
```

**ReSharper Plugins**

![Downloads](https://img.shields.io/badge/downloads-90k-brightgreen)
```
![Downloads](https://img.shields.io/resharper/dt/:packageName)
```

### Rating

**IntelliJ Plugin Numeric Rating**

![Rating](https://img.shields.io/badge/rating-4.5/5-brightgreen)
```
![Rating](https://img.shields.io/jetbrains/plugin/r/rating/:pluginId)
```

**IntelliJ Plugin Stars Rating**

![Rating](https://img.shields.io/badge/rating-%E2%98%85%E2%98%85%E2%98%85%E2%98%85%C2%BD-brightgreen){ignore-vars="true"}
```
![Rating](https://img.shields.io/jetbrains/plugin/r/stars/:pluginId)
```

### Version

**IntelliJ Plugins**

![Version](https://img.shields.io/jetbrains/plugin/v/IdeaVim)
```
![Version](https://img.shields.io/jetbrains/plugin/v/:pluginId)
```

**ReSharper Plugins**

![Version](https://img.shields.io/badge/resharper-v2017.2.0-blue)
```
![Version](https://img.shields.io/resharper/v/:packageName)
```

**ReSharper Plugins (incl. pre-releases)**


![Version](https://img.shields.io/badge/resharper-v2017.3.0--pre0001-yellow)
```
![Version](https://img.shields.io/resharper/v/:packageName?include_prereleases)
```

### Other Badges

**GitHub Actions Workflow**

![Build](https://github.com/JetBrains/intellij-sdk-docs/actions/workflows/code-samples.yml/badge.svg)
```
![Build](https://github.com/<OWNER>/<REPOSITORY>/actions/workflows/<WORKFLOW_FILE>/badge.svg)
```
