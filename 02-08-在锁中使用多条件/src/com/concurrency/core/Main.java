package com.concurrency.core;

import com.concurrency.task.Consumer;
import com.concurrency.task.Producer;
import com.concurrency.utils.Buffer;
import com.concurrency.utils.FileMock;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 10:58
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个文件模拟对象，他有101行,每行的长度是10
        FileMock mock = new FileMock(101,10);

        //创建一个缓冲对象，最多可以缓存20行数据
        Buffer buffer = new Buffer(20);

        //创建一个生产者对象，并且让它在一个单独的线程中运行
        Producer producer = new Producer(mock,buffer);
        Thread threadProducer = new Thread(producer,"producer");

        //创建三个消费者对象，并且让他们分别在不同的线程中运行
        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];

        //启动生产者
        threadProducer.start();

        //启动消费者
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i],"Consumer " + i);
            threadConsumers[i].start();
        }
    }
}
