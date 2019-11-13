# UEFI驱动介绍.md
Wednesday, November 13th 2019, 13:08

现在添加的UEFI驱动有: (U盘启动盘只安装1 2 3的驱动即可)

1. ApfsDriverLoader.efi APFS文件系统必需
2. AptioMemoryFix.efi
3. VBoxHfs.efi
4. AptioInputFix.efi
5. AudioDxe.efi
6. DataHubDxe.efi
7. FSInject.efi
8. HashServiceFix.efi
9. OsxFatBinaryDrv.efi
10. PartitionDxe.efi
11. UsbKbDxe.efi Clover引导界面的键盘驱动, 不添加的话在引导界面可能无法正常使用键盘
12. UsbMouseDxe.efi Clover引导界面的鼠标驱动

大多数人只需要：
ApfsDriverLoader-64.efi（或apfs.efi）
AptioMemoryFix-64.efi
FSInject-64.efi
HFSPlus.efi（或vboxhfs-64-efi）

# 安装Clover时, 对应目录下的UEFI驱动和介绍:

## RECOMMENDED DRIVERS目录

AudioDxe 可加
HDA driver to play Startup Sound during Clover boot.
Configure it via Startup sound output from Option menu, including:
Volume and Audio device (for supported IOAudio devices only).

DataHub 可加
This is DataHub protocol support mandatory for macOS.
Usually it is already present but sometime it may be missed. In this case you should see warning on screen.
7.DataHubDxe-64.efi
macOS要求强制启动的协议，大部分情况下都是启动的，而且这个efi不会引起崩溃，大家都勾选即可。
这是DataHub协议支持强制性的MacOSX的。通常它已经存在，

但有时也可能被忽略了，在这种情况下，你应该在屏幕上看到的警告。
该文件的存在始终是安全的。建议还是使用它，不会产生冲突。

FSInject 可加
Provide injection of kernel extensions from Clover folder.
9.FSInject-64.efi
必须勾选，正常情况下，我们需要对黑苹果注入kernal kext，这个是必备efi。

SMCHelper 可不加
Restore SMC keys left in NVRAM by FakeSMC.
12.SMCHelper-64.efi
是和FakeSMC联动的efi，smc是苹果为了限制非Apple设备安装macOS的东西，所以这个必须勾选。


## HUMAN INTERFACE DEVICES目录

AptioInputFix
Driver to fix input problems on UEFI firmware such as AMI Aptio.

Ps2MouseDxe 可不加
PS/2 mouse driver, 64 bit.

UsbKbDxe
Keyboard driver for boot UI support.

UsbMouseDxe
USB mouse driver, 64 bit.

FILE SYSTEM DRIVERS目录:
ApfsDriverLoader 必需
Supports APFS filesystem driver from container for macOS 10.13 and newers

2.ApfsDriverLoader-64.efi
直接读取APFS盘下的APFS.efi

Fat
FAT filesystem driver.

VBoxExt2
EXT2/3 filesystem driver from VirtualBox, 64 bit.

VBoxExt4
EXT4 filesystem driver from VirtualBox, 64 bit.

VBoxHfs   HFSPlus.efi 二选一 必需
HFS+ filesystem driver.

VBoxIso9600
ISO 9600 filesystem driver.


## MEMORY FIX DRIVERS目录  选一个

AptioMemoryFix 加
Preferred 64bit driver to fix Memory problems on UEFI firmware such as AMI Aptio. Do not use with other AptioFix together.

修复AMI Aptio EFI内存映射。如果没有就不能启动OS X

OsxAptioFix3Drv
Alternate driver (v3) to fix Memory problems on UEFI firmware. Do not use with other AptioFix together.

OsxAptioFixDrv
Old 64-bits Driver to fix Memory problems on UEFI firmware such as AMI Aptio. Do not use with other AptioFix together.
针对AMI Aptio UEFI 台式主板多见

OsxLowMemFixDrv
Simplified variant of OsxAptioFixDrv, 64 bit. Do not use with other AptioFix together.

5.AptioMemoryFix-64.efi
处理引导初始阶段的UEFI内存分配修正efi

OsxAptioFixDrv-64.efi
针对AMI Aptio UEFI 台式主板多见 技嘉、华硕、华擎等，解决找不到内核问题

OsxAptioFix3Drv-64.efi

OsxAptioFix2Drv-64.efi

