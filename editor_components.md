---
title: Editor Components
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/Editor+Components
-->

# {{ page.title }}

## EditorTextField

Compared to Swing JTextArea, IntelliJ IDEA's editor component has a ton of advantages: syntax highlighting support, code completion, code folding and much more.
IntelliJ IDEA's editors are normally displayed in editor tabs, but they can be embedded in dialogs or toolwindows, too.
This is enabled by the EditorTextField component.

When creating an EditorTextField, you can specify the following attributes:

*  The file type according to which the text in the text field is parsed;

*  Whether the text field is read-only or editable;

*  Whether the text field is single-line or multiline.

A common use case for EditorTextField is entering the name of a Java class or package. This can be accomplished with the following steps:

*  Use ```JavaCodeFragmentFactory.getInstance().createReferenceCodeFragment()``` to create a code fragment representing the class or package name;

*  Call ```PsiDocumentManager.getInstance().getDocument()``` to get the document corresponding to the code fragment;

*  Pass the returned document to the EditorTextField constructor or its ```setDocument()``` method.

