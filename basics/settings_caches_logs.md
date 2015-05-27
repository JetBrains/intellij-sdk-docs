---
title: Settings, Caches, Logs, and Plugins
layout: editable
---

Every IntelliJ-based IDE stores it's local settings, caches, log and installed plugins on a hard drive.
Location of the IDE files depends on the operating system, product and version.
IDE settings directory name is made up of the product code name and product version.
We will use ```<product_system_name><product_version>``` as an alias for the product internal settings home directory name.

## Directories Location


### Windows or Linux

* **Windows:**
  You can find internal configuration data in:
  ```<User home>\.<product_system_name><product_version>```

* **Linux:**
  You can find internal configuration data in:
  ```~/.<product_system_name><product_version>```


  In the folder you can find two subdirectories:

  * *config* that contains user-specific settings.

  * *system* that stores IntelliJ IDEA data caches.

### Mac OS X

  You can find internal configuration data in:

  ```/Applications/<product_system_name><product_version>/Contents/plugins``` contains the catalog with plugins.

  ```~/Library/Preferences/<product_system_name><product_version>``` contains the rest of the configuration settings.

  ```~/Library/Caches/<product_system_name><product_version>``` contains data caches, logs, local history, etc.

  These files can be quite significant in size.

  ```~/Library/Logs/<product_system_name><product_version>``` contains logs.

## Product System Names

* IntelliJ IDEA Ultimate Edition - **IntelliJIdea**
* IntelliJ IDEA Community Edition - **IdeaIC**
* Ruby Mine IDE - **RubyMine**
* PhpStorm and WebStorm before 7.0 - **WebIde**
* WebStorm starting from 7.0 version - **WebStorm**
* PyCharm - **PyCharm**
* AppCode - **appCode**
* CLion - **clion**
* 0xDBE Data Base IDE - **0xDBE**

## Example

To go to the *IntelliJ IDEA 14* plugin directory on Mac execute the following command:

```bash
    cd /Applications/IntelliJ\ IDEA\ 14.app/Contents/plugins
```