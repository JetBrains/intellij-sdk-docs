<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Search Field

<link-summary>UI guidelines on using search fields.</link-summary>

<tldr>

**Implementation:** [`SearchTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SearchTextField.java)

</tldr>

A search field is an [input field](input_field.md) that helps users locate objects, actions, or text.

![](search_field.png){width=706}

[//]: # (TODO: See another article for [search results]&#40;search_results.md&#41;.)

## When to use

Use a search field when specific objects are difficult to find at a glance:

* There are many objects, as in the VCS Log table or in the Editor with a large file opened.
* Objects aren't in a single location, for example, controls are located on different setting pages.

## How to use

### Label

Don't add a label <control>Search</control> to the search field. The magnifying glass icon is self-explanatory.

<table style="none" border="false">
    <tr>
        <td><format style="bold" color="Green">Correct</format><img src="search_field_label_correct.png" width="378"/></td>
        <td><format style="bold" color="Red">Incorrect</format><img src="search_field_label_incorrect.png" width="378"/></td>
    </tr>
</table>

### Placeholder

Provide hints such as search scope as placeholder text.

![](search_field_hints.png){width=706}

### Search and Replace fields

Mark the Search and Replace fields with placeholders when shown together to distinguish between them:

![](search_field_search_replace.png){width=706}

### Search options

Use [icon buttons](icon_button.md) inside the search field to save space and not to overload the UI.

![](search_field_search_options.png){width="706"}

Icon buttons should change background color when enabled so that it is clear which options are currently enabled.

![](search_field_enabled_icons.png){width=706}

### Tab navigation

Make icon buttons easily accessible with <control>Tab</control>. Focused button should have a colored border.

![](search_field_tab_focus.png){width=706}

### Clear a Search String

Show the <control>Clear</control> button <icon src="../../../images/ui/search_field/search_field_close_icon.png" width="20"/> when the search field has been filled.
When the button is clicked, clear the search field and restore the content to its default state.

![](search_field_clear_search.png){width=706}

When search can be configured with options, show the clear button on the left of the option buttons.
This way, option buttons won't jump when the clear button appears.

![](search_field.png){width=706}

### Multiline search strings

If multiline search is needed, use the New Line <icon src="../../../images/ui/search_field/search_field_new_line_icon.png" width="16"/> action button. It should be placed on the left of the rest of the search options.

![](search_field_multiline.png){width=706}

The default shortcut for the New Line action: <shortcut>⇧⌘⏎</shortcut> for macOS and <shortcut>Ctrl + Shift + Enter</shortcut> for Windows/Linux.

### Filters

Provide filters for complex searches. Don't use filter attributes in the search query.

<table style="none" border="false">
    <tr>
        <td><format style="bold" color="Green">Correct</format><img src="search_field_attributes_correct.png" width="378"/></td>
        <td><format style="bold" color="Red">Incorrect</format><img src="search_field_attributes_incorrect.png" width="378"/></td>
    </tr>
</table>

### Search history

Use the magnifying glass icon with an arrow when search history is available. Show search history in a popup when clicking on the magnifying glass icon.

![](search_field_history.png){width=706}

The keyboard shortcut for the History action is <shortcut>Alt + Down</shortcut> on all systems.

Add a search string to history:

* On <shortcut>Enter</shortcut> for searches that are executed.
* When the search field loses focus for searches that are executed on any keystroke.

## Sizes and placement

### Placement

Put the search field on top of the content that is being searched and align them vertically.
For example, the search field in <control>Settings</control> dialog is aligned with the filtered settings tree.

![](search_field_placement.png){width=706}

<!-- #### Inline search

Fit the search field to a popup window or to a toolbar if it has top borders. This way the overall form looks less complex.

<table style="none" border="false">
    <tr>
        <td><format style="bold" color="Green">Correct</format><img src="search_field_placement_correct.png" width="378"/></td>
        <td><format style="bold" color="Red">Incorrect</format><img src="search_field_placement_incorrect.png" width="378"/></td>
    </tr>
</table> -->

#### Speed Search

Show the search field on demand when search is supplementary to other functionality or if space is limited. The search field should be accessible with <shortcut>cmd+F</shortcut> on macOS and <shortcut>Ctrl + F</shortcut> on Windows/Linux.
For example, space in the Project view is limited and too loaded to always show the Search bar:

![](search_field_project.png){width=706}

### Sizes

#### Grouped with other controls {#grouped-search-size}

For a search field which is displayed as an input field in a group of controls, for example, in a tool window toolbar, follow the [input sizes guideline](input_field.md#sizes-and-placement).

#### Above lists and trees {#list-trees-search-size}

When placed above lists or trees, expand the field to the width of the list or tree.

#### Speed search {#speed-search-size}

For a search field shown on demand, for example, in the <control>Project</control> tool window, expand the field to the width of the tool window.

<!-- #### In a toolbar {#toolbar-search-size}

* For a search field shown without a toolbar, for example, Search in trees, the search width could be adjusted on typing to accommodate input.
* For a search field that is not fitted to a toolbar, set the minimum width to 200 px. -->
