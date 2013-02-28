package com.simpleplugin;

import com.intellij.testFramework.ParsingTestCase;

public class SimpleParsingTest extends ParsingTestCase {
    public SimpleParsingTest() {
        super("", "simple", new SimpleParserDefinition());
    }

    public void testExample() {
        doTest(true, false);
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    protected void doTest(boolean checkResult, boolean suppressErrors) {
        super.doTest(checkResult);
        if (!suppressErrors) {
            assertFalse(
                    "PsiFile contains error elements",
                    toParseTreeText(myFile, skipSpaces(), includeRanges()).contains("PsiErrorElement")
            );
        }
    }
}
