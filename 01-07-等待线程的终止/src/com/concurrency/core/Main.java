package com.concurrency.core;

import com.concurrency.task.DataSourcesLoader;
import com.concurrency.task.NetworkConnectionsLoader;

import java.util.Date;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 10:39
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建并启动数据源加载器
        DataSourcesLoader dataSourcesLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dataSourcesLoader, "dataSourcesThread");
        thread1.start();

        //创建并启动网络连接加载器
        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader,"networkConnectionsLoader");
        thread2.start();

        //等待两个线程的任务完成
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //只有在上面两个线程都完成后，主线程才会继续往下走，用join控制
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
