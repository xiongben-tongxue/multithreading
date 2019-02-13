package com.concurrency.task;

/**
 * @Classname Reader
 * @Description TODO
 * @Date 2019-02-12 09:27
 * @Created by wangdong
 */
public class Reader implements Runnable{

    /**
     * 价格信息对象
     */
    private PricesInfo pricesInfo;

    /**
     * 构造函数
     */
    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    /**
     * 核心方法，消费两个价格，并且将他们输出
     */
    @Override
    public void run() {
        //调用一次run()方法循环读十次
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
