<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Integration Tests: UI Testing

<primary-label ref="2023.2"/>

<link-summary>Walkthrough how to interact with UI in integration tests.</link-summary>

> This page is part of the [](integration_tests.md) tutorial.

For introduction and setting up dependencies, refer to [](integration_tests_intro.md).

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
ideFrame { // 1
  invokeAction("SearchEverywhere") // 2
  searchEverywherePopup { // 3
    actionButtonByXpath(xQuery { byAccessibleName("Preview") }) // 4
      .click()
  }
}
```

This code demonstrates hierarchical navigation:

1. Find the main IDE window.
2. Trigger the _Search Everywhere_ action.
3. Locate the <control>Search Everywhere</control> popup.
4. Find and click the <control>Preview</control> button within the popup.

The code could be more concise:

```kotlin
ideFrame {
  actionButtonByXpath(xQuery { byAccessibleName("Preview") }).click()
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
Starter.newContext(...).apply { ... }
  .runIdeWithDriver().useDriverAndCloseIde {
    Thread.sleep(30.minutes.inWholeMilliseconds) // pause the IDE
  }
```

When the test is running, the following line will appear in the logs: `http://localhost:63343/api/remote-driver/`.
Opening this URL reveals an HTML representation of the IDE's Swing component tree:

![](integration_tests_devtools.png){width=706 thumbnail=true}

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

![](integration_tests_ui_sample.png){width=706 thumbnail=true}

Similar to web testing frameworks like Selenium, XPath is used to locate components.
The Driver framework provides a simple XPath builder [`QueryBuilder`](%gh-ic%/platform/remote-driver/test-sdk/src/com/intellij/driver/sdk/ui/QueryBuilder.kt).

For reliable component identification, prioritize these attributes, which can be found with predefined methods in `QueryBuilder`:

- `accessiblename`: `xQuery { byAccessibleName() }`
- `visible_text`: `xQuery { byVisibleText() }`
- `javaclass`: `xQuery { byType() }`
- `myicon`: `xQuery { byAttribute("myicon", "") }`

A single component can be found in several ways:

```kotlin
xQuery { byAccessibleName("Current File") }
xQuery { byVisibleText("Current File") }
xQuery {
  byType("com.intellij.execution.ui.RedesignedRunConfigurationSelector\$createCustomComponent$1")
}
```

Multiple attributes can be combined for a more precise selection, for example:

```kotlin
xQuery { and(byAccessibleName("Current File"), byVisibleText("Current File")) }
xQuery {
  or(
    contains(byAccessibleName("Current")),
    byType("com.intellij.execution.ui.RedesignedRunConfigurationSelector\$createCustomComponent$1")
  )
}
```

## Interaction with Components

Once a component is located, it's possible to interact with it or verify its properties.

To click the <control>Current File</control> button:

```kotlin
x(xQuery { byVisibleText("Current File") }).click()
```

The `x()` call creates a lazy reference to the component.
It means that the XPath query isn't executed immediately and component lookup happens only when an action (like `click()`) is invoked.

Here's a part of a test that incorporates UI interaction:

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

Keyboard methods perform presses using `java.awt.Robot` so to type to some particular component or invoke a shortcut in the appropriate place, you first need to make the component focused.
The most reliable way to do this is to perform `click` on the component first.

> On macOS, the interaction via `java.awt.Robot` requires special permissions.
> IntelliJ IDEA should be granted the necessary permissions via the <control>Accessibility</control> page, which can be found under <ui-path>System Settings | Privacy & Security</ui-path>.
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
      GitHubProject.fromGithub(
        branchName = "master",
        repoRelativeUrl = "JetBrains/ij-perf-report-aggregator"
      )
    )
      .withVersion("2024.3")
  )
    .apply {
      val pathToPlugin = System.getProperty("path.to.build.plugin")
      PluginConfigurator(this).installPluginFromFolder(File(pathToPlugin))
    }.runIdeWithDriver().useDriverAndCloseIde {
      waitForIndicators(1.minutes)
      ideFrame {
        x(xQuery { byVisibleText("Current File") }).click() //1
        val configurations = popup().jBlist( //2
          xQuery { contains(byVisibleText("Edit Configurations")) } //3
        )
        configurations.shouldBe("Configuration list is not present", present) //4
        Assertions.assertTrue(
          configurations.rawItems.contains("backup-data"), //5
          "Configurations list doesn't contain 'backup-data' item: ${configurations.rawItems}"
        ) //6
      }
    }
}
```

The test does the following:

1. Opening the popup by clicking the <control>Current File</control> button.
2. Finding the list by using `popup()` to locate the popup with a configuration list.
   This works without any XPath because at the moment of the call, there are no other popups shown on the UI.
3. Finding the list containing the text `Edit Configurations`.
   XQuery searches for the list component that contains the visible text `Edit Configurations` and verifies the list presence.
4. Using `shouldBe(<message>, present)` to ensure the list exists.
   This is important because `popup().jBlist` creates a lazy reference without actually checking the results.
   The actual check happens when `shouldBe` calls the `present` method.
   The `shouldBe` method waits 15 seconds until the condition is met and can be used to assert various properties.
5. Checking list contents by accessing the `rawItems` property to get all list items and asserting `backup-data` exists in the list.
6. Including the full list content in the error message for debugging.

