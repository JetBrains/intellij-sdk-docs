---
title: Plugins List API
---

This API endpoint provides a list of all plugins compatible with a provided IDE build:
 
```
https://plugins.jetbrains.com/plugins/list/?build=<productCode>-<buildNumber>
```

Where

* **productCode** is a two-character product code. Can be blank.

* **buildNumber** is a build number of the IDE (specified in the *About Dialog* in the product, and in the release notes).

e.g. for IntelliJ IDEA 2017.1 (`productCode=IU`, `buildNumber=171.3780.107`):

```
https://plugins.jetbrains.com/plugins/list/?build=IU-171.3780.107
```
