package com.simpleplugin;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.indexing.FileBasedIndex;
import com.simpleplugin.psi.SimpleElementFactory;
import com.simpleplugin.psi.SimpleFile;
import com.simpleplugin.psi.SimpleProperty;
import com.simpleplugin.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

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
                Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SimpleFileType.INSTANCE,
                        GlobalSearchScope.allScope(project));
                if (virtualFiles.size() == 1) {
                    createProperty(project, virtualFiles.iterator().next());
                } else {
                    final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(SimpleFileType.INSTANCE);
                    descriptor.setRoots(project.getBaseDir());
                    final VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
                    if (file != null) {
                        createProperty(project, file);
                    }
                }
            }
        });
    }

    private void createProperty(final Project project, final VirtualFile file) {
        new WriteCommandAction.Simple(project) {
            @Override
            public void run() {
                SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(file);
                ASTNode lastChildNode = simpleFile.getNode().getLastChildNode();
                if (lastChildNode != null && !lastChildNode.getElementType().equals(SimpleTypes.CRLF)) {
                    simpleFile.getNode().addChild(SimpleElementFactory.createCRLF(project).getNode());
                }
                // IMPORTANT: change spaces to escaped spaces or the new node will only have the first word for the key
                SimpleProperty property = SimpleElementFactory.createProperty(project, key.replaceAll(" ", "\\\\ "), "");
                simpleFile.getNode().addChild(property.getNode());
                ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
                FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().
                        moveCaretRelatively(2, 0, false, false, false);

                  // almost the same thing but manipulating plain text of the document instead of PSI
//                FileEditorManager.getInstance(project).openFile(file, true);
//                final Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
//                final Document document = editor.getDocument();
//                document.insertString(document.getTextLength(), "\n" + key.replaceAll(" ", "\\\\ ") + " = ");
//                editor.getCaretModel().getPrimaryCaret().moveToOffset(document.getTextLength());
            }
        }.execute();
    }
}
