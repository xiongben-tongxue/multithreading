package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname DataSourcesLoader
 * @Description 数据源加载器，模拟数据加载，它会休眠4s
 * @Date 2019-02-11 10:37
 * @Created by wangdong
 */
public class DataSourcesLoader implements Runnable{
    @Override
    public void run() {

        // 输出一条消息
        System.out.printf("Beginning data sources loading: %s\n",new Date());

        // 休眠10s
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出一条消息
        System.out.printf("Data sources loading has finished: %s\n",new Date());
    }
}
