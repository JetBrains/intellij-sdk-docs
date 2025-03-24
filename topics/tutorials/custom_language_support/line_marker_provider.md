<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 8. Line Marker Provider

<link-summary>Sample implementation of line marker provider adding gutter icons for Simple language property occurrences in Java files, and allowing to navigate to a property definition.</link-summary>

<tldr>

**Code**: [`SimpleLineMarkerProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLineMarkerProvider.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

Line markers help annotate code with icons on the gutter.
These markers can provide navigation targets to related code.

## Define a Line Marker Provider

A line marker provider annotates usages of Simple Language properties within Java code and provides navigation to the definition of these properties.
The visual marker is a Simple Language icon in the gutter of the Editor window.

The [`SimpleLineMarkerProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLineMarkerProvider.java) subclasses [`RelatedItemLineMarkerProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/RelatedItemLineMarkerProvider.java).
For this example, override the `collectNavigationMarkers()` method to collect usage of a Simple Language [key and separators](language_and_filetype.md#define-the-language):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLineMarkerProvider.java" include-symbol="SimpleLineMarkerProvider"}

Extending from [`GutterIconDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/GutterIconDescriptor.java) allows configuring gutter icons to be shown via <ui-path>Settings | Editor | General | Gutter Icons</ui-path>.

## Best Practices for Implementing Line Marker Providers

This section addresses important details about implementing a marker provider.

The `collectNavigationMarkers()` method should:
* Only return line marker information consistent with the element passed into the method.
  For example, do not return a _class_ marker if `getLineMarkerInfo()` was called with an element that corresponds to a _method_.
* Return line marker information for the appropriate element at the correct scope of the PSI tree.
  Return leaf elements only - i.e., the smallest possible elements.
  For example, do not return method marker for [`PsiMethod`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiMethod.java).
  Instead, return it for the [`PsiIdentifier`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiIdentifier.java) which contains the name of the method.

![Line Marker Location](line_marker_location.png){width="900"}

What happens when a `LineMarkerProvider` returns marker information for a `PsiElement` that is a higher node in the PSI tree?
For example, if `MyWrongLineMarkerProvider()` erroneously returns a `PsiMethod` instead of a `PsiIdentifier` element:

```java
final class MyWrongLineMarkerProvider implements LineMarkerProvider {
  public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
    if (element instanceof PsiMethod) {
      return new LineMarkerInfo(element, ...);
    }
    return null;
  }
}
```

The consequences of the `MyWrongLineMarkerProvider()` implementation have to do with how the IntelliJ Platform performs inspections.
For performance reasons, inspection, and specifically the [`LineMarkersPass`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/LineMarkersPass.java) queries all [`LineMarkerProviders`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProviders.java) in two phases:
* The first pass is for all elements visible in the Editor window,
* The second pass is for the rest of the elements in the file.

If providers return nothing for either area, the line markers get cleared.
However, if a method like `actionPerformed()` is not completely visible in the Editor window (as shown in the image above,) and `MyWrongLineMarkerProvider()` returns marker info for the `PsiMethod` instead of `PsiIdentifier`, then:
* The first pass removes line marker info because whole `PsiMethod` isn't visible.
* The second pass tries to add a line marker because `MyWrongLineMarkerProvider()` is called for the `PsiMethod`.

As a result, _the line marker icon would blink annoyingly_.
To fix this problem for this case, rewrite `MyWrongLineMarkerProvider` to return info for `PsiIdentifier` instead of `PsiMethod` as shown below:

```java
final class MyCorrectLineMarkerProvider implements LineMarkerProvider {
  public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
    if (element instanceof PsiIdentifier &&
            element.getParent() instanceof PsiMethod) {
      return new LineMarkerInfo(element, ...);
    }
    return null;
  }
}
```

## Register the Line Marker Provider

The `SimpleLineMarkerProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file using
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.lineMarkerProvider"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <codeInsight.lineMarkerProvider
      language="JAVA"
      implementationClass="org.intellij.sdk.language.SimpleLineMarkerProvider"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the Java [Test file](annotator.md#run-the-project).
Now the icon appears next to line 3 on the gutter.
A user can click on the icon to navigate to the property definition.

![Line Marker](line_marker.png)
