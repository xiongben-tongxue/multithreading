package com.concurrency.task;

import java.util.concurrent.Phaser;

/**
 * @Classname MyPhaser
 * @Description 线程阶段类，控制线程阶段的改变
 * @Date 2019-02-12 19:19
 * @Created by wangdong
 */
public class MyPhaser extends Phaser {

    /**
     * 重写控制线程阶段的方法
     * @param phase
     * @param registeredParties
     * @return
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    /**
     * 从0->1阶段改变时调用这个方法
     * 总是返回false，表明还要断续执行
     * @return
     */
    private boolean studentArrived(){
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        //可以看到有多少个学生到了
        System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
        return false;
    }

    /**
     * 从1->2阶段改变时调用这个方法
     *
     * @return 总是返回false，表明还要断续执行
     */
    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students has finished the first exercise.\n");
        System.out.printf("Phaser: It's turn for the second one.\n");
        return false;
    }

    /**
     * 从2->3阶段改变时调用这个方法
     *
     * @return 总是返回false，表明还要断续执行
     */
    private boolean finishSecondExercise() {
        System.out.printf("Phaser: All the students has finished the second exercise.\n");
        System.out.printf("Phaser: It's turn for the third one.\n");
        return false;
    }

    /**
     * 从3->4阶段改变时调用这个方法
     *
     * @return 总是返回true，表明执行完了
     */
    private boolean finishExam() {
        System.out.printf("Phaser: All the students has finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }
}
