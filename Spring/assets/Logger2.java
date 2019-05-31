package com.vauke.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Vauke on 5/31/19.
 */
@Component
@Aspect
public class Logger {
    @Pointcut("execution(* com.vauke.service.*.*(..))")
    public void pointcut1() {}

    // @Before("execution(* *..AccountServiceImpl.*(..))")
    // public void beforePrintLog() {
    //     System.out.println("Logger类中的beforePrintLog...");
    // }

    // @AfterReturning(value = "pointcut1()")
    // @AfterReturning(value = "pointcut1()", returning = "result")
    // // public void afterReturningPrintLog() {
    // public void afterReturningPrintLog(int result) {
    //     // System.out.println("Logger类中的afterReturningPrintLog...");
    //     System.out.println("Logger类中的afterReturningPrintLog..." + result);
    // }

    // @AfterThrowing(value = "pointcut1()", throwing = "throwable")
    // public void afterThrowingPrintLog(Throwable throwable) {
    //     System.out.println("Logger类中的afterThrowingPrintLog..." + throwable);
    // }

    // @After("pointcut1()")
    // public void afterPrintLog() {
    //     System.out.println("Logger类中的afterPrintLog...");
    // }

    @Around("pointcut1()")
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
