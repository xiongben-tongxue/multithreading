package com.concurrency.task;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Classname Consumer
 * @Description 消费者类
 * @Date 2019-02-12 20:19
 * @Created by wangdong
 */
public class Consumer implements Runnable{

    /**
     * 消费者消费数据的地方，也是与生产者交换数据的地方
     */
    private List<String> buffer;

    /**
     * 同步生产者与消费者交换数据的交换对象
     */
    private final Exchanger<List<String>> exchanger;

    /**
     * 构造函数
     * @param buffer
     * @param exchanger
     */
    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    /**
     * 核心方法
     * 它消费生产者生产的数据，每消费10个事件，它使用交换对象去同步生产者
     * 他将已经消费完的空缓存对象发送给生产者，同时获取生产者生产的装有10个事件的缓存对象
     */
    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            //等待消费生产者的数据，并且将空的缓存对象发送给生产者
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Consumer: %d\n", buffer.size());

            for (int j = 0; j < 10; j++) {
                //这里必须写0
                String message = buffer.get(0);
                System.out.printf("Consumer: %s\n", message);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
