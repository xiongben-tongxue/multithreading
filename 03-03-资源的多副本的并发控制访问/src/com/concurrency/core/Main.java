package com.concurrency.core;

import com.concurrency.task.Job;
import com.concurrency.task.PrintQueue;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 13:51
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个打印机对象
        PrintQueue printQueue = new PrintQueue();

        //创建12个线程，运行作业任务，这些任务都向同一个打印队列对象发出打印请求
        Thread threads[] = new Thread[12];
        for (int i = 0; i < 12; i++) {
            threads[i] = new Thread(new Job(printQueue),"Thread " + i);
            //启动线程
            threads[i].start();
        }
    }
}
