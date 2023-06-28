<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Coding Guidelines

<link-summary>Coding guidelines for the code contributed to the IntelliJ Platform.</link-summary>

If you are writing code that you would like to contribute to the IntelliJ Platform, following these guidelines will make it easier for the JetBrains development team to review and accept your changes.

## Following the Latest Source Code

If you submit patches, we strongly recommend building your patches against the latest version of the code from the [intellij-community Git repository](intellij_platform.md#open-source).
The easiest way to do so is to clone the repository, track your work in Git, and provide your changes as described in [](platform_contributions.md#submit-a-patch).

## General Architectural Principles

Please do your best to follow common Java architectural principles. "Effective Java" by Joshua Bloch is the right place to start.

## Tests

Functional tests cover most of the existing functionality of IntelliJ IDEA.
If tests cover the area you're modifying, you must run the tests and make sure that your changes do not introduce any new test failures.
It's also strongly recommended that you provide new functional tests that cover the bugs you fix or the new features that you add.

## Code Formatting

We're generally pretty lax about code formatting, but at least the following conventions must be observed:

- 2 space indents in source files
- Use `my` prefix for instance variables and `our` prefix for class variables.
- New source code files must include a copyright statement with the Apache 2 license and the name of the contributor.

The easiest way to follow our code formatting guidelines is to reformat your code submissions using the shared code style, which is included in the IntelliJ IDEA Community Edition project directory.

## Inspections

The IntelliJ IDEA Community Edition project includes a shared inspection profile.
We strongly recommend making sure that the code you submit does not contain any warnings highlighted by the inspections configured in that inspection profile.

## Javadoc Comments

If your code adds new OpenAPI interfaces, classes, methods, or extension points, you must provide Javadoc comments describing the parameters and intended usage of the APIs.
Providing Javadoc or other comments for other parts of the code is a good idea but isn't required.

## Commits

To avoid unnecessary work when reviewing your changes, please follow these guidelines:

- Look through all of your changes in your patch or pull request before you submit it to us.
  Make sure that everything you've changed is there for a reason.
- Please don't include unfinished work in the patch.
  Make sure that it doesn't contain any TODO comments.
  If you added some code and ended up not needing it, please make sure that you delete it before you submit your patch.
- Please don't include any changes that affect formatting, fixing "yellow code" (warnings), or code style along with actual changes that fix a bug or implement a feature.
  No one likes to leave poor code, but remember that having these changes mixed complicates the process of review.
- Please don't fix multiple problems within a single patch or pull request.
- Please don't commit your changes to configuration files (<path>runConfigurations/IDEA.xml</path>, <path>codeStyleSettings.xml</path>, <path>misc.xml</path>, etc.) unless it is essential for the fix itself.
- Please avoid moving or renaming code unless it is necessary for the fix. Keeping backwards compatibility is critical for the platform.

The ideal pull request would contain one commit with everything needed to fix the bug or implement a feature, but nothing else.
"Commit early, commit often" perfectly applies only to local commits, but such "public commits" are hard to review (the reviewer needs either to go commit by commit spending more time to review work-in-progress, or to review all changes at once thus losing valuable information stored in commit messages).

The best would be to commit early, but then to squash all commits into one with a descriptive commit message.

Sometimes several commits for a single issue are also acceptable, but each of these should be self-contained "steps" to solve the problem.
