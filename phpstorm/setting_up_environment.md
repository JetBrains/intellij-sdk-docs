---
title: Setting-up the Environment for PhpStorm Plugin Development
---

## General information
Follow steps that are described at [Getting Started with Plugin Development](/basics/getting_started.md).

There are two ways to develop plugins for PhpStorm:

1. Use IntelliJ IDEA Ultimate with the PHP plugin installed.
2. Use PhpStorm as a targeted IDE in a first place.

The choice affects how you will configure SDK for the plugin. In the first case you need to specify current installation of IntelliJ IDEA as SDK and in the second case you need to specify current installation of PhpStorm. This step is described in [Getting Started with Plugin Development](/basics/getting_started.md).

## How to use OpenAPI library

> OpenAPI is available for PhpStorm 6 and above.

This section explains how to configure the IntelliJ Platform for using PhpStorm OpenAPI.

### Adding library to a module

1. Open Project Structure **File \| Project Structure**
2. Select **Libraries**
3. Press **Add** button
4. Find and select `php-openapi.jar` and `php.jar`. They are located in `<your_installation_of_PhpStorm>/plugins/php/lib`.

    ![Adding Library](img/AddingLibrary.png)

5. Agree to add the libraries to your Module
6. Open **Modules \| Dependencies** and change **Scope** to **Provided**. This step is necessary because otherwise `ClassCastException` will be thrown because two instances of the library will be loaded via different class loaders

    ![Changing Scope](img/changingscope.png)

### Adding dependencies to `plugin.xml`

1. Open `plugin.xml` in the directory `META-INF`
2. Add two `<depends>` items to `plugin.xml`:

```xml
<depends>com.jetbrains.php</depends>
<depends>com.intellij.modules.platform</depends>
```
