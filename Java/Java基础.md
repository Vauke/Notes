# Java基础.md
Monday, July 16th 2018, 21:10

## 数据类型
| &nbsp; | 类型 | 占用空间 | 取值范围 |
| :---: | :---: | :---: | :---: |
| 整型 | byte | 1 byte | -2<sup>7</sup> ~ 2<sup>7</sup>-1 |
| ^ | short | 2 bytes | -2<sup>15</sup> ~ 2<sup>15</sup>-1 |
| ^ | int | 4 bytes | -2<sup>31</sup> ~ 2<sup>31</sup>-1 |
| ^ | long | 8 bytes | -2<sup>63</sup> ~ 2<sup>63</sup>-1 |
| 浮点类型 | float | 4 bytes | 大约 ± 3.402 823 47E+38F (有效位数为 6 ~ 7 位）[<sup>[1]</sup>](https://blog.csdn.net/a327369238/article/details/52354811) |
| ^ | double | 8 bytes | 大约 ± 1.797 693 134 862 315 70E+308 (有效位数为 15 位) |
| char类型 | char | 2 bytes | 0 ~ 2<sup>16</sup>-1 |
| Boolean | boolean | 1 byte | true \| false |

## 自动类型提升
```java
byte b = 1;
int x = b + 1; //byte + int --> int + int ==> int
System.out.println(x);//2

byte b = 1;
byte x = b + 1; // byte + int --> int + int ==> byte   1
System.out.println(x);//error: incompatible types: possible lossy conversion from int to byte
```
但是为什么直接写`byte b = 1`又是正确的呢?
`1`是<mark>常量</mark>, 在做上述运算时, 编译器会做范围检查(byte是-128 ~ 127), 满足就赋值, 否则`error: incompatible types: possible lossy conversion from int to byte`, 
