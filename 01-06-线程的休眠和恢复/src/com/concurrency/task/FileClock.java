package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname FileClock
 * @Description 文件定时类，每隔一秒钟将实际的时间输出
 * @Date 2019-02-11 10:18
 * @Created by wangdong
 */
public class FileClock implements Runnable{


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());

            try {
                //休眠一秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //当线程被中断时，释放活着关闭线程正在使用的资源
                System.out.printf("The FileClock has been interrupted");
                e.printStackTrace();
                //异常发生时跳出
                return;
            }
        }
    }
}
