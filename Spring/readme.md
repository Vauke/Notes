# Spring
Wednesday, May 22nd 2019, 09:40

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [Spring](#spring)
* [Intro](#intro)
* [基于XML配置](#基于xml配置)
	* [节点](#节点)
		* [bean节点](#bean节点)
* [基于Annotation](#基于annotation)

<!-- /code_chunk_output -->

# Intro

Overview:

![spring-framework-runtime](./assets/spring-overview.png)

方便解耦, 简化开发

读取配置文件, 向IoC容器中注入java bean

- ApplicationContext
    - 间接继承自BeanFactory接口
    - 在读取完配置文件后就立即加载scope为singleton的类, 并初始化加入容器
    - 因此, 单例对象时使用ApplicationContext创建, Spring会自动选择
    - 实现类
        - ClassPathXmlApplicationContext
            - 常用
            - 读取类路径下的配置文件 ,IDEA中是resources目录下
        - FileSystemXmlApplicationContext
            - 读取系统路径下的配置文件
- BeanFactory
    - Spring IoC容器的顶层接口
    - 读取配置文件后, 延迟加载对象 即使用bean时才会创建该bean
    - prototype时, 使用BeanFactory创建bean, Spring自动选择

```java
// 使用BeanFactory
// 读取配置文件
Resource resource = new ClassPathResource("bean.xml");
BeanFactory beanFactory = new XmlBeanFactory(resource);

// 从容器中取出bean
UserService userService = beanFactory.getBean("userService", UserService.class);

// 使用ApplicationContext
// 读取配置文件
AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

// 从容器中取出bean
UserService UserService = (UserService) applicationContext.getBean("userService");
```

# 基于XML配置

## 节点

### bean节点

用于向上图所示的Core Container中加入java bean并由其对java bean进行生命周期的管理

- bean节点属性
    - id
        - java bean的唯一标识
    - class
        - 要创建的对象的全限定类名: com.vauke.spring.service.UserServiceImpl
    - abstract
        - 当前bean能实例化, 可在配置很多具有相同特点(同个类, 同个属性...)的bean时, 用作模板
        - 当前bean节点有abstract属性时, 可以不指定class属性
    - parent
        - 可与abstract结合使用
        - 当前bean节点会使用parent指定的bean的所有配置
    -

- bean的内部节点

# 基于Annotation
