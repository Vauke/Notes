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
	* [RESTful风格的URL](#restful风格的url)
* [常用注解](#常用注解)
* [响应数据和结果视图](#响应数据和结果视图)
	* [返回值分类](#返回值分类)
* [异常处理器](#异常处理器)

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

REST: HTTP协议是无状态的, 所有的状态都存放在服务器端, 如果客户端想要操作服务器, 就必须通过某种手段让服务器端的"状态"产生变化, 而这种状态建立在表现层上, 所以就称为"表现层的状态转化(Representational State Transfer, 即 REST)", 具体的讲, 就是HTTP协议里面四个表示操作方式的动词: GET(获取资源), POST(新建资源), PUT(更新资源), DELETE(删除资源), 使用不同的请求方式来区分对同一资源的不同操作.

# 常用注解

1. @RequestMapping
	- 方法和类注解
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
	- 参数注解
	- 将请求参数和方法的形参进行绑定
	- value 指定请求参数的名称
	- required 请求参数中是否必须有某个参数, 默认true, 表示必须有, 否则报错
3. @RequestBody
	- 参数注解
	- 用于获取请求体, 直接使用获得的是key=value&key=value形式的数据
	- 不适用于get请求
	- required 是否必须有请求体, 默认 true, 为true时, 使用get请求会直接报错, 为false时使用get请求得到的是null
4. @PathVariable
	- 参数注解
	- 指定URL中需要填充的参数
	- value 指定URL中占位符的名称
	- required 是否必须提供
5. @RequestHeader
	- 参数注解
	- value 指定要获取的请求头的key
6. @CookieValue
	- 参数注解
	- 用于获取指定名称的cookie值
	- value 指定要获取的cookie的名称
7. @ModelAttribute
	- 方法和参数注解
		- 用在方法上表示该方法会在当前控制器类的*所有*方法执行之前执行
		- 用在参数上用于获取指定的数据并赋值给参数
	- 用途: 当表单中要提交的数据中没有实体类的某些属性时, 这些没有的属性就使用原有的, 而表单中有的数据就作为新的数据替换原有数据
		- 例如在用于更新个人信息时, 其用户ID作为全局唯一的, 是不能进行更改的, 那么此时表单就可以不提供ID的input输入框, 这时就使用`@ModelAttribute`在方法上进行注解, 方法体为从数据库中获得user的数据并返回, 然后才会去调用对应的更新信息的controller进行数据的更新.
8. @ResponseBody
	- 将响应数据封装为JSON, 需要jackson依赖

#

<details>
    <summary>@ModelAttribute代码示例</summary>

```html
<form action="account/testModelAttribute">
<!--  去掉不可更改的loginName的输入框  -->
<%--  loginName:<input type="text" name="loginName"/> <br>  --%>
    username: <input type="text" name="username"/><br>
    password: <input type="password" name="password"/><br>
    <input type="submit" value="submit"/>
</form>
```

```java
@RequestMapping("/testModelAttribute")
public String testModelAttribute(User user) {
	System.out.println("this is controller..." + user);
	return "success";
}

@ModelAttribute
public User thisWillExecutedBeforeController() {
	// 模拟从数据库中读取数据
	User user = new User();
	user.setLoginName("hyc");
	user.setUsername("vauke");
	user.setPassword("123456");
	System.out.println("this will executed before testModelAttribute()" + user);
}
```

返回值为void时, 控制台打印:

this will executed before testModelAttribute()User{loginName='hyc', username='vauke', password='123456'}

this is controller...User{loginName='null', username='vauke', password='123'}

返回值类型改为User后:

```java
@ModelAttribute
public User thisWillExecutedBeforeController() {
	// 模拟从数据库中读取数据
	User user = new User();
	user.setLoginName("hyc");
	user.setUsername("vauke");
	user.setPassword("123456");
	System.out.println("this will executed before testModelAttribute()" + user);
	return user;
}
```

控制台打印:

this will executed before testModelAttribute()User{loginName='hyc', username='vauke', password='123456'}

this is controller...User{loginName='hyc', username='vauke', password='123'}

</details>

#

# 响应数据和结果视图

## 返回值分类

1. 字符串
	- 返回的字符串可以指定逻辑视图名, 通过视图解析器解析为物理视图的地址
		- 具体为: 将返回的字符串和配置文件中InternalResourceViewResolver配置的前后缀进行拼接, 寻找对应页面并渲染
2. void
	- 返回void时, 默认会使用 *`@RequestMapping`的映射的URL作为视图的名称(不是方法名称哦)* 作为视图名并查找对应的视图
	- 也可以使用`HttpServletRequest`进行转发或者使用`HttpServletResponse`进行重定向
3. ModelAndView
	- addObject 将对象或集合添加到*request域*中
		- 参数 `String attributeName` 设置属性名称
		- 参数 `Object attributeValue` 键值对形式, 设置属性值
	- setViewName 设置逻辑视图名称

# 异常处理器

1. 自定义异常类
2. 自定义异常处理类, 实现`HandlerExceptionResolver`接口, 重写`resolveException(..)`
3. 向IoC容器中注入自定义异常处理类
4. 在自定义的错误页面上使用`${pageContext.findAttribute()}`或者`${requestScope.get()}`, 得到存放异常信息的属性值

<details>
    <summary>代码示例</summary>

```java
// 自定义异常类
public class CustomException extends Exception {
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

// 自定义异常处理器
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        CustomException customException = null;

        if (ex instanceof CustomException) {
            customException = (CustomException) ex;
        } else {
            customException = new CustomException("system error");
        }

        ModelAndView modelAndView = new ModelAndView();
        // 设置要返回的逻辑视图名称
        modelAndView.setViewName("error");
        // 将自定义异常实例添加到request域的属性中
        modelAndView.addObject("errorMessage", customException);
        return modelAndView;
    }
}
```

</details>


# 拦截器
