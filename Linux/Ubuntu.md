# Ubuntu.md
Monday, August 13th 2018, 23:26

### 安装gnome-tweak-tool
sudo apt install gnome-tweak-tool
###
sudo apt-get install chrome-gnome-shell

sudo apt install fcitx
sudo apt install fcitx-bin fcitx-table
reboot
install sogou input method
reboot

https://extensions.gnome.org</br>

header 1 | header 2
---|---
Caffeine by eon | row 1 col 2
Clipboard Indicator by Tudmotu | row 2 col 2
Coverflow Alt-Tab | by p91paul
Dynamic Top Bar | by AMDG
OpenWeather | by jens
Sound Input & Output Device Chooser | by kgshank
Top Panel Workspace Scroll | by gfxmonk
TopIcons Plus | by phocean
User Themes | by fmuellner
Workspace Indicator | by fmuellner
system-monitor | by Cerin

sudo apt-get install gir1.2-gtop-2.0 gir1.2-networkmanager-1.0  gir1.2-clutter-1.0

### 安装plank
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
### 修改grub等待时间
```shell
sudo vi /etc/default/grub
sudo update-grub
```

https://albertlauncher.github.io/ </br>
https://software.opensuse.org/download.html?project=home:manuelschneid3r&package=albert </br>
```shell
wget -nv https://download.opensuse.org/repositories/home:manuelschneid3r/xUbuntu_18.04/Release.key -O Release.key
sudo apt-key add - < Release.key
sudo apt-get update

sudo sh -c "echo 'deb http://download.opensuse.org/repositories/home:/manuelschneid3r/xUbuntu_18.04/ /' > /etc/apt/sources.list.d/home:manuelschneid3r.list"
sudo apt-get update
sudo apt-get install albert
```

diskmount

### zsh
https://ohmyz.sh/
```shell
sudo apt-get install zsh

chsh -s $(which zsh)
sudo su
chsh -s $(which zsh)
logout

sudo apt install git
sh -c "$(wget https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh -O -)"
```

sudo apt install aria2
```shell
sudo add-apt-repository ppa:apt-fast/stable
sudo apt update
sudo apt install apt-fast

mkdir /home/vauke/.aria2
cd .aria2
touch aria2.session
chmod 777 aria2.session
```
--conf-path=/home/vauke/.aria2/aria2.conf


java
```shell
sudo vi /etc/profile

export JAVA_HOME=/home/vauke/jdk/jdk1.8.0_162
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
export PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin:$PATH

reboot or source /etc/profile
```

trim

zeal
```shell
sudo add-apt-repository ppa:zeal-developers/ppa
sudo apt-get update
sudo apt-get install zeal
```

shutter
```shell
sudo add-apt-repository ppa:shutter/ppa
sudo apt-get update
sudo apt-get install shutter
```


calibre
sudo -v && wget -nv -O- https://download.calibre-ebook.com/linux-installer.sh | sudo sh /dev/stdin

axel
sudo apt install axel

uget
https://github.com/ugetdm/uget-integrator
```shell
sudo add-apt-repository ppa:uget-team/ppa
sudo apt update
sudo apt install uget-integrator
```

git proxy

git config --global https.proxy http://127.0.0.1:1080
git config --global https.proxy https://127.0.0.1:1080

git config --global --unset http.proxy
git config --global --unset https.proxy

git config --global http.proxy 'socks5://127.0.0.1:1080'
git config --global https.proxy 'socks5://127.0.0.1:1080'


## vim
vundle
git clone https://github.com/VundleVim/Vundle.vim.git ~/.vim/bundle/Vundle.vim
sudo apt-get install fonts-powerline

powerline font https://powerline.readthedocs.io/en/latest/installation/linux.html#fonts-installation
wget https://github.com/powerline/powerline/raw/develop/font/PowerlineSymbols.otf
wget https://github.com/powerline/powerline/raw/develop/font/10-powerline-symbols.conf


sudo vi /etc/default/apport
enabled=0


## vlc
sudo apt install vlc

## y-ppa-manager
sudo add-apt-repository ppa:webupd8team/y-ppa-manager
sudo apt update
sudo apt install y-ppa-manager

## meld
sudo apt install meld

## jad
http://jd.benow.ca/

## GoldenDict

## Font
```shell
sudo apt install ttf-mscorefonts-installer
sudo fc-cache -f -v
```

## MySQL
```shell
sudo apt install mysql-server

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

## Termnal
sudo apt install tilda



## atom plugins
https://momentjs.com/docs/#/displaying/


## ufw
```shell
sudo apt install ufw

sudo ufw enable
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
sudo  apt install nvidia-driver-390
reboot

//beta
sudo add-apt-repository ppa:graphics-drivers/ppa
sudo apt update

ubuntu-drivers devices
sudo apt install nvidia-driver-396
```
解决画面撕裂:
http://forum.ubuntu.org.cn/viewtopic.php?t=487744
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
sudo apt insatll screenfetch
sudo apt install neofetchneofetch
```
