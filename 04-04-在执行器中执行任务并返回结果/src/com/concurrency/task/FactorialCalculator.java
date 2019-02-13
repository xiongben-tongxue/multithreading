package com.concurrency.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Classname FactorialCalculator
 * @Description 阶乘计算类 实现Callable接口，并且参数化为Integer类型
 * @Date 2019-02-13 10:00
 * @Created by wangdong
 */
public class FactorialCalculator implements Callable<Integer> {

    /**
     * 生成一个私有的，类型为Integer，名为number的属性，用来存储任务将要计算的数
     */
    private Integer number;

    /**
     * 构造函数，初始化
     * @param number
     */
    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    /**
     * 核心方法，返回阶乘计算的结果
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int num,result;
        num = number;
        result = 1;

        //如果数是1或0，则返回1。否则，计算这个数的阶乘。
        //为了演示效果，在两次乘之间，令这个任务睡眠20毫秒
        if (num == 0 || num == 1){
            return 1;
        }else {
            for (int i = 2; i < number; i++) {
                //阶乘等价于result = i*i。乘后赋值
                result *= i;
                TimeUnit.MICROSECONDS.sleep(20);
            }
        }

        // 操作结果的信息写入控制台。
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
        //返回操作结果
        return result;
    }
}
