// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.codeInspection;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;

/**
 * -ea -Xbootclasspath/p:../out/classes/production/boot -XX:+HeapDumpOnOutOfMemoryError -Xmx512m -XX:MaxPermSize=320m
 * -Didea.system.path=../test-system -Didea.home.path=../ -Didea.config.path=../test-config -Didea.test.group=ALL_EXCLUDE_DEFINED
 */
public class CompRefInspTest extends LightJavaCodeInsightFixtureTestCase {
  
  /**
   * Returns path to the test data relative to "idea.home.path" defined in build.gradle.
   */
  @Override
  protected String getBasePath() {
    return "sdk/code_samples/comparing_references_inspection/src/test/testData";
  }
  
  /**
   * Specifies the requirements for this project needed in this test,
   * such as the module type, the configured SDK, facets, libraries, etc.
   * @return
   */
  @NotNull
  protected LightProjectDescriptor getProjectDescriptor() {
    return LightJavaCodeInsightFixtureTestCase.JAVA_8_ANNOTATED;
  }
  
  /**
   * Test the "==" case
   * Note the hint must match CriQuickFix#getName
   *
   */
  public void testRelationalEq() {
    myFixture.configureByFiles("before.java");
    final IntentionAction action = myFixture.findSingleIntention(ComparingReferencesInspection.QUICK_FIX_NAME);
    assertNotNull(action);
    myFixture.launchAction(action);
    myFixture.checkResultByFile("before.after.java");
  }
  
  /**
   * Test the "!=" case
   * Note the hint must match CriQuickFix#getName
   *
   */
  public void testRelationalNeq() {
    myFixture.configureByFiles("before1.java");
    final IntentionAction action = myFixture.findSingleIntention(ComparingReferencesInspection.QUICK_FIX_NAME);
    assertNotNull(action);
    myFixture.launchAction(action);
    myFixture.checkResultByFile("before1.after.java");
  }
  
}
