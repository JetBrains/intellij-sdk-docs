<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Capitalization

<link-summary>UI guidelines for using title and sentence capitalization in various contexts.</link-summary>

## Title

> The current Windows guidelines use sentence capitalization for actions. Some Microsoft products still title-capitalize actions according to the previous guidelines. macOS also title-capitalizes actions. Since IntelliJ IDEs are cross-platform, we use title capitalization as well.
>
{style="note"}

Use for:

* Actions in buttons, menus and tooltips
* Headers in tables, popups, message boxes and dialogs
* Headers of UI control groups

Do **not** use for:

* Actions in links
* Actions in the popup on <shortcut>Alt+Enter</shortcut>: quick-fixes, intention actions, and others
* Headers in notifications

### Rules {id="rules_1"}

Always capitalize the first and the last words.

Capitalize the words in-between except:

* articles: *a, an, the*,
* coordinating conjunctions: *and, or, but*,
* prepositions of four and fewer letters which are not a part of a phrasal verb: *in, with*.

In hyphenated phrases, capitalize all nouns, adjectives and adverbs: *Auto-Indent, Command-Line Launcher*.
Do not capitalize articles, prepositions and conjunctions: *Side-by-Side, Drag-and-Drop*.

### Examples {id="examples_1"}

- _Compare With…_ — the short preposition *with* is capitalized as the last word.
- _Compare with Latest Repository Version_ — *with* is not capitalized when in the middle.
- _Check Out from Version Control_ — *out* is a part of the phrasal verb *check out* and is capitalized.

## Sentence

Use for:

* Labels of UI controls: text boxes, checkboxes, radio buttons, combo boxes, etc.
* Items in combo boxes, lists, trees and tables
* Links
* Actions in the popup on Alt+Enter
* Header and body text in notifications
* Body text in error messages, tooltips, status descriptions and any kind of instructions

### Rules

Capitalize the first word in a sentence, proper nouns and adjectives, and abbreviations.

### Examples

- _Control+Shift+F_ — keyboard button names are considered proper nouns.
- _Accept the terms of the License Agreement_ — License Agreement is a proper noun.
- _Side-by-side layout_ — only the first word of a hyphenated phase is capitalized.
