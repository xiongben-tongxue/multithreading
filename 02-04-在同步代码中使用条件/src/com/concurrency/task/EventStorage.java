package com.concurrency.task;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname EventStorage
 * @Description 事件存储类，生产者会存储事件，消费者会处理存储的事件，一个事件就是一个日期对象
 * @Date 2019-02-11 19:35
 * @Created by wangdong
 */
public class EventStorage {

    /**
     * 最多可以保存的事件数量
     */
    private int maxSize;

    /**
     * 存储事件的集合
     */
    private List<Date> storage;

    public EventStorage() {
        //最多可以存储10个事件
        this.maxSize = 10;
        //初始化事件存储集合
        this.storage = new LinkedList<>();
    }

    /**
     * @Description 生产者 同步方法，向事件集合中添加一个事件
     * @Param: []
     * @Return: void
     * @Date 2019-02-11 19:44
     * @Auther wangdong
     */
    public synchronized void set() {
        //如果集合已经满了，就等待
        while (this.storage.size() == this.maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //产生事件并且添加到集合中去
        this.storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        //唤醒其他线程
        notify();
    }

    /**
     * @Description 消费者 同步方法，处理事件集合中的一个事件
     * @Param: []
     * @Return: void
     * @Date 2019-02-11 19:45
     * @Auther wangdong
     */
    public synchronized void get(){
        //如果集合为空就等待
        while (this.storage.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 消费一个事件
        System.out.printf("Get: %d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
        // 唤醒其它线程
        notify();
    }


}
