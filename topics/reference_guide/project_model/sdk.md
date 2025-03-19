<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Configuring and getting information about a project SDK.</link-summary>

# SDK

<tldr>

**Product Help:** [SDKs](https://www.jetbrains.com/help/idea/working-with-sdks.html)

</tldr>

Every project uses a Software Development Kit (SDK).
For Java projects, the SDK is referred to as the JDK (Java Development Kit).
The SDK determines which API library is used to build the project.
If a project is multi-module, the project SDK by default is common for all modules within the project.
Optionally, individual SDKs for each module can be configured.

## Working with SDKs

<include from="project.md" element-id="useWorkspaceModelAPI"/>

### Getting Project SDK Information

The information about the project SDK is accessed via [`ProjectRootManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java) like the following example shows

```java
Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
```

### Getting and Setting Project SDK Attributes

* To get the project-level SDK:

  ```java
  Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdkName();
  ```

* To get the project-level SDK name:

  ```java
  String projectSdkName = ProjectRootManager.getInstance(project).getProjectSdkName();
  ```

* To set the project-level SDK:

  ```java
  ProjectRootManager.getInstance(project).setProjectSdk(sdk);
  ```

* To set the project-level SDK name:

  ```java
  ProjectRootManager.getInstance(project).setProjectSdkName(name, sdk.getSdkType().getName());
  ```

See the [project_model](%gh-sdk-samples-master%/project_model/src/main/java/org/intellij/sdk/project/model/ProjectSdkAction.java) code sample to get more familiar with the SDK manipulation toolset.

### Available SDKs

[`ProjectJdkTable`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/projectRoots/ProjectJdkTable.java) can be used to query and modify configured SDKs.

### Working with a Custom SDK

To create a custom SDK, provide a class extending [`SdkType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/projectRoots/SdkType.java), leave `saveAdditionalData()` blank, and register it
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.sdkType"/></include>.

To make SDK settings persistent, override `setupSdkPaths()` and save settings by `modificator.commitChanges()`:

```java

@Override
public boolean setupSdkPaths(@NotNull Sdk sdk, @NotNull SdkModel sdkModel) {
  SdkModificator modificator = sdk.getSdkModificator();
  modificator.setVersionString(getVersionString(sdk));
  modificator.commitChanges(); // save
  return true;
}
```

To let a user select an SDK, see [`ProjectJdksEditor`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/projectRoots/ui/ProjectJdksEditor.java).

However, it is not recommended to use "SDK" in non-IntelliJ IDEA IDEs.
Although "SDK" is available in most JetBrains products, `ProjectJdksEditor` is specific to Java, making the operation around "SDK" difficult.
The recommended way of managing "SDK" settings is to create a [`CustomStepProjectGenerator`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/util/projectWizard/CustomStepProjectGenerator.java) implementation and save settings in a [`PersistentStateComponent`](persisting_state_of_components.md).

## Assisting in Setting Up an SDK

Prompting the user with a notification to set up an SDK can help them get up-and-running with a plugin faster.
Use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectSdkSetupValidator"/></include> to register
an implementation of [`ProjectSdkSetupValidator`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/ProjectSdkSetupValidator.java)
to notify the user if they are missing an SDK.

The following is a simplified example that checks whether an instance of "DemoSdk" has been configured in the project when the user opens a "DemoFileType":

```kotlin
internal class DemoProjectSdkSetupValidator : ProjectSdkSetupValidator {
  override fun isApplicableFor(project: Project, file: VirtualFile): Boolean {
    return file.fileType == DemoFileType
  }

  override fun getErrorMessage(project: Project, file: VirtualFile): String? {
    if (ProjectJdkTable.getInstance().getSdksOfType(DemoSdkType.getInstance()).isEmpty()) {
      return "No DemoSdks are configured for this project!"
    }
    return null
  }

  override fun getFixHandler(project: Project, file: VirtualFile):
        EditorNotificationPanel.ActionHandler {
    return SdkPopupFactory.newBuilder()
        .withProject(project)
        .withSdkTypeFilter { it is DemoSdkType }
        .updateSdkForFile(file)
        .buildEditorNotificationPanelHandler()
  }
}
```

Within `DemoProjectSdkSetupValidator`:

* `isApplicableFor()` checks what condition(s) should be met to run the validation.
* `getErrorMessage()` runs the validation and returns an appropriate error message if the validation fails.
* If the validation is successful, then it should return null.
* `getFixHandler()` returns an `EditorNotificationPanel.ActionHandler` that enables the user to execute a quick fix to resolve the validation issue.

> `ProjectSdkSetupValidator` will not work in IntelliJ Platform-based IDEs such as PyCharm.
> In such cases, you should register an implementation of [`EditorNotificationProvider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationProvider.java)
> via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.editorNotificationProvider"/></include> and override the `createNotificationPanel()` method with the conditionality and panel setup you want.
>
{style="warning"}
