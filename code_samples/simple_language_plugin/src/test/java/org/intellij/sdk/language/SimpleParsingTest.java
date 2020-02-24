// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

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
    return "src/test/testData";
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
