package com.sxzhongf.deep.in.java.concurrency;

/**
 * 方法和块
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronizedMethodAndBlock {


    public static void main(String[] args) {
        /**
         * Important : 重进入(Reentrant)是针对 线程 而言的
         * 不是针对于监视器（Monitor）
         * 重进入的意思是：
         * synchronized 保证互斥
         * 当某个线程获得锁(1),每重新遇到synchronized（+1）依次类推
         * 如下：object
         */


        /**
         * Q : Echo 方法到底重进入了几次？
         * A : echo -> PrintStream#println -> newline() 3次
         */
        echo("Hello concurrency!");

        /**
         * Q : doEcho 方法到底重进入了几次？
         * A : doEcho -> echo -> PrintStream#println -> newline() 4次
         */
        doEcho("Hello synchronized!");
    }

    /**
     * synchronized block
     */
    private static void doEcho(String message) {
        //Monitor 不是必须为当前类（SynchronizedMethodAndBlock.class）

        /**
         * Etc.
         * @<code>synchronized (SynchronizedMethodAndBlock.class) {
         *  todo...
         * }
         * </code>
         */
        Object object = new Object();
        synchronized (object) {
            echo(message);
        }
    }

    /**
     * synchronized method (实例或类的方法)
     */
    private synchronized static void echo(String message) {
        System.out.println("synchronized method exec : " + message);
    }
}
