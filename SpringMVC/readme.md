# readme.md
Monday, June 3rd 2019, 22:54

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [readme.md](#readmemd)
* [Intro](#intro)
	* [SpringMVC的优势](#springmvc的优势)
	* [JavaEE的三层架构与MVC的关系](#javaee的三层架构与mvc的关系)
		* [MVC模型](#mvc模型)
* [注解](#注解)

<!-- /code_chunk_output -->

# Intro

> SpringMVC是一种基于Java实现MVC设计模型的请求驱动类型的轻量级Web框架, 已经融合在Spring Web FLow中. 它通过一套注解, *让一个简单的Java类成为处理请求的控制器, 而无需实现任何接口*, 同时它还支持RESTful风格的请求.

## SpringMVC的优势

1. 清晰的角色划分
    - 前端控制器 DispatcherServlet
    - 请求到处理器映射 HandlerMapping
    - 处理器适配器 HandlerAdapter
    - 视图解析器 ViewResolver
    - 页面控制器 Controller
    - 验证器 Validator
    - 命令对象 Command 请求参数绑定到的对象就是命令对象
    - 表单对象 Form Object
2. 分工明确, 易于扩展
3. 命令对象就是一个POJO, 无需继承框架中的特定类

## JavaEE的三层架构与MVC的关系

1. 表现层 web
    - 负责接收客户端请求, 向客户端响应请求的结果
    - *MVC只是表现层的设计模型, 其他层没有关系*
    - 开发时依赖于业务层
2. 业务层 service
    - 负责业务逻辑处理
    - 可能会依赖于持久层
3. 持久层 dao
    - 负责数据的持久化

### MVC模型

1. Model 模型
    - 指数据模型, 一般情况下用于封装数据
2. View 视图
    - 指JSP或HTML页面, 用于展示数据
3. Controller 控制器
    - 用于处理程序逻辑

```sequence
Tomcat->>web.xml: 1. Tomcat容器启动并读取web.xml中的配置信息
web.xml->>DispatcherServlet: 2. 由于在web.xml中将DispatcherServlet配置为拦截所有请求, load-on-startup设置为1, 因此DispatcherServlet已经被创建
DispatcherServlet->>applicationContext.xml: 根据配置的spring配置文件的位置, 读取配置文件, 初始化IoC容器
```

# 注解

1. @RequestMapping
    -
2.
