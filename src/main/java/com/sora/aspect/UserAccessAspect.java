package com.sora.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author sora
 * @create 2021-05-10 11:16
 */
@Component
@Aspect
public class UserAccessAspect {
    @Pointcut(value = "@annotation(com.sora.aspect.UserAccess)")
    public void access(){

    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{
        System.out.println("second before");
    }

    @Around("@annotation(userAccess)")
    public Object around(ProceedingJoinPoint joinPoint,UserAccess userAccess){
        //获取注解里的值
        System.out.println("second around: " + userAccess.desc());

        try {
            return  joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
