# Snippets
{is-library="true"}

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<snippet id="runTests">

Run the test and make sure it's successful.

<procedure title="Running tests" collapsible="true" default-state="collapsed">

1. Open the <control>Gradle</control> Tool Window.
2. Select the <control>simple_language_plugin</control> node.
  You may need to reimport it as a Gradle project.
3. Drill down under <control>simple_language_plugin</control> to <control>Tasks</control>, <control>verification</control>, <control>test</control> task.
4. Run the <control>test</control> task.

The results are displayed in the <control>Run</control> Tool Window, and also written to the <path>simple_language_plugin/build/test-results/test</path> directory.

If the <control>Run</control> Tool Window displays the error *Test events were not received*, do the following:
1. In the <control>Gradle</control> Tool Window, drill down under <control>simple_language_plugin</control> to <control>Tasks</control>, <control>build</control>, <control>clean</control> task.
2. Run the <control>clean</control> task, which deletes the <path>simple_language_plugin/build</path> directory.
3. Restart the test.

</procedure>

</snippet>
