---
title: SDK
---

Every project uses a Software Development Kit (SDK). For Java projects, the SDK is referred to as the JDK (Java Development Kit). The SDK determines which API library is used to build the project. If your project is multi-module, the project SDK by default is common for all modules within the project. Optionally, you can configure individual SDKs for each module. For more information about SDKs, see [SDK](https://www.jetbrains.com/help/idea/working-with-sdks.html) in the IntelliJ IDEA Web Help.

## Getting project SDK information

Main information about the project SDK can be accessed via [ProjectRootManager.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java) like the following example shows

```java
Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
```

## Getting and setting project SDK attributes

* To get the project level SDK

  ```java
  Sdk projectSDK = ProjectRootManager.getInstance(project).getProjectSdk();
  ```

* To get the project level SDK name:
 
  ```java 
  String projectSDKName = ProjectRootManager.getInstance(project).getProjectSdkName();
  ```

* To set the project level SDK:
 
  ```java
  ProjectRootManager.getInstance(project).setProjectSdk(Sdk jdk);
  ```

* To set the project level SDK name:
 
  ```java
  ProjectRootManager.getInstance(project).setProjectSdkName(String name);
  ```
  
See the following [code sample](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/project_model/src/com/intellij/tutorials/project/model/ProjectSdkAction.java) to get more familiar with SDK manipulation tool set.

## Working with your own SDK

To create your own SDK, You need to create a class extends [SdkType](upsource:///platform/lang-api/src/com/intellij/openapi/projectRoots/SdkType.java), leave `saveAdditionalData` blank, and register it in the `com.intellij.sdkType` extension point.

To make your SDK settings persistent, you should override `setupSdkPaths` and save your settings by `modificator.commitChanges()`:

```java
@Override
public boolean setupSdkPaths(@NotNull Sdk sdk, @NotNull SdkModel sdkModel) {
    SdkModificator modificator = sdk.getSdkModificator();
    modificator.setVersionString(getVersionString(sdk));
    modificator.commitChanges(); // save
    return true;
}
```

To let user select an SDK, see [ProjectJdksEditor](upsource:///java/idea-ui/src/com/intellij/openapi/projectRoots/ui/ProjectJdksEditor.java).

However, it is not recommended to use "SDK" in non-IntelliJ IDEA IDEs. Although "SDK" is available in most JetBrains products, `ProjectJdksEditor` is specific to Java, making the operation around "SDK" difficult.
The most recommended way of managing your "SDK" settings is to create a [`CustomStepProjectGenerator`](upsource:///platform/lang-impl/src/com/intellij/ide/util/projectWizard/CustomStepProjectGenerator.java)
implementation and save settings in a [`PersistentStateComponent`](/basics/persisting_state_of_components.md).

## Assisting in setting up SDK
Register your implementation of [ProjectSdkSetupValidator](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/ProjectSdkSetupValidator.java) in extension point `com.intellij.projectSdkSetupValidator` to provide quick fix.