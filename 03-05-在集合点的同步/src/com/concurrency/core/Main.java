package com.concurrency.core;

import com.concurrency.task.Grouper;
import com.concurrency.task.Searcher;
import com.concurrency.utils.MatrixMock;
import com.concurrency.utils.Results;

import java.util.concurrent.CyclicBarrier;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 16:15
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //矩阵的行数
        final int ROWS = 10000;
        //矩阵的列数
        final int NUMBERS = 1000;
        //要查询的数字
        final int SEARCH = 5;
        //查询的线程个数
        final int PARTICIPANTS = 5;
        //每个查询线程处理的行数
        final int LINES_PARTICIPANT = 2000;
        //创建矩阵模拟对象
        MatrixMock matrixMock = new MatrixMock(ROWS,NUMBERS,SEARCH);
        //创建结果对象
        Results results = new Results(ROWS);
        //创建结果组合对象
        Grouper grouper = new Grouper(results);

        //创建一个同步栅对象，它有五个参与者，5个参与者线程查询完成后
        //会调用grouper中的run方法，进行结果组合
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);

        //创建5个参与者对象，并且让他们各自在独自的线程中运行
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < searchers.length; i++) {
            searchers[i] = new Searcher(barrier, i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT,
                    matrixMock, results, PARTICIPANTS);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}
