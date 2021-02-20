package com.ming.tools.binding2;

/**
 * EventProducer提供了监听容器listenerRegister，负责消费者的注册
 */
public class EventProducer {
    ListenerRegister register = new ListenerRegister();
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        if (value != newValue) {
            value = newValue;
            ValueChangeEvent event = new ValueChangeEvent(this, value);
            fireAEvent(event);
        }
    }

    public void addListener(ValueChangeListener a) {
        register.addListener(a);
    }

    public void removeListener(ValueChangeListener a) {
        register.removeListener(a);
    }

    public void fireAEvent(ValueChangeEvent event) {
        register.fireAEvent(event);
    }

}
