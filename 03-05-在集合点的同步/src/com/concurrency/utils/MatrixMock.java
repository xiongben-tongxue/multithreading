package com.concurrency.utils;

import java.util.Random;

/**
 * @Classname MatrixMock
 * @Description 矩阵模拟类，随机生成0-9之间数字二维距
 * @Date 2019-02-12 14:44
 * @Created by wangdong
 */
public class MatrixMock {

    /**
     * 0-9之间数字二维矩阵
     */
    private int[][] data;

    /**
     * @Title MatrixMock
     * @Description 构造函数
     * @Param size 矩阵的行数
     * @Param length 每行的长度
     * @Param number 要查找的数字
     * @Return
     * @Date 2019-02-12 15:32
     * @Auther wangdong
     */
    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();
        //行
        for (int i = 0; i < size; i++) {
            //列
            for (int j = 0; j < length; j++) {
                //这个地方的目的是别说在[2][3]=随机数
                data[i][j] = random.nextInt(10);
                //如果这个随机数等于要查找的数
                if (data[i][j] == number){
                    counter++;
                }
            }
        }
        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n", counter, number);
    }

    /**
     * @Title getRow
     * @Description todo
 * @Param row
     * @Return int[]
     * @Date 2019-02-12 15:41
     * @Auther wangdong
     */
    public int[] getRow(int row){
        //row的值要对于0，且小于行数
        if (row >= 0 && row < data.length){
            return data[row];
        }
        return null;
    }
}
