package com.intellij.sdk.language;

import com.intellij.testFramework.ParsingTestCase;

public class SimpleParsingTest extends ParsingTestCase {
  public SimpleParsingTest() {
    super("", "simple", new SimpleParserDefinition());
  }

  public void testParsingTestData() {
    doTest(true);
  }
  
  /**
   *
   * @return path to test data file directory relative to root of this module.
   */
  @Override
  protected String getTestDataPath() {
    return "src/test/resources";
  }

  @Override
  protected boolean skipSpaces() {
    return false;
  }

  @Override
  protected boolean includeRanges() {
    return true;
  }
}
