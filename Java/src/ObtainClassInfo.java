package com.vauke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Created by Vauke on 8/4/18.
 */
public class ObtainClassInfo {
    public static void main(String[] args) {
        String className; //录入的类名

        if (args.length > 0) { // 命令行没参数就提示输入
            className = args[0];
        } else {
            System.out.println("please input a class name(include full path) that you want to inspect:");
            Scanner sc = new Scanner(System.in);
            className = sc.next();
        }

        try {
            Class currentClass = Class.forName(className); // 通过类型加载当前类class文件
            Class superClass = currentClass.getSuperclass(); // 加载父类
            String classModifier = Modifier.toString(currentClass.getModifiers()); // 获得类修饰符

            if (superClass != null && superClass != Object.class) { // 判断继承
                System.out.println(classModifier + " class " + currentClass.getName() + " extends " + superClass.getName() + " {");
            } else {
                System.out.println(classModifier + " class " + currentClass.getName() + " {");
            }

            printClassFields(currentClass); // 打印成员变量
            printClassConstructors(currentClass); // 构造方法
            printClassMethods(currentClass); // 成员方法
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printClassFields(Class currentClass) {
        System.out.println("    // Fields");

        Field[] currentClassFields = currentClass.getDeclaredFields(); // 获取本类所有成员变量

        for (Field f : currentClassFields) {
            System.out.print("    ");
            // 获取修饰符, 类型, 名称
            System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName() + ";");
        }
        System.out.println();
    }

    private static void printClassConstructors(Class currentClass) {
        System.out.println("    // Constructors");

        Constructor[] currentClassConstructors = currentClass.getDeclaredConstructors(); // 获取所有构造

        for (Constructor c : currentClassConstructors) {
            System.out.print("    ");

            Class[] parametersTypes = c.getParameterTypes(); // 获取构造参数
            System.out.print(Modifier.toString(c.getModifiers()) + " " + c.getName() + "(");
            for (Class type : parametersTypes) {
                System.out.print(type.getName() + ", ");
            }
            System.out.println(") {}");
        }
        System.out.println();
    }

    private static void printClassMethods(Class currentClass) {
        System.out.println("    // Methods");
        Method[] currentClassMethods = currentClass.getDeclaredMethods(); // 获取所有成员方法

        for (Method m : currentClassMethods) {
            System.out.print("    ");
            Class[] parametersTypes = m.getParameterTypes(); // 获取参数
            System.out.print(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getName() + " " + m.getName() + "(");

            for (Class type : parametersTypes) {
                System.out.print(type.getName() + ", ");
            }

            System.out.println(") {}");
        }
        System.out.println();
    }
}
