---
title: Check Out And Build Community Edition
---

# {{ page.title }}

## Installing Git
The source code of IntelliJ IDEA Community Edition is stored in a Git repository. 
Therefore, in order to check out the sources, you need to have Git installed. 
We recommend using the 
[msys git](http://code.google.com/p/msysgit/)
distribution on Windows and 
[git-osx-installer](http://code.google.com/p/git-osx-installer/) 
on Mac.

## Checking Out the Code

You can check out the code either by using IntelliJ IDEA or from the command line.


**Checking out from IntelliJ IDEA:**
 
* Select **VCS \| Checkout from Version Control \| Git** from the main menu

* In the **Git Repository URL** field, enter ```git://git.jetbrains.org/idea/community.git```

![Check Out Community](/img/getting_started/checkout_and_build_community/check_out_community.png)

**Checking out from the command line**

Please execute the following command:
```
git clone git://git.jetbrains.org/idea/community.git idea
```

The
[master](https://github.com/JetBrains/intellij-community/tree/master)
branch currently contains the code for the development version of IntelliJ IDEA 15.x.
Source code of older releases of IntelliJ IDEA can be found in the following branches:

14.1.x -
[141](https://github.com/JetBrains/intellij-community/tree/141)

14.0.x -
[139](https://github.com/JetBrains/intellij-community/tree/139)

13.1.x -
[135](https://github.com/JetBrains/intellij-community/tree/135)

13.0.x -
[133](https://github.com/JetBrains/intellij-community/tree/133)

12.0.x -
[123](https://github.com/JetBrains/intellij-community/tree/123)

11.1.x -
[117](https://github.com/JetBrains/intellij-community/tree/117)

11.0.x -
[nika](https://github.com/JetBrains/intellij-community/tree/nika)

10.5.x -
[xena](https://github.com/JetBrains/intellij-community/tree/xena)

10.0.x -
[x0x](https://github.com/JetBrains/intellij-community/tree/x0x)

9.x -
[maia](https://github.com/JetBrains/intellij-community/tree/maia)

You can also browse the source code through the Web interface.

## Forking on GitHub

As an alternative to checking out the official repository, you can fork the GitHub mirror of the IntelliJ IDEA source code, make changes in your own fork, and send us a pull request.

The GitHub mirror can be found at 
[https://github.com/JetBrains/intellij-community](https://github.com/JetBrains/intellij-community)

## Building and Running from the IDE

To develop IntelliJ IDEA, you can use either
[IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)
or
[IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/).

**Building and running the code**

* Make sure you have the
[Groovy plugin](https://plugins.jetbrains.com/plugin/1524)
enabled by going to **Settings \| Plugins** and enabling **Groovy** plugin checkbox.

![Groovy plugin](/img/getting_started/checkout_and_build_community/groovy_plugin_enabled.png)

Parts of IntelliJ IDEA are written in Groovy, and you will get compilation errors if you don't have the plugin enabled

* Make sure you have the UI Designer plugin enabled. 
Most of IntelliJ IDEA's UI is built using the UI Designer, and the version you build will not run correctly if you don't have the plugin enabled

![UI Designer plugin](/img/getting_started/checkout_and_build_community/ui_designer_plugin_enabled.png)

* Open the directory with the source code as a directory-based project

* Configure a Java SDK named *IDEA jdk* (case sensitive), pointing to an installation of JDK 1.8
![Configure SDK](/img/getting_started/checkout_and_build_community/configure_sdk.png)

* Add lib\tools.jar from the JDK installation directory to the classpath of IDEA JDK

* Use Build \| Make Project to build the code

* To run the code, use the provided shared run configuration "IDEA"

## Building and Running from the Command Line
<!--TODO link to ant--> 
To build the distribution archive of IntelliJ IDEA Community Edition, execute build.xml Ant build script in the root directory of the source code. 
The results of the build execution can be found at out/artifacts.