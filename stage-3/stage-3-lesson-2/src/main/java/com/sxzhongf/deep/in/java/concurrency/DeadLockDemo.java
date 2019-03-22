package com.sxzhongf.deep.in.java.concurrency;

/**
 * 死锁演示
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class DeadLockDemo {
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o1)

            {
                System.out.printf("First thread [ID : %d] holds m1.\n", Thread.currentThread().getId());
                synchronized (o2) {

                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o2) {
                System.out.printf("Second thread [ID : %d] holds m2.\n", Thread.currentThread().getId());
                synchronized (o1) {

                }
            }
        });
    }
}