OsxLowMemFixDrv-64.efi
针对 Insyde H2O UEFI 的本本 修复低位内存问题


## FileVault 2 UEFI DRIVERS目录 不需要加密硬盘则不选

AppleImageCodec
Decode PNG and BMP for FileVault2.

AppleKeyAggregator
Support for boot UI dialog for FileVault2.

AppleKeyFeeder
Support for PS/2 keyboard to use with FileVault 2.

AppleUITheme
Create boot UI Themes support for FileVault2.

FirmwareVolume
Create FirmwareVolume with cursor images for FileVault2.

HashServiceFix
Fix Hash support if absent in native UEFI BIOS.


## ADDITIONAL DRIVERS目录

CsmVideoDxe 不加
64-bits Video Driver for Clover GUI allowing to choose more resolutions. It is based on CSM module in UEFI BIOS and required CSM will be enabled.
Clover may not started with it and you may have wake problem in system. Use with precautions.
比UEFI里提供更多分辨率的显卡驱动(可选)
13.CsmVideoDxe-64是在CSM开启情况下提供更多的分辨率的efi，由于我们是纯粹的UEFI启动，所以不装（这玩意儿装了可能会出错）。
Clover图形界面的图像驱动，可以有更多的分辨率选择。（仅限于启动界面）。他基于UEFI BIOS的CSM模块，因此需要CSM可用。
增强显卡在EFI模式下的兼容性 源自Intel EFI规范，与分辨率的实现相关

EmuVariableUefi 照情况
Workaround for store NVRAM variables for systems without UEFI hardware.
Mostly UEFI boot uses hardware NVRAM but in some rare cases this driver is needed. Use it only if you have a problem without it
8.EmuVariableUefi-64.efi
是对某些UEFI启动无法调用NVRAM的机器提供的NVRAM模拟，部分Skylake架构的机器会需要这个efi（真的是很小一部分，我装过Skylake三台机器，都没有用过这个efi），我建议只有在出错告知你需要这玩意儿的时候再调整，虽然这玩意儿装了好像也不会导致崩溃，但是我不确认本来NVRAM正常的机器装上这个会不会就不调用硬件NVRAM了。所以我这里也不勾选。
针对某些 Phoenix UEFI 本本 例如DELL Vostro，某些ThinkPad

EnglishDxe
Support for UnicodeCollation protocol used by EFI Shell if it missed in UEFI.

NvmExpressDxe 有NVMe时加
Driver for support NVM Express devices.
11.NvmExpressDxe-64.efi
支持SSD连接到NVM Express总线
Clover的NVMe固态硬盘驱动，将提供主板BIOS不具备的NVMe固态硬盘识别能力，让电脑能够从NVMe启动Windows系统。

OsxFatBinaryDrv
Driver for support FAT Binary executables for OS X 10.9 and older.
允许加载FAT模块比如boot.efi

PartitionDxe
64-bits Driver to support non-usual partition maps such as: hybrid GPT/MBR or Apple Partition Map.
14.PartitionDxe-64主要用于处理macOS的Hybrid磁盘分区表，这个是Bootcamp中用到的，我们没啥用，不用勾选。
支持不常见的分区地图，如：混合GPT / MBR或Apple分区图，提供对MBR、GPT等多分区表的支持 源自Intel EFI规 但没有为Apple分区优化，也没有为GPT/MBR优化。很可能（选项B）需要。文件的存在始终是安全



1.Apfs.efi apfs
在黑苹果开机时候会跑码

3.AppleImageLoader-64.efi
1和2.3的功能类似，有2.3的情况下，可以删除1了。

4.AppleUISupport-64.efi

OsxFatBinaryDrv-64.efi
胖二进制，对多架构提供支持
如OSX的boot.efi OSX必备，或称通用二进制Universal Binary
即Intel/PPC
属于同类型的内存分配，只能选取一个，多个可能会卡内存的++++++，注意删除

6.AudioDxe-64.efi

10.HFSPlus.efi

15.PS2Mouse.efi, PS2MouseAbsolute.efi, UsbMouse*.efi
用来使鼠标，触摸板在CloverGUI界面工作的一组驱动，它们对操作系统没有

16.NTFS.efi 识别NTFS分区，用于启动Windows EFI系统，比如引导Win8/8.1

HFSPlus.efi

HFS+文件系统驱动程序。这个驱动对于通过启动方式B（UEFI）来启动Mac OS X是必须的。
