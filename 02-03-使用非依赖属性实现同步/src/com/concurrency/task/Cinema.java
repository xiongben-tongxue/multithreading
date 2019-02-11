package com.concurrency.task;

/**
 * @Classname Cinema
 * @Description 影视院类，有两个电影院
 * @Date 2019-02-11 16:45
 * @Created by wangdong
 */
public class Cinema {

    /**
     * 保存影院厅1的剩余电影票数
     */
    private long vacanciesCinema1;
    /**
     * 保存影院厅2的剩余电影票数
     */
    private long vacanciesCinema2;

    /**
     * 控制vacanciesCinema1同步访问的对象
     */
    private final Object controlCinema1;
    /**
     * 控制 vacanciesCinema2同步访问的对象
     */
    private final Object controlCinema2;

    public Cinema() {
        controlCinema1 = new Object();
        controlCinema2 = new Object();
        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
    }

    /**
     * 下面的synchronized
     * 是针对的是锁住同步访问的对象
     */
    /**
     * 出售影院厅1的门票
     * @param number 出售的门票张数
     * @return true出售成功，false出售失败
     */
    public boolean sellTickets1(int number){
        synchronized (controlCinema1){
            if (number < vacanciesCinema1){
                vacanciesCinema1 -= number;
                return true;
            }else {
                return false;
            }
        }

    }

    /**
     * 出售影院厅2的门票
     * @param number
     * @return
     */
    public boolean sellTickets2(int number){
        synchronized (controlCinema2){
            if (number < vacanciesCinema2){
                vacanciesCinema2 -= number;
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * 向影院1退门票
     * @param number
     * @return
     */
    public boolean returnTickets1(int number){
        synchronized (controlCinema1){
            vacanciesCinema1 += number;
            return true;
        }
    }

    /**
     * 向影院2退门票
     * @param number
     * @return
     */
    public boolean returnTickets2(int number){
        synchronized (controlCinema2){
            vacanciesCinema2 += number;
            return true;
        }
    }

    /**
     * @Description 获取影院1剩余的门票数
     * @Param: []
     * @Return: long
     * @Date 2019-02-11 17:03
     * @Auther wangdong
     */
    public long getVacanciesCinema1(){
        return vacanciesCinema1;
    }

    /**
     * @Description 获取影院2剩余的门票数
     * @Param: []
     * @Return: long
     * @Date 2019-02-11 17:04
     * @Auther wangdong
     */
    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}
