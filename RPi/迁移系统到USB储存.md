# 迁移系统到USB储存.md
Sunday, July 28th 2019, 10:36

note: 树莓派3B+以后可以直接从USB启动(可能需要闪迪U盘)

目标: SD卡上的boot分区用于启动, USB用作根分区

1. 将系统分别烧录到SD卡和USB存储(烧录完成后SD卡和USB内容一样)(如果SD卡太小, 不能烧录系统, 可以使用`dd bs=4M conv=noerror if=/dev/sdc1 of=/dev/sdd`, sdc1为USB的boot分区, sdd为SD卡)
2. 使用`sudo blkid`查看SD卡的boot分区和USB的根分区的UUID或PARTUUID
3. 修改SD卡boot分区中的`cmdline.txt`, 将其中的`root=PARTUUID=...`改为USB根分区的PARTUUID(好像只能使用PARTUUID指定, 改为UUID后无法启动)
4. (USB的系统分区中的`/etc/fstab`中的根分区使用UUID指定为根分区对应的UUID,)boot分区可以不改, 若无法正常启动时再改为SD的boot分区的UUID
5. 能正常启动后, 可以使用GParted等软件将USB的boot分区删除, 将根分区扩大到整个USB存储的容量

FYI: :point_right: [elinux.org](https://elinux.org/Transfer_system_disk_from_SD_card_to_hard_disk)
