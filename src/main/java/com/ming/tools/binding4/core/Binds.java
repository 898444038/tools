package com.ming.tools.binding4.core;

import com.ming.tools.binding4.bind.hashmap.MapProxy;
import com.ming.tools.binding4.bind.property.LoggingPropertyChangeListener;
import com.ming.tools.binding4.bind.property.Observable;
import com.ming.tools.binding4.bind.property.ObservableBeanFactory;
import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

public class Binds {

    public static <T> T bindObject(Object obj){
        Class clazz = obj.getClass();
        Object regular = ObservableBeanFactory.createObservableBean(clazz);
        copy(obj,regular);
        ((Observable) regular).addPropertyChangeListener(new LoggingPropertyChangeListener());
        return (T) regular;
    }

    public static <T> T bindObject(Class<? extends T> clazz){
        T regular = ObservableBeanFactory.createObservableBean(clazz);
        ((Observable) regular).addPropertyChangeListener(new LoggingPropertyChangeListener());
        return regular;
    }

    public static <T> T bindObject(Class<? extends T> clazz,Object key){
        T regular = ObservableBeanFactory.createObservableBean(clazz);
        ((Observable) regular).addPropertyChangeListener(new LoggingPropertyChangeListener());
        return regular;
    }

    /**
     * 删除对象
     */
    public static <T> T removeObject(Class<? extends T> clazz,Object key) {
        T regular = ObservableBeanFactory.createObservableBean(clazz);
        ((Observable) regular).addPropertyChangeListener(new LoggingPropertyChangeListener());
        return regular;
    }

    public static <K,V> Map<K,V> bindMap() {
        Map<K,V> proxyMap = new HashMap<>();
        proxyMap = MapProxy.newProxy(proxyMap);
        return (Map<K,V>) proxyMap;
    }

    public static <K,V> Map<K,V> bindMap(Map<? extends K, ? extends V> map) {
        //Map<K,V> proxyMap = new HashMap<>();
        map = MapProxy.newProxy(map);
        return (Map<K,V>) map;
    }

    public static <K,V> Map<K,V> removeMap(Map<? extends K, ? extends V> map) {
        //return new Collections.UnmodifiableMap<>(m);
        return null;
    }

    public static void copy(Object from, Object to) {
        BeanCopier copier = BeanCopier.create(from.getClass(), to.getClass(), false);
        copier.copy(from, to, null);
    }


    public static <K,V> Map<K,V> copyMap(Map<? extends K, ? extends V> from, Map<K, V> to) {
        for (Map.Entry<? extends K,? extends V> entry : from.entrySet()){
            K k = entry.getKey();
            V v = entry.getValue();
            to.put(k,v);
        }
        return to;
    }
}
