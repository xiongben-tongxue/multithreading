package com.concurrency.core;

import com.concurrency.task.Consumer;
import com.concurrency.task.EventStorage;
import com.concurrency.task.Producer;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 19:58
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个事件存储对象
        EventStorage eventStorage = new EventStorage();

        //创建一个事件生产者对象，并且将其放入到一个线程中运行
        Producer producer = new Producer(eventStorage);
        Thread producerThread = new Thread(producer);

        //创建一个事件消费者对象，并且将其放到一个线程中去运行
        Consumer consumer = new Consumer(eventStorage);
        Thread consumerThread = new Thread(consumer);

        //启动线程
        consumerThread.start();
        producerThread.start();

        //这边不需要手动去停止程序。
        //线程调用了一次，run()就只生产100个事件，消费完了就没有了
    }
}
