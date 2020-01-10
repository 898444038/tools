package com.ming.tools.thread.util;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport提供了一些静态方法用于阻塞线程，和唤醒线程的功能。
 处于park()挂起状态的线程是Waiting状态，park()方法阻塞的线程还支持中断，不抛出中断异常的同时设置中断标志位，然后我们可以通过中断标志位来检查。
 */
public class LockSupportUtil implements Runnable{
    public static Object sObject = new Object();

    public void run() {
        synchronized (sObject){
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            LockSupport.park();
            if (Thread.currentThread().isInterrupted()){
                System.out.println( Thread.currentThread().getName() +  "被中断了");
            }
            System.out.println("执行结束");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        LockSupportUtil demo = new LockSupportUtil();
        Thread t1 = new Thread(demo,"t1");
        Thread t2 = new Thread(demo,"t2");
        t1.start();
        Thread.sleep(3000);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }

}
