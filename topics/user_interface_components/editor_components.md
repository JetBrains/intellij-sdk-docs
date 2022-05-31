[//]: # (title: Editor Components)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## EditorTextField

Compared to [Swing `JTextArea`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html), the IntelliJ Platform's editor component has a ton of advantages: syntax highlighting support, code completion, code folding and much more.
Editors are normally displayed in editor tabs, but they can be embedded in dialogs or tool windows, too.
This is enabled by the [`EditorTextField`](upsource:///platform/platform-impl/src/com/intellij/ui/EditorTextField.java) component.

The following attributes can be specified:

* The file type according to which the text in the text field is parsed;
* Whether the text field is read-only or editable;
* Whether the text field is single-line or multiline.

Further customizations are possible by subclassing and overriding `createEditor()` and applying [`EditorCustomization`](upsource:///platform/platform-impl/src/com/intellij/ui/EditorCustomization.java).
Several commonly needed customization implementations exist, including:
- [`SpellCheckingEditorCustomization`](upsource:///spellchecker/src/com/intellij/spellchecker/ui/SpellCheckingEditorCustomization.java) disables spellchecking
- [`HorizontalScrollBarEditorCustomization`](upsource:///platform/platform-impl/src/com/intellij/ui/HorizontalScrollBarEditorCustomization.java) to turn on/off horizontal scrollbar
- [`ErrorStripeEditorCustomization`](upsource:///platform/platform-impl/src/com/intellij/ui/ErrorStripeEditorCustomization.java) to turn on/off error stripes on right

`EditorTextField` has a number of subclasses that can be used as needed for additional features.

If you want to use an editor as an input field in a dialog box, then consider using
[`LanguageTextField`](upsource:///platform/platform-impl/src/com/intellij/ui/LanguageTextField.java),
it provides a more accessible API.

If you want to add autocompletion to the editor, then use
[`TextFieldWithCompletion`](upsource:///platform/platform-impl/src/com/intellij/util/textCompletion/TextFieldWithCompletion.java).
The constructor takes as an argument a class that implements
[`TextCompletionProvider`](upsource:///platform/platform-impl/src/com/intellij/util/textCompletion/TextCompletionProvider.java)
to provide autocompletion variants.
Use
[`TextFieldCompletionProvider`](upsource:///platform/lang-impl/src/com/intellij/util/TextFieldCompletionProvider.java)
to create your own provider.
For this, override `addCompletionVariants()` and add completion variants using `CompletionResultSet.addElement()`.

See also
[`TextFieldCompletionProviderDumbAware`](upsource:///platform/lang-impl/src/com/intellij/util/TextFieldCompletionProviderDumbAware.java)
for completion even at the indexing stage.

Refer to the [](code_completion.md) to learn more about completion.

### Java

A common use case for `EditorTextField` is entering the name of a Java class or package.
This can be accomplished with the following steps:

* Use [`JavaCodeFragmentFactory.createReferenceCodeFragment()`](upsource:///java/java-psi-api/src/com/intellij/psi/JavaCodeFragmentFactory.java) to create a code fragment representing the class or package name;
* Call [`PsiDocumentManager.getDocument()`](upsource:///platform/core-api/src/com/intellij/psi/PsiDocumentManager.java) to get the document corresponding to the code fragment;
* Pass the returned document to the [`EditorTextField`](upsource:///platform/platform-impl/src/com/intellij/ui/EditorTextField.java) constructor or its `setDocument()` method.

```java
PsiFile psiFile = PsiDocumentManager.getInstance(project)
        .getPsiFile(editor.getDocument());
PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());

PsiExpressionCodeFragment code =
        JavaCodeFragmentFactory.getInstance(project)
        .createExpressionCodeFragment("", element, null, true);

Document document =
        PsiDocumentManager.getInstance(project).getDocument(code);

EditorTextField myInput =
        new EditorTextField(document, project, JavaFileType.INSTANCE);
```

> If your plugin depends on Java functionality and targets 2019.2 or later, please make sure to follow the steps from this [blog post](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/).
>
{type="note"}

**TIPS**:

* When creating more than one field, two separate documents are needed.
  This is accomplished by using separate instances of `PsiExpressionCodeFragment`.
* `setText()` no longer works for the input field.
  However, `createExpressionCodeFragment()` accepts the text for the field as an argument.
  The empty string can be replaced and create a new document in lieu of `setText()`.
* Instances of `JTextField` in the GUI builder can be replaced with a custom replacement component using the right click in your IDE.
  Make sure to use "Custom Create" so the initialization code works properly.
