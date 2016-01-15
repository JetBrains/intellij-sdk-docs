IntelliJ Platform SDK Documentation
=======

This is a repository for 
[IntelliJ Platform SDK Documentation](http://www.jetbrains.org/intellij/sdk/docs/)
site.

##Reporting Bugs
Please inform about any content inconsistencies, outdated materials, cosmetic issues, and other defects you find by submitting an issue to
[YouTrack](https://youtrack.jetbrains.com/issues/IJSDK).

##Working With the Site Locally
To check out and run a local copy of the site follow the steps described below.

###Pre-requirements

*  Make sure you have a 
   [git client](http://git-scm.com/downloads)
   installed

*  This site requires
   [Ruby 2.0](https://www.ruby-lang.org/) or higher.
   Follow the official Ruby language
   [download](https://www.ruby-lang.org/en/downloads/)
   and
   [installation](https://www.ruby-lang.org/en/documentation/installation/)
   instructions to get Ruby working on your machine.
   
*  This site requires [Jekyll](http://jekyllrb.com/), 
   a Ruby-based site generating framework.
   To install Jekyll refer to it's
   [installation guidelines](http://jekyllrb.com/docs/installation/).
   **Note:** If you are using Windows, you can face some specific aspects while installing Jekyll.
   See this [Run Jekyll on Windows Guide](http://jekyll-windows.juthilo.com/) to get more information.
   
### Checking Out Site Repository

To check out the source code run the following command:

```bash
git clone https://github.com/JetBrains/intellij-sdk-docs.git
```
   
###Initializing Submodules

The site uses JetBrains custom web templates.
To enable custom templates locally you need to initialize repository submodules.
Run the following command in the checkout directory to do so.
 
```bash
git submodule update --init --remote
```

###Building and Previewing 
A set of Rake tasks, a Make-like programs implemented in Ruby, provides short commands to build and run the site locally.

####Building Site from Sources
 
*  Make sure you are in a project root directory
*  To build static site content run
   ```
   rake build
   ```
   
####Previewing

*  To start the web-server run
    ```
    rake preview
    ```
*  Open the address
   [http://127.0.0.1:4000/](http://127.0.0.1:4000/)
   in your browser.
   **Note:** Make sure you haven't change default Jekyll port during installation.

####Deployment to GitHub Pages

To deploy updates from this repository to GitHub Pages, contributors can take the following steps:

1. Fork `intellij-sdk-docs` and push changes to your local `master` branch.

2. Deployment is possible via `grunt deploy` or by rebuilding `_site` manually.
    * Node.js deployment:
        * Install [node.js](https://nodejs.org/en/download).
        * From parent directory, run: `npm install grunt grunt-exec grunt-build-control --save-dev`
        * Run `grunt -v deploy` to commit and push changes to the `gh-pages` branch.
    * Manual deployment:
        * `rake build[_config-gh-pages.yml]`
        * `cd _site`
        * `git add -A && git commit -m "Commit message"`
        * `git push origin gh-pages`
        
3. Visit `<YOUR_GH_USERNAME>.github.io/intellij-sdk-docs/` to view the site.