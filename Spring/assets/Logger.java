package com.vauke.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Vauke on 5/31/19.
 */
public class Logger {
    public void beforePrintLog() {
        System.out.println("Logger类中的beforePrintLog...");
    }

    public void afterReturningPrintLog(int result) {
        System.out.println("Logger类中的afterReturningPrintLog..." + result);
    }

    public void afterThrowingPrintLog(Throwable throwable) {
        System.out.println("Logger类中的afterThrowingPrintLog..." + throwable);
    }

    public void afterPrintLog() {
        System.out.println("Logger类中的afterPrintLog...");
    }

    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint) {
        // 可以获得切入点方法的参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 可以有返回值
        Object result = null;

        try {
            // 这里添加前置通知
            System.out.println("before in around...");

            // 调用切入点方法
            if (args.length == 0)
                result = proceedingJoinPoint.proceed();
            else
                result = proceedingJoinPoint.proceed(args);

            // 这里添加后置返回通知
            System.out.println("afterReturning in around...");
            return result;
        } catch (Throwable throwable) {
            // 这里添加后置异常通知
            System.out.println("afterThrowing in around...");
            throwable.printStackTrace();
        } finally {
            // 这里添加后置通知
            System.out.println("after in around...");
        }

        System.out.println("Logger类中的aroundPrintLog...");
        return null;
    }
}
