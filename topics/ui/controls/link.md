<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Link

<link-summary>UI guidelines on using links.</link-summary>

<tldr>

**Implementation:** [`ActionLink`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/ActionLink.kt), [`DropDownLink`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/DropDownLink.kt)

</tldr>

![](01_link_example.png){width=120}

## When to use

Use a regular link for navigation between pages of the same window.

![](02_deployment_server.png){width=494}

*This option is in the Settings dialog. "Configure servers" opens another page of the same dialog.*

![](03_manage_scopes.png){width=411}

*Do not use a button to navigate to another page of the same window.*

Use an external link for navigation to web resources. See below for the [external link icon](#external-link-icon).

![](04_external_link.png){width=248}

Use a regular or [drop-down link](#drop-down-link) for secondary actions in packed or small UI areas.

![](05_action_regular_link.png){width=367}

*The "Reset" action is a link for two reasons: (1) The action appears only when the default value in the field is changed and reverting to a default is considered a rare scenario. (2) A lightweight link fits better than a button in this busy layout.*

![](06_action_dropdown.png){width=550}

*The "Modify options" drop-down link fits into the top right corner of a busy layout.*

Do <control>not</control> use links for primary actions or when a UI is not constrained.
Use a [button](button.topic) or a [drop down list](drop_down.md) instead.
These controls can be selected from the keyboard and are bigger and easier to click.

![](07_android_sdk.png){width=451}

## How to use

### Writing guidelines

Use sentence capitalization.

Add an ellipsis to a button-link if it opens another UI area where input is possible. See examples for the [Button](button.topic).

Do not use words like "navigate" or "click here". A link already implies navigation or clicking.

![](08_writing_1.png){width=398}

When possible, replace "Learn more" and other generic phrases with more informative ones.

![](08_writing_2.png){width=316}

### Link as a part of text

A link can be a part of a checkbox or radio button label or of any stand-alone text, like the text in an [empty state](empty_state.md) or in [context help](context_help.md).

![](09_part_of_text_1.png){width=403}

Make a link the minimum phrase that is enough to understand what will happen without reading the whole text.

![](09_part_of_text_2.png){width=489}

### Link in a tree or table

Use a link as a secondary action for some items of a tree or table.

![](10_tree.png){width=387}

If an action is needed for all items of a tree or table:

* Add an [icon button](icon_button.md) to a toolbar.
* For a list of choices, add a separate table column of drop-down cells (see [Table](table.md#editing-values)).
* If a link in every tree or table line has meaningful formatting, leave it:
  ![](11_table.png){width=342}
  *Links in the "Push Commits" window have the format that helps understand the relation between them: "[remote repository] : [branch]". Moving these links elsewhere would break this meaning.*

### External link icon

Always add the arrow icon for an external link. The icon shows that the user will switch to a browser and lose the current context.

![](04_external_link.png){width=248}
<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val externalLink = ActionLink("External link") {
  BrowserUtil.browse("https://www.jetbrains.com")
}.apply {
  setExternalLinkIcon()
}
```

</tab>
<tab title="Java" group-key="java">

```java
ActionLink externalLink = new ActionLink(
    "External link",
    event -> BrowserUtil.browse("https://www.jetbrains.com")
);
externalLink.setExternalLinkIcon();
```

</tab>
</tabs>

An exception is a help topic link in [empty states](empty_state.md).
The help icon already hints that this is an external help resource, so the arrow icon is unnecessary.

![](12_external_link_no_icon.png){width=164}

### Drop-down link

Drop-down links show a context menu or a popup.
Use [`DropDownLink`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/DropDownLink.kt) to implement a drop-down link.

![](13_drop_down_links.png){width=445}

## Placement

Lay out button-links as buttons and dropdown-links as combo boxes. See [Layout](layout.md).

## Built-in behavior

A focused link is activated from the keyboard with <shortcut>Space</shortcut>.

