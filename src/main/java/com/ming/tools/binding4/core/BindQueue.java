package com.ming.tools.binding4.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列
 */
public class BindQueue<T> {
    // 定义一个队列
    private Queue<T> queue;

    public Queue<T> getQueue(){
        // 实例化队列变量
        queue = new LinkedList<>();
        return queue;
    }

    public static void main(String[] args) {
        BindQueue<String> bindQueue = new BindQueue<>();
        Queue queue = bindQueue.getQueue();
        // offer方法向队列中添加元素，返回布尔值
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");

        // 弹出元素
        Object pollEle = queue.poll(); // 先进先出,弹出了a
        System.out.println(pollEle); // a
        System.out.println(queue); // [b, c, d, e]

        // 查看首个元素
        Object peek = queue.peek(); // 首个元素是a,最先加入
        System.out.println(peek); // a
        System.out.println(queue); // [a, b, c, d, e]
    }

}
