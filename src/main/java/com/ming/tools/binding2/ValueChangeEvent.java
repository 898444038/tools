package com.ming.tools.binding2;

import java.util.EventObject;

/**
 * ValueChangedEvent用于在设置属性值的时候，触发事件
 */
public class ValueChangeEvent extends EventObject {

    private static final long serialVersionUID = 767352958358520268L;

    private int value;

    public ValueChangeEvent(Object source) {
        this(source, 0);
    }

    public ValueChangeEvent(Object source, int newValue) {
        super(source);
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
