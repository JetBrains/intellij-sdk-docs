Rules for migrating documents from other sources
====

#Tracking content source
If you migrate any documents from other sources (e.g. Confluence of Development Forum) to IntelliJ Doc Engine,
please mark the newly created Markdown file with INITIAL_SOURCE tag like the following sample shows.

```
<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/Build+Number+Ranges
-->
```

#TODOs and issues under development
Any issues in a document needed to be re-worked, extended, and\/or newly written should be marked with.

```
<!--TODO Provide detailed description here-->
```

#Tracking old already processed content
If you've migrated a document from confluence, this document should be marked with <migrated> tag.

Please make all the tags **case sensitive**.
These tags are required for future coverage and consistency testing.
Thanks.