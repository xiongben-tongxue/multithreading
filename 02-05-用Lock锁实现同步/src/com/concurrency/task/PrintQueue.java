package com.concurrency.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname PrintQueue
 * @Description 打印队列类，模拟一个打印队列事件
 * @Date 2019-02-11 20:15
 * @Created by wangdong
 */
public class PrintQueue {

    /**
     * 用于控制队列访问的锁
     */
    private final Lock queueLock = new ReentrantLock();

    /**
     * @Description 模拟打印一个文档
     * @Param: [object]
     * @Return: void
     * @Date 2019-02-11 20:17
     * @Auther wangdong
     */
    public void printJob(Object object) {
        //上锁
        queueLock.lock();
        long duration = (long) (Math.random() * 10000);
        System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), (duration / 1000));
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //用完了要释放锁
            queueLock.unlock();
        }
    }

}
