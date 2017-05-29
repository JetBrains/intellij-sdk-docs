---
title: Upload Plugin API
---
 
Please note that maximum allowed plugin size is *200 MB*.


Parameters:

* **userName** is a plugin author username used to access the [JetBrains Plugin Repository](https://plugins.jetbrains.com) ([JetBrains Account](https://account.jetbrains.com) username or email).  
* **password** is a plugin author password used to access the [JetBrains Plugin Repository](https://plugins.jetbrains.com) ([JetBrains Account](https://account.jetbrains.com) password).
* **pluginId** is a numeric ID of the plugin, can be retrieved from the plugin repository URL. e.g. [Scala](https://plugins.jetbrains.com/plugin/1347-scala) plugin ID is *1347*. (*pluginXmlId* can be used instead)
* **pluginXmlId** is an unique identifier of the plugin specified as <id> in plugin.xml. Can be found as a *Plugin XML ID* parameter on the right of the plugin's individual update page and in the plugin.xml. (*pluginId* can be used instead)
* **channel** is a release channel the update is published to (empty channel means default *Stable* channel) (optional)

## POST
**Using pluginId**


Provide file as file contents. Curl command template:

```
curl -k -i -F userName=<userName> -F password=<password> -F pluginId=<pluginId> -F file=@<path to plugin .jar/.zip file> -F channel=<channel> https://plugins.jetbrains.com/plugin/uploadPlugin
```

Curl command example:

```
curl -k -i -F userName=pluginrobot -F password=123456 -F pluginId=5047 -F file=@Go-0.11.1197.zip -F channel=nightly https://plugins.jetbrains.com/plugin/uploadPlugin
```

**Using pluginXmlId**

Provide file as file contents. Curl command template:

```
curl -k -i -F userName=<userName> -F password=<password> -F xmlId=<pluginXmlId> -F file=@<path to plugin .jar/.zip file> -F channel=<channel> https://plugins.jetbrains.com/plugin/uploadPlugin
```

Curl command example:

```
curl -k -i -F userName=pluginrobot -F password=123456 -F xmlId=ro.redeul.google.go -F file=@Go-0.11.1197.zip -F channel=nightly https://plugins.jetbrains.com/plugin/uploadPlugin
```
