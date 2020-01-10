package com.ming.tools.thread.util;

import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障CyclicBarrier:让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
 * CyclicBarrier会等待指定线程的await()方法调用结束。
 * 如果指定了三个线程CyclicBarrier(3)，但只有2个线程调用了await()方法，
 * 这两个线程将一直等待下去。
 */
public class CyclicBarrierUtil extends Thread {

    CyclicBarrier barrier;

    public CyclicBarrierUtil(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println(currentThread().getName() + " start");
            Thread.sleep(1000);
            barrier.await();
            System.out.println(currentThread().getName() + " end");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            new CyclicBarrierUtil(barrier).start();
        }
    }

}
