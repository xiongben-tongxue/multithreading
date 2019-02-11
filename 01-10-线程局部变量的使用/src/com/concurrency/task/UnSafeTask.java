package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname UnSafeTask
 * @Description 线程不安全的任务，当这个任务在多个线程中时
 * 其中的变量会被多个线程共享
 * @Date 2019-02-11 14:10
 * @Created by wangdong
 */
public class UnSafeTask implements Runnable{

    /**
     * 日期对象，被所有线程共享
     */
    private Date startDate;

    @Override
    public void run() {
        this.startDate = new Date();
        System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate);

    }
}
