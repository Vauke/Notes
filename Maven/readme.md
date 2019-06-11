# readme.md
Tuesday, June 11th 2019, 14:29

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [readme.md](#readmemd)
* [常用构建命令](#常用构建命令)
* [使用archetype创建目录](#使用archetype创建目录)
* [maven生命周期](#maven生命周期)

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

# maven生命周期

完整的项目构建过程: 清理, 编译, 测试, 打包, 集成测试, 验证, 部署

maven的生命周期基本对应了项目的完整的构建过程. maven的生命周期由插件进行具体的实现.

maven中定义了三套相互独立的生命周期, 每个生命周期又包含很多个阶段.

1. clean生命周期 用于清理项目
    - 包含3个阶段
        - pre-clean
        - clean
        - post-clean
2. default生命周期 用于构建项目
    - 常用的阶段
        - compile
        - test
        - package
        - install
3. site生命周期 用于生成项目的站点
    - 包含4个阶段
        - pre-site
        - site
        - post-site
        - site-deploy
