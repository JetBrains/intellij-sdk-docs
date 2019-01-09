---
title: Upload Plugin API
---
 
Please note that maximum allowed plugin size is *200 MB*.

You should create a [**hubPermanentToken**](https://www.jetbrains.com/help/hub/Manage-Permanent-Tokens.html) at [JetBrains Hub](https://hub.jetbrains.com/users/me?tab=authentification) and specify Plugin Repository in Scope.

   ![Hub Token](img/hub_token.png)

Other parameters:

* **pluginId** is a numeric ID of the plugin, can be retrieved from the plugin repository URL. e.g. [Scala](https://plugins.jetbrains.com/plugin/1347-scala) plugin ID is *1347*. (*pluginXmlId* can be used instead)
* **pluginXmlId** is an unique identifier of the plugin specified as <id> in plugin.xml. Can be found as a *Plugin XML ID* parameter on the right of the plugin's individual update page and in the plugin.xml. (*pluginId* can be used instead)
* **channel** is a release channel the update is published to (empty channel means default *Stable* channel) (optional)

## POST

**Using pluginId**

Provide file as file contents. Curl command template:

```
curl -i --header "Authorization: Bearer <hubPermanentToken>" -F pluginId=<pluginId> -F file=@<path to plugin .jar/.zip file> -F channel=<channel> https://plugins.jetbrains.com/plugin/uploadPlugin
```

Curl command example:

```
curl -i --header "Authorization: Bearer perm:qwertyasdfghzxcvb" -F pluginId=5047 -F file=@Go-0.11.1197.zip -F channel=nightly https://plugins.jetbrains.com/plugin/uploadPlugin
```

**Using pluginXmlId**

Provide file as file contents. Curl command template:

```
curl -i --header "Authorization: Bearer <hubPermanentToken>" -F xmlId=<pluginXmlId> -F file=@<path to plugin .jar/.zip file> -F channel=<channel> https://plugins.jetbrains.com/plugin/uploadPlugin
```

Curl command example:

```
curl -i --header "Authorization: Bearer perm:qwertyasdfghzxcvb" -F xmlId=ro.redeul.google.go -F file=@Go-0.11.1197.zip -F channel=nightly https://plugins.jetbrains.com/plugin/uploadPlugin
```
