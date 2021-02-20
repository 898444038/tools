package com.ming.tools.binding3.map;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
public class TaskbarObserver implements Observer {

    /**
     * @Title: update
     * @Description: 实现观察者接口,当发现被观察者有Changed时执行该方法
     * @param o
     * @param arg:
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     * @author
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }

}
