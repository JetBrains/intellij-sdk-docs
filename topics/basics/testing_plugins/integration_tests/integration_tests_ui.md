<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Integration Tests: UI Testing

<link-summary>Walkthrough how to interact with UI in integration tests.</link-summary>

> This page is part of [](integration_tests.md) tutorial.

For introduction and setting up dependencies please refer to [](integration_tests_intro.md).

## UI Hierarchy

IntelliJ-based IDEs primarily use Swing and AWT for their user interface, while [JCEF](embedded_browser_jcef.md) is used in specific cases like Markdown rendering.
These UI frameworks organize elements in a parent-child hierarchy, similar to HTML's DOM structure:

* Top-level containers (IDE frame and dialogs).
* Nested containers.
* Individual components (buttons, text fields, and lists).

Every UI element (except top-level containers) must have a parent container, creating a clear hierarchical structure.

The Driver framework provides a Kotlin DSL that mirrors this hierarchy.
Here's an example:

```kotlin
ideFrame {
  invokeAction("SearchEverywhere")
  searchEverywherePopup {
    actionButtonByXpath(xQuery { byAccessibleName("Preview")}).click()
  }
}
```

This code demonstrates hierarchical navigation:

* Find the main IDE window (`ideFrame`).
* Trigger the _Search Everywhere_ action (`invokeAction("SearchEverywhere")`).
* Locate the <control>Search Everywhere</control> popup (`searchEverywherePopup`).
* Find and click the <control>Preview</control> button within the popup (`actionButtonByXpath(xQuery { byAccessibleName("Preview")}).click()`).

The code could be more concise:

```kotlin
ideFrame {
  actionButtonByXpath(xQuery { byAccessibleName("Preview")}).click()
}
```

But the shorter code has two significant drawbacks:

* **Reduced precision**: The code searches for the <control>Preview</control> button throughout the entire IDE frame.
  It might find unintended matches in the project explorer, tool windows, or other UI elements.
  This can make tests unreliable and prone to breaking when the UI content changes.
* **Decreased readability**: While the code is more concise, it doesn't communicate the intended navigation path.
  The longer version makes it clear exactly where it's expected to find the Preview button, making the code more maintainable and easier to debug.

So, being explicit about the component hierarchy helps create more robust and maintainable UI automation code.

## Searching Components

While the Driver framework provides many pre-built components (like `ideFrame`, `codeEditor`, `button`, `tree`, etc.), sometimes it's required to locate custom elements.

It can be done by pausing the IDE to examine its UI structure:

```kotlin
.runIdeWithDriver().useDriverAndCloseIde {
  Thread.sleep(30.minutes.inWholeMilliseconds)
}
```

When the test is running, the following line will appear in the logs: `http://localhost:63343/api/remote-driver/`.
Opening this URL reveals an HTML representation of the IDE's Swing component tree:

![](integration_tests_devtools.png){width=706}

Using Developer Tools in the browser, it's possible to inspect detailed component attributes.
Here's an example component:

```html

<div accessiblename="Current File"
     asstring="<result of calling toString on component>"
     class="ActionButtonWithText"
     enabled="true" hide_dropdown_icon="HIDE_DROPDOWN_ICON"
     javaclass="com.intellij.execution.ui.RedesignedRunConfigurationSelector$createCustomComponent$1"
     myaction="Select Run/Debug Configuration (null)"
     tool_tip_text_key="ToolTipText"
     visible="true" visible_text="Current File" visible_text_keys=""/>
```

There are other attributes which are omitted for clarity.

The element corresponds to the following button:

![](integration_tests_ui_sample.png){width=706}

Similar to web testing frameworks like Selenium, XPath is used to locate components.
The Driver framework provides a simple XPath builder.
Here are several ways to find the same component:

```kotlin
xQuery { byVisibleText("Current File") }
xQuery { byAccessibleName("Current File") }
xQuery { byType("com.intellij.execution.ui.RedesignedRunConfigurationSelector\$createCustomComponent$1") }
```

For reliable component identification, prioritize these attributes: `accessiblename`, `visible_text`, `icon`, `javaclass`.
Multiple attributes can be combined for a more precise selection.

## Interaction with Components

Once a component is located, it's possible to interact with it or verify its properties.

To click the <control>Current File</control> button:

```kotlin
x(xQuery { byVisibleText("Current File") }).click()
```

The `x()` call creates a lazy reference to the component.
It means that the XPath query isn't executed immediately and component lookup happens only when an action (like `click()`) is invoked.

Here's a part of test that incorporates UI interaction:
```kotlin
runIdeWithDriver().useDriverAndCloseIde {
  waitForIndicators(1.minutes)
  ideFrame {
    x(xQuery { byVisibleText("Current File") }).click()
  }
}
```

Beyond mouse clicks, keyboard input and shortcuts can be simulated:

```kotlin
keyboard {
  enterText("Sample text")
  enter()
  hotKey(if (SystemInfo.isMac) KeyEvent.VK_META else KeyEvent.VK_CONTROL, KeyEvent.VK_A)
  backspace()
}
```

> On macOS, the interaction via `java.awt.Robot` requires special permissions.
> IntelliJ IDEA should be granted the necessary permissions via the Accessibility page, which can be found under _System Settings | Privacy & Security_.
>
{style="note"}

## Asserting Properties

The complete UI test:

```kotlin
@Test
fun simpleTestForCustomUIElement() {
  Starter.newContext(
    "testExample",
    TestCase(
    IdeProductProvider.IC,
    GitHubProject.fromGithub(branchName = "master",
      repoRelativeUrl = "JetBrains/ij-perf-report-aggregator"))
      .withVersion("2024.3")
    ).apply {
  val pathToPlugin = System.getProperty("path.to.build.plugin")
  PluginConfigurator(this).installPluginFromFolder(File(pathToPlugin))
  }.runIdeWithDriver().useDriverAndCloseIde {
      waitForIndicators(1.minutes)
      ideFrame {
      x(xQuery { byVisibleText("Current File") }).click()
      val configurations = popup().jBlist(xQuery { contains(byVisibleText("Edit Configurations")) })
      configurations.shouldBe("Configuration list is not present", present)
      Assertions.assertTrue(configurations.rawItems.contains("backup-data"),
        "Configurations list doesn't contain 'backup-data' item: ${configurations.rawItems}")
    }
  }
}
```

The test does the following:

* Opening the popup
* Click the <control>Current File</control> button.
* Popup menu appears.
* Finding the list
* Use `popup()` to locate the popup with a configuration list.
  This works without any XPath because at the moment of the call, there are no other popups shown on the UI.
* Find the list containing the text `Edit Configurations` by using the following query: `jBlist(xQuery { contains(byVisibleText("Edit Configurations")) })`.
* XQuery searches for the list component that contains the visible text `Edit Configurations`.
* Verifying list presence
* Use `shouldBe(<message>, present)` to ensure the list exists.
  This is important because `popup().jBlist` creates a lazy reference without actually checking the results.
  The actual check happens when `shouldBe` calls the `present` method.
  The `shouldBe` method waits 15 seconds until the condition is met and can be used to assert various properties.
* Checking list contents
* Access the `rawItems` property to get all list items.
* Verify the `backup-data` exists in the list.
* Include full list content in the error message for debugging.

