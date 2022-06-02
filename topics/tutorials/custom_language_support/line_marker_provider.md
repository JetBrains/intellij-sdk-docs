[//]: # (title: 8. Line Marker Provider)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

Line markers help annotate code with icons on the gutter.
These markers can provide navigation targets to related code.

## Define a Line Marker Provider

A line marker provider annotates usages of Simple Language properties within Java code and provides navigation to the definition of these properties.
The visual marker is a Simple Language icon in the gutter of the Editor window.

The Simple Language marker provider subclasses [`RelatedItemLineMarkerProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/RelatedItemLineMarkerProvider.java).
For this example, override the `collectNavigationMarkers()` method to collect usage of a Simple Language [key and separators](language_and_filetype.md#define-the-language):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLineMarkerProvider.java"}

Extending from [`GutterIconDescriptor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/GutterIconDescriptor.java) allows configuring gutter icons to be shown via <menupath>Settings/Preferences | Editor | General | Gutter Icons</menupath>.

## Best Practices for Implementing Line Marker Providers

This section addresses important details about implementing a marker provider.

The `collectNavigationMarkers()` method should:
* Only return line marker information consistent with the element passed into the method.
  For example, do not return a _class_ marker if `getLineMarkerInfo()` was called with an element that corresponds to a _method_.
* Return line marker information for the appropriate element at the correct scope of the PSI tree.
  Return leaf elements only - i.e., the smallest possible elements.
  For example, do not return method marker for [`PsiMethod`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiMethod.java).
  Instead, return it for the [`PsiIdentifier`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiIdentifier.java) which contains the name of the method.

![Line Marker Location](line_marker_location.png){width="900"}

What happens when a `LineMarkerProvider` returns marker information for a `PsiElement` that is a higher node in the PSI tree?
For example, if `MyWrongLineMarkerProvider()` erroneously returns a `PsiMethod` instead of a `PsiIdentifier` element:

```java
public class MyWrongLineMarkerProvider implements LineMarkerProvider {
  public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
    if (element instanceof PsiMethod) {
      return new LineMarkerInfo(element, ...);
    }
    return null;
  }
}
```

The consequences of the `MyWrongLineMarkerProvider()` implementation have to do with how the IntelliJ Platform performs inspections.
For performance reasons, inspection, and specifically the [`LineMarkersPass`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/daemon/impl/LineMarkersPass.java) queries all [`LineMarkerProviders`](upsource:///platform/lang-api/src/com/intellij/codeInsight/daemon/LineMarkerProviders.java) in two phases:
* The first pass is for all elements visible in the Editor window,
* The second pass is for the rest of the elements in the file.

If providers return nothing for either area, the line markers get cleared.
However, if a method like `actionPerformed()` is not completely visible in the Editor window (as shown in the image above,) and `MyWrongLineMarkerProvider()` returns marker info for the `PsiMethod` instead of `PsiIdentifier`, then:
* The first pass removes line marker info because whole `PsiMethod` isn't visible.
* The second pass tries to add a line marker because `MyWrongLineMarkerProvider()` is called for the `PsiMethod`.

As a result, _the line marker icon would blink annoyingly_.
To fix this problem for this case, rewrite `MyWrongLineMarkerProvider` to return info for `PsiIdentifier` instead of `PsiMethod` as shown below:

```java
public class MyCorrectLineMarkerProvider implements LineMarkerProvider {
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

The `SimpleLineMarkerProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `com.intellij.codeInsight.lineMarkerProvider` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <codeInsight.lineMarkerProvider
      language="JAVA"
      implementationClass="org.intellij.sdk.language.SimpleLineMarkerProvider"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

Open the Java [Test file](annotator.md#run-the-project).
Now the icon appears next to line 3 on the gutter.
A user can click on the icon to navigate to the property definition.

![Line Marker](line_marker.png)
