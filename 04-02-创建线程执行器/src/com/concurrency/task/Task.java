package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname Task
 * @Description TODO
 * @Date 2019-02-13 09:35
 * @Created by wangdong
 */
public class Task implements Runnable{
    /**
     * 任务创建的事件
     */
    private Date initDate;

    /**
     * 任务的名称
     */
    private String name;

    /**
     * 构造函数，初始化属性
     * @param name
     */
    public Task(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    /**
     * 核心类，执行任务，等待一个随机的时间完成任务
     */
    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());

        long duration = (long) (Math.random() * 10);
        System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name, duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
    }
}
