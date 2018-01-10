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

To create your own SDK, You need to create a class extends `SdkType`, and set your recommended SDK name, SDK home path, a presentable name. <br/>
Finally you need to implement `isValidSdkHome` to do the SDK validation, and this example just check if the given path is a directory.

```java
import com.intellij.openapi.projectRoots.*;

public class MySdkType extends SdkType {
    public MySdkType() {
        super("MY_SDK_NAME");
    }

    @Override public @Nullable String suggestHomePath() {
        return "/usr/share/covscript";
    }

    @Override public boolean isValidSdkHome(@NotNull String path) {
        return Files.isDirectory(Paths.get(path));
    }

    @Override public @Nullable String suggestSdkName(@NotNull String s, @NotNull String s1) {
        return "Replace with your SDK name";
    }

    @Override public @Nullable AdditionalDataConfigurable createAdditionalDataConfigurable(
            @NotNull SdkModel sdkModel, @NotNull SdkModificator sdkModificator) {
        return null; // not needed
    }

    @Override public @NotNull String getPresentableName() {
        return "My Awesome Sdk";
    }

    @Override public void saveAdditionalData(
            @NotNull SdkAdditionalData sdkAdditionalData, @NotNull Element element) {
        // leave blank
    }

    @Override public boolean setupSdkPaths(@NotNull Sdk sdk, @NotNull SdkModel sdkModel) {
        SdkModificator modificator = sdk.getSdkModificator();
        modificator.setVersionString(getVersionString(sdk));
        modificator.commitChanges();
        return true;
    }
}
```

Optionally, you can override `getIcon` to set an icon to your SDK type.

The last method is not abstract, so it's possible to remove it. But to save your SDK settings
(say, the next time you open your IDE, the SDK will still be there), you should override it.

To let users create or select an existing SDK, you may do this in some configuration panels (like, module wizard, facet, or run configuration):

```java
ProjectJdksEditor editor = new ProjectJdksEditor(sdkCurrentlyUsed, project, parentGuiComponent);
editor.setTitle("Select a SDK");
editor.show();
if (editor.isOK()) {
    Sdk newSdkSelected = editor.getSelectedJdk();
    // do your update job here
}
```
