package com.simpleplugin;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.indexing.FileBasedIndex;
import com.simpleplugin.psi.SimpleProperty;

import java.util.Collection;

public class SimpleCodeInsightTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "../../SimplePlugin/testData";
    }

    public void testResolve() {
        myFixture.configureByFiles("ResolveTestData.java", "Example.simple");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
        assertEquals("http://en.wikipedia.org/", ((SimpleProperty)element.getReferences()[0].resolve()).getValue());
    }

    public void testCompletion() {
        myFixture.configureByFiles("CompleteTestData.java", "Example.simple");
        myFixture.complete(CompletionType.BASIC, 1);
        System.out.println(myFixture.getLookupElementStrings());
    }

    public void testAnnotations() {
        myFixture.configureByFiles("AnnotationTest.java", "Example.simple");
        myFixture.checkHighlighting(false, false, true);
    }

    public void testFolding() {
        myFixture.configureByFiles("Example.simple");
        myFixture.testFolding(getTestDataPath() + "/FoldingTestData.java");
    }

    public void testFormatting() {
        myFixture.configureByFiles("FormattingTestData.simple");
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
            }
        });
        myFixture.checkResultByFile("Example.simple");
    }

    public void testRename() {
        myFixture.configureByFiles("RenameTestData.java", "RenameTestData.simple");
        myFixture.renameElementAtCaret("websiteUrl");
        myFixture.checkResultByFile("RenameTestData.simple", "RenameTestDataAfter.simple", false);
    }

    public void testCommenter() {
        myFixture.configureByText(SimpleFileType.INSTANCE, "<caret>website = http://en.wikipedia.org/");
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("#website = http://en.wikipedia.org/");
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("website = http://en.wikipedia.org/");
    }

    public void testFindUsages() {
        Collection<UsageInfo> usageInfos = myFixture.testFindUsages("FindUsagesTestData.simple", "FindUsagesTestData.java");
        assertEquals(1, usageInfos.size());
    }
}