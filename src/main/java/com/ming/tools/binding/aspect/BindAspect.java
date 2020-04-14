package com.ming.tools.binding.aspect;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.ming.tools.binding.anno.Bind;
import com.ming.tools.binding.anno.PrimaryKey;
import com.ming.tools.binding.bean.BeanInfo;
import com.ming.tools.binding.bean.ChangeInfo;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Aspect
public class BindAspect {

    //execution(* *(..))防止多次切入（Ajc编译器的一个Bug）
    @Pointcut("execution(* *(..)) && @within(com.ming.tools.binding.anno.Bind)")
    private void point(){}

    @Before(value="point()")
    public void before(){
//        System.out.println("start Bind...");
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Object proceed = joinPoint.proceed();
        if(method.getName().startsWith("set")){
            String propName = method.getName();
            String propertyName = StringUtils.property(propName);
            Object target = joinPoint.getTarget();
            Class clazz = target.getClass();
            Integer classHashCode = clazz.hashCode();
            Integer ObjectHashCode = target.hashCode();
            /*if(clazz.isAnnotationPresent(Bind.class)){
                Bind bind = (Bind) clazz.getAnnotation(Bind.class);
            }*/

            String primaryKeyName = null;
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                if(field.isAnnotationPresent(PrimaryKey.class)){
                    primaryKeyName = field.getName();
                    field.setAccessible(true);
                    // 获取属性的对应的值
                    if(field.get(target)!=null){
                        ObjectHashCode = Integer.valueOf(field.get(target).toString());
                    }
                }
            }
            Map<Integer,BeanInfo> map = EasyCacheUtil.getT(classHashCode.toString());
            if(map==null){
                map = new HashMap<Integer, BeanInfo>();
                Object newObj = target.getClass().newInstance();
                ReflectAsmHelper.copyProperties(target,newObj,propertyName);
                BeanInfo beanInfo = new BeanInfo();
                Map<String,Boolean> changeMap = new HashMap<String, Boolean>();
                changeMap.put(propertyName,true);
                beanInfo.setObj(newObj);
                beanInfo.setChangeMap(changeMap);
                map.put(ObjectHashCode,beanInfo);
                EasyCacheUtil.set(classHashCode.toString(),map);
                System.out.println(propertyName+"保存");
            }else{
                BeanInfo info = map.get(ObjectHashCode);
                Object obj = info.getObj();
                Map<String,Boolean> changeMap = info.getChangeMap();
                if(obj!=null){
                    MethodAccess objAccess = MethodAccess.get(obj.getClass());
                    MethodAccess targetAccess = MethodAccess.get(target.getClass());
                    Object value1 = objAccess.invoke(obj,  "get" + StringUtils.upperCase(propertyName), null);
                    Object value2 = targetAccess.invoke(target,  "get" + StringUtils.upperCase(propertyName), null);
                    if(value1==null && value2==null){
                        System.out.println(propertyName+"不变");
                    }else if(value1!=null && value2!=null){
                        if(value1.equals(value2)){
                            System.out.println(propertyName+"不变");
                        }else{
                            System.out.println(propertyName+"修改");
                            ReflectAsmHelper.copyProperties(target,obj,propertyName);
                            BeanInfo beanInfo = new BeanInfo();
                            beanInfo.setObj(obj);
                            changeMap.put(propertyName,true);
                            beanInfo.setChangeMap(changeMap);
                            map.put(ObjectHashCode,beanInfo);
                            EasyCacheUtil.set(classHashCode.toString(),map);
                        }
                    }else{
                        System.out.println(propertyName+"保存");
                        ReflectAsmHelper.copyProperties(target,obj,propertyName);
                        BeanInfo beanInfo = new BeanInfo();
                        beanInfo.setObj(obj);
                        changeMap.put(propertyName,true);
                        beanInfo.setChangeMap(changeMap);
                        map.put(ObjectHashCode,beanInfo);
                        EasyCacheUtil.set(classHashCode.toString(),map);
                    }
                }
            }
        }else{

        }
        return proceed;
    }

    @AfterReturning(value = "point()")
    public void after(){
//        System.out.println("end Bind.");
    }
}
