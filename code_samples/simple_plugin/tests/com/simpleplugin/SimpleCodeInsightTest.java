package com.simpleplugin;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.vfs.newvfs.impl.VfsRootAccess;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import com.simpleplugin.psi.SimpleProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SimpleCodeInsightTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected void setUp() throws Exception {
        VfsRootAccess.SHOULD_PERFORM_ACCESS_CHECK = false; // TODO: a workaround for v15
        super.setUp();
    }

    @Override
    protected String getTestDataPath() {
        return "../../SimplePlugin/testData";
    }

    public void testCompletion() {
        myFixture.configureByFiles("CompleteTestData.java", "DefaultTestData.simple");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(Arrays.asList("key with spaces", "language", "message", "tab", "website")));
        assertEquals(5, strings.size());
    }

    public void testAnnotator() {
        myFixture.configureByFiles("AnnotatorTestData.java", "DefaultTestData.simple");
        myFixture.checkHighlighting(false, false, true);
    }

    public void testFormatter() {
        myFixture.configureByFiles("FormatterTestData.simple");
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
        CodeStyleSettingsManager.getSettings(getProject()).KEEP_BLANK_LINES_IN_CODE = 2;
        new WriteCommandAction.Simple(getProject()) {
            @Override
            protected void run() throws Throwable {
                CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
            }
        }.execute();
        myFixture.checkResultByFile("DefaultTestData.simple");
    }

    public void testRename() {
        myFixture.configureByFiles("RenameTestData.java", "RenameTestData.simple");
        myFixture.renameElementAtCaret("websiteUrl");
        myFixture.checkResultByFile("RenameTestData.simple", "RenameTestDataAfter.simple", false);
    }

    public void testFolding() {
        myFixture.configureByFiles("DefaultTestData.simple");
        myFixture.testFolding(getTestDataPath() + "/FoldingTestData.java");
    }

    public void testFindUsages() {
        Collection<UsageInfo> usageInfos = myFixture.testFindUsages("FindUsagesTestData.simple", "FindUsagesTestData.java");
        assertEquals(1, usageInfos.size());
    }

    public void testCommenter() {
        myFixture.configureByText(SimpleFileType.INSTANCE, "<caret>website = http://en.wikipedia.org/");
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("#website = http://en.wikipedia.org/");
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("website = http://en.wikipedia.org/");
    }

    public void testReference() {
        myFixture.configureByFiles("ReferenceTestData.java", "DefaultTestData.simple");
        PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
        assertEquals("http://en.wikipedia.org/", ((SimpleProperty) element.getReferences()[0].resolve()).getValue());
    }
}
