# UbuntuInstallationGuide.md
Monday, August 13th 2018, 23:26

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [UbuntuInstallationGuide.md](#ubuntuinstallationguidemd)
	* [安装gnome-tweak-tool](#安装gnome-tweak-tool)
	* [chrome](#chrome)
	* [搜狗输入法](#搜狗输入法)
	* [安装plank](#安装plank)
	* [修改grub等待时间](#修改grub等待时间)
	* [albert](#albert)
	* [diskmount](#diskmount)
	* [git](#git)
	* [git proxy](#git-proxy)
	* [zsh](#zsh)
	* [aria2](#aria2)
	* [apt-fast](#apt-fast)
	* [java](#java)
	* [trim](#trim)
	* [password management](#password-management)
	* [wine-qq wechat](#wine-qq-wechat)
	* [zeal](#zeal)
	* [shutter](#shutter)
	* [calibre](#calibre)
	* [axel](#axel)
	* [uget](#uget)
	* [vim](#vim)
			* [vundle](#vundle)
			* [powerline font](#powerline-font)
	* [取消错误通知](#取消错误通知)
	* [y-ppa-manager](#y-ppa-manager)
	* [meld](#meld)
	* [jad](#jad)
	* [GoldenDict](#goldendict)
	* [Font](#font)
	* [MySQL](#mysql)
	* [Terminal](#terminal)
	* [atom plugins](#atom-plugins)
	* [sublime](#sublime)
	* [ufw](#ufw)
	* [ssh](#ssh)
	* [nvidia](#nvidia)
	* [neofetch](#neofetch)
	* [vm](#vm)
	* [htop](#htop)
	* [catfish](#catfish)
	* [tree](#tree)
	* [music](#music)
	* [video](#video)
	* [打开不了设置中心](#打开不了设置中心)
	* [误删network-manager后无法联网](#误删network-manager后无法联网)
	* [snap安装软件太慢](#snap安装软件太慢)
	* [e-mail client](#e-mail-client)
	* [chat all in one](#chat-all-in-one)
	* [syncthing](#syncthing)

<!-- /code_chunk_output -->

## 安装gnome-tweak-tool
```shell
sudo apt install gnome-tweak-tool

sudo apt install chrome-gnome-shell
```
安装插件 <br/>
https://extensions.gnome.org </br>

|             extensions              |    author    |
|:-----------------------------------:|:------------:|
|              Caffeine               |    by eon    |
|         Clipboard Indicator         |  by Tudmotu  |
|          Coverflow Alt-Tab          |  by p91paul  |
|           Dynamic Top Bar           |   by AMDG    |
|             OpenWeather             |   by jens    |
| Sound Input & Output Device Chooser |  by kgshank  |
|     Top Panel Workspace Scroll      |  by gfxmonk  |
|            TopIcons Plus            |  by phocean  |
|             User Themes             | by fmuellner |
|         Workspace Indicator         | by fmuellner |
|           system-monitor            |   by Cerin   |

安装system-monitor需要额外安装以下依赖:
```shell
sudo apt-get install gir1.2-gtop-2.0 gir1.2-networkmanager-1.0  gir1.2-clutter-1.0
```

安装图标主题 <br/>
yosa-max <br/>
ultra-flat-icons

## chrome
安装simsun.ttf解决chrome中文字体发虚

## 搜狗输入法
```shell
sudo apt install fcitx
// sudo apt install fcitx-bin fcitx-table
reboot
```
install sogou input method <br/>
reboot <br/>
![添加英文键盘](assets/添加英文键盘.png) <br/>
![设置快捷键](assets/设置快捷键.png)

## 安装plank
```shell
sudo add-apt-repository ppa:docky-core/stable
sudo apt-get update
sudo apt-get install plank
```
软件中心搜索下载plank preferences
reboot

```shell
sudo update-alternatives --config default.plymouth
sudo update-initramfs -u
reboot

sudo apt purge plymouth-theme-ubuntu-budgie-logo
sudo apt purge plymouth-theme-ubuntu-budgie-text
sudo update-initramfs -u
reboot
```
## 修改grub等待时间
```shell
sudo vi /etc/default/grub
// 将GRUB_TIMEOUT修改为0.1
GRUB_TIMEOUT=0.1
:wq

sudo update-grub
```

## albert
https://albertlauncher.github.io/ </br>
https://software.opensuse.org/download.html?project=home:manuelschneid3r&package=albert
```shell
wget -nv https://download.opensuse.org/repositories/home:manuelschneid3r/xUbuntu_18.04/Release.key -O Release.key
sudo apt-key add - < Release.key
sudo apt-get update

sudo sh -c "echo 'deb http://download.opensuse.org/repositories/home:/manuelschneid3r/xUbuntu_18.04/ /' > /etc/apt/sources.list.d/home:manuelschneid3r.list"
sudo apt-get update
sudo apt-get install albert
```

## diskmount
[Ubuntu挂载ntfs硬盘.md](Ubuntu挂载ntfs硬盘.md) <br/>
修改/etc/fstab, 参考[fstab](fstab) <br/>
若是无写入权限, 先`umount`, 然后`sudo ntfsfix /dev/sdX`, 或者进入win10->控制面板->电源->盖子, 电源按钮->更改不可用设置->取消勾选快速启动

## git
安装zsh前安装
```shell
sudo apt install git
```

## git proxy
git config --global https.proxy http://127.0.0.1:1080 <br/>
git config --global https.proxy https://127.0.0.1:1080 <br/>

//取消代理 <br/>
git config --global --unset http.proxy <br/>
git config --global --unset https.proxy <br/>

git config --global http.proxy 'socks5://127.0.0.1:1080' <br/>
git config --global https.proxy 'socks5://127.0.0.1:1080' <br/>

git config --global user.name "username" <br/>
git config --global user.email "email" <br/>

## zsh
https://ohmyz.sh/
```shell
sudo apt-get install zsh

chsh -s $(which zsh)
sudo su
chsh -s $(which zsh)
logout

sh -c "$(wget https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh -O -)"
```
修改`~/.zshrc`
```script
alias rm="rm -i"

setopt no_nomatch
# setopt no_glob
setopt EXTENDED_GLOB

export http_proxy="http://127.0.0.1:11080"
export https_proxy="https://127.0.0.1:11080"
```

## aria2
安装apt-fast前先安装aria2, 不然安装apt-fast时安装的aria2会导致uget不能使用aria2?(在uget中修改aria2c的路径为`whereis aria2c`获取到的, 可以解决)
```shell
sudo apt install aria2

mkdir /home/vauke/.aria2
cd .aria2
touch aria2.session
chmod 777 aria2.session
```
//指定aria2配置文件 --conf-path=/home/vauke/.aria2/aria2.conf <br/>
[aria2.conf](aria2.conf)

## apt-fast
```shell
sudo add-apt-repository ppa:apt-fast/stable
sudo apt update
sudo apt install apt-fast
```

## java
```shell
sudo vi /etc/profile

export JAVA_HOME=/home/vauke/jdk/jdk1.8.0_162
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
export PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin:$PATH

reboot or source /etc/profile
```

## trim
将脚本[trim](trim)放在/etc/cron.weekly/下
```shell
sudo chmod +x trim
```

## password management
keepassxc
```shell
sudo add-apt-repository ppa:phoerious/keepassxc
sudo apt-get update
sudo apt install keepassxc
```
bitwarden <br/>
https://bitwarden.com

## wine-qq wechat
https://www.lulinux.com/archives/1319 <br/>
https://github.com/wszqkzqk/deepin-wine-ubuntu <br/>
// https://github.com/askme765cs/Wine-QQ-TIM

## zeal
```shell
sudo add-apt-repository ppa:zeal-developers/ppa
sudo apt-get update
sudo apt-get install zeal
```

## shutter
设置快捷键`ctrl-shift-A`, `shutter -s`
```shell
sudo add-apt-repository ppa:shutter/ppa
sudo apt-get update
sudo apt-get install shutter
```

## calibre
```shell
sudo -v && wget -nv -O- https://download.calibre-ebook.com/linux-installer.sh | sudo sh /dev/stdin
```

## axel
```shell
sudo apt install axel
```

## uget
https://github.com/ugetdm/uget-integrator
```shell
sudo add-apt-repository ppa:uget-team/ppa
sudo apt update
sudo apt install uget-integrator
```

## vim
安装vundle后, 将[vimrc](vimrc)放到`~/.vim/`下

#### vundle
```shell
git clone https://github.com/VundleVim/Vundle.vim.git ~/.vim/bundle/Vundle.vim
sudo apt-get install fonts-powerline
```

#### powerline font
https://powerline.readthedocs.io/en/latest/installation/linux.html#fonts-installation
```shell
wget https://github.com/powerline/powerline/raw/develop/font/PowerlineSymbols.otf
wget https://github.com/powerline/powerline/raw/develop/font/10-powerline-symbols.conf
```

## 取消错误通知
```shell
sudo vi /etc/default/apport
enabled=0
```

## y-ppa-manager
```shell
sudo add-apt-repository ppa:webupd8team/y-ppa-manager
sudo apt update
sudo apt install y-ppa-manager
```

## meld
sudo apt install meld

## jad
http://jd.benow.ca/

## GoldenDict
在软件中心下载安装

## Font
```shell
sudo apt install ttf-mscorefonts-installer
sudo fc-cache -f -v
```

## MySQL
```shell
sudo apt install mysql-server

#禁止启动
sudo systemctl disable mysql
#开机启动
sudo systemctl enable mysql

mysql -u root -p
```
```sql
show variables like 'char%';
show engines;

create user 'vauke'@'localhost' identified by '123456';
grant all privileges on *.* to 'vauke'@'localhost';
flush privileges;
show grants;
```

## Terminal
sudo apt install tilda

## atom plugins
|          plugin           |    author    |
| ------------------------- | ------------ |
|        file-header        |   guiguan    |
|        file-icons         |  file-icons  |
|     language-markdown     | burodepeper  |
| markdown-preview-enhanced |  shd101wyy   |
|   markdown-table-editor   |    susisu    |
|      markdown-writer      |   zhuochun   |
|          minimap          | atom-minimap |
|  platformio-ide-terminal  |  platformio  |
|         tool-bar          |     suda     |
| tool-bar-markdown-writer  |   zhuochun   |
|       vim-mode-plus       |     t9md     |
|   vim-mode-plus-ex-mode   |     t9md     |

file-header设置日期格式:https://momentjs.com/docs/#/displaying/ <br/>
file-header插件设置文件映射 <br/>
![file-header插件设置文件映射.png](assets/file-header插件设置文件映射.png) <br/>

Edit->Snippets...
添加
```cson
'.text.md':
  '<sup>':
    'prefix':'sup'
    'body':'<sup>$1</sup>$2'
  '<sub>':
    'prefix':'sub'
    'body':'<sub>$1</sub>$2'
  '<mark>':
    'prefix':'mark'
    'body':'<mark>$1</mark>$2'
  '<center>':
    'prefix':'center'
    'body':'<center>$1</center>$2'
  '<br>':
    'prefix':'br'
    'body':'<br/>$1'
  '<hr>':
    'prefix':'hr'
    'body':'<hr/>$1'
```

## sublime
安装gbk支持: 先安装codecs33, 然后安装GBK4subl <br/>
![gbk编码支持](assets/gbk编码支持.png) <br/>
![sublime设置](assets/sublime设置.png)

## ufw
```shell
sudo apt install ufw

sudo ufw enable
sudo ufw allow tcp // protocol
sudo ufw allow 21 // port
sudo ufw status
```

## ssh
```shell
ps -e | grep sshd
sudo apt install openssh-server
```

## nvidia
https://linuxconfig.org/how-to-install-the-nvidia-drivers-on-ubuntu-18-04-bionic-beaver-linux
```shell
ubuntu-drivers devices

//stable
sudo  apt install nvidia-driver-390 // nvidia-settings nvidia-prime
reboot

//beta
sudo add-apt-repository ppa:graphics-drivers/ppa
sudo apt update

ubuntu-drivers devices
sudo apt install nvidia-driver-396
```

切换显卡
```shell
prime-select query
prime-select intel //intel
prime-select nvidia //nvidia
```

解决画面撕裂:
http://forum.ubuntu.org.cn/viewtopic.php?t=487744 <br/>
[截图](assets/解决画面撕裂.png)
```shell
sudo vi /etc/modprobe.d/nvidia-graphics-drivers.conf

添加:
options nvidia_drm modeset=1

:wq
sudo update-initramfs -u
reboot
```

## neofetch
```shell
// sudo apt insatll screenfetch
sudo apt install neofetchneofetch
```

## vm
安装vbox要额外装extension pack才能用u盘 <br/>
Settings->System->Motherboard->Enable I/O APIC <br/>
Settings->Storage->Controller:SATA->Use Host I/O Cache <br/>
Settings->Storage->xxx.vdi->Solid-State Drive <br/>
粘贴拖拽功能不如vmware, 不能粘贴时在guest中调出任务管理器重启VboxGuestAddtions <br/>

vmware启动慢, 功能更完善稳定

## htop
sudo apt install htop

## catfish
文件搜索

## tree
sudo apt install tree

## music
iease music <br/>
https://github.com/trazyn/ieaseMusic <br/>
https://github.com/sunzongzheng/music <br/>

spotify

## video
```shell
sudo apt install vlc

sudo apt install mplayer //装smplayer前装mplayer或者mpv

sudo add-apt-repository ppa:rvm/smplayer
sudo apt-get update
sudo apt-get install smplayer smplayer-themes smplayer-skins
```
vlc简单设置:
打开所有设置: <br/>
解决播放视频卡顿: Input / Codecs-> Advanced->File Caching 改为1000以上 <br/>
解决内存占用大: Playlist -> Automatically preparse items 取消勾选 <br/>
Video -> General video settings -> Drop late frames 去掉勾选 <br/>
Video -> Window properties -> Source Aspect ratio 改为16:9 <br/>
简单设置页面: <br/>
Input / Codecs: Hardware-accelerated decoding 选择VA-API video decoder(使用Intel核显)或者VDPAU(需安装nvidia驱动) <br/>
Video: Display -> Output -> VDPAU output (codec选择VDPAU后才设置)

## 打开不了设置中心
```shell
sudo apt install gnome-control-center
```

## 误删network-manager后无法联网

修改`/etc/network/interfaces`, 备份原来内容:

```shell
# interfaces(5) file used by ifup(8) and ifdown(8)
auto lo
iface lo inet loopback
```

使用`ifconfig -a`查看到有线网口为`enp4s0f1`, 修改`/etc/network/interfaces`:

```shell
# interfaces(5) file used by ifup(8) and ifdown(8)
auto enp4s0f1
iface enp4s0f1 inet dhcp
```

`dhcp`会自动获取ip, 也可替换为`static`, 然后指定ip, netmask, gateway

然后连接到路由器上, 重启即可联网

```shell
sudo apt install gnome-control-center
```

上条命令会连带安装`network-manager-gnome`, 如果没有, 则手动安装

## snap安装软件太慢
```shell
# search the app you want to install with snap
snap search "app_name"

# download the app to local
snap download "app_name"

# add the assertion to the system, auth may be required
snap ack app_name.assert

# install the snap app, auth may be required
snap install app_name.snap

# uninstall the snap app
sudo snap remove app_name
```

## e-mail client
mailspring <br/>
https://github.com/Foundry376/Mailspring <br/>

thunderbird

## chat all in one
rambox.pro

## syncthing

```shell
# Add the release PGP keys:
curl -s https://syncthing.net/release-key.txt | sudo apt-key add -

# Add the "stable" channel to your APT sources:
echo "deb https://apt.syncthing.net/ syncthing stable" | sudo tee /etc/apt/sources.list.d/syncthing.list

# Update and install syncthing:
sudo apt update
sudo apt install syncthing
```
