# service.md
Monday, January 21st 2019, 16:49

# Intro

> service is a command that controls how a program startup.

usage:

```shell
service SCRIPT COMMAND [OPTIONS]
```

this command will run a script which is in `/etc/init.d/` or a `upstart` program located in `/etc/init/` with `service`

```shell
servcie --status-all
```

this command will list all the service in current system

# add a service manually

```shell
sudo vi /etc/init.d/hello  # service name can make arbitrary
```

add the command you want to execute, here is an example:

```shell
#!/bin/bash # this is required and must be the first line of the script
echo "hello"
```

make it executable:

```shell
sudo chmod +x hello
```

then, execute the following will print `hello`

```shell
sudo service hello start
```

# update-rc.d

usage:

```shell
update-rc.d [-f] <service_name> remove | defaults | defaults-disabled | disable | enable [S|2|3|4|5]
```

`-f` means force

the disable | enable API is not stable and might change in the future

# make the service startup with the system boot

```shell
sudo update-rc.d hello defaults
```

# remove a service from auto startup

```shell
sudo update-rc.d [-f] hello remove
```

# reference link

[Mike的分享空间](http://www.mikewootc.com/wiki/linux/usage/ubuntu_service_usage.html)

for more customization, see:[ufw](../assets/ufw)
