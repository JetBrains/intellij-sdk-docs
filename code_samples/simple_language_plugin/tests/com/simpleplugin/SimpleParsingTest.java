package com.simpleplugin;

import com.intellij.testFramework.ParsingTestCase;

public class SimpleParsingTest extends ParsingTestCase {
  public SimpleParsingTest() {
    super("", "simple", new SimpleParserDefinition());
  }

  public void testParsingTestData() {
    doTest(true);
  }

  @Override
  protected String getTestDataPath() {
    return "code_samples/simple_language_plugin/testData";
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
