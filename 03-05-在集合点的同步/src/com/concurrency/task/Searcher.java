package com.concurrency.task;

import com.concurrency.utils.MatrixMock;
import com.concurrency.utils.Results;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Classname Searcher
 * @Description 查找类
 * @Date 2019-02-12 15:47
 * @Created by wangdong
 */
public class Searcher implements Runnable{

    /**
     * 开始查找的行数
     */
    private int firstRow;

    /**
     * 最后查找的行数(不包括)
     */
    private int lastRow;

    /**
     * 矩阵模拟对象
     */
    private MatrixMock matrixMock;

    /**
     * 结果对象
     */
    private Results results;

    /**
     * 要查找的数字
     */
    private int number;

    /**
     * 同步栅
     */
    private final CyclicBarrier barrier;

    /**
     * 构造函数
     *
     * @param barrier  同步栅
     * @param firstRow 开始查找的行数
     * @param lastRow  最后查找的行数（不包含）
     * @param mock     矩阵模拟对象
     * @param results  结果对象
     * @param number   要查找的数字
     */
    public Searcher(CyclicBarrier barrier, int firstRow, int lastRow, MatrixMock mock, Results results, int number) {
        this.barrier = barrier;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = mock;
        this.results = results;
        this.number = number;
    }

    /**
     * 核心方法
     * 查找指定行数范围内的指定数字
     * 将结果保存在结果数组对应的位置
     */
    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
        //这是针对的是行,这里是在遍历行
        for (int i = firstRow; i < lastRow; i++) {
            int row[] = matrixMock.getRow(i);
            counter = 0;
            //这是针对的列，这里是在遍历行中的列，例如第三行，从第一列开始遍历
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number){
                    counter++;
                }
            }
            results.setData(i,counter);
        }

        System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

        //等待所有查找完成
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
