package com.ming.tools.binding2;

/**
 * ValueChangeListener 提供属性值变化的接口
 */
public interface ValueChangeListener extends java.util.EventListener {
    public abstract void performed(ValueChangeEvent e);
}
