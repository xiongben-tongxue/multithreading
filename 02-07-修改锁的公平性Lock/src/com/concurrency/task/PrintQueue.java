package com.concurrency.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname PrintQueue
 * @Description 打印队列类，模拟一个打印队列事件
 * @Date 2019-02-12 09:50
 * @Created by wangdong
 */
public class PrintQueue {
    /**
     * 用于控制队列访问的锁，使用公平锁
     */
    private final Lock queueLock = new ReentrantLock(false);

    /**
     * @Description 打印一个文档
     * @Param: [object]
     * @Return: void
     * @Date 2019-02-12 09:52
     * @Auther wangdong
     */
    public void printJob(Object object) {
        queueLock.lock(); // 上锁
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock(); // 解锁
        }

        queueLock.lock(); // 上锁
        try {
            long duration = (long) (Math.random() * 10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock(); // 解锁
        }
    }
}
