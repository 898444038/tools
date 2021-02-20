package com.ming.tools.binding4.bind.hashmap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class MapProxy<K,V> implements InvocationHandler {

    private Map<K,V> map;

    public static <K,V> Map<K,V> newProxy(Map<K,V> map) {
        return new MapProxy<>(map).getProxy();
    }

    private MapProxy(Map<K,V> map) {
        this.map = map;
    }

    private Map<K,V> getProxy() {
        return (Map<K,V>) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[] { Map.class }, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        String methodName = method.getName();
        if (methodName.equalsIgnoreCase("put")) {
            //this.beforePut(args);
            result = method.invoke(map, args);
            //this.afterPut(result);
            this.change((K)args[0],(V)args[1],result);
        }/* else if(methodName.equalsIgnoreCase("putAll")){
            Map maps = (Map) args[0];
            Iterator<String> it = maps.keySet().iterator();
            String key = null;
            while (it.hasNext()) {
                key = it.next();
                result = method.invoke(map, args);
                this.change((K)key,(V)maps.get(key),result);
            }
        }*/
        return result;
    }

    protected void change(K key,V val,Object result) {
        System.out.println("Map变化[" + key +" : "+ result +" => "+ val +"]");
    }

    /*protected void beforePut(Object[] args) {
        System.out.println("put: " + Arrays.toString(args));
    }

    protected void afterPut(Object result) {
        System.out.println("return: " + result);
    }*/


}
