package com.concurrency.task;

/**
 * @Classname Job
 * @Description TODO
 * @Date 2019-02-12 11:17
 * @Created by wangdong
 */
public class Job implements Runnable{

    /**
     * 打印队列的对象
     */
    public PrintQueue printQueue;

    /**
     * 构造函数
     * @param printQueue
     */
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    /**
     * 核心方法，向打印队列中发送打印任务，并且等待它完成
     */
    @Override
    public void run() {
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
