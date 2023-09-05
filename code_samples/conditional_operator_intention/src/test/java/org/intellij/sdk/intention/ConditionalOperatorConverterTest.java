// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.intention;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.Assert;

public class ConditionalOperatorConverterTest extends LightJavaCodeInsightFixtureTestCase {

  /**
   * Defines path to files used for running tests.
   *
   * @return The path from this module's root directory ({@code $MODULE_WORKING_DIR$}) to the
   * directory containing files for these tests.
   */
  @Override
  protected String getTestDataPath() {
    return "src/test/testData";
  }

  protected void doTest(String testName, String hint) {
    myFixture.configureByFile(testName + ".java");
    final IntentionAction action = myFixture.findSingleIntention(hint);
    Assert.assertNotNull(action);
    myFixture.launchAction(action);
    myFixture.checkResultByFile(testName + ".after.java");
  }

  public void testIntention() {
    doTest("before.template", "SDK: Convert ternary operator to if statement");
  }

}
