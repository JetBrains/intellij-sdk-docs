[![official JetBrains project](https://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

IntelliJ Platform SDK Documentation
=======

Welcome to the repository for [IntelliJ Platform SDK Documentation](http://www.jetbrains.org/intellij/sdk/docs/) site.

## Reporting Bugs
Please report any content inconsistencies, outdated materials, cosmetic issues and other defects you find in the docs and samples by submitting an issue to
[YouTrack](https://youtrack.jetbrains.com/issues/IJSDK). 

## Working With the Site Locally
To check out and run a local copy of the site follow the steps described below.

### Pre-requirements

*  Make sure you have a 
   [git client](https://git-scm.com/downloads)
   installed

*  This site requires
   [Ruby 2.0](https://www.ruby-lang.org/) or higher.
   Follow the official Ruby language
   [download](https://www.ruby-lang.org/en/downloads/)
   and
   [installation](https://www.ruby-lang.org/en/documentation/installation/)
   instructions to get Ruby working on your machine.
   
*  This site requires [Jekyll](https://jekyllrb.com/), 
   a Ruby-based site generating framework.
   To install Jekyll refer to it's
   [installation guidelines](https://jekyllrb.com/docs/installation/).
   **Note:** If you are using Windows, you can face some specific aspects while installing Jekyll.
   See this [Run Jekyll on Windows Guide](https://jekyll-windows.juthilo.com/) to get more information.
   
### Checking Out Site Repository

To check out the source code run the following command:

```bash
git clone https://github.com/JetBrains/intellij-sdk-docs.git
```
   
### Initializing Submodules

The site uses JetBrains custom web templates.
To enable custom templates locally, you need to initialize repository submodules.
Run the following command in the checkout directory to do so.
 
```bash
git submodule update --init --remote
```

### Installing Gems

After you performed the initial checkout for the main repository and the submodule, run `bundle install` to install additionally required gems.

### Building and Previewing 
A set of Rake tasks, a Make-like program implemented in Ruby, provides short commands to build and run the site locally.

#### Building Site from Sources
 
*  Make sure you are in a project root directory
*  To build static site content run
   ```
   rake build
   ```
   
#### Previewing

*  To start the web-server run
    ```
    rake preview
    ```
*  Open the address
   [http://localhost:4000/intellij/sdk/docs/](http://localhost:4000/intellij/sdk/docs/)
   in your browser.
   **Note:** Make sure you haven't changed default Jekyll port during installation.


