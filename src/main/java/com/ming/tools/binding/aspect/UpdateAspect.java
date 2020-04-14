package com.ming.tools.binding.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Aspect
public class UpdateAspect {

    //execution(* *(..))防止多次切入（Ajc编译器的一个Bug）
    @Pointcut("execution(* *(..)) && @annotation(com.ming.tools.binding.anno.operator.Update)")
    private void point(){}

    @Before(value="point()")
    public void before(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        System.out.println("===============Update==================");
        return proceed;
    }

    @AfterReturning(value = "point()")
    public void after(){

    }
}
