---
layout: editable
title: 18. Quick Fix
---


A quick fix allows to apply an automatic changes to the code via *⌥⏎*.

Let's add a quick fix which helps to define an unresolved property from its usage.

### 1. Update the element factory

```java
package com.simpleplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.simpleplugin.SimpleFileType;

public class SimpleElementFactory {
    public static SimpleProperty createProperty(Project project, String name, String value) {
        final SimpleFile file = createFile(project, name + " = " + value);
        return (SimpleProperty) file.getFirstChild();
    }

    public static SimpleProperty createProperty(Project project, String name) {
        final SimpleFile file = createFile(project, name);
        return (SimpleProperty) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final SimpleFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

    public static SimpleFile createFile(Project project, String text) {
        String name = "dummy.simple";
        return (SimpleFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SimpleFileType.INSTANCE, text);
    }
}
```

### 2. Define an intention action

The quick fix will create a property in the file chosen by user, and navigate to this property after creation.

```java
package com.simpleplugin;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.IncorrectOperationException;
import com.simpleplugin.psi.SimpleElementFactory;
import com.simpleplugin.psi.SimpleFile;
import com.simpleplugin.psi.SimpleProperty;
import com.simpleplugin.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

class CreatePropertyQuickFix extends BaseIntentionAction {
    private String key;

    CreatePropertyQuickFix(String key) {
        this.key = key;
    }

    @NotNull
    @Override
    public String getText() {
        return "Create property";
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return "Simple properties";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(SimpleFileType.INSTANCE);
                descriptor.setRoots(project.getBaseDir());
                final VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
                if (file != null) {
                    ApplicationManager.getApplication().runWriteAction(new Runnable() {
                        @Override
                        public void run() {
                            SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(file);
                            ASTNode lastChildNode = simpleFile.getNode().getLastChildNode();
                            if (lastChildNode != null && !lastChildNode.getElementType().equals(SimpleTypes.CRLF)) {
                                simpleFile.getNode().addChild(SimpleElementFactory.createCRLF(project).getNode());
                            }
                            SimpleProperty property = SimpleElementFactory.createProperty(project, key, "");
                            simpleFile.getNode().addChild(property.getNode());
                            ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
                            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().
                                    moveCaretRelatively(2, 0, false, false, false);
                        }
                    });
                }
            }
        });
    }
}
```

### 3. Update the annotator

```java
package com.simpleplugin;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.simpleplugin.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            String value = (String) literalExpression.getValue();
            if (value != null && value.startsWith("simple:")) {
                Project project = element.getProject();
                String key = value.substring(7);
                List<SimpleProperty> properties = SimpleUtil.findProperties(project, key);
                if (properties.size() == 1) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset() + 7,
                            element.getTextRange().getStartOffset() + 7);
                    Annotation annotation = holder.createInfoAnnotation(range, null);
                    annotation.setTextAttributes(SyntaxHighlighterColors.LINE_COMMENT);
                } else if (properties.size() == 0) {
                    TextRange range = new TextRange(element.getTextRange().getStartOffset() + 8,
                            element.getTextRange().getEndOffset());
                    holder.createErrorAnnotation(range, "Unresolved property").
                            registerFix(new CreatePropertyQuickFix(key));
                }
            }
        }
    }
}
```

### 4. Run the project

Now let's try to use a property which is not defined yet.

![Quick Fix](img/quick_fix.png)

[Previous](commenter.html)
[Top](cls_tutorial.html)

