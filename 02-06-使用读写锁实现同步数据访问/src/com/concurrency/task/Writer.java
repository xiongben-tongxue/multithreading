package com.concurrency.task;

/**
 * @Classname Writer
 * @Description 写者类，产生价格
 * @Date 2019-02-12 09:32
 * @Created by wangdong
 */
public class Writer implements Runnable{

    /**
     * 价格信息对象
     */
    private PricesInfo pricesInfo;

    /**
     * 构造函数
     * @param pricesInfo
     */
    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    /**
     * 核心方法，写价格
     */
    @Override
    public void run() {
        //调用一次run()，会去写3次
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random() * 10,Math.random() * 8);
            System.out.printf("Writer: Prices have been modified.\n");
            //每改一次就短暂的睡眠一下，将cpu让给其他线程
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
