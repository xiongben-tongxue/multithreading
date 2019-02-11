package com.concurrency.core;

import com.concurrency.handler.ExceptionHandler;
import com.concurrency.task.Task;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 13:54
 * @Created by wangdong
 */
public class Main {

    public static void main(String[] args) {
        //创建一个任务
        Task task = new Task();
        //创建一个线程
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();

        try {
            //等待线程完成
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread has finished\n");
    }
}
