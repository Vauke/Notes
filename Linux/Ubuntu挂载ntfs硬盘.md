# Ubuntu挂载ntfs硬盘并使用系统回收站
Tuesday, August 14th 2018, 20:54

1. 在目标挂载点新建目录(必需, 一般在`/media`下), 新建目录`Application`
2. 编辑`/etc/fstab`文件, `sudo vi /etc/fstab`, 在末尾添加:
```
/dev/sda1 /media/Application ntfs defaults 0 0
```
3. 重启,硬盘已经成功挂载, 但是发现当删除文件时,文件会出现强制删除的提示,不能进入回收站, 接着来吧.
4. 百度, 参考:http://forum.ubuntu.org.cn/viewtopic.php?f=48&t=484559, 使用`id vauke`命令, 查看到用户`vauke`的uid和gid是1000, 继续编辑`/etc/fstab`文件, 将刚刚修改的地方添加上uid和gid, 修改为:
```
/dev/sda1 /media/Application ntfs defaults,uid=1000,gid=1000 0 0
```
5. 重启, 现在删除文件就可以直接删除了, 删除的文件进入了系统的回收站, 再也不怕误删文件啦:smile:
但是此时还有一个问题, 刚刚是通过硬盘在系统中的s设备名`/dev/sda1`挂载的, 此种方法在添加拨出硬盘时,可能会导致未拨出的硬盘不能正常挂载, 同样的挂载点却挂载了另一个硬盘等现象(设备标识在添加拔出硬盘时会改变), 使用硬盘的UUID来唯一对应某一硬盘即可解决
6. 使用`sudo blkid`查看所有硬盘的UUID, 找到要挂载的硬盘的UUID并复制, 继续编辑`/etc/fstab`文件, 将`/dev/sda1`修改为`UUID=硬盘的UUID`:
```
UUID=88A0F1B6A0F1AAB8 /media/Application ntfs defaults,utf8,uid=1000,gid=1000 0 0
```
以上解决Ubuntu下ntfs硬盘的一般挂载使用, 其中utf8为指定文件的编码, 防止中文乱码, 但是作者之前未添加, 中文显示因为没有乱码, 视情况加吧.更详细设置请参考:https://www.cnblogs.com/ahslnx/articles/4833604.html
