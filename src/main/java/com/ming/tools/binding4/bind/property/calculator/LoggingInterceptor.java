package com.ming.tools.binding4.bind.property.calculator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

public class LoggingInterceptor implements MethodInterceptor {
    private static final Log LOG = LogFactory.getLog(LoggingInterceptor.class);

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        LOG.info("Starting call to: " + method.getName());
        Object returnObject = proxy.invokeSuper(target, args);
        LOG.info("Completed call to: " + method.getName());
        return returnObject;
    }

}