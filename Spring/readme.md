# Spring
Wednesday, May 22nd 2019, 09:40

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [Spring](#spring)
* [Intro](#intro)
	* [Overview:](#overview)
	* [读取配置文件, 向IoC容器中注入java bean](#读取配置文件-向ioc容器中注入java-bean)
	* [bean的三种创建方式](#bean的三种创建方式)
	* [bean的作用范围](#bean的作用范围)
* [基于XML配置](#基于xml配置)
	* [节点](#节点)
		* [bean节点](#bean节点)
* [基于Annotation](#基于annotation)

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
UserService UserService = (UserService) applicationContext.getBean("userService");
```

## bean的三种创建方式

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



# 基于XML配置

[XML配置完整示例](./assets/bean.xml)

## 节点

### bean节点

用于向上图所示的Core Container中加入java bean并由其对java bean进行生命周期的管理

- bean节点属性
    - id
        - java bean的唯一标识
    - class
        - 要创建的对象的全限定类名: com.vauke.spring.service.UserServiceImpl
    - factory-bean
        - 使用实例工厂方法创建bean时使用, 使用静态工厂不需要此属性, 直接通过class属性指定静态工厂类的全限定类名
        - 指定工厂bean对象
    - factory-method
        - 实例或静态工厂创建bean时, 用于指定要使用的工厂方法
    - abstract
        - 当前bean能实例化, 可在配置很多具有相同特点(同个类, 同个属性...)的bean时, 用作模板
        - 当前bean节点有abstract属性时, 可以不指定class属性
    - parent
        - 可与abstract结合使用
        - 当前bean节点会使用parent指定的bean的所有配置
    -

- bean的内部节点

# 基于Annotation
