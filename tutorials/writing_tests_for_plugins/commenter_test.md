---
title: 9. Commenter Test
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test will check if the commenter, implemented in the [Commenter](/tutorials/custom_language_support/commenter.md) section of the Custom Language Support Tutorial, works as expected.

## 9.1. Define a Test Method
Add the `testCommenter()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test constructs a Simple Language properties file containing one line, with the virtual caret positioned at the beginning of the line.
The test calls the commenter to insert a comment character at the caret, then verifies the results.
It again calls the line comment action to remove the comment character and verifies the results.

```java
  public void testCommenter() {
    myFixture.configureByText(SimpleFileType.INSTANCE, "<caret>website = https://en.wikipedia.org/");
    CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
    commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
    myFixture.checkResult("#website = https://en.wikipedia.org/");
    commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
    myFixture.checkResult("website = https://en.wikipedia.org/");
  }
```

## 9.2. Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.
