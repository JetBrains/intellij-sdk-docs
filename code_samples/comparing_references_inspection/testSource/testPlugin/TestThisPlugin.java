package testPlugin;


import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ComparingReferencesInspection;
import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.UsefulTestCase;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @see JavaCodeInsightFixtureTestCase
 * @see LightCodeInsightFixtureTestCase
 */
public class TestThisPlugin extends UsefulTestCase {
  
  protected CodeInsightTestFixture myFixture;
  
  
  // Specify the path to the test data directory
  final String dataPath = PathManager.getResourceRoot(TestThisPlugin.class, "/testPlugin/TestThisPlugin.class");
  
  @Before
  public void setUp() throws Exception {
    
    final IdeaTestFixtureFactory fixtureFactory = IdeaTestFixtureFactory.getFixtureFactory();
    final TestFixtureBuilder<IdeaProjectTestFixture> testFixtureBuilder =
          fixtureFactory.createFixtureBuilder(getName());
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
    myFixture = null;
  }
  
  protected void doTest(String testName, String hint) throws Throwable {
    myFixture.configureByFile(testName + ".java");
    
    myFixture.enableInspections(ComparingReferencesInspection.class);
    List<HighlightInfo> highlightInfos = myFixture.doHighlighting();
    Assert.assertTrue(!highlightInfos.isEmpty());
    
    final IntentionAction action = myFixture.findSingleIntention(hint);
    
    Assert.assertNotNull(action);
    myFixture.launchAction(action);
    myFixture.checkResultByFile(testName + ".after.java");
  }
  
  /**
   * Test the "==" case
   * Note the hint must match CriQuickFix#getName
   *
   * @throws Throwable
   */
  @Test
  public void test() throws Throwable {
//    doTest("before", "Use equals()");
  doTest("before", ComparingReferencesInspection.QUICK_FIX_NAME);
  }
  
  /**
   * Test the "!=" case
   * Note the hint must match CriQuickFix#getName
   * @throws Throwable
   */
  @Test
  public void test1() throws Throwable {
    doTest("before1", ComparingReferencesInspection.QUICK_FIX_NAME);
  }
  
}
