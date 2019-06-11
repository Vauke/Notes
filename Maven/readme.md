# readme.md
Tuesday, June 11th 2019, 14:29

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [readme.md](#readmemd)
* [常用构建命令](#常用构建命令)
* [使用archetype创建目录](#使用archetype创建目录)

<!-- /code_chunk_output -->

# 常用构建命令

1. mvn compile
    - 编译项目
2. mvn test
    - 运行test包下的测试
3. mvn package
    - 打包项目为jar包
4. mvn clean
    - 删除项目路径下的target目录
5. mvn install
    - 安装jar包到本地仓库, 这样其他项目就可以在POM中添加其坐标来依赖

# 使用archetype创建目录

1. archetype:generate
    - 命令行交互的方式设置groupId, artifactId...
2. archetype:generate -DgroupId=... -DartifactId=... -Dversion=... -Dpackage=...
