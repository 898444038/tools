package com.ming.tools.thread.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号灯同步工具
 * 与互斥锁对比，信号灯可以由其它线程释放，互斥锁只能由自己释放
 * 模拟场景：现在有3个厕所坑，有5个人要进去，先进去3个人，其它2个等待，有人出来，等待的人再进去
 * <p>
 * TTP：
 * 知识很容易就学会，最大的困难是在以后的工作中遇到困难了，正好能想到用这个工具
 * 每个药能治什么病，很容易学会，难就难在当看到一个病人以后，能想起来就这个药，能想到这一步你就是华佗。
 */
public class SemaphoreUtil {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        //可使用的公共资源数量
        final Semaphore sp = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        // 获取一个信号灯
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程: " + Thread.currentThread().getName() + "进入，当前已有"
                            + (3 - sp.availablePermits()) + "个并发。");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "即将离开。");
                    sp.release();
                    System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有"
                            + (3 - sp.availablePermits()) + "个并发");
                }
            };
            executor.execute(runnable);
        }
        executor.shutdown();
    }
}
