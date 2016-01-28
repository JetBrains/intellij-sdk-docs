---
title: Code Inspections
---

This topic describes the [comparing_refereces_inspection](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/comparing_references_inspection), a sample plugin that creates a custom [inspection](http://www.jetbrains.com/idea/webhelp/code-inspection.html) for Java code. In addition, the sample plugin contains a JUnit-based test.

## About Code Inspections

The **IntelliJ Platform** provides tools designed for static code analysis (so called _code inspections_) that help you maintain and clean up your code without actually executing it. For more information, refer to [Code Inspection](http://www.jetbrains.com/idea/webhelp/code-inspection.html) in the **IntelliJ IDEA** Web Help. In **IntelliJ IDEA** you will find a set of built-in inspections that are grouped by their goals and sense.

You can create custom inspections through the **IntelliJ IDEA** interface (see [Creating Custom Inspections](https://www.jetbrains.com/idea/help/creating-custom-inspections.html)). Alternatively, you can develop a plugin to implement a custom inspection.

## Techniques Used

The [comparing_refereces_inspection](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/comparing_references_inspection) sample plugin illustrates the use of the following techniques:

- How to analyze a [PSI tree](/basics/architectural_overview/psi_files.md).
- How to find a Java token of interest in the PSI tree.
- How to inspect Java code in the **IntelliJ IDEA** editor using the [BaseJavaLocalInspectionTool](upsource:///java/java-analysis-api/src/com/intellij/codeInspection/BaseJavaLocalInspectionTool.java) class.
- How to create a JUnit test for this plugin using the [IdeaTestFixtureFactory](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaTestFixtureFactory.java) class.

## Sample Plugin

The **comparingReferences** sample plugin is available in the `<%IntelliJ SDK Docs project%>/code_samples/comparing_references_inspection` directory. When launched, this plugin adds the **'==' or '!=' instead of 'equals()'** item to the **Probable bugs** node in the [Inspections list](http://www.jetbrains.com/idea/webhelp/inspections-2.html).

#### Running the Plugin

**To run the sample plugin**

1. Start **IntelliJ IDEA** and open the **comparingReferences** plugin project saved into the `<%IntelliJ SDK Docs project%>/code_samples/comparing_references_inspection` directory.
2. Open the [Project Structure](http://www.jetbrains.com/idea/webhelp/project-structure.html) dialog and ensure that the project settings are valid for your environment.
3. If necessary, modify the [Run/Debug Configurations](http://www.jetbrains.com/idea/webhelp/run-debug-configuration-plugin.html) and Run the plugin by choosing the **Run | Run** on the main menu.

#### Configuring the Plugin

Once the plugin is launched, you can set the plugin options. You can specify the Java classes to be participated in the code inspection and the severity level of the found probable bugs.

**To configure the sample plugin**

1. On the IDEA main menu, choose **File | Settings**, and then under **Project Settings**, click **Inspections**.
2. In the list of the IntelliJ IDEA inspections, expand the **Probable bugs** node, and then click **'==' or '!=' instead of 'equals()'**.  

![](img/comparingReferences_options.png)

3. Under **Options**, you can specify the following plugin settings:
    - From the **Severity** list, select the severity level of probable bugs the plugin will find (such as Warning, Info, etc.)
    - In the text box under **Severity**, specify the semicolon separated list of Java classes to be participated in this code inspection.
4. When finished, click **OK**.

#### How does it work?

The plugin inspects your code opened in the **IntelliJ IDEA** editor or the code you are typing. The plugin highlights the code fragments where two variables of the reference type are separated by **==** or **!=** and proposes to replace this code fragment with **.equals()**:

![](img/comparingReferences.png)

In this example, the **s1** and **s2** are variables of the String type. Clicking **Use equals()** replaces

```java
return (s1==s2);
```

with the code:

```java
return (s1.equals(s2));
```

#### Testing the Plugin

The sample plugin contains the `TestThisPlugin` Java class in the `testSource/testPlugin` package and the test data in the `testData` directory. This test adds two test cases to this plugin project. To run test cases, run the `YourTest.test()` or `YourTest.test1()` method, respectively.

For detailed information about testing and all related procedures, refer to [Testing](http://www.jetbrains.com/idea/webhelp/testing.html) and [Testing Support](http://www.jetbrains.com/idea/webhelp/testing-support.html) in the **IntelliJ IDEA** Web Help.