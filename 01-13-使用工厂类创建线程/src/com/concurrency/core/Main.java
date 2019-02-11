package com.concurrency.core;

import com.concurrency.factory.MyThreadFactory;
import com.concurrency.task.Task;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 15:23
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个线程工厂
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        //新建一个任务
        Task task = new Task();
        Thread thread;

        //创建并且启动10个线程对象
        System.out.printf("Starting the Threads\n");
        for (int i = 0; i < 10; i++) {
            //通过线程工厂，直接创建thread
            thread = factory.newThread(task);
        }

        //打印线程工厂的统计信息
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n", factory.getStats());
    }
}
