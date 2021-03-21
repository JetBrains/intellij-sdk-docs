[//]: # (title: Editor Notifications Provider)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Notifications that appear at the top of the file editor are a great way to ask the user to take an important action that would otherwise impede their experience if ignored. 

For example, if you have started IntelliJ IDEA Community/Ultimate Edition without setting any Project JDK, you may have experienced an editor notification like this upon opening a JAVA file:

![Editor notification asking to set up JDK](editor_notification.png)

In order to create your own editor notification for your plugin, you will need to register an implementation of [`EditorNotifications.Provider`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotifications.java) at the `com.intellij.editorNotificationProvider` extension point.

```xml
<editorNotificationProvider implementation="org.jetbrains.sdk.ide.notifications.DemoNotificationProvider"/>
```

As an example, suppose we want to notify the user if they have not set up a [custom sdk](sdk.md#working-with-a-custom-sdk) that is associated with our plugin. In this case, we could implement the `DemoNotificationProvider` (registered above) as follows:

```kotlin
class DemoNotificationProvider : EditorNotifications.Provider<EditorNotificationPanel>(), DumbAware {
    override fun getKey(): Key<EditorNotificationPanel> = Key.create("Set up AutoHotkey runner")

    override fun createNotificationPanel(
        file: VirtualFile,
        editor: FileEditor,
        project: Project
    ): EditorNotificationPanel? {
        if (file.fileType != DemoFileType) return null
        if (ProjectJdkTable.getInstance().getSdksOfType(DemoSdkType.getInstance()).isNotEmpty()) return null
        return EditorNotificationPanel().apply {
            setText("No DemoSdks are configured for the project")
            createActionLabel("Configure DemoSdk") {
                ShowSettingsUtil.getInstance().showSettingsDialog(project, DemoProjectConfigurable::class.java)
            }
        }
    }
}
```

In the `createNotificationPanel()` method which we must override, we check the following:

1. Is the currently open file of the `DemoFileType`? If not, we return `null` so that no notification is shown since we only want to consider files associated with our plugin.

2. Is an instance of `DemoSdkType` already configured within the IDE? If so, we again return `null` so that no notification is shown since the user has already taken care of configuring a `DemoSdk`.

If both conditions fail, we return an [`EditorNotificationPanel`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotificationPanel.java) that contains some information text alerting the user that no `DemoSdk`s are configured. We also provide an action button that will open up our [custom project configurable](settings-guide.md#declaring-project-settings) within the Settings window where the user would be able to configure a `DemoSdk`. 

By implementing the [`DumbAware`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbAware.java) interface in our provider, we are allowing IntelliJ to execute our provider even while the IDE is indexing files.

> If you wanted to open the "Project Structure" window available within IntelliJ IDEA Community/Ultimate Edition only, you could execute `IdeaProjectSettingsService.getInstance(project).openProjectSettings()` in `createActionLabel()` instead. 
> However, this example is contrived to simply demonstrate the functionality of the `editorNotificationProvider` extension point. 
> If you actually wanted to validate the existence of an sdk in your plugin, the IntelliJ Platform SDK offers the better-suited [`projectSdkSetupValidator`](sdk.md#assisting-in-setting-up-an-sdk) extension point instead. 
>
{type="note"}
