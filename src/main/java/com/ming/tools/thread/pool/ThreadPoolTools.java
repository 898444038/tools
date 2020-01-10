package com.ming.tools.thread.pool;

/**
 * Created by Administrator on 2020/1/9 0009.
 */
public class ThreadPoolTools {
    private volatile static ThreadPoolTools instance = null;

    private ThreadPoolTools(){}

    public static ThreadPoolTools getInstance(){
        if (instance==null){
            synchronized (ThreadPoolTools.class){
                if (instance==null){
                    instance = new ThreadPoolTools();
                }
            }
        }
        return instance;
    }

    public void execute(Runnable task){
        ThreadPoolUtils.execute(task);
    }
    public void execute(Runnable task,int size){
        ThreadPoolUtils.execute(task,size);
    }
}
