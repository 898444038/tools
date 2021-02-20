package com.ming.tools.binding4.bind.property;

import net.sf.cglib.proxy.Enhancer;

public final class ObservableBeanFactory {

    public static <T> T createObservableBean(Class<T> beanClass) {
        PropertyChangeInterceptor interceptor = new PropertyChangeInterceptor();

        Enhancer e = new Enhancer();
        e.setSuperclass(beanClass);
        e.setCallback(interceptor);
        e.setInterfaces(new Class[] { Observable.class });

        @SuppressWarnings("unchecked")
        T bean = (T) e.create();

        interceptor.setTarget(bean);

        return bean;
    }
}
