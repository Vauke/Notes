# MacInstallationGuide.md
Sunday, November 3rd 2019, 16:50

<!-- TOC -->

- [MacInstallationGuide.md](#macinstallationguidemd)
    - [安装系统](#%E5%AE%89%E8%A3%85%E7%B3%BB%E7%BB%9F)
    - [Finder](#finder)
    - [misc](#misc)
    - [Homebrew](#homebrew)
    - [Homebrew cask](#homebrew-cask)
    - [MAS](#mas)
    - [Dash](#dash)
    - [Sublime](#sublime)
    - [Eudic](#eudic)
    - [Atom](#atom)
    - [剪切板历史](#%E5%89%AA%E5%88%87%E6%9D%BF%E5%8E%86%E5%8F%B2)
    - [密码管理](#%E5%AF%86%E7%A0%81%E7%AE%A1%E7%90%86)
    - [菜单栏](#%E8%8F%9C%E5%8D%95%E6%A0%8F)
    - [终端](#%E7%BB%88%E7%AB%AF)
        - [让iTerm像Tilda一样显示在屏幕左上角](#%E8%AE%A9iterm%E5%83%8Ftilda%E4%B8%80%E6%A0%B7%E6%98%BE%E7%A4%BA%E5%9C%A8%E5%B1%8F%E5%B9%95%E5%B7%A6%E4%B8%8A%E8%A7%92)
        - [让iTerm像Guake一样成为下拉式终端](#%E8%AE%A9iterm%E5%83%8Fguake%E4%B8%80%E6%A0%B7%E6%88%90%E4%B8%BA%E4%B8%8B%E6%8B%89%E5%BC%8F%E7%BB%88%E7%AB%AF)
        - [misc](#misc-1)
    - [阻止熄屏](#%E9%98%BB%E6%AD%A2%E7%86%84%E5%B1%8F)
    - [压缩](#%E5%8E%8B%E7%BC%A9)
    - [视频](#%E8%A7%86%E9%A2%91)
    - [文档工具](#%E6%96%87%E6%A1%A3%E5%B7%A5%E5%85%B7)
    - [快捷键查看工具](#%E5%BF%AB%E6%8D%B7%E9%94%AE%E6%9F%A5%E7%9C%8B%E5%B7%A5%E5%85%B7)
    - [VSCode](#vscode)
    - [JDK](#jdk)
    - [vim](#vim)
    - [窗口管理](#%E7%AA%97%E5%8F%A3%E7%AE%A1%E7%90%86)
        - [窗口置顶](#%E7%AA%97%E5%8F%A3%E7%BD%AE%E9%A1%B6)
    - [鼠标侧键](#%E9%BC%A0%E6%A0%87%E4%BE%A7%E9%94%AE)

<!-- /TOC -->·

## 安装系统
boot from install mac
boot from mac hd
boot from mac hd

安装clover到系统EFI分区
初始kext就要有 AppleALC RTL8111 FakeSMC WEG Lilu
替换config.plist,改clover主题, 用configurator修改主题

修改机型15,1


引导处 lastbootvolume 时间 0

0x0d220003

ig-platform-id注入错误可能无法启动卡在进度条,可重启在clover页面临时修改
clover->OPtions->Graphics Injector->*-platform-id:0x12345678, 不会写入config.plist文件中

ACPI

大多数人只需要：
ApfsDriverLoader-64.efi（或apfs.efi）
AptioMemoryFix-64.efi
FSInject-64.efi
HFSPlus.efi（或vboxhfs-64-efi）

所以请确保至少有这四个。

hibernationfixup.kext


https://zhuanlan.zhihu.com/p/55991446

https://blog.daliansky.net/Mac-frequently-used-to-the-command---continuous-update.html

http://bbs.pcbeta.com/forum.php?mod=viewthread&tid=1829036&highlight=


如果鼠标卡顿添加ps2kext

https://blog.csdn.net/sky_miange/article/details/68067989

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

关闭brew每次执行前的更新检查
export HOMEBREW_NO_AUTO_UPDATE=true

FYI :point_right: https://juejin.im/post/5cd2a50e518825356d54b847

## Homebrew cask

```shell
# install
brew
```

FYI :point_right: https://github.com/Homebrew/homebrew-cask/blob/master/USAGE.md

## MAS

`QQ`

## Dash

## Sublime

## Eudic

## Atom

[same as on ubuntu](./UbuntuInstallationGuide.md#atom-plugins)

## 剪切板历史

CopyQ

Clipy

## 密码管理

Bitwarden

## 菜单栏

iStat Menus

HWMonitor

Inter Power Gadget

Itsycal日历

Vanilla隐藏多余图标

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
3. 在上一步中设置Window的透明度时, 勾选`Keep background colors opaque`, 这样vim中使用主题时, 主题的背景就不会再是透明的

### 让iTerm像Guake一样成为下拉式终端

1. 打开iTerm2，然后iTerm2—>Preferences…–>Profiles->创建一个新Profiles，命名为Guake
2. 设置同上，区别是Window->Settings for new windows->Style, 设置为`Full-width top of screen`，Keys->Hotkey window指定不同快捷键

### misc

1. 启动iTerm2时不打开终端窗口
    1. 菜单栏->Shell->Close关闭所有终端
    2. 菜单栏->Window->Save Window Arrangement
    3. 菜单栏->iTerm2->Preferences->Arrangments->No Windows->set default
    4. Preferences->General->Startup->Window restoration policy，选择`open default window arrangement`
2. 启动iTerm2时打开Tilda终端窗口
    1. 菜单栏->Shell->Close关闭所有终端
    2. 快捷键调出Tilda，用于记录窗口排列位置
    3. 菜单栏->Window->Save Window Arrangement
    4. 菜单栏->iTerm2->Preferences->Arrangments->Tilda->set default
    5. Preferences->general->Startup->Window restoration policy，选择`open default window arrangement`

FYI :point_right: http://ju.outofmemory.cn/entry/150383

## 阻止熄屏

MAS -> Amphetamine

## 压缩

Keka

## 视频

IINA

## 文档工具

Dash

Calibre

## 快捷键查看工具

CheatSheet

## VSCode

```shell
brew cask install visual-studio-code
```

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
添加：
# Java
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home
JRE_HOME=$JAVA_HOME/jre
CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
export JAVA_HOME JRE_HOME CLASSPATH PATH
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

[spectacle](https://github.com/eczarny/spectacle) 不再更新

[Rectangle](https://github.com/rxhanson/Rectangle) 基于spectacle

### 窗口置顶

先安装macforge插件管理器, 然后安装afloat或AfloatX插件, 完成后在Dock上右键开启的图标, 选择置顶

[macforge插件管理](https://github.com/w0lfschild/MacForge)

[AfloatX插件](https://github.com/jslegendre/AfloatX)

## 鼠标侧键

安装logi G Hub·
