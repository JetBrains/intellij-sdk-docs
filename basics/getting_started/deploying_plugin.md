---
title: Deploying a Plugin
layout: editable
---

To use your custom plugin, it must be built, installed, and then enabled using Plugin Manager.
This section outlines steps to deploy your plugins.

To deploy a plugin:

* Make your project by invoking **Build \| Make Project**.

* Prepare your plugin for deployment.
  To do this, on the main menu, choose **Build \| Prepare Plugin Module \<module name\> for Deployment**.
  If the plugin module does not depend on libraries, the .jar archive will be created.
  Otherwise, a .zip archive will be created. It will include all the plugin libraries specified in the project settings.

* Copy the newly created archive file to the *.IntelliJIDEAx0\config\plugins* folder, and then restart Intellij IDEA for changes to take effect.

* On the main menu, choose **File \| Settings**.
  The Settings dialog box opens.

* In the Settings dialog box, under **IDE Settings**, click **Plugins**.

* In the Plugins area, open the **Installed** tab, and then select the check box next to your plugin name.

* When finished, click OK to close the Settings dialog box.

* For your changes to take effect, restart *Intellij IDEA*.