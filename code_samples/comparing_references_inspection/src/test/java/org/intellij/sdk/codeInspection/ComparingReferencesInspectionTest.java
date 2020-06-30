// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.codeInspection;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class for testing ComparingReferencesInspection.
 * Requires idea.home.path to be set in build.gradle
 * doTest() does the work for individual test cases.
 */
public class ComparingReferencesInspectionTest extends LightJavaCodeInsightFixtureTestCase {

  /**
   * Defines path to files used for running tests
   *
   * @return The path from this module's root directory ($MODULE_WORKING_DIR$) to the
   * directory containing files for these tests.
   */
  @Override
  protected String getTestDataPath() {
    return "src/test/testData";
  }

  /**
   * Given the name of a test file, runs comparing references inspection quick fix and tests
   * the results against a reference outcome file. File name pattern 'foo.java' and 'foo.after.java'
   * are matching before and after files in the testData directory.
   *
   * @param testName The name of the test file before comparing references inspection.
   */
  protected void doTest(@NotNull String testName) {
    // Initialize the test based on the testData file
    myFixture.configureByFile(testName + ".java");
    // Initialize the inspection and get a list of highlighted
    myFixture.enableInspections(new ComparingReferencesInspection());
    List<HighlightInfo> highlightInfos = myFixture.doHighlighting();
    assertFalse(highlightInfos.isEmpty());
    // Get the quick fix action for comparing references inspection and apply it to the file
    final IntentionAction action = myFixture.findSingleIntention(ComparingReferencesInspection.QUICK_FIX_NAME);
    assertNotNull(action);
    myFixture.launchAction(action);
    // Verify the results
    myFixture.checkResultByFile(testName + ".after.java");
  }

  /**
   * Test the "==" case
   */
  public void testRelationalEq() throws Throwable {
    doTest("Eq");
  }

  /**
   * Test the "!=" case
   */
  public void testRelationalNeq() throws Throwable {
    doTest("Neq");
  }

}
