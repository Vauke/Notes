# Spring
Wednesday, May 22nd 2019, 09:40

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [Spring](#spring)
* [Intro](#intro)
	* [Overview:](#overview)
	* [读取配置文件, 向IoC容器中注入java bean](#读取配置文件-向ioc容器中注入java-bean)
* [基于XML配置](#基于xml配置)
	* [bean创建的3种方式](#bean创建的3种方式)
	* [bean的作用范围](#bean的作用范围)
	* [bean的生命周期](#bean的生命周期)
	* [bean的依赖注入的3种方式](#bean的依赖注入的3种方式)
		* [集合类型的依赖注入](#集合类型的依赖注入)
	* [节点](#节点)
		* [bean节点](#bean节点)
		* [bean的内部节点](#bean的内部节点)
			* [constructor-arg 用于通过构造器注入依赖](#constructor-arg-用于通过构造器注入依赖)
			* [property 用于通过setter方法注入依赖](#property-用于通过setter方法注入依赖)
* [基于Annotation](#基于annotation)
	* [bean创建](#bean创建)
	* [依赖注入](#依赖注入)
		* [注入容器中的其他bean类型](#注入容器中的其他bean类型)
		* [注入String和基本类型](#注入string和基本类型)
		* [完全使用注解](#完全使用注解)
	* [bean的作用范围](#bean的作用范围-1)
	* [bean的生命周期](#bean的生命周期-1)

<!-- /code_chunk_output -->

# Intro

## Overview:

![spring-framework-runtime](./assets/spring-overview.png)

方便解耦, 简化开发

## 读取配置文件, 向IoC容器中注入java bean

- ApplicationContext
    - 间接继承自BeanFactory接口
    - 在读取完配置文件后就立即加载scope为singleton的类, 并初始化加入容器
    - 因此, 单例对象时使用ApplicationContext创建, Spring会自动选择
    - 实现类
        - ClassPathXmlApplicationContext
            - 常用
            - 读取类路径下的配置文件, IDEA中是resources目录下
        - FileSystemXmlApplicationContext
            - 读取系统路径下的配置文件
		- XmlWebApplicationContext
			- 读取WEB-INF目录下的配置文件
- BeanFactory
    - Spring IoC容器的顶层接口
    - 读取配置文件后, 延迟加载对象 即使用bean时才会创建该bean
    - prototype时, 使用BeanFactory创建bean, Spring自动选择

example:

```java
/* 使用BeanFactory */
// 读取配置文件
Resource resource = new ClassPathResource("bean.xml");
BeanFactory beanFactory = new XmlBeanFactory(resource);

// 从容器中取出bean
UserService userService = beanFactory.getBean("userService", UserService.class);

/* 使用ApplicationContext */
// 读取配置文件
AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

// 从容器中取出bean
// UserService userService = (UserService) applicationContext.getBean("userService");
UserService userService = applicationContext.getBean("userService", UserService.class);
```

# 基于XML配置

[XML配置完整示例](./assets/bean.xml)

## bean创建的3种方式

1. 通过构造创建
    - 通过默认构造
        - 不指定`constructor-arg`子节点时(默认时), 要求实体类必须有默认构造, 否则创建失败, 抛出异常`BeanCreationException`...
    - 通过含参构造
        - 配置`constructor-arg`子节点
2. 通过静态工厂创建
    - 工厂提供一个可创建并返回所需bean的静态方法
3. 通过实例工厂创建
    - 工厂提供一个可创建并返回所需bean的方法

## bean的作用范围

使用scope属性指定

1. singleton
    - 单例, 默认
    - 单例对象在读取配置文件后就被创建
    - 创建时会执行init-method指定的方法, 销毁时会执行destroy-method指定的方法, 整个生命周期都由容器控制
2. prototype
    - 多例
    - 对象并不会在读取文件时被创建, 而是在使用时才被创建
    - 创建时会执行init-method指定的方法, 而销毁时不会
    - 具体表现为关闭容器时, 不会像singleton对象一样调用init-destroy方法
3. request
    - web项目中, 将bean放入request域中
4. session
    - web项目中, 将bean放入session域中
5. global-session
    - web项目中, 应用在Portlet环境下, 如果没有Portlet环境就相当于session

## bean的生命周期

1. 单例对象的生命周期
    - 出生
        - 容器创建时, 读取配置文件后就生成
    - 活着
        - 只要容器未被销毁就一直存在
    - 死亡
        - 容器被销毁时, 对象销毁
2. 多例对象的生命周期
    - 出生
        - 使用时容器才会创建该bean
    - 活着
        - 只要对象在使用, 就一直存活
    - 死亡
        - 当对象没有被引用并且长时间未被使用时, Spring会将其交由GC回收

## bean的依赖注入的3种方式

1. 构造注入
    - 实体类中必须要有相应的构造函数
    - 在bean节点中使用constructor-arg节点逐个指定构造的入参
2. setter注入
    - 根据setXxx(..)方法注入
    - 配置时值为xxx(Xxx的第一个字母改为小写)
3. p名称空间注入
    - 本质也是利用setter注入, 写法上简化
    - 导入p名称空间
    - p:xxx
        - xxx为setter的Xxx小写首字母
        - 基本类型属性和String
    - p:xxx-ref
        - 容器中的其他bean类型的属性

### 集合类型的依赖注入

1. 数组
	```
	<array>
		<value>xxx</value>
		...
	</array>
	```
2. List
	```
	<list>
		<value>xxx</value>
		...
	</list>
	```
3. Set
	```
	<set>
		<value>xxx</value>
		...
	</set>
	```
4. Map
	```
	<map>
		<entry key="xxx" value="xxx" />
		<entry key="xxx">
		    <value>xxx</value>
		</entry>
		...
	</map>
	```
5. Properties
	```
	<props>
		<prop key="xxx">xxx</prop>
		...
	</props>
	```

## 节点

### bean节点

用于向上图所示的Core Container中加入java bean并由其对java bean进行生命周期的管理

- bean节点属性
    - id
        - java bean的唯一标识
    - class
        - 要创建的对象的全限定类名
			- com.vauke.spring.service.UserServiceImpl
    - factory-bean
        - 使用实例工厂方法创建bean时使用. 使用静态工厂时不需要此属性, 直接通过class属性指定静态工厂类的全限定类名
        - 指定工厂bean对象
    - factory-method
        - 实例或静态工厂创建bean时, 用于指定要使用的工厂方法
    - parent
        - 将某个bean节点的配置当作模板来引用
        - 当前bean节点会使用parent指定的bean的所有配置
        - 可与abstract结合使用
    - abstract
        - 当前bean不会实例化, 可在配置很多具有相同特点(同个类, 同个属性...)的bean时, 用作模板
        - 当前bean节点有abstract属性时, 可以不指定class属性, 而仅仅用其来配置公共属性的值
    - init-method
        - 用于指定实体类中的某个方法作为初始化方法, 在容器创建bean时调用
    - destroy-method
        - 同init-method, 用于在容器关闭, bean销毁时调用
        - prototype不涉及此方法
    -

### bean的内部节点

#### constructor-arg 用于通过构造器注入依赖

- 属性
    - name
        - 指定构造的入参名为其赋值
    - type
        - 指定参数的类型为其赋值
        - 当有两个及以上参数类型相同时, 不适用, 因此一般不用
    - index
        - 指定构造中该参数的位置为该位置的参数赋值
    - 上述用于指定参数的三个属性, 选一即可
    - value
        - 指定值
        - *基本类型和String*
    - ref
        - 指定值
        - *容器中的其他bean类型的属性*

#### property 用于通过setter方法注入依赖

可以和constructor-arg共同作用, 但*只有property时, 实体类必须有默认构造*, 才能成功创建bean对象

- 属性
    - name
        - 必须有对应的setter
        - 指定setter的入参名为其赋值
    - value
        - 指定值
        - 基本类型和String
    - ref
        - 指定值
        - 基本类型和String

# 基于Annotation

## bean创建

1. @Component
	- 相当于xml配置中的bean节点
	- 属性
		- value
			- 用于指定bean的id, 不指定时, 默认为当前类名首字母小写
2. @Repository
	- 同@Component, 一般用于指代持久层的bean
3. @Service
	- 一般用于指代服务层的bean
4. @Controller
	- 一般用于指代控制层的bean

`@Repository`, `@Service`, `@Controller`都和`@Component`作用相同, 但提供更明确的语义来指代不同层的bean

## 依赖注入

### 注入容器中的其他bean类型

1. @Autowired
	- 自动按*类型*注入, 只要容器中有*唯一*的类型匹配时, 就可以完成依赖的注入, 否则抛出异常
		- *当容器中有两个及以上的相同类型的bean时, 将按照bean的id来进行自动注入*
	- 可以省略该属性在该bean中的setter(即直接将这个注解加到属性的声明上)
	- 属性
		- required
			- 是否必须, 默认为true
2. @Qualifier
	- 须和@Autowired结合使用, 用于指定bean的id
	- 不能用于通过构造方法实现的注入方式, @Qualifier不能用在构造之上
3. @Resource
	- 直接通过bean的id进行注入
	- 属性
		- name
			- 用于指定bean的id
	- 不能用于通过构造方法实现的注入

### 注入String和基本类型

1. @Value
	- 属性
		- value
			- 指定要注入的属性, 支持SpEL

### 完全使用注解

上述配置中虽然使用到了注解来向容器中注入bean, 但仍需在xml配置中配置包扫描的路径, properties文件的路径以及数据源的配置等

1. @Configuration
	- 类注解, 用于指定Spring的配置类
2. @ComponentScan
	- 类注解, 用于替代xml配置中的`<context:component-scan base-package="xxx" />`
	- 属性
		- basePackages
			- 指定要扫描的包的路径
		- value 同 basePackages
3. @Bean
	- 用于方法之上, 将方法的返回值作为bean注入到容器中
	- 属性
		- name
			- 指定bean的id, 不指定时为方法名
		- value 同 name
		- autowire
			- 指定是按类型注入还是按名称注入
	- 若方法有参数列表, 则*自动根据参数类型到容器中进行匹配*, 若匹配到, 则自动注入, 否则报错
	- 若匹配到多个, 则使用`@Qualifier`指定
4. @Import
	- 用于在主配置类中指定从配置文件
	- 属性
		- value
			- 指定从配置文件的Class类的类型
5. @PropertySource
	- 类注解, 用于指定properties文件的路径

[注解配置类示例1](./assets/MainConfig.java)

[注解配置类示例2](./assets/JdbcConfig.java)

*完全使用注解后, 获取容器上下文的方式也改变*

```java
/** 使用xml时 */
AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

/** 使用注解配置类 */
AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
```

## bean的作用范围

1. @Scope
	- 改变bean的作用范围
	- 属性
		- value 指定bean的作用范围, 取值和xml中一致

## bean的生命周期

1. @PostConstruct
	- 指定bean的初始化方法, 和xml中的init-method一致
2. @PreDestroy
	- 指定bean的销毁方法, 和xml中的destroy-method一致
