---
title: 9. Commenter Test
---

In this test we will check if the commenter, implemented in the [Commenter](/tutorials/custom_language_support/commenter.md) section of the [Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md), works as we expect.

### 9.1. Define a test method

```java
public void testCommenter() {
    myFixture.configureByText(SimpleFileType.INSTANCE, "<caret>website = http://en.wikipedia.org/");
    CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
    commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
    myFixture.checkResult("#website = http://en.wikipedia.org/");
    commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
    myFixture.checkResult("website = http://en.wikipedia.org/");
}
```

### 9.2. Run the test

Run the test and make sure it's green.

-----

[Previous](find_usages_test.md)
[Top](/tutorials/writing_tests_for_plugins.md)
[Next](reference_test.md)


