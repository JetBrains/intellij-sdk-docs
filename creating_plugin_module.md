---
title: Creating a Plugin Module
---

# {{ page.title }}

<!--TODO Add process description with pics and links to code sample-->

This section explains how you can create a new plugin module from a scratch using the New Project wizard.
Optionally, you can import an existing project or import a project from external models.
You can also add a new plugin module to an existing *Intellij IDEA*  project.
For more information, refer to
[Intellij IDEA Web Help](http://www.jetbrains.com/idea/webhelp/index.jsp?reference.dialogs.new.project).

## To create a plugin module:

*  On the main menu, choose *File \| New Project*. The New Project wizard starts.

*  On the starting page, select *Create new project from scratch*  and click *Next*  to proceed with the wizard.
The wizard displays the *Settings*  page.

*  On the *Settings*  page, do the following:

    *  In *Name*, enter the name of your *Intellij IDEA*  project to be created.

    *  In *Project files location*, specify the location of the source files to be used to create the project from.
  Note that this path is also used to keep the *IntelliJ IDEA*  project files that depend on the project storage format.

    *  From the *Project storage format*, select the format in which you want to store your project.
  Note that the project can be stored in one of the following formats: *file-based*  or *directory-based*.
  For more information about these formats, refer to [Intellij IDEA Web Help](http://www.jetbrains.com/idea/webhelp/project.html).

    *  Select the *Create module*  check box.
  This creates a project with an initial module, with the name and type you can define in the *Module Settings*  area.
  If you leave this check box cleared, the wizard creates an empty project, but you can create modules later.

    *  In *Name*  under *Module Settings*, enter the name of your plugin module.

    *  In *Content root*, specify the path to the content root of the module.

    *  In *Module file location*, specify the path to the folder where the *\*.iml* file will be stored.

    *  Under *Select type*, select *Plugin Module*, and then click *Next*  to proceed to the *Sources*  page.

*  On the *Sources*  page, select one of the following options, and then click *Next*  to proceed to the *SDK Selection*  page:

    *  Create source directory*: creates a directory that will be marked as the source root in the new project.
    Enter the directory name, or click the ellipsis button and locate the desired directory in the *Select Path*  dialog box.

    *  *Do not create source directory*:  Omits creating the source directory. When selected, an empty project will be created.
    You can add content roots later.

    *  On the *SDK Selection*  page, under *Project JDK*, select the Intellij IDEA Plugin SDK to be set for your plugin module, and then click *Finish*  to complete the wizard.

