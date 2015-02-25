Rules for migrating documents from other sources
====

If you migrate any documents from other sources to IntelliJ Doc Engine,
please mark initial source with INITIAL_SOURCE tag like the following sample shows.

```
<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/Build+Number+Ranges
-->
```

Any issues in a document needed to be re-worked, extended, and\/or newly written should be marked with.

```
<!--TODO Provide detailed description here-->
```
Please make it **case sensitive**.
These tags are required for future testing.
Thanks.