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
  
See the following [code sample](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/project_model/src/com/intellij/tutorials/project/model/ProjectSdkAction.java) to get more familiar with SDK manipulation tool set.

## Working with your own SDK

To create your own SDK, You need to create a class extends [SdkType](https://upsource.jetbrains.com/idea-ce/file/idea-ce-974a6ed084613aa5b0e345fbeb50de59720ab283/platform/lang-api/src/com/intellij/openapi/projectRoots/SdkType.java), leave `saveAdditionalData` blank, and register it in the `com.intellij.sdkType` extension point.

To make your SDK settings persistant, you should override `setupSdkPaths` and save your settings by `modificator.commitChanges()`:

```java
@Override
public boolean setupSdkPaths(@NotNull Sdk sdk, @NotNull SdkModel sdkModel) {
    SdkModificator modificator = sdk.getSdkModificator();
    modificator.setVersionString(getVersionString(sdk));
    modificator.commitChanges();
    return true;
}
```

To let user select an SDK, see [ProjectJdksEditor](https://upsource.jetbrains.com/idea-ce/file/idea-ce-8c9022ae739b82b2ee8f3355da98b9bbce2cb915/java/idea-ui/src/com/intellij/openapi/projectRoots/ui/ProjectJdksEditor.java).
