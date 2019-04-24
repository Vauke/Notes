# systemctl.md
Friday, March 8th 2019, 14:16

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [systemctl.md](#systemctlmd)
* [Intro](#intro)
* [systemd](#systemd)
* [common uses](#common-uses)
	* [system related](#system-related)
	* [service related](#service-related)
* [Unit](#unit)
	* [控制启动顺序](#控制启动顺序)
	* [控制依赖](#控制依赖)
* [Service](#service)
	* [Type](#type)
	* [Restart](#restart)
* [Install](#install)
	* [Target](#target)

<!-- /code_chunk_output -->

# Intro

> systemctl is used to manage services that running on the system and controls the systemd

使用了 Systemd, 就不需要再用init了. Systemd 取代了initd, 成为系统的第一个进程(PID 等于 1), 其他进程都是它的子进程.

# systemd

systemd is a manager of system and services on linux-based os. it uses `systemctl` to manage services, and it's compatible with the script of `system V` and `LSB`.

# common uses

a root privilege may be required

## system related

```shell
# reboot system
systemctl reboot

# 关闭系统，切断电源
systemctl poweroff

# CPU停止工作
systemctl halt

# 暂停系统
systemctl suspend

# 让系统进入冬眠状态
systemctl hibernate

# 让系统进入交互式休眠状态
systemctl hybrid-sleep

# 启动进入救援状态(单用户状态)
systemctl rescue
```

## service related

```shell
# check service status
systemctl status redis-server.service
systemctl is-active redis-server.service

# start a service
systemctl start redis-server.service

# stop a service
systemctl stop redis-server.service

# if stop doesn't work well
systemctl kill redis-server.service

# restart
systemctl restart redis-server.service

# make a service bootup
systemctl enable redis-server.service

# cancel bootup
systemctl disable redis-server.service

# view the config
systemctl cat redis-server.service
```

```
systemctl cat sshd.service

[Unit]
Description=OpenSSH server daemon
Documentation=man:sshd(8) man:sshd_config(5)
After=network.target sshd-keygen.service
Wants=sshd-keygen.service

[Service]
EnvironmentFile=/etc/sysconfig/sshd
ExecStart=/usr/sbin/sshd -D $OPTIONS
ExecReload=/bin/kill -HUP $MAINPID
Type=simple
KillMode=process
Restart=on-failure
RestartSec=42s

[Install]
WantedBy=multi-user.target
```

the service scripts are usually located in `/etc/systemd/system`, and are structured files consisted of 3 blocks, `[Unit]`, `[Service]` and `[Install]`.

# Unit

所有引导过程中systemd要控制的文件/设备/程序等等都称为一个单元

`Description`: describes the service

`Documentation`: location of documents

## 控制启动顺序

`After`: current service will *start after* the specified services, i.e. if network.service and sshd-keygen.service need to be start, then start before sshd.service

`Before`: refer `After`

## 控制依赖

`Wants`: 表示`sshd.service`与`sshd-keygen.service`之间存在*弱依赖*关系, 即如果`sshd-keygen.service`启动失败或停止运行, 不影响`sshd.service`继续执行.

`Requires`: 表示*强依赖*关系, 即如果该服务启动失败或异常退出, 那么`sshd.service`也必须退出.

# Service

defines how to start current service

`Type`: 启动类型

`ExecStart`: 启动服务时执行的命令, 你要开机执行的命令, 给出命令的绝对路径

`ExecStop`: 停止服务时执行的命令

`ExecReload`: 重启服务时执行的命令

`Restart`: 重启规则

`RemainAfterExit`: 表示进程退出以后, 服务仍然保持执行

`ExecStartPre`: 启动服务之前执行的命令

`ExecStartPost`: 启动服务之后执行的命令

`ExecStopPost`: 停止服务之后执行的命令

## Type

`simple`: 默认类型, 启动的进程将成为服务进程

`forking`: ExecStart字段将以fork()方式启动, 此时父进程将会退出, 子进程将成为主进程

`oneshot`: 类似于simple, 但*只执行一次*, Systemd会等它执行完, 再启动其他服务

下面是一个oneshot的例子, 笔记本电脑启动时, 要把触摸板关掉, 配置文件可以这样写

```
[Unit]
Description=Switch-off Touchpad

[Service]
Type=oneshot
ExecStart=/usr/bin/touchpad-off

[Install]
WantedBy=multi-user.target
```

上面的配置文件, 启动类型设为`oneshot`, 就表明这个服务只要运行一次就够了, 不需要长期运行

如果关闭以后, 将来某个时候还想打开, 配置文件修改如下

```
[Unit]
Description=Switch-off Touchpad

[Service]
Type=oneshot
ExecStart=/usr/bin/touchpad-off start
ExecStop=/usr/bin/touchpad-off stop
RemainAfterExit=yes

[Install]
WantedBy=multi-user.target
```

当使用`systemctl stop`命令停止`停用触摸板`的服务时, 就会执行ExecStop指定的命令, 从而打开触摸板

## Restart

`no`: default, 重启后不会重启

`always`: 不管是什么退出原因, 总是重启

`on-success`: 只有正常退出时(退出状态码为0), 才会重启

`on-failure`: 非正常退出时(退出状态码非0), 包括被信号终止和超时, 才会重启

# Install

定义如何安装这个配置文件, 即怎样做到开机启动

`WantedBy`: 表示该服务所在的Target

`Alias`: 服务别名, 可以通过`systemctl 服务别名 restart`之类的来操作

## Target

Target的含义是服务组, 表示一组服务. `WantedBy=multi-user.target`指的是, sshd 所在的Target是multi-user.target, 这个设置非常重要, 因为执行`systemctl enable sshd.service`命令时, sshd.service的一个符号链接, 就会放在`/etc/systemd/system`目录下面的`multi-user.target.wants`子目录之中

设置开机启动:

```shell
systemctl enable xxx.service
```

Systemd 有默认的启动 Target

```shell
systemctl get-default # graphical.target

# 查看 multi-user.target 包含的所有服务
systemctl list-dependencies multi-user.target
```

一般来说, 常用的 Target 有两个: 一个是`multi-user.target`, 表示多用户命令行状态; 另一个是`graphical.target`, 表示图形用户状态, 它依赖于`multi-user.target`.

for more customization, see: :point_right: [ssh.service](../assets/ssh.service)

FYI :point_right: [Systemd 入门教程：实战篇](https://www.ruanyifeng.com/blog/2016/03/systemd-tutorial-part-two.html) & [使用systemd来构建你的服务](https://segmentfault.com/a/1190000018346564)
