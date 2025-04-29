<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Editor Components

<link-summary>Creating, customizing, and using editor components in different contexts.</link-summary>

## `EditorTextField`

Compared to [Swing `JTextArea`](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/JTextArea.html), the IntelliJ Platform's editor component has a ton of advantages: syntax highlighting support, code completion, code folding, and much more.
[Editors](editors.md) are normally displayed in editor tabs, but they can be embedded in dialogs or tool windows, too.
This is enabled by the [`EditorTextField`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/EditorTextField.java) component.

The following attributes can be specified:

* The file type according to which the text in the text field is parsed;
* Whether the text field is read-only or editable;
* Whether the text field is single-line or multiline.

Further customizations are possible by subclassing and overriding `createEditor()` and applying [`EditorCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/EditorCustomization.java).
Several commonly needed customization implementations exist, including:
- [`SpellCheckingEditorCustomization`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/ui/SpellCheckingEditorCustomization.java) disables spellchecking
- [`HorizontalScrollBarEditorCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/HorizontalScrollBarEditorCustomization.java) to turn on/off horizontal scrollbar
- [`ErrorStripeEditorCustomization`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/ErrorStripeEditorCustomization.java) to turn on/off error stripes on the right

`EditorTextField` has a number of subclasses that can be used as needed for additional features.

If you want to use an editor as an input field in a dialog, then consider using
[`LanguageTextField`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/LanguageTextField.java)
as it provides a more accessible API.

### Providing Completion

If you want to add autocompletion to the editor, then use
[`TextFieldWithCompletion`](%gh-ic%/platform/platform-impl/src/com/intellij/util/textCompletion/TextFieldWithCompletion.java).
The constructor takes as an argument a class that implements
[`TextCompletionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/util/textCompletion/TextCompletionProvider.java)
to provide autocompletion variants.
Use
[`TextFieldCompletionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/util/TextFieldCompletionProvider.java)
to create your own provider.
For this, override `addCompletionVariants()` and add completion variants using `CompletionResultSet.addElement()`.

See also
[`TextFieldCompletionProviderDumbAware`](%gh-ic%/platform/lang-impl/src/com/intellij/util/TextFieldCompletionProviderDumbAware.java)
for completion even at the indexing stage.

See [](code_completion.md) to learn more about completion.

### Java

> If your plugin depends on Java functionality, see [](plugin_compatibility.md#java).
>
{style="note"}

A common use case for `EditorTextField` is entering the name of a Java class or package.
This can be accomplished with the following steps:

* Use [`JavaCodeFragmentFactory.createReferenceCodeFragment()`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaCodeFragmentFactory.java) to create a code fragment representing the class or package name;
* Call [`PsiDocumentManager.getDocument()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiDocumentManager.java) to get the document corresponding to the code fragment;
* Pass the returned document to the [`EditorTextField`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/EditorTextField.java) constructor or its `setDocument()` method.

```java
PsiFile psiFile = PsiDocumentManager.getInstance(project)
        .getPsiFile(editor.getDocument());
PsiElement element =
        psiFile.findElementAt(editor.getCaretModel().getOffset());

PsiExpressionCodeFragment code =
        JavaCodeFragmentFactory.getInstance(project)
        .createExpressionCodeFragment("", element, null, true);

Document document =
        PsiDocumentManager.getInstance(project).getDocument(code);

EditorTextField editorTextField =
        new EditorTextField(document, project, JavaFileType.INSTANCE);
```

#### Tips

* When creating more than one field, two separate documents are needed.
  This is accomplished by using separate instances of `PsiExpressionCodeFragment`.
* `setText()` no longer works for the input field.
  However, `createExpressionCodeFragment()` accepts the text for the field as an argument.
  The empty string can be replaced and create a new document in lieu of `setText()`.
* Instances of `JTextField` in the GUI builder can be replaced with a custom replacement component using the right click in your IDE.
  Make sure to use "Custom Create" so the initialization code works properly.
