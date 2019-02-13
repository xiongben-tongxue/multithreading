package com.concurrency.task;

/**
 * @Classname Job
 * @Description 打印文档的队列，实现了Runnable接口
 * @Date 2019-02-11 20:21
 * @Created by wangdong
 */
public class Job implements Runnable{

    /**
     * 打印文档的队列
     */
    private PrintQueue printQueue;

    /**
     * 构造一个函数
     * @param printQueue
     */
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
