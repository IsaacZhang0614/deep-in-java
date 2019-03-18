package com.sxzhongf.deep.in.java.thread;

import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class HowToStopThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        StoppedAction action = new StoppedAction();
        Thread thread = new Thread(action);

        //线程启动，并不一定会执行
        thread.start();

        //main 线程调整stopped 状态为 true
        action.Stopped();

        //如果是线程池，以上线程可能会出现问题
        Executors.newFixedThreadPool(2);//有且只有2个线程在复用
        Executors.newCachedThreadPool(); //几乎是无线数量线程

        //thread.join();
    }

    private static class StoppedAction implements Runnable {

        /**
         * 确保stopped 原子操作，等价于AtomicBoolean
         */
        private volatile boolean stopped;

        @Override
        public void run() {
            if (stopped) {
                //第三种，通过抛出异常方式，可以kill掉当前线程
                System.out.println("Action 终止...");
                return;
            }
            System.out.println("Action 执行...");
        }

        /**
         * 设置状态
         */
        private void Stopped() {
            this.stopped = true;
        }
    }

}


