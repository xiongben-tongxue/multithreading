package com.concurrency.task;

import com.concurrency.event.Event;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * @Classname WriterTask
 * @Description 写事件的类，每秒钟产生一个事件的对象
 * @Date 2019-02-11 10:48
 * @Created by wangdong
 */
public class WriterTask implements Runnable{

    /**
     * 用于存储事件对象的队列
     */
    Deque<Event> deque;

    /**
     * 构造函数
     * @param deque
     */
    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        //产生100个事件对象
        for (int i = 0; i < 100; i++) {
            //创建和初始化事件对象
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));

            //将事件添加到队列的头部
            deque.addFirst(event);

            try {
                // 休眠一秒种
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
