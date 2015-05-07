---
layout: editable
title: Project
---

In IntelliJ IDEA, a project encapsulates all your source code, libraries, build instructions into a single organizational unit.
Everything you do in IntelliJ IDEA, is done within the context of a project.
A project defines some collections referred to as modules and libraries.
Depending on the logical and functional requirements to the project, you can create a single-module or a multi-module project.


## Project Structure

A project consists of one or several modules.
Each module includes the plugin source code and so called order entries that refer to SDK and libraries the module uses.
By default, all modules uses the project SDK.
In addition, a module can optionally have a set of facets.
This document explains how you can explore and change the structure of projects using API.