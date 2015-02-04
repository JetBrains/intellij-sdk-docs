Project wizard. Adding support for creating new project types.
===============

#Project Wizard
Working with the project wizard can be excessively illustrated with the following
[plugin] (https://github.com/bulenkov/RedlineSmalltalk.git)

##Implementing new module type
Additional support for specific tools and technologies is usually done via implementing some certain module type which is attached to the project.
New module type should be derived from the class
[ModuleType.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/openapi/module/ModuleType.java).

[Code sample] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)

##Project wizard
Main utilities to configure a custom project wizard can be found in the package
[lang-api.ide.util.projectWizard] (https://github.com/JetBrains/intellij-community/tree/master/platform/lang-api/src/com/intellij/ide/util/projectWizard).
These classes and interfaces serve the following purposes:

* Modification of the configuration wizard view
* Adding new steps to the wizard
* Providing additional setting for project creation
* Handling activities during project creation
* Initial environment configuration

###Module type
To create a new module type and an extension
```<moduleType id="MY_MODULE" implementationClass="st.redline.smalltalk.module.MyModuleType"/>```
to the [plugin.xml] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/resources/META-INF/plugin.xml).
A custom module type should extend the
[ModuleType.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/openapi/module/ModuleType.java)
generic from
[ModuleBuilder.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java).
The following
[sample] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)
of a custom module type show how this instance can be registered and implemented.

###Implementing module builder
To set up a new module environment
[ModuleBuilder.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java)
class should be extended and registered as an extension point like the following snippet shows:

    <extensions>
        <!--Place your extensions here-->
        <moduleBuilder builderClass="org.jetbrains.plugins.ruby.rails.facet.versions.MyModuleBuilder"/>
    </extensions>

Functionality which is mandatory to implement consists of:

* Setting up a root model for the new module by overriding ```public abstract void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException;```
* Getting a module type ```public abstract ModuleType getModuleType();```

See
[JavaModuleBuilder.java] (https://github.com/JetBrains/intellij-community/blob/master/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java)
to understand better how to implement a module builder.

If your module type is based on the java module and meant to support Java as well, extending
[JavaModuleBuilder.java] ((https://github.com/JetBrains/intellij-community/blob/master/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java))
is enough. No extension point needs no be registered.
A [code sample] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)
illustrating how
[JavaModuleBuilder.java] (https://github.com/JetBrains/intellij-community/blob/master/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java)
can be derived.

###Implementing module builder listener
Module builder listener reacts on a new module creation, which could be done either as a part of the project creation process,
or as adding a new module to the already existing project.
To provide a certain behavior right after a module has been created, module builder should implement
[ModuleBuilderListener.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilderListener.java)
Method ```public void moduleCreated(@NotNull final Module module);``` executed tasks right after a module has been created,
these may include configuring roots looking up for an SDK and setting it up, adding a specific facet if required and others.
For more details please see this
[code sample] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)

###Adding new wizard steps
Adding new steps to the module wizard can be done by overriding the
``` public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ModulesProvider modulesProvider);```
method in a custom
[module builder] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleBuilder.java).
If this method returns a non-empty array of ModuleWizardStep objects, new steps will be shown in their indexing oder while creating a new module.
This
[code sample] (https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleWizardStep.java)
illustrates how a custom wizard step can be created.
This class is derived from
[ModuleWizardStep.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java),
which has two methods to be overridden:

* ```public JComponent getComponent();``` defines how the step will look like
* ```public void updateDataModel();``` commits data from UI into ModuleBuilder and WizardContext

#Facet
Facets in IntelliJ are the way to store multiple kinds of module-specific settings, for instance to make a language support or framework available in some given module.
To understand facets better from the point of view of an end-user, please see
[this page] (http://confluence.jetbrains.com/display/IntelliJIDEA/Facets).



