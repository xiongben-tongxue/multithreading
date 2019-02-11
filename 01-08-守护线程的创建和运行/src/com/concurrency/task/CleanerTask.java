package com.concurrency.task;

import com.concurrency.event.Event;

import java.util.Date;
import java.util.Deque;

/**
 * @Classname CleanerTask
 * @Description 事件的清除类，每隔10秒钟从队尾取出一个事件
 * @Date 2019-02-11 10:55
 * @Created by wangdong
 */
public class CleanerTask extends Thread{

    /**
     * 用于存储事件对象的队列
     */
    Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        // 表明当前对象是一个精灵线程
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date){
        long difference;
        boolean delete;

        /**
         * @Description 如果队列为空，直接返回
         * @Param: [date]
         * @Return: void
         * @Date 2019-02-11 11:03
         * @Auther wangdong
         */
        if (this.deque.size() == 0){
            return;
        }

        delete = false;
        do {
            Event e = this.deque.getLast();
            //计算最早的事件距离现在的事件
            difference = date.getTime() - e.getDate().getTime();
            //如果大于10秒钟，就输出信息，并且删除掉最先发生的事件
            if (difference > 10000){
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }

        } while (difference > 10000);

        //为true的时候，就说明有删除的操作
        if (delete){
            System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
        }
    }
}
