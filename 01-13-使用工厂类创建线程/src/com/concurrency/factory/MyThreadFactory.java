package com.concurrency.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * @Classname MyThreadFactory
 * @Description 线程工厂类实现了线程工厂的接口
 * @Date 2019-02-11 15:15
 * @Created by wangdong
 */
public class MyThreadFactory implements ThreadFactory {

    private int counter; // 线程计数器，计数已经产生了多少个线程
    private String name; // 线程的基准名称
    private List<String> stats;  // 线程统计信息集合

    public MyThreadFactory(String name) {
        this.name = name;
        this.counter = 0;
        this.stats = new ArrayList<>();
    }

    /**
     * 使用Runnable对象创建一个线程
     * @param r
     * @return
     */
    @Override
    public Thread newThread(Runnable r) {
        // 创建一个新的线程对象
        Thread t = new Thread(r, this.name + "-Thread_" + this.counter);
        this.counter++;
        // Actualize the statistics of the factory
        this.stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
        return t;
    }

    public String getStats(){
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext()){
            buffer.append(it.next());;
        }
        return buffer.toString();
    }
}
