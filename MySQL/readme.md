# readme.md
Monday, June 10th 2019, 20:01

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [readme.md](#readmemd)
* [常用命令](#常用命令)
	* [创建数据库](#创建数据库)
	* [修改数据库](#修改数据库)
	* [删除数据库](#删除数据库)
* [数据类型](#数据类型)
	* [整型](#整型)
	* [浮点型](#浮点型)
	* [日期](#日期)
	* [字符型](#字符型)
* [表相关](#表相关)
	* [查看表结构](#查看表结构)
* [约束](#约束)

<!-- /code_chunk_output -->

# 常用命令

1. SELECT VERSION();
    - 显示当前版本
2. SELECT NOW();
    - 当前时间
3. SELECT USER();
    - 当前用户
4. PROMPT
    - 命令行下修改前面的修饰符, 默认为`mysql>`
    - 常用选项
        - `\u` 当前登录用户
        - `\h` 当前主机名
        - `\d` 当前使用的数据库名
        - `\D` 当前的完整日期
    - 示例
    - ```shell
      # 句末不加分号
      PROMPT \D \u@\h:\d>
      # 效果
      Mon Jun 10 20:09:34 2019 vauke@localhost:mysql>
      ```
5. SHOW DATABASES;
6. USE db_name;
7. SELECT DATABASE();
8. SHOW TABLES;

## 创建数据库

```shell
CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] db_name [DEFAULT] CHARACTER SET [=] charset_name;

# 示例
CREATE SCHEMA IF NOT EXISTS imooc DEFAULT CHARACTER SET utf8mb4;
```

## 修改数据库

```shell
ALTER {DATABASE | SCHEMA} [db_name] [DEFAULT] CHARACTER SET [=] charset_name

ALTER DATABASE imooc CHARACTER SET utf8mb4;
```


## 删除数据库

```shell
DROP {DATABASE | SCHEMA} [IF EXISTS] db_name;

DROP DATABASE imooc;
```

# 数据类型

## 整型

1. TINYINT
    - 1字节
    - -128 ~ 127
    - 0 ~ 255
2. SMALLINT
    - 2字节
3. MEDIUMINT
    - 3字节
4. INT
    - 4字节
5. BIGINT
    - 8字节

## 浮点型

1. FLOAT[(M,D)]
    - M指总的位数, D指小数点后的位数
    - 大约可以精确到小数点后7位
2. DOUBLE[(M,D)]


## 日期

1. YEAR
    - 1存储需求
    - 年份
2. TIME
    - 3存储需求
    - 时间 时分秒
3. DATE
    - 3存储需求
    - 日期 年月日
4. DATETIME
    - 8存储需求
    - 年月日 时分秒
5. TIMESTAMP
    - 4存储需求
    - 年月日 时分秒

实际可能用数字来存储, 或者存储为字符串

## 字符型

具体占用空间大小取决于编码

1. CHAR(M)
    - M为0 ~ 255
2. VARCHAR(M)
    - M+1 字节 M为0 ~ 65535
3. TINYTEXT
    - M+1 字节 M < 256
4. TEXT
5. MEDIUMTEXT
6. LONGTEXT
7. ENUM('value1','value2'..)
8. SET('value1','value2'..)

# 表相关

## 查看表结构

```shell
SHOW COLUMNS FROM db_name.table_name;

DESC db_name.table_name;
```

# 约束

> 约束用来保证数据的完整性和一致性, 约束分为表级约束和列级约束.

1. 主键约束
    - 每张表最多有一个主键
    - 主键可以不使用AUTO_INCREMENT
2. 唯一约束
    - 唯一约束保证记录的唯一性
    - 有唯一约束的字段可以为NULL
    - 每张表可以有多个存在唯一约束的字段
3. 默认值约束
    - 当插入一条记录时, 如果没有明确为字段赋值, 则使用默认值
4. 非空约束
5. 外键约束
    - 父表和子表必须使用相同的存储引擎(只能为InnoDB), 禁止使用临时表
    - 外键列和参照列的数据类型要相似
    - 外键列和参照列必须创建索引, 如果外键列无索引, 会自动创建, 而参照列必须手动创建索引
