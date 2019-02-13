package com.concurrency.task;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Classname Producer
 * @Description 生产者类
 * @Date 2019-02-12 20:07
 * @Created by wangdong
 */
public class Producer implements Runnable{

    /**
     * 生产者生产数据后存储的位置，也是与消费者交换数据的地方
     */
    private List<String> buffer;

    /**
     * 同步生产者与消费者交换数据的交换对象
     */
    private final Exchanger<List<String>> exchanger;

    /**
     * 构造函数，初始化属性
     * @param buffer 数据的存储对象
     * @param exchanger 数据的交换对象
     */
    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    /**
     * 核心方法
     * 产生100个事件，分10次产生，每次产生10个事件
     * 每产生10个事件后，调用数据交换对象去同步消费者。
     * 生产者将存放10个事件的缓存对象发送给消费者
     * 并且从消费者哪里接受到一个空的缓存对象
     */
    @Override
    public void run() {
        int cycle = 1;
        //一共100个事件，分10次产生
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer: Cycle %d\n", cycle);
            //每次生产10个事件
            for (int j = 0; j < 10; j++) {
                String message = "Event " + ((i * 10) + j);
                System.out.printf("Producer: %s\n", message);
                buffer.add(message);
            }

            //每生产完10个事件，与消费者交换数据
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Producer: %d\n", buffer.size());

            cycle++;
        }
    }
}
