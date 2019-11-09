# MacInstallationGuide.md
Sunday, November 3rd 2019, 16:50

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

## 剪切板历史

CopyQ

Clipy

## 密码管理

Bitwarden

## 菜单栏

iStat Menus

HWMonitor

Inter Power Gadget

Itsycal

Vanilla

## 终端

iTerm2

## 阻止熄屏

MAS -> Amphetamine

## 压缩

Keka

## 视频

IINA

## 文档工具

Dash

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

# vim

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
