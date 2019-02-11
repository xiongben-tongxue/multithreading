package com.concurrency.task;

/**
 * @Classname Producer
 * @Description 生产者对象，生产事件
 * @Date 2019-02-11 19:52
 * @Created by wangdong
 */
public class Producer implements Runnable{
    /**
     * 事件的存储对象
     */
    private EventStorage storage;

    /**
     * 构造函数
     * @param storage 事件存储对象
     */
    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        //调用一次生产100个事件
        for (int i = 0; i < 100; i++) {
            this.storage.set();
        }
    }
}
