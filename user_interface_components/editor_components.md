---
title: Editor Components
---

## EditorTextField

Compared to
[Swing JTextArea](http://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html),
*IntelliJ IDEA's* editor component has a ton of advantages: syntax highlighting support, code completion, code folding and much more.
*IntelliJ IDEA's* editors are normally displayed in editor tabs, but they can be embedded in dialogs or toolwindows, too.
This is enabled by the
[EditorTextField](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/platform-impl/src/com/intellij/ui/EditorTextField.java)
component.

When creating an
[EditorTextField](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/platform-impl/src/com/intellij/ui/EditorTextField.java),
you can specify the following attributes:

*  The file type according to which the text in the text field is parsed;

*  Whether the text field is read-only or editable;

*  Whether the text field is single-line or multiline.

A common use case for
[EditorTextField](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/platform-impl/src/com/intellij/ui/EditorTextField.java)
is entering the name of a Java class or package.
This can be accomplished with the following steps:

*  Use
   [JavaCodeFragmentFactory.getInstance().createReferenceCodeFragment()](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/java/java-psi-api/src/com/intellij/psi/JavaCodeFragmentFactory.java)
   to create a code fragment representing the class or package name;

*  Call
   [PsiDocumentManager.getInstance().getDocument()](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/core-api/src/com/intellij/psi/PsiDocumentManager.java)
   to get the document corresponding to the code fragment;

*  Pass the returned document to the
   [EditorTextField](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/platform-impl/src/com/intellij/ui/EditorTextField.java)
   constructor or its `setDocument()` method.

