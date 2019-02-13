package com.concurrency.utils;

/**
 * @Classname FileMock
 * @Description 文件模拟类
 * @Date 2019-02-12 10:07
 * @Created by wangdong
 */
public class FileMock {

    /**
     * 模拟文件的内容
     */
    private String[] content;

    /**
     * 当前需要处理的文件第多少行
     */
    private int index;

    /**
     * @Description 构造函数，随机生产文件的内容
     * @Param: [size, index]
     * @Return:
     * @Date 2019-02-12 10:09
     * @Auther wangdong
     */
    public FileMock(int size, int length) {
        this.content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                builder.append((char) indice);
            }
            content[i] = builder.toString();
        }
        this.index = 0;
    }

    /**
     * @Description 判断是否还有文件的行数需要处理
     * @Param: []
     * @Return: boolean
     * @Date 2019-02-12 10:18
     * @Auther wangdong
     */
    public boolean hasMoreLines(){
        return this.index<this.content.length;
    }

    /**
     * @Description 返回下一行的文件内容
     * @Param: []
     * @Return: java.lang.String
     * @Date 2019-02-12 10:20
     * @Auther wangdong
     */
    public String getLine(){
        if (this.hasMoreLines()){
            System.out.println("Mock: " + (this.content.length - this.index));
            return this.content[this.index++];
        }
        return null;
    }
}
