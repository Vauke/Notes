# Object_Oriented.md
Friday, July 20th 2018, 23:12

## 方法重载与方法重写
方法重载是编译时多态(前绑定), 方法重写是运行时多态(后绑定).
> 编译器通过用各个方法给出的参数类型与特定方法调用所使用的值类型进行匹配来挑选出相应的方法. 如果编译器找不到匹配的参数, 就会产生编译时错误, 因为根本不存在匹配, 或者没有一个比其他的更好.(没有一个更相符的匹配)<br/>
> Java 允许重载任何方法， 而不只是构造器方法。因此，要完整地描述一个方法，需要指出方法名以及参数类型。这叫做方法的签名（signature)。 例如， String 类有 4 个称为 indexOf 的公有方法。它们的签名是<br/>
> indexOf(int)<br/>
> indexOf(int, int)<br/>
> indexOf(String)<br/>
> indexOf(String, int)<br/>
> 返回类型不是方法签名的一部分。也就是说，不能有两个名字相同、参数类型也相同却返回不同类型值的方法。<sup>Java核心技术卷I P123</sup><br/>


单继承, 多实现 <br/>
当父类有多个构造时, 须显示声明默认构造;<br/>
当有继承关系时; 子类需能够访问到父类的无参构造(即不能没有或者使用`private`修饰)<br/>

接口是完全抽象的一个类, 不能有具体实现. 接口中的方法是抽象方法(且abstract和static不能共存), 接口不能有静态方法, 不能有成员变量, 可以有常量. 接口的定义模板:
```java
public interface InterfaceDemo {
    public static final int i = 1; // 任何时候常量都要先初始化

    public String getString();
}
```
接口中的所有常量默认为`public static final`, 可省略;
接口中的所有方法默认为`public`, 可省略不写; 但在实现接口时, 必须显式地给出, 否则编译器会认为是默认修饰符<br/>

## 反射

```java
Class类:
获取Class对象的3种方式:
Class c1 = Object.class;
Class c2 = obj.getClass();
Class c3 = Class.forName("java.lang.Object");// 处理ClassNotFoundException

getFields(), getMethods()将自身和父类的使用public修饰的域/方法装入数组返回
getConstructors()将自身public修饰的构造装入数组返回
getDeclaredFields(), getDeclaredConstructors(), getDeclaredMethods()返回自身所有域/构造/方法数组 不包括父类

Class, Field, Constructor, Method类:
getName()//获取名称
getModifiers()//获取修饰符的16进制整数表示, 使用Modifier.toString(class.getModifiers())转成字符串
getType()//获取数据类型的class对象
getReturnType()//返回Method类对象的返回值类型的class对象
getParameterTypes()//返回参数列表类型的class对象数组
```
以上方法的综合应用:[获取类的成员变量, 构造, 成员方法等信息](src/ObtainClassInfo.java)<sup>参考Java核心技术卷1 p195</sup>

```java
Student stu = new Student();
stu.setName("vauke");

Class stuClass = stu.getClass(); // 获取Student类class对象
Field f = stuClass.getDeclaredField("name"); // 获取Class对象中的名称为 name 的域的Field对象
String stuName = (String) f.get(stu); // 获取stu对象的域 name 的值
f.set(stu, "hyc");// 设置新值
```
如果name是private修饰的, `f.get(...)`不能获取其值, 抛出`IllegalAccessException`, 可使用`f.setAccessible(true);`解决
