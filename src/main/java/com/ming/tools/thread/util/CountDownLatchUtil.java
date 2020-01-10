package com.ming.tools.thread.util;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch首先定义任务次数，并调用await()方法等待任务完成。
 * 调用countDown()方法表明已经完成一项任务，
 * 当任务全部完成后，继续await()方法后的任务。
 *
 *
 * CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 * CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。isBroken方法用来知道阻塞的线程是否被中断。比如以下代码执行完之后会返回true。
 */
public class CountDownLatchUtil extends Thread {

    CountDownLatch latch;

    public CountDownLatchUtil(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            System.out.println(currentThread().getName() + " start");
            Thread.sleep(1000);
            latch.countDown();
            System.out.println(currentThread().getName() + " end");
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            new CountDownLatchUtil(latch).start();
        }

        System.out.println(Thread.currentThread().getName() + " start");
        latch.await();
        System.out.println(Thread.currentThread().getName() + " end");
    }

}
