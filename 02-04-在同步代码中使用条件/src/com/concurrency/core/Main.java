package com.concurrency.core;

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
    }
}
