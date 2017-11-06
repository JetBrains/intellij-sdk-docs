---
title: PyCharm
---

## Which API should be used by PyCharm plugin developers?

### Platform Open API

The APIs from the standard set of Platform Open API modules can be safely used in plugins. Public symbols defined in these modules have a lifecycle, and will be marked as deprecated for several releases before being removed.

The modules are [defined in the `CommunityRepositoryModules.groovy` file](https://github.com/JetBrains/intellij-community/blob/master/platform/build-scripts/groovy/org/jetbrains/intellij/build/CommunityRepositoryModules.groovy).

### PyCharm Open API

Symbols defined in the **python-psi-api** and **python-openapi** modules are also public API and considered stable.

### Other API

Classes and methods defined in other modules are used at your own risk, and care should be taken to test your plugin with any version of PyCharm you wish to support.
