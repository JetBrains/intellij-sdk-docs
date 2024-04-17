<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Internationalization

<link-summary>Tips about implementing message bundles, organizing translations, etc.</link-summary>

TODO

[//]: # (Property values mostly follow MessageFormat rules.)
[//]: # ()
[//]: # (> Due to historic reasons main menu, toolbar, popup menus and other actions have their mnemonic char prefixed with `_` &#40;underscore&#41; char while all other mnemonics like those for checkboxes, buttons etc. use `&` &#40;ampersand&#41; sign for the same purpose.)
[//]: # (> Moreover one can encounter `&&` &#40;double ampersand&#41; in some places, which denote alternative mnemonic to be used under macOS &#40;mnemonics mapped to `U`, `I`, `O`, `N` chars won't work there&#41;.)
[//]: # (> Generally, use the same mnemonic denotation used in the original property value and everything will be OK.)
[//]: # (>)
[//]: # ({style="note"})

[//]: # (TODO: update link in settings_guide)
