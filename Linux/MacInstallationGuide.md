# MacInstallationGuide.md
Sunday, November 3rd 2019, 16:50

boot from install mac
boot from mac hd
boot from mac hd

安装clover到系统EFI分区
初始kext就要有 AppleALC RTL8111 FakeSMC WEG Lilu
替换config.plist,改clover主题, 用configurator修改主题

修改机型15,1
注入igpu

引导处 lastbootvolume 时间 0

0x0d220003

ig-platform-id注入错误可能无法启动卡在进度条,可重启在clover页面临时修改
clover->OPtions->Graphics Injector->*-platform-id:0x12345678, 不会写入config.plist文件中

ACPI

新的系统安装好后，通常你打开一个dmg文件准备安装的时候，系统会提示你不允许打开未知来源的应用
从第三方来源安装程序：

sudo spctl --master-disable

关闭Thunderbolt更新：

sudo softwareupdate --ignore ThunderboltFirmwareUpdate1.2

为第三方SSD启用TRIM

sudo trimforce enable



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

# Finder

```shell
# 显示隐藏文件
defaults write com.apple.finder AppleShowAllFiles TRUE

# 在Finder的标题栏显示文件路径
defaults write com.apple.finder _FXShowPosixPathInTitle -bool YES

# 重启Finder
killall Finder
```

# misc

```shell
# 允许从第三方来源安装程序
sudo spctl --master-disable

# 关闭Thunderbolt更新
sudo softwareupdate --ignore ThunderboltFirmwareUpdate1.2

# 为第三方SSD启用TRIM
sudo trimforce enable
```
