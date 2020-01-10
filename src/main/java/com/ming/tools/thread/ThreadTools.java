package com.ming.tools.thread;

import com.ming.tools.thread.pool.ThreadPoolTools;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class ThreadTools {
    private volatile static ThreadTools instance = null;

    private ThreadTools(){}

    public static ThreadTools getInstance(){
        if (instance==null){
            synchronized (ThreadTools.class){
                if (instance==null){
                    instance = new ThreadTools();
                }
            }
        }
        return instance;
    }

    public ThreadPoolTools threadPool(){
        return ThreadPoolTools.getInstance();
    }
}
