# Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Sample Article in IntelliJ SDK Docs][docs:sampleArticle]*

## Quickstart

The Sample implements `com.intellij.sample` Extension Point, which should be explained properly in this Quickstart section.

### Extension Points

| Name                  | Implementation                                    | Extension Point Class |
|-----------------------|---------------------------------------------------|-----------------------|
| `com.intellij.sample` | [SampleExtensionPoint][file:SampleExtensionPoint] | `ExtensionPoint`      |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

### Actions

| ID                                     | Implementation                    | Base Action Class |
|----------------------------------------|-----------------------------------|-------------------|
| `org.intellij.sdk.action.SampleAction` | [SampleAction][file:SampleAction] | `AnAction`        |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

### Listeners

| Name     | Implementation                        | Listener Class |
|----------|---------------------------------------|----------------|
| listener | [SampleListener][file:SampleListener] | `Listener`     |

*Reference: [Plugin Listeners in IntelliJ SDK Docs][docs:listeners]*

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/action-system.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html
[docs:listeners]: https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html
[docs:sampleArticle]: https://plugins.jetbrains.com/docs/intellij/sampleArticle.html

[file:SampleExtensionPoint]: ./src/main/java/org/intellij/sdk/sample/SampleExtensionPoint.java
[file:SampleAction]: ./src/main/java/org/intellij/sdk/sample/SampleAction.java
[file:SampleListener]: ./src/main/java/org/intellij/sdk/sample/SampleListener.java
