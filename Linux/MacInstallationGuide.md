# MacInstallationGuide.md
Sunday, November 3rd 2019, 16:50

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

- [MacInstallationGuide.md](#macinstallationguidemd)
  - [安装系统](#安装系统)
    - [开机跳过Clover引导界面](#开机跳过clover引导界面)
    - [ig-platform-id注入错误可能无法启动卡在进度条](#ig-platform-id注入错误可能无法启动卡在进度条)
    - [UEFI驱动和kext](#uefi驱动和kext)
    - [kext操作的相关命令](#kext操作的相关命令)
    - [Intel核显输出](#intel核显输出)
    - [misc](#misc)
    - [系统更新](#系统更新)
  - [Finder](#finder)
  - [misc](#misc-1)
  - [Homebrew](#homebrew)
    - [Homebrew cask](#homebrew-cask)
    - [Homebrew GUI](#homebrew-gui)
    - [重装系统环境迁移](#重装系统环境迁移)
    - [Homebrew安装的软件的服务管理](#homebrew安装的软件的服务管理)
- [阻止熄屏](#阻止熄屏)
  - [Sublime](#sublime)
  - [Eudic](#eudic)
  - [Atom](#atom)
  - [剪切板历史](#剪切板历史)
  - [密码管理](#密码管理)
  - [菜单栏](#菜单栏)
    - [隐藏多余图标](#隐藏多余图标)
  - [终端](#终端)
    - [让iTerm像Tilda一样显示在屏幕左上角](#让iterm像tilda一样显示在屏幕左上角)
    - [让iTerm像Guake一样成为下拉式终端](#让iterm像guake一样成为下拉式终端)
    - [misc](#misc-2)
  - [压缩工具](#压缩工具)
  - [视频](#视频)
  - [文档工具](#文档工具)
  - [快捷键查看工具](#快捷键查看工具)
  - [VSCode](#vscode)
    - [Plugins](#plugins)
  - [JDK](#jdk)
  - [vim](#vim)
  - [窗口管理](#窗口管理)
      - [窗口置顶 [deprecated]](#窗口置顶-deprecated)
  - [鼠标键盘](#鼠标键盘)
  - [截图](#截图)
  - [MySQL](#mysql)
    - [修改为简单密码](#修改为简单密码)
    - [连接工具](#连接工具)
  - [使用bat替换cat](#使用bat替换cat)
  - [Quick Look plugins](#quick-look-plugins)
  - [在Finder中打开终端](#在finder中打开终端)
  - [自动化](#自动化)
  - [清理](#清理)
  - [zsh plugins](#zsh-plugins)
  - [检测pkg安装包内容](#检测pkg安装包内容)
  - [代码片段管理](#代码片段管理)
  - [better365系列](#better365系列)

<!-- /code_chunk_output -->

## 安装系统

1. 下载镜像并格式化U盘
2. 终端执行`sudo /Applications/Install\ macOS\ Catalina.app/Contents/Resources/createinstallmedia --volume /Volumes/U盘名称`, 将镜写入U盘
3. 下载[Clover](https://github.com/CloverHackyColor/CloverBootloader), 选择安装到U盘, 勾选仅安装UEFI, 启动盘需要安装的UEFI驱动和kexts参考 ![Screen Shot 2019-11-12 at 23.25.05](./assets/Screen%20Shot%202019-11-12%20at%2023.25.05.png)
4. 完成后使用[Clover Configurator](https://mackie100projects.altervista.org/download-clover-configurator/)挂载U盘的EFI分区, 使用CCG打开`config.plist`, 下载`FakeSMC.kext`, `Lilu.kext`和`WhateverGreen.kext`, 并下载所有sensor的kext
5. 下载[Vanilla极简版config.plist](https://github.com/corpnewt/Hackintosh-Guide/blob/master/Configs/Haswell/config.plist), 替换Clover自带的config.plist
6. 测试U盘是否可引导, 将常用的`Clover`, `CCG`等放到U盘下, 方便系统安装好后使用
7. 重启, U盘引导, 选择boot from install mac, 格式化硬盘选择APFS格式, 若安装时出现提示文件损坏就找到菜单栏的工具选项中的终端打开, 使用`date`命令设置一个过去的时间点即可, 可能需要拔掉网线, 防止自动获取时间
    1. 如`date 092614102019` 按回车键确认 09是月，26是日，14是时，10是分，2019是年
8. 接下来安装过程会重启两次, 都选择boot from mac hd, mac hd是系统盘的名字
9. 进入系统后, 打开CCG挂载系统盘的EFI分区, 把U盘中的EFI分区的EFI文件夹复制进去
10. 拔掉U盘, 验证是否可以通过系统盘引导
11. 按需求将UEFI驱动和kext复制进对应目录
12. 使用CCG打开config.plist, 设置相应机型
13. 使用[macinfo生成序列号](https://github.com/acidanthera/MacInfoPkg/), 解压, 执行`macserial -a | grep -i iMac15,1`在机型设置中做相应替换,
    1. 第二列填到SMBIOS -> Serial Number
    2. 第三列填到SMBIOS -> Board Serial Number和Rt Variables -> MLB
14. 打开CCG, Rt Variables -> CsrActiveConfig 设置系统SIP控制为0x11(允许安装未签名第三方kext)或0x10(完全开启SIP)
    1. CsrActiveConfig默认为0x3E7, 系统升级前改回此值
    2. [Clover设置SIP详细介绍](http://www.memacx.com/thread-6874-1-1.html)
    3. SIP和BooterConfig介绍 [原文](http://www.yekki.me/apple-sip-overview-and-how-to-disable-it-in-clover/) [图片](./assets/SIP和BooterConfig介绍.png)
15. 后续使用[OpenCore](https://github.com/acidanthera/OpenCorePkg)

[安装过程和BIOS设置参考](https://zhuanlan.zhihu.com/p/55991446)

[Vanilla config.plist的讲解](https://hackintosh.gitbook.io/-r-hackintosh-vanilla-desktop-guide/config.plist-per-hardware/haswell)

[安装后注意事项参考](https://www.itpwd.com/292.html)

[搭建黑苹果的22条军规](http://bbs.pcbeta.com/forum.php?mod=viewthread&tid=1829036&highlight=)

[系统升级注意项](https://www.itpwd.com/397.html)

### 开机跳过Clover引导界面

使用CCG编辑config.plist Boot -> Default Boot Volume改为 LastBootVolume timeout设置为0

开机时按任意键可以调出Clover引导界面

### ig-platform-id注入错误可能无法启动卡在进度条

重启在clover options页面临时修改该id, clover->Options->Graphics Injector->*-platform-id:0x12345678, 这里的修改并不会写入config.plist文件中

### UEFI驱动和kext

1. [UEFI驱动介绍](UEFI驱动介绍.md)
2. kext介绍
    1. U盘启动盘中只需要安装FakeSMC, Lilu, WEG和各类sensor的kext, 最多再添加网卡和声卡
    2. 安装完成后, 在系统EFI分区中对应目录依次添加所需kext
    3. 如果睡眠有问题, 添加hibernationfixup.kext
    4. [现在添加的kext](./assets/Screen%20Shot%202019-11-13%20at%2013.33.33.png)

### kext操作的相关命令

```shell
# 查看已经加载的kext
kextstat

# kext缓存相关的命令使用kextcache
```

使用[KextViewr](https://objective-see.com/products/kextviewr.html)可以查看已经加载的kext及其详细信息

### Intel核显输出

[Hackintool使用教程及插入姿势](https://blog.daliansky.net/Intel-FB-Patcher-tutorial-and-insertion-pose.html) 还包括USB端口限制补丁, 音频补丁

[利用Hackintool打开第8代核显HDMI/DVI输出的正确姿势](https://blog.daliansky.net/Tutorial-Using-Hackintool-to-open-the-correct-pose-of-the-8th-generation-core-display-HDMI-or-DVI-output.html)

[Intel Framebuffer patching using WhateverGreen](https://www.tonymacx86.com/threads/guide-intel-framebuffer-patching-using-whatevergreen.256490/)

[Hackintool on tonymac](https://www.tonymacx86.com/threads/release-hackintool-v2-8-6.254559/)

大致步骤:

1. 备份EFI
2. 打开Hackintool, 菜单栏->Framebuffer->macOS 10.14, Patch->Apply Current Patches
3. Hackintool->Patch->选择正确的Intel Generation和Platform ID
4. 切换到Connectors标签, 绑定对应的Bus ID和接口类型
5. 切换到Patch标签页, 勾选图示:
    - ![Screen Shot 2019-12-13 at 22.08.05](./assets/Screen%20Shot%202019-12-13%20at%2022.08.05.png)
    - ![Screen Shot 2019-12-13 at 22.12.36](./assets/Screen%20Shot%202019-12-13%20at%2022.12.36.png)
6. 点击Generate Patch, 然后菜单栏->File->Export->Bootloader config.plist 操作后会备份并替换掉原有的config.plist,
7. 重启, 如果不能点亮显示器, 重复4-6, 如果不能进入系统, 在clover引导界面进入显卡设置, 临时修改platform id为0x12345678

### misc

[硬件加速和USB注入](https://zhongce.sina.com.cn/article/view/20503)

[常用到的命令](https://blog.daliansky.net/Mac-frequently-used-to-the-command---continuous-update.html)

[clover使用教程](https://blog.daliansky.net/clover-user-manual.html)

[苹果三码](https://www.zhihu.com/question/35340215/answer/125643224)

### 系统更新

1. 更新clover, 安装最新的efi文件
    1. 如果pkg包的clover无法安装可直接下载使用app文件
    2. 也可以在设置的clover面板中更新
2. 更新kext文件, 如果有万能驱动, 暂时移除万能驱动
3. 打开clover开机引导界面, 如果忘记可以在开机时按任意键进入引导界面

## Finder

```shell
# 显示隐藏文件
defaults write com.apple.finder AppleShowAllFiles TRUE

# 在Finder的标题栏显示文件路径
defaults write com.apple.finder _FXShowPosixPathInTitle -bool YES

# 重启Finder
killall Finder
```

## misc

```shell
# 允许从第三方来源安装程序
sudo spctl --master-disable

# 关闭Thunderbolt更新
sudo softwareupdate --ignore ThunderboltFirmwareUpdate1.2

# 为第三方SSD启用TRIM
sudo trimforce enable
```

## Homebrew

https://brew.sh/

Homebrew下载后的软件包放在`/Library/Caches/Homebrew`中，时间长了以后，软件包会特别多，所以我们应该定期清理

```shell
# install
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

# 安装
brew install 软件名
# 搜索 homebrew cask 也用这个命令
brew search 软件名
# 卸载
brew uninstall 软件名
# 更新所有软件
brew update
# 更新具体软件
brew upgrade 软件名
# 查看已安装软件
brew list
```

关闭brew每次执行前的更新检查:

`export HOMEBREW_NO_AUTO_UPDATE=true`

FYI :point_right: [安装](https://juejin.im/post/5cd2a50e518825356d54b847) & [常用命令和换源](https://sspai.com/post/56009)

### Homebrew cask

```shell
# install
brew tap Homebrew/homebrew-cask
```

FYI :point_right: https://github.com/Homebrew/homebrew-cask/blob/master/USAGE.md

### Homebrew GUI

[Cakebrew](https://www.cakebrew.com/)

```shell
brew cask install cakebrew
```

### 重装系统环境迁移

执行`brew bundle dump`来完成当前环境的导出, 导出完成后, 会得到一个`Brewfile`

将`Brewfile`复制到新的系统中，并执行`brew bundle`来开始安装

### Homebrew安装的软件的服务管理

```shell
# 查看所有服务
brew services list
# 单次运行某个服务
brew services run [服务名]
# 运行某个服务，并设置开机自动运行
brew services start [服务名]
# 停止某个服务
brew services stop [服务名]
# 重启某个服务
brew services restart
```

可使用[LaunchRocket](https://github.com/jimbojsb/launchrocket)图形化管理所有服务

# 阻止熄屏

Amphetamine

## Sublime

## Eudic

## Atom

```shell
brew cask install atom
```

[same as on ubuntu](./UbuntuInstallationGuide.md#atom-plugins)

## 剪切板历史

[Maccy](https://maccy.app/)

```shell
brew cask install maccy

# 修改大小
defaults write org.p0deje.Maccy historySize 20 # default is 200

# 修改快捷键
defaults write org.p0deje.Maccy hotKey control+option+m # default is command+shift+c

# 开启模糊搜索
defaults write org.p0deje.Maccy fuzzySearch true # default is false
```

[iPaste](https://en.toolinbox.net/iPaste/) [他家还有iText OCR和图床工具iPic](https://en.toolinbox.net/)

[CopyQ](https://hluk.github.io/CopyQ/)

[Clipy](https://clipy-app.com/) 不够直观

## 密码管理

Bitwarden

## 菜单栏

iStat Menus

HWMonitor

Inter Power Gadget

Itsycal日历

### 隐藏多余图标

[Vanilla](https://matthewpalmer.net/vanilla/)

[Dozer](https://github.com/Mortennn/Dozer)

[Hidden](https://github.com/dwarvesf/hidden) 可以在MAS中下载

```shell
brew cask install dozer
```

## 终端

iTerm2

```shell
brew cask install iterm2
```

### 让iTerm像Tilda一样显示在屏幕左上角

1. 打开iTerm2，然后iTerm2—>Preferences…–>Profiles->创建一个新Profiles，命名为Tilda
2. 设置
    1. Keys->Hotkey Window->Configure Hotkey Window
        1. 指定快捷键
        2. 勾选`Pin hotkey window`, `Animate showing and hiding`, `Floating window`
    2. Window->Settings for new windows->Style, 先设置为`Full screen`，并按下快捷键触发，然后command+w关闭，然后设置为`No title bar`, 这时按下快捷键，窗口就到左上角了（原因为No title bar样式会根据上次终端所在位置来打开新终端，也因此，这样设置后，如果又打开其他终端，那么tilda后续打开的位置就可能不在屏幕左上角了，解决方法: 使用下方`misc`中的方法, 将tilda的位置保存为Arrangements
    3. Session->Miscellaneous->Status bar enabled->Configure status bar，选择需要的监视器
3. 在上一步中设置Window的透明度时, 勾选`Keep background colors opaque`, 这样vim中使用主题时, 主题的背景就不会变为透明的

### 让iTerm像Guake一样成为下拉式终端

1. 打开iTerm2，然后iTerm2—>Preferences…–>Profiles->创建一个新Profiles，命名为Guake
2. 设置同上，区别是Window->Settings for new windows->Style, 设置为`Full-width top of screen`，Keys->Hotkey window指定不同快捷键

### misc

1. 启动iTerm2时不打开终端窗口
    1. 菜单栏->Shell->Close关闭所有终端
    2. 菜单栏->Window->Save Window Arrangement命名为No Windows
    3. 菜单栏->iTerm2->Preferences->Arrangments->No Windows->set default
    4. Preferences->General->Startup->Window restoration policy，选择`open default window arrangement`
2. 启动iTerm2时打开Tilda终端窗口
    1. 菜单栏->Shell->Close关闭所有终端
    2. 快捷键调出Tilda，用于记录窗口排列位置
    3. 菜单栏->Window->Save Window Arrangement命名为Tilda
    4. 菜单栏->iTerm2->Preferences->Arrangments->Tilda->set default
    5. Preferences->general->Startup->Window restoration policy，选择`open default window arrangement`

FYI :point_right: http://ju.outofmemory.cn/entry/150383

## 压缩工具

[Keka](https://www.keka.io/en/)

[eZip支持预览](https://ezip.awehunt.com/)

## 视频

[IINA](https://iina.io/)

## 文档工具

[Dash](https://kapeli.com/dash)

Calibre

## 快捷键查看工具

[CheatSheet](https://mediaatelier.com/CheatSheet/)

## VSCode

```shell
brew cask install visual-studio-code
```

### Plugins

|        extensions         |       author        |
|:-------------------------:|:-------------------:|
|           amVim           |      auiWorks       |
|      Auto Close Tag       |       Jun Han       |
|      Auto Rename Tag      |       Jun Han       |
|  Bracket Pair Colorizer   |      CoenraadS      |
|        change-case        |       wmaurer       |
|        Code Runner        |       Jun Han       |
|        Color Info         |    Matt Bierner     |
|         CSS Peek          |   Pranay Prakash    |
|         Eva Theme         |       fisheva       |
|     HTML CSS Support      |        ecmel        |
|       HTML Snippets       |   Mohamed Abusaid   |
|       Image preview       |     Kiss Tamás      |
|        Live Server        |     Ritwick Dey     |
| Markdown Preview Enhanced |      Yiyi Wang      |
|     Marp for VS Code      |      Marp team      |
|      One Dark Bimbo       |    Mario Terron     |
|       One Dark Pro        |      binaryify      |
|     Output Colorizer      |         IBM         |
|     Path Intellisense     |  Christian Kohler   |
|      Project Manager      | Alessandro Fragnani |
|         Quokka.js         |     Wallaby.js      |
|      TODO Highlight       |      Wayou Liu      |
|         Todo Tree         |     Gruntfuggly     |
|          topper           |   Sidharth Mishra   |
|           Vetur           |       Pine Wu       |
|       vscode-icons        |  VSCode Icons Team  |
|         Beautify          |       HookyQR       |
|      Color Highlight      |    Sergii Naumov    |
|    Debugger for Chrome    |      Microsoft      |
|        file-icons         |     file-icons      |
|          Guides           |      spywhere       |
|        Icon Fonts         |      idleberg       |
|    Live Server Preview    |       negokaz       |
|      open in browser      |       TechER        |
| Prettier - Code formatter |   Esben Petersen    |
|      Trailing Spaces      |   Shardul Mahadik   |
|            Vim            |      vscodevim      |
|    vscode chester atom    |   chriseckenrode    |
|     vscode-fileheader     |        mikey        |

## JDK

```shell
# 查看所有JDK的在系统中默认的安装位置
/usr/libexec/java_home  -V

# 查看指定版本JDK在系统中默认安装位置
/usr/libexec/java_home -v 1.8

sudo vi /etc/profile

# 添加：
# Java
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
```

## vim

```shell
# install vim-plug plugin manager
curl -fLo ~/.vim/autoload/plug.vim --create-dirs \
    https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim

# then copy vimrc into ~/.vim/
# then type vim in shell to enter vim
# install plugins
:PlugInstall
# exit vim, done
```

## 窗口管理

[Spectacle](https://github.com/eczarny/spectacle) 不再更新

[Rectangle](https://github.com/rxhanson/Rectangle) 基于spectacle

```shell
brew cask install rectangle

# 默认快捷键和Spectacle一致, 这里改为Magnet的快捷键
defaults write com.knollsoft.Rectangle alternateDefaultShortcuts -bool true
```

[Amethyst](https://github.com/ianyh/Amethyst)

```shell
brew cask install amethyst
```

#### 窗口置顶 [deprecated]

先安装macforge插件管理器, 然后安装afloat或AfloatX插件, 完成后在Dock上右键开启的图标, 选择置顶

[macforge插件管理](https://github.com/w0lfschild/MacForge)

[AfloatX插件](https://github.com/jslegendre/AfloatX)

## 鼠标键盘

罗技鼠标可以安装logi G Hub设置键或者宏

[其他鼠标](https://github.com/archagon/sensible-side-buttons)

[MOS让鼠标像触控板一样丝滑滚动](https://github.com/Caldis/Mos)

[Karabiner-Elements按键映射](https://pqrs.org/osx/karabiner/)

## 截图

1. <kbd>command-shift-3</kbd> 截取整个屏幕
2. <kbd>command-shift-4</kbd> 截取选定窗口
3. 上述两个命令添加<kbd>control</kbd>后, 改变截图的保存方式为保存到剪切板
4. [xnip](https://zh.xnipapp.com/) 支持滚动截图
5. [iShot](https://www.better365.cn/)

## MySQL

```shell
# 安装
brew install mysql

# 安装后默认是没有后台服务的, 不会开机自启, 可执行以下命令, 开启自启
brew services start mysql

# 手动启动|关闭|重启|状态
mysql.server start | stop | restart | status

# 初始化MySQL, 设置加密方式和root密码
mysql_secure_installation
```

```sql
- 查看编码和引擎
show variables like 'char%';
show engines;

- 创建用户
create user 'vauke'@'localhost' identified by '123456';
- 赋予权限
grant all privileges on *.* to 'vauke'@'localhost' with grant option;
- 刷新权限
flush privileges;
- 查看当前用户的权限
show grants;
```

### 修改为简单密码

```sql
- 查看密码策略
SHOW VARIABLES LIKE 'validate_password%';
- 设置密码强度等级为LOW
SET GLOBAL validate_password.policy=LOW;
- 设置密码最小长度为6
SET GLOBAL validate_password.length=6;
- 修改密码
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
```

### 连接工具

[mycli](https://github.com/dbcli/mycli)

```shell
# CLI
brew install mycli

# 修改myclirc
vi ~/.myclirc

multi_line = True
syntax_style = fruity
prompt = '\R:\m:\s \t \u@\h:\d > '
# 关闭pager可以让查询结果和SQL语句显示在同一页面
enable_pager = False

# GUI
brew cask install dbeaver
brew cask install sequel-pro
```

## 使用bat替换cat

[bat](https://github.com/sharkdp/bat)

```shell
brew install bat
```

## Quick Look plugins

https://github.com/sindresorhus/quick-look-plugins

```shell
brew cask install qlcolorcode qlstephen qlmarkdown quicklook-json qlimagesize suspicious-package qlvideo

# 预览java class文件, 需安装jad到/usr/local/bin/jad
brew cask install jad
```

[自定义需要高亮的文件](https://github.com/anthonygelibert/QLColorCode#handled-languages)

## 在Finder中打开终端

[OpenInTerminal](https://github.com/Ji4n1ng/OpenInTerminal/)

## 自动化

Automator 系统自带

[Hazel](https://www.noodlesoft.com/)

[Hammerspoon](http://www.hammerspoon.org/)

[Keyboard Maestro](http://www.keyboardmaestro.com/)

## 清理

[AppCleaner](http://freemacsoft.net/appcleaner/)

## zsh plugins

[zsh-syntax-highlighting](https://github.com/zsh-users/zsh-syntax-highlighting)

```shell
# 开启命令的高亮 clone到~/.oh-my-zsh/plugins目录下 然后在.zshrc中添加插件
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git
```

## 检测pkg安装包内容

[Suspicious Package](https://www.mothersruin.com/software/SuspiciousPackage/)

```shell
# 会同时安装Quick Look的插件
brew cask install suspicious-package
```

## 代码片段管理

[SnippetsLab](https://www.renfei.org/snippets-lab/)

## better365系列

1. [Better And Better工具集](https://www.better365.cn/bab.html)

2. [Better NTFS挂载NTFS硬盘](https://www.better365.cn/betterntfs.html)
    1. [Mounty](https://mounty.app/)

3. [自动切换输入法](https://www.better365.cn/)

4. [Better Menubar系统监视](https://www.better365.cn/)
