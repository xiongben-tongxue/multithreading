package com.concurrency.task;

/**
 * @Classname Consumer
 * @Description 消费者对象，消费事件
 * @Date 2019-02-11 19:55
 * @Created by wangdong
 */
public class Consumer implements Runnable{
    /**
     * 事件的存储对象
     */
    private EventStorage storage;

    /**
     * 构造函数
     * @param storage
     */
    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.storage.get();
        }
    }
}
