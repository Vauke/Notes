# systemctl.md
Friday, March 8th 2019, 14:16

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [systemctl.md](#systemctlmd)
* [Intro](#intro)
* [systemd](#systemd)
* [common uses](#common-uses)
* [Unit](#unit)
	* [控制启动顺序](#控制启动顺序)
	* [控制依赖](#控制依赖)
* [Service](#service)

<!-- /code_chunk_output -->

# Intro

> systemctl is used to manage services that running on the system and controls the systemd

# systemd

systemd is a manager of system and services in linux-based os. it uses `systemctl` to manage services, and it's compatible with the script of `system V` and `LSB`.

# common uses

a root privilege may be required

```shell
# check service status
systemctl status redis-server.service

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

`After`: current service will start after the specified services, i.e. if network.service and sshd-keygen.service need to be start, then start before sshd.service

`Before`: refer `After`

## 控制依赖

`Wants`: 表示`sshd.service`与`sshd-keygen.service`之间存在*弱依赖*关系, 即如果`sshd-keygen.service`启动失败或停止运行, 不影响`sshd.service`继续执行.

`Requires`: 表示*强依赖*关系, 即如果该服务启动失败或异常退出, 那么`sshd.service`也必须退出.

# Service

defines how to start current service
