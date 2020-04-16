// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.application.options.CodeStyle;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.sdk.language.psi.SimpleProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SimpleCodeInsightTest extends LightJavaCodeInsightFixtureTestCase {

  /**
   *
   * @return path to test data file directory relative to working directory in the run configuration for this test.
   */
  @Override
  protected String getTestDataPath() {
    return "src/test/testData";
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
    myFixture.checkHighlighting(false, false, true, true);
  }

  public void testFormatter() {
    myFixture.configureByFile("FormatterTestData.simple");
    CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
    CodeStyle.getLanguageSettings(myFixture.getFile()).KEEP_BLANK_LINES_IN_CODE = 2;
    WriteCommandAction.writeCommandAction(getProject()).run(() ->
            CodeStyleManager.getInstance(getProject()).reformatText(
                    myFixture.getFile(),
                    ContainerUtil.newArrayList(myFixture.getFile().getTextRange())
            )
    );
    myFixture.checkResultByFile("DefaultTestData.simple");
  }

  public void testRename() {
    myFixture.configureByFiles("RenameTestData.java", "RenameTestData.simple");
    myFixture.renameElementAtCaret("websiteUrl");
    myFixture.checkResultByFile("RenameTestData.simple", "RenameTestDataAfter.simple", false);
  }

  public void testFolding() {
    myFixture.configureByFile("DefaultTestData.simple");
    myFixture.testFolding(getTestDataPath() + "/FoldingTestData.java");
  }

  public void testFindUsages() {
    Collection<UsageInfo> usageInfos = myFixture.testFindUsages("FindUsagesTestData.simple", "FindUsagesTestData.java");
    assertEquals(1, usageInfos.size());
  }

  public void testCommenter() {
    myFixture.configureByText(SimpleFileType.INSTANCE, "<caret>website = https://en.wikipedia.org/");
    CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
    commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
    myFixture.checkResult("#website = https://en.wikipedia.org/");
    commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
    myFixture.checkResult("website = https://en.wikipedia.org/");
  }

  public void testReference() {
    myFixture.configureByFiles("ReferenceTestData.java", "DefaultTestData.simple");
    PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
    assertEquals("https://en.wikipedia.org/", ((SimpleProperty) element.getReferences()[0].resolve()).getValue());
  }
}
