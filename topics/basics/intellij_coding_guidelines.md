<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Coding Guidelines

<link-summary>Coding guidelines for the code contributed to the IntelliJ Platform.</link-summary>

Following these guidelines when writing code intended for contribution to the IntelliJ Platform makes it easier for the JetBrains development team to review and accept the changes.

## Following the Latest Source Code

When submitting patches, it is strongly recommended to build them against the latest version of the code from the [intellij-community Git repository](intellij_platform.md#open-source).
The easiest way to do so is to clone the repository, track the work in Git, and provide the changes as described in [](platform_contributions.md#submit-a-patch).

## General Architectural Principles

Follow common Java architectural principles as closely as possible. "Effective Java" by Joshua Bloch is the right place to start.

## Tests

Functional tests cover most of the existing functionality of IntelliJ IDEA.
If tests cover the modified area, run the tests and make sure that the changes do not introduce any new test failures.
It is also strongly recommended to provide new functional tests that cover the fixed bugs or the added features.

## Code Formatting

The code formatting requirements are generally lax, but at least the following conventions must be observed:

- 2 space indents in source files
- Use `my` prefix for instance variables and `our` prefix for class variables.
- New source code files must include a copyright statement with the Apache 2 license and the name of the contributor.

The easiest way to follow the code formatting guidelines is to reformat the submitted code using the shared code style, which is included in the IntelliJ IDEA project directory.

## Inspections

The IntelliJ IDEA project includes a shared inspection profile.
Make sure that the submitted code does not contain any warnings highlighted by the inspections configured in that inspection profile.

## Javadoc Comments

If the code adds new OpenAPI interfaces, classes, methods, or extension points, provide Javadoc comments describing the parameters and intended usage of the APIs.
Providing Javadoc or other comments for other parts of the code is a good idea but isn't required.

## Commits

To avoid unnecessary work when reviewing the changes, follow these guidelines:

- Look through all changes in the patch or pull request before submitting it.
  Make sure that every change is there for a reason.
- Please don't include unfinished work in the patch.
  Make sure that it doesn't contain any TODO comments.
  If some added code ended up not being needed, make sure to delete it before submitting the patch.
- Please don't include any changes that affect formatting, fixing "yellow code" (warnings), or code style along with actual changes that fix a bug or implement a feature.
  No one likes to leave poor code, but remember that having these changes mixed complicates the process of review.
- Please don't fix multiple problems within a single patch or pull request.
- Please don't commit changes to configuration files (<path>runConfigurations/IDEA.xml</path>, <path>codeStyleSettings.xml</path>, <path>misc.xml</path>, etc.) unless it is essential for the fix itself.
- Please avoid moving or renaming code unless it is necessary for the fix. Keeping backwards compatibility is critical for the platform.

The ideal pull request would contain one commit with everything needed to fix the bug or implement a feature, but nothing else.
"Commit early, commit often" perfectly applies only to local commits, but such "public commits" are hard to review (the reviewer needs either to go commit by commit spending more time to review work-in-progress, or to review all changes at once thus losing valuable information stored in commit messages).

The best would be to commit early, but then to squash all commits into one with a descriptive commit message.

Sometimes several commits for a single issue are also acceptable, but each of these should be self-contained "steps" to solve the problem.
