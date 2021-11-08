# PSI Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Navigating the PSI in IntelliJ SDK Docs][docs:navigating_psi]*

## Quickstart

PSI Demo project demonstrates working with the PSI Navigation by implementing `AnAction` that through the message dialog, informs about:
- an element at the caret,
- containing method,
- containing class,
- local variables.

### Actions

| ID                  | Implementation                                          | Base Action Class |
|---------------------|---------------------------------------------------------|-------------------|
| `PsiNavigationDemo` | [PsiNavigationDemoAction][file:PsiNavigationDemoAction] | `AnAction`        |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/basic-action-system.html
[docs:navigating_psi]: https://plugins.jetbrains.com/docs/intellij/navigating-psi.html

[file:PsiNavigationDemoAction]: ./src/main/java/org/intellij/sdk/psi/PsiNavigationDemoAction.java
