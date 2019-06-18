// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package testPlugin;

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

public class YourTest extends UsefulTestCase {
  protected CodeInsightTestFixture myFixture;
  final String dataPath = PathManager.getResourceRoot(YourTest.class, "/testPlugin/YourTest.class");

  @Before

  public void setUp() throws Exception {

    final IdeaTestFixtureFactory fixtureFactory = IdeaTestFixtureFactory.getFixtureFactory();
    final TestFixtureBuilder<IdeaProjectTestFixture> testFixtureBuilder = fixtureFactory.createFixtureBuilder(getName());
    myFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(testFixtureBuilder.getFixture());
    myFixture.setTestDataPath(dataPath);
    final JavaModuleFixtureBuilder builder = testFixtureBuilder.addModule(JavaModuleFixtureBuilder.class);

    builder.addContentRoot(myFixture.getTempDirPath()).addSourceRoot("");
    builder.setMockJdkLevel(JavaModuleFixtureBuilder.MockJdkLevel.jdk15);
    myFixture.setUp();

  }

  @After
  public void tearDown() throws Exception {
    myFixture.tearDown();
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
