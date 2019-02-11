package com.concurrency.task;

/**
 * 创建一个Calculator类实现了Run
 */

public class Calculator implements Runnable{

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {

        //指定数字进行乘法运算
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, number * i);
        }
    }
}
