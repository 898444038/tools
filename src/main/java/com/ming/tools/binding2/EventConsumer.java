package com.ming.tools.binding2;

/**
 * EventConsumer实现ValueChangedListener，当EventProducer 发送事件时，接收事件
 */
public class EventConsumer implements ValueChangeListener {

    @Override
    public void performed(ValueChangeEvent e) {
        System.out.println("value changed, new value = " + e.getValue());
    }
}
