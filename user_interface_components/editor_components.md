---
title: Editor Components
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## EditorTextField

Compared to
[Swing `JTextArea`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html), the
*IntelliJ Platform's* editor component has a ton of advantages: syntax highlighting support, code completion, code folding and much more.
Editors are normally displayed in editor tabs, but they can be embedded in dialogs or tool windows, too.
This is enabled by the
[`EditorTextField`](upsource:///platform/platform-impl/src/com/intellij/ui/EditorTextField.java)
component.

The following attributes can be specified:

*  The file type according to which the text in the text field is parsed;
*  Whether the text field is read-only or editable;
*  Whether the text field is single-line or multiline.
                                        
Further customizations are possible by subclassing and overriding `createEditor()`.

A common use case for `EditorTextField` is entering the name of a Java class or package.
This can be accomplished with the following steps:

*  Use
   [`JavaCodeFragmentFactory.getInstance().createReferenceCodeFragment()`](upsource:///java/java-psi-api/src/com/intellij/psi/JavaCodeFragmentFactory.java)
   to create a code fragment representing the class or package name;
*  Call
   [`PsiDocumentManager.getInstance().getDocument()`](upsource:///platform/core-api/src/com/intellij/psi/PsiDocumentManager.java)
   to get the document corresponding to the code fragment;
*  Pass the returned document to the
   [`EditorTextField`](upsource:///platform/platform-impl/src/com/intellij/ui/EditorTextField.java)
   constructor or its `setDocument()` method.

E.g.:

```java
PsiFile psiFile = PsiDocumentManager.getInstance(editor.getProject()).getPsiFile(editor.getDocument());
PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());

PsiExpressionCodeFragment code = JavaCodeFragmentFactory.getInstance(editor.getProject())
                                   .createExpressionCodeFragment("", element, null, true);

Document document = PsiDocumentManager.getInstance(editor.getProject()).getDocument(code);

EditorTextField myInput = new EditorTextField(document, editor.getProject(), JavaFileType.INSTANCE);
```

**TIPS**: 

* When creating more than one field two separate documents are needed. This is accomplished by using separate instances of `PsiExpressionCodeFragment`.
* `setText()` no longer works for the input field. However, `createExpressionCodeFragment()` accepts the text for the field as an argument. The empty string can be replaced and create a new document in lieu of `setText()`.
* Instances of `JTextField` in the GUI builder can be replaced with a custom replacement component using the right click in your IDE. Make sure to use "Custom Create", so the initialization code works properly.
