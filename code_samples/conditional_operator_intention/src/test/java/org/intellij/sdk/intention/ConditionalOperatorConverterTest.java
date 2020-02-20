// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.intention;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.UsefulTestCase;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import org.junit.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Sep 13, 2010
 * Time: 9:35:50 PM
 * To change this template use File | Settings | File Templates.
 * This test requires Editor > Code Style set to Default IDE
 */

public class ConditionalOperatorConverterTest extends LightJavaCodeInsightFixtureTestCase {

  /**
   * Defines path to files used for running tests
   *
   * @return The path from this module's root directory ($MODULE_WORKING_DIR$) to the
   * directory containing files for these tests.
   */
  @Override
  protected String getTestDataPath() {
    return "src/test/resources";
  }

  protected void doTest(String testName, String hint) {
    // Messages.showInfoMessage("Test started", "Info");
    myFixture.configureByFile(testName + ".java");
    final IntentionAction action = myFixture.findSingleIntention(hint);
    Assert.assertNotNull(action);
    myFixture.launchAction(action);
    myFixture.checkResultByFile(testName + ".after.java");
  }

  @Test
  public void test() {
    doTest("before.template", "SDK Convert ternary operator to if statement");
  }

}
