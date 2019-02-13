package com.concurrency.task;

import com.concurrency.utils.Results;

/**
 * @Classname Grouper
 * @Description
 * @Date 2019-02-12 16:07
 * @Created by wangdong
 */
public class Grouper implements Runnable{

    /**
     * 结果对象
     */
    private Results results;

    /**
     * 构造函数
     * @param results
     */
    public Grouper(Results results) {
        this.results = results;
    }


    /**
     * 核心方法
     * 对查找的结果进行汇总
     */
    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = results.getData();
        for (int number: data) {
            finalResult += number;
        }
        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
