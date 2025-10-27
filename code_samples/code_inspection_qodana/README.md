# IntelliJ SDK Code Inspection Sample for Qodana

**IntelliJ SDK Code Inspection Sample for Qodana** contains a code inspection sample for IntelliJ Platform plugins.

A single rule is established: every class in the `service` packages must have a `Service` suffix.

## Subprojects

- `code_inspection` - a standalone IntelliJ Platform plugin with this inspection
- `qodana-playground` - a sample project which is validated by Qodana and the custom plugin

## Building and Running

1. Build the IntelliJ Platform plugin and copy it into the custom plugin directory in the Qodana playground project:

   ```
   ./gradlew stagePluginForQodana
   ```

   This will build a plugin JAR and copy it into the `qodana-playground/.qodana` directory.

2. Run Qodana from the `qodana-playground` directory with the custom plugin installed.

   ```
   cd qodana-playground
   qodana scan --clear-cache -v $(echo $PWD/.qodana/*.jar):/opt/idea/custom-plugins/codeinspection.jar
   ```

3. See the Qodana reports in the browser.

## Building and Running in the IDE

1. Build the IntelliJ Platform plugin.

   ```
   ./gradlew buildPlugin
   ```

2. Install the `build/code_inspection-<version>.zip` plugin into JetBrains IDE manually.
3. Make sure that the Qodana plugin is installed and enabled in the JetBrains IDE.
4. Run Qodana by clicking the Tools | Qodana | Try Code Analysis with Qodana
5. See the Qodana reports in the Qodana tool window in the IDE.
