package com.smart.smartapp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @description: 参考：https://blog.csdn.net/weixin_44102992/article/details/128087883
 * @author: zhizj
 * @date: 2024/7/17
 */
@Aspect
@Configuration
public class UserAuthentication {

    @Pointcut("@annotation(com.smart.smartapp.annotation.Authentication)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String name = methodSignature.getName();
        System.out.println("before  " + name);
        Annotation[] annotations = methodSignature.getMethod().getAnnotations();
        System.out.println(Arrays.toString(annotations));
    }


    @Around(value = "pointCut()")
    public Object aroundExec(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕-前置");
        Object proceed = joinPoint.proceed();
        System.out.println("环绕-后置");
        return proceed;
    }

    @AfterReturning(value = "pointCut()")
    public void doAfterReturning() {
        System.out.println("do afterReturning");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String name = methodSignature.getName();
        System.out.println("after " + name);
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(Exception e) {
        System.out.println("异常通知：" + e.getMessage());

    }

}
