# PSI Demo [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Navigating the PSI in IntelliJ SDK Docs][docs:navigating_psi]*

## Quickstart

PSI Demo project demonstrates working with the PSI Navigation by implementing `AnAction` that through the message
dialog, informs about:
- an element at the caret,
- containing method,
- containing class,
- local variables.

### Actions

| Name   | Implementation Class                                    | Interface                |
| ------ | ------------------------------------------------------- | ------------------------ |
| action | [PsiNavigationDemoAction][file:PsiNavigationDemoAction] | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:navigating_psi]: https://jetbrains.org/intellij/sdk/docs/basics/architectural_overview/navigating_psi.html

[file:PsiNavigationDemoAction]: ./src/main/java/org/intellij/sdk/psi/PsiNavigationDemoAction.java

[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
