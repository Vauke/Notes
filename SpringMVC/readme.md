# readme.md
Monday, June 3rd 2019, 22:54

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [readme.md](#readmemd)
* [Intro](#intro)
	* [SpringMVC的优势](#springmvc的优势)
	* [JavaEE的三层架构与MVC的关系](#javaee的三层架构与mvc的关系)
		* [MVC模型](#mvc模型)
	* [SpringMVC处理流程](#springmvc处理流程)
	* [SpringMVC组件](#springmvc组件)
	* [<mvc:annotation-driven>说明](#mvcannotation-driven说明)
	* [请求参数的绑定](#请求参数的绑定)
* [注解](#注解)

<!-- /code_chunk_output -->

# Intro

> SpringMVC是一种基于Java实现MVC设计模型的请求驱动类型的轻量级Web框架, 已经融合在Spring Web FLow中. 它通过一套注解, *让一个简单的Java类成为处理请求的控制器, 而无需实现任何接口*, 同时它还支持RESTful风格的请求.

## SpringMVC的优势

1. 清晰的角色划分
    - 前端控制器 DispatcherServlet
    - 处理器映射器 HandlerMapping
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

## SpringMVC处理流程

![springmvc处理流程](./assets/springmvc处理流程.png)

1. Tomcat启动, 读取web.xml中的配置
2. 由于web.xml中配置了DispatcherServlet并将其load-onstartup设置为1, url-pattern设置为/, 则在tomcat启动后就创建了DispatcherServlet对象
3. 根据配置的applicationContext.xml的位置读取文件, 初始化IoC容器, 创建bean
4. 客户端发起请求
5. 请求到达tomcat后被DispatcherServlet拦截
6. 此时, 根据URL匹配对应的controller
7. 若有对应controller且请求方法也匹配, ,就调用对应controller的方法
8. 根据ViewResolver的实现类InternalResourceViewResolver的配置找到对应的页面并显示

## SpringMVC组件

1. DispatcherServlet
    - 前端控制器, 是整个流程控制的中心, 只负责拦截和转发请求
    - 它使得组件之间的耦合性降低
2. HandlerMapping
    - 根据请求找到对应的Handler(Controller)
    - 不同映射器有不同的映射方式
        - 配置文件方式
        - 实现接口方式
        - 注解方式
3. Handler
    - 即 Controller
4. HandlerAdapter
    - 处理器适配器
    - 使用到适配器模式
    - 使用其调用适合的Handler
5. ViewResolver
    - 视图解析器负责将处理结果生成View视图对象
6. View
    - 视图对象
        - 包括 jsp, html...

## <mvc:annotation-driven>说明

此标签用于在spring配置文件中, 表示注解处理器和适配器的配置

> 在SpringMVC的个个组件中, 处理器映射器HandlerMapping, 处理器适配器HandlerAdapter和视图解析器ViewResolver称为SpringMVC的三大组件. 在配置文件中添加<mvc:annotation-driven>会自动加载`RequestMappingHandlerMapping(处理器映射器)`和`RequestMappingHandlerAdapter(处理器适配器)`

## 请求参数的绑定

参数名和方法的形参名保持一致, 可以自动绑定, 也可以使用`@RequestParam`指定参数名, 这时形参名可以为任意.

支持的数据类型
1. 基本类型
	- 包括基本类型和String
2. POJO类型
	- 包括实体类及其关联实体
	- 要求表单参数名和POJO类属性名保持一致, 并且参数类型也是该POJO类型的
3. 数组和集合类型

## RESTful风格的URL

优点:	结构清晰, 符合标准, 易于理解, 扩展方便

特性:

> 资源: 网络上的一个实体或一个具体的信息

> 表现层(Representation): 资源具体的呈现形式

> 状态转化(State Transfer): 每发出一个请求, 即代表了客户端和服务器端的一次交互过程

REST: HTTP协议是无状态的, 所有的状态都存放在服务器端, 如果客户端想要操作服务器, 就必须通过某种手段让服务器端的"状态"产生变化, 而这种状态建立在表现层上, 所以就称为"表现层的状态转化(Representational State Transfer, 即 REST)", 具体的讲, 就是HTTP协议里面四个表示操作方式的动词: GET(获取资源), POST(新建资源), PUT(更新资源), DELETE(删除资源)

# 注解

1. @RequestMapping
	- 用于建立请求URL和处理请求方法之间的对应关系
    - 属性
		- value 指定映射的URL
		- path 同 value
		- method 指定限制请求的方式
		- params 指定限制请求参数的条件, 支持简单的表达式
			- params = {"accountName"},表示请求参数中必须要有accountName
			- params = {"money!100"}, 表示请求参数中money不能为100
		- headers 用于指定限制请求的消息头的条件
2. @RequestParam
	- 将请求参数和方法的形参进行绑定
	- value 指定请求参数的名称
	- required 请求参数中是否必须有某个参数, 默认true, 表示必须有, 否则报错
3. @RequestBody
	- 用于获取请求体, 直接使用获得的是key=value&key=value形式的数据
	- 不适用于get请求
	- required 是否必须有请求体, 默认 true, 为true时, 使用get请求会直接报错, 为false时使用get请求得到的是null
4.
