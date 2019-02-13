package com.concurrency.core;

import com.concurrency.task.Consumer;
import com.concurrency.task.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 20:29
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建两个缓存对象
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        //创建一个交换器对象
        Exchanger<List<String>> exchanger = new Exchanger<>();

        //创建生产者对象
        //为什么要创建两个缓存对象
        //因为生产者生产满了buffer1就给消费者
        //消费者把空的buffer2给生产者
        Producer producer = new Producer(buffer1,exchanger);
        //创建消费者对象
        Consumer consumer = new Consumer(buffer2,exchanger);

        //将生产者和消费者对象放置到不同的线程中
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);

        //启动两个线程
        threadProducer.start();
        threadConsumer.start();
    }
}
