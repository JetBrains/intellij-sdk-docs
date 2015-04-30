---
title: Contribution Guidelines
---

This document explains how to contribute changes to the *IntelliJ SDK Platform Documentation* project.

# Licensing
Project source code terms of use, reproduction, and redistribution is defined by
[Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html)

# Contributing Changes

## Uses with Write Access
To contribute your changes
[clone the project](https://help.github.com/articles/fetching-a-remote/),
implement your changes, and
[push](https://help.github.com/articles/pushing-to-a-remote/) them to the repository.


## User with Read-Only Access

To contribute you changes use a 
[pull request](https://help.github.com/articles/using-pull-requests/).
[Creating a pull request](https://help.github.com/articles/creating-a-pull-request/)
article on GitHub explains how to do it.

# Setting Up Environment
To build and run the project locally you need to set up its development environment,
Refer to the 
[README](https://github.com/JetBrains/intellij-sdk-docs/blob/master/README.md) 
section of this project.

# Project Structure
The project contain the following major types of content:
 
1.  Articles
    An article describes some part of the API and general rule of using it in a free form.
2.  Step-by-step tutorials
3.  Code samples



## Markup

Documents are written in 
[Markdown](https://en.wikipedia.org/wiki/Markdown) 
markup template language. 
Processing of Markdown source code and conversion into HTML is made by
[Kramdown](http://kramdown.gettalong.org/syntax.html) library.


## Creating an Article

To post an article, create a Markdown document inside of the project directory structure.
Jekyll engine will process all the Markdown files inside the project and create corresponding static HTML pages with same relative paths for them.
To process a Markdown file you need to refer to it's HTML counterpart in *\_SUMMARY.md*
             
## Table of Contents and \_SUMMARY.md

File 
[\_SUMMARY.md](https://github.com/JetBrains/intellij-sdk-docs/blob/master/_SUMMARY.md) 
located in the root directory defines project's hierarchical structure displayed on the left side of the page in a tree form.
Place the name of an article and a link relatively to the site root to the correcponding HTML document to *\_SUMMARY.md* to list the article in TOC.
Headers without links to corresponding files won't be visible. Files not included in the listing wont be processed. 

## Formatting Rules

## Project Structure

## Styling Rules

## Images




