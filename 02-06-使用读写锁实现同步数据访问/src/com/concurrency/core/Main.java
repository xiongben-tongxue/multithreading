package com.concurrency.core;

import com.concurrency.task.PricesInfo;
import com.concurrency.task.Reader;
import com.concurrency.task.Writer;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 09:39
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建价格信息对象，用于存储价格
        PricesInfo pricesInfo = new PricesInfo();

        //创建一个写着，并且将其放在一个线程中运行
        Writer writer = new Writer(pricesInfo);
        Thread writerThread = new Thread(writer);
        //启动写着线程
        writerThread.start();

        //创建5个读者，创建5个读者线程
        Reader readers[] = new Reader[5];
        Thread readerThreads[] = new Thread[5];

        //创建5个读者并且将其放在不同的线程中去运行
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            readerThreads[i] = new Thread(readers[i]);
            //启动读者线程
            readerThreads[i].start();
        }
    }
}
