---
title: SDK
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Every project uses a Software Development Kit (SDK). 
For Java projects, the SDK is referred to as the JDK (Java Development Kit). 
The SDK determines which API library is used to build the project. 
If a project is multi-module, the project SDK by default is common for all modules within the project. 
Optionally, individual SDKs for each module can be configured. 
For more information about SDKs, see [SDK](https://www.jetbrains.com/help/idea/working-with-sdks.html) in the IntelliJ IDEA Web Help.

## Getting Project SDK Information
The information about the project SDK is accessed via [`ProjectRootManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java) like the following example shows

```java
Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
```

## Getting and Setting Project SDK Attributes

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
  
See the [project_model](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/project_model/src/main/java/org/intellij/sdk/project/model/ProjectSdkAction.java) code sample to get more familiar with SDK manipulation toolset.

## Available SDKs

[`ProjectJdkTable`](upsource:///platform/projectModel-api/src/com/intellij/openapi/projectRoots/ProjectJdkTable.java) can be used to query and modify configured SDKs.

## Working with a Custom SDK

To create a custom SDK, provide a class extending [`SdkType`](upsource:///platform/lang-api/src/com/intellij/openapi/projectRoots/SdkType.java), leave `saveAdditionalData()` blank, and register it in the `com.intellij.sdkType` extension point.

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

To let a user select an SDK, see [`ProjectJdksEditor`](upsource:///java/idea-ui/src/com/intellij/openapi/projectRoots/ui/ProjectJdksEditor.java).

However, it is not recommended to use "SDK" in non-IntelliJ IDEA IDEs. Although "SDK" is available in most JetBrains products, `ProjectJdksEditor` is specific to Java, making the operation around "SDK" difficult.
The recommended way of managing "SDK" settings is to create a [`CustomStepProjectGenerator`](upsource:///platform/lang-impl/src/com/intellij/ide/util/projectWizard/CustomStepProjectGenerator.java)
implementation and save settings in a [`PersistentStateComponent`](/basics/persisting_state_of_components.md).

## Assisting in Setting Up an SDK
Register the implementation of [`ProjectSdkSetupValidator`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/ProjectSdkSetupValidator.java) in extension point `com.intellij.projectSdkSetupValidator` to provide quick fix.