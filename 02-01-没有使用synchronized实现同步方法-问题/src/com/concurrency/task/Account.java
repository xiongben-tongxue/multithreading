package com.concurrency.task;

/**
 * @Classname Account
 * @Description 账户类，模拟一个银行账户
 * @Date 2019-02-11 15:40
 * @Created by wangdong
 */
public class Account {

    /**
     * 账户余额
     */
    private double balance;

    /**
     * 获取账户余额
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 设置账户余额
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 添加账户余额
     * @param amount
     */
    public void addAccount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }

    /**
     * 减少帐户余额
     *
     * @param amount 减少的余额
     */
    public void subtractAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp -= amount;
        balance = tmp;
    }
}
