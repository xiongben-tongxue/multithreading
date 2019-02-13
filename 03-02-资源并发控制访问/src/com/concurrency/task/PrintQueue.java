package com.concurrency.task;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Classname PrintQueue
 * @Description 打印队列类，使用信号量来控制打钱作业的访问
 * @Date 2019-02-12 11:08
 * @Created by wangdong
 */
public class PrintQueue {

    /**
     * 信号量，用来控制队列的访问
     */
    private final Semaphore semaphore;

    /**
     * 构造函数，初始化信号量
     */
    public PrintQueue() {
        this.semaphore = new Semaphore(1);
    }

    /**
     * 模拟文档打印的方法
     *
     * @param document
     */
    public void printJob(Object document) {

        try {
            //请求信号量，如果已经被其他线程请求过了,则当前请求线程会休眠，直到获得这个信号量
            //会一直在这个地方卡着
            semaphore.acquire();

            //请求到了信号了，处理
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            //假装处理
            Thread.sleep(duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放信号量
            //如果刚刚好有其他线程在请求这个信号量
            //Jvm会选择其中的某一个线程的信号量，让其运行
            semaphore.release();
        }
    }
}
