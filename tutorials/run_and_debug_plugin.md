Run and debug IntelliJ plugin
=======

#What to do it a fatal error arises while running a plugin
If you get the
[following fatal error] (https://github.com/JetBrains/intellij-sdk/blob/master/tutorials/fatal_error.md)

    Internal error. Please report to http://youtrack.jetbrains.com
    java.lang.RuntimeException: com.intellij.ide.plugins.PluginManager$StartupAbortedException:
    Fatal error initializing 'com.intellij.openapi.actionSystem.ActionManager'
it means that the system IntelliJ IDEA caches might be corrupted and need to be removed.
On
[this page] (https://devnet.jetbrains.com/docs/DOC-181)
more info about the caches location for different operation system is provided.


**TBC**