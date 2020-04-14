package com.ming.tools.binding.aspect;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.ming.tools.binding.anno.PrimaryKey;
import com.ming.tools.binding.bean.BeanInfo;
import com.ming.tools.binding.core.ReflectAsmHelper;
import com.ming.tools.binding.store.cache.EasyCacheUtil;
import com.ming.tools.binding.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Aspect
public class InsertAspect {

    //execution(* *(..))防止多次切入（Ajc编译器的一个Bug）
    @Pointcut("execution(* *(..)) && @annotation(com.ming.tools.binding.anno.operator.Insert)")
    private void point(){}

    @Before(value="point()")
    public void before(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object proceed = joinPoint.proceed();
        System.out.println("===============Insert==================");
        return proceed;
    }

    @AfterReturning(value = "point()")
    public void after(){

    }
}
