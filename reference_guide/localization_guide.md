---
title: Localization Guide
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The purpose of the document is to describe steps necessary to create localized versions of IDEA.

## Application Bundle Layout

In regards to localization purpose all the resources (in English) that need to be translated are located in jar files called
***resources_en.jar***.
There's one such jar file for IDEA core functionality located at
***%INST_HOME%\lib\resources_en.jar***
and one jar for each of bundled plugins at
***%INST_HOME%\plugins\$Plugin$\lib\resources_en.jar***.

Translated resources should be jarred and placed exactly in the same folder original jar comes from.
So localization pack should have exactly the same number of jar files and they have to be laid out in exactly the same way original jars are laid out.
In order to enable multiple localizations per installation without localization packs overriding each other we suggest to include the name of the locale in the jar name (for example, ***resources_ja.jar***).

## Content and Layout of resources_en.jar

Property files  usually contain messages, menu items, dialog labels texts etc.
For every such file localized jar should contain translated version that is placed to exactly the same path relative to the jar root and has exactly the same name as original file plus locale identifier.
For example ***messages/ActionsBundle.properties*** file from ***resources_en.jar*** should have its translated version ***messages/ActionsBundle_ja.properties*** file in ***resources_ja.jar***.
All property files should be ASCII encoded with *\uXXXX* sequences used for characters that have no their representation in ASCII range.
See
[native2ascii](https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/native2ascii.html)
tool for more details.

Property values mostly follow MessageFormat rules.

> **NOTE** Due to historic reasons main menu, toolbar, popup menus and other actions have their mnemonic char prefixed with `\_` (underscore) char while all other mnemonics like those for checkboxes, buttons etc. use `&` (ampersand) sign for the same purpose.  Moreover one can encounter `&&` (double ampersand) in some places, which denote alternative mnemonic to be used under MacOS X (mnemonics mapped to `U`, `I`, `O`, `N` chars won't work there).  Generally, use the same mnemonic denotation used in original property value and everything will be OK.

## Components Location

*  **Inspection descriptions**  appear in Settings|Errors and represent short information about what each of the inspection tools is intended to do.
Each description is represented by single html file under ***/inspectionDescriptions/*** folder that should be encoded in UTF-8 encoding.
Localized versions should be stored in folder suffixed with locale instead. For instance ***/inspectionDescriptions/CanBeFinal.html*** from ***resources_en.jar*** translation should be placed in ***/inspectionDescriptions_ja/CanBeFinal.html*** in ***resources_ja.jar***.

*  **Intention descriptions and samples**  are very similar to inspection descriptions but the layout is a bit more advanced.
Every intention has a bunch of files located in the folder named after intention's short name in /intentionDescriptions/.
These files include description.html, which holds description similar to inspection one plus a couple of template files demonstrating what the intention will do on a sample.
Those templates are optional to translate. Similar to inspection descriptions whole intentionDescriptions folder should be suffixed with locale identifier.
For instance ***/intentionDescriptions/AddOnDemandStaticImportAction/description.html*** translation should be placed in ***/intentionDescriptions_ja/AddOnDemandStaticImportAction/description.html***.
All the HTML files should be UTF-8 encoded.

*  **Tips of the day**  follow the same pattern inspections and intentions do.
For instance translation of ***/tips/AdaptiveWelcome.html*** goes to ***/tips_ja/AdaptiveWelcome.html***.
The only thing special about tips is they use special pattern for denoting shortcuts like *EnterAction*;
will be replaced to keystroke mapped to *EnterAction* in currently used keymap at run-time.
So please make sure you leave such sequences intact while translating.
Remember UTF-8 encoding.

*  **File templates**  again go the same way (if at all should be translated).
***/fileTemplates/Singleton.java.ft*** goes to ***/fileTemplates_ja/Singleton.java.ft***.

Following Sun rules for property bundles whenever certain resource cannot be found in localized version its default version from ***resources_en.jar*** will be used instead.

