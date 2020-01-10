package com.ming.tools.thread.util;

/**
 * BlockingQueue是Java中的阻塞队列，JDK中提供了7个阻塞队列

 ArrayBlockingQueue : 数组实现的有界队列，对元素进行FIFO(先进先出)的原则排序。

 LinkedBlockingQueue： 链表组成的有界队列，长度默认最大值为Integer.MAX_VALUE，元素按FIFO原则排序，性能好于ArrayBlockingQueue。

 PriorityBlockingQueue：支持优先级的无界阻塞队列。

 DelayQueue： 支持延迟获取元素的无界阻塞队列

 SynchronousQueue：不存储元素的阻塞队列。每一个put操作必须等待take操作，否则不能继续添加元素。

 LinkedTransferQueue：链表组成的无界传输队列

 LinkedBlockingDeque：由链表组成的双向阻塞队列。可以从两段插入和移除元素。

 */
public class BlockingQueueUtil {
}
