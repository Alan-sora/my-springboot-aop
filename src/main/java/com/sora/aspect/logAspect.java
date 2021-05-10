package com.sora.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author sora
 * @create 2021-05-10 10:27
 */
@Aspect
@Component
public class logAspect {
    @Pointcut("execution(public * com.sora.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("URL :" + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD :" + request.getMethod());
        System.out.println("IP :" + request.getRemoteAddr());
        System.out.println("CLASS_METHOD :" + joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName());
        System.out.println("ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        System.out.println("方法的返回值 :" + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public  void  throwss(JoinPoint joinPoint){
        System.out.println("方法异常时通知......");
    }

    //后置最终通知，final增强，不管是抛出异常还是正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint joinPoint){
        System.out.println("方法最后执行......");
    }

    //环绕通知
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint joinPoint){
        System.out.println("方法环绕start......");
        try {
            Object o = joinPoint.proceed();
            System.out.println("方法环绕proeccd，结果是 ：" + o);
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
