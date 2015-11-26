---
title: Go to Class and Go to Symbol
---

A custom language plugin can provide its own items to be included in the lists shown when the user chooses the `Go to | Class...` or `Go to | Symbol...` action.
In order to do so, the plugin must provide implementations for the
[ChooseByNameContributor](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java)
interface (separate implementations need to be provided for `Go to Class` and `Go to Symbol`), and register them in the `com.intellij.gotoClassContributor` and `com.intellij.gotoSymbolContributor` extension points.

Each contributor needs to be able to return a complete list of names to show in the list for a specified project, which will then be filtered by the IDE according to the text typed by the user in the dialog.
For each name in that list, the contributor needs to provide a list of
[NavigationItem](upsource:///platform/core-api/src/com/intellij/navigation/NavigationItem.java)
instances (typically
[PsiElement](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
), which specify the destinations to jump to when a specific name is selected from the list.
