package com.ming.tools.binding.core;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.ming.tools.binding.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2020/3/18 0018.
 */
public class ReflectAsmManager {
    private static final ConcurrentMap<Class, MethodAccess> localCache = new ConcurrentHashMap<Class, MethodAccess>();

    public static MethodAccess get(Class clazz) {
        if(localCache.containsKey(clazz)) {
            return localCache.get(clazz);
        }

        MethodAccess methodAccess = MethodAccess.get(clazz);
        localCache.putIfAbsent(clazz, methodAccess);
        return methodAccess;
    }

    public static <F,T> void copyProperties(F from, T to,String key) {
        MethodAccess fromMethodAccess = get(from.getClass());
//        MethodAccess toMethodAccess = get(to.getClass());

        Field[] fromDeclaredFields = from.getClass().getDeclaredFields();
        for(Field field : fromDeclaredFields) {
            String name = field.getName();
            if(key.equalsIgnoreCase(name)){
                try {
                    Object value = fromMethodAccess.invoke(from,  "get" + StringUtils.upperCase(name), null);
                    //toMethodAccess.invoke(to, "set" + StringUtils.upperCase(name), value);
                    field.setAccessible(true);//压制java检查机制
                    field.set(to, value);
                } catch (Exception e) {
                    // 设置异常，可能会没有对应字段，忽略
                    //e.printStackTrace();
                }
            }
        }

    }
}