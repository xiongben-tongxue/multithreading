package com.concurrency.task;

/**
 * 这个银行账户
 * 这个是对账户类做减少操作的
 * 实现了Runnable接口
 */
public class Bank implements Runnable {
    /**
     * 一个帐户
     */
    private Account account;

    /**
     * 构造函数
     *
     * @param account 银行帐户
     */
    public Bank(Account account) {
        this.account = account;
    }



    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //减少帐户余额
            this.account.subtractAmount(1000);
        }
    }
}
