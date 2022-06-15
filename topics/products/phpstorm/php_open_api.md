[//]: # (title: PHP Open API)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> Please join the dedicated [intellij-php](https://jetbrains-platform.slack.com/archives/C5P9YB0LT/p1653913208725609) Slack channel to discuss PHP related plugin development.
>
{type="tip"}

## Dependency in `plugin.xml`

```xml
<depends>com.jetbrains.php</depends>
```

## PHP PSI

`com.jetbrains.php.lang.psi.elements.*;`

## Utility Classes

### `PhpFilePathUtils`

`com.jetbrains.php.lang.psi.elements.impl.PhpFilePathUtils` contains helper methods for working with paths from PSI elements.

- `getFileName()` returns a constant string representation of the path from the PSI element.

    For example, for the expression:

    ```php
    // in file: /bin/folder/file.php
    __DIR__ . "/file2.php";
    ```

    it returns `"/bin/folder/file2.php"`.

    Note that the element passed as an argument can contain any expression that can be statically evaluated to a constant.

- `getReferences()` returns all path references from the PSI element.

    Using this method in conjunction with
    [PsiReferenceContributor](upsource:///platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java)
    you can add autocompletion for string literals in the specific contexts.
    For example, if certain PHP functions in your code accept paths, you can autocomplete them when writing arguments.

    Note that PhpStorm automatically adds references for concatenation expressions with `__DIR__` or `dirname(__FILE__)` function call.

## PHP Extension Points

> See [](php_extension_point_list.md) for the complete list.
>
{type="note"}
