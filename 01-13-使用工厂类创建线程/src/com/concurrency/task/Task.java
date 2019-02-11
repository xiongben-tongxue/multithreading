package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * @Classname Task
 * @Description TODO
 * @Date 2019-02-11 15:15
 * @Created by wangdong
 */
public class Task implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
