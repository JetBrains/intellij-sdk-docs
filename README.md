[![official JetBrains project](http://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

IntelliJ Platform SDK 中文文档
=======

这是官方文档 
[IntelliJ Platform SDK Documentation](http://www.jetbrains.org/intellij/sdk/docs/)
的中文翻译

## Reporting Bugs
请通过提交问题通知您在文档和样本中找到的任何内容不一致，过期的材料，美容问题和其他缺陷
[YouTrack](https://youtrack.jetbrains.com/issues/IJSDK). 

## Working With the Site Locally
要检出并运行该网站的本地副本，请按照以下步骤操作。

### Pre-requirements

*  安装 [git client](http://git-scm.com/downloads)
   
*  [Ruby 2.0](https://www.ruby-lang.org/) 或者更高版本.
   遵循官方的Ruby语言
   [download](https://www.ruby-lang.org/en/downloads/)
   和
   [installation](https://www.ruby-lang.org/en/documentation/installation/)
   指示让Ruby在你的机器上工作。
   
*  依赖于 [Jekyll](http://jekyllrb.com/), 
   一个基于Ruby的网站生成框架。要安装Jekyll，请参考它
   [installation guidelines](http://jekyllrb.com/docs/installation/).
   **注意:** 如果您正在使用Windows，您可以在安装时面对一些特定的方面 Jekyll.
  查看 [Run Jekyll on Windows Guide](http://jekyll-windows.juthilo.com/) 以获取更多详情.
   
### Checking Out Site Repository

要 clone 源代码，请运行以下命令：

```bash
git clone https://github.com/JetBrains/intellij-sdk-docs.git
```
   
### Initializing Submodules

该网站使用JetBrains自定义网页模板。
要在本地启用自定义模板，您需要初始化存储库子模块。
在checkout目录中运行以下命令来执行此操作。
 
```bash
git submodule update --init --remote
```

### Building and Previewing 
一套Rake任务，一个在Ruby中实现的Make-like程序，提供简短的命令来在本地构建和运行站点。

#### Building Site from Sources
 
*  确保你在一个项目的根目录下
*  建立静态网站内容运行
   ```
   rake build
   ```
   
#### Previewing

*  启动网络服务器
    ```
    rake preview
    ```
*  打开地址
   [http://localhost:4000/intellij/sdk/docs/](http://localhost:4000/intellij/sdk/docs/)
   
    **Note:** 确保您在安装过程中没有更改默认的Jekyll端口。


