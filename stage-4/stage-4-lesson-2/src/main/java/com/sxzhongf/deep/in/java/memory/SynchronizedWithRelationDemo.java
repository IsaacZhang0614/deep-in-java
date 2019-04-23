package com.sxzhongf.deep.in.java.memory;

/**
 * synchronized-with relation demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronizedWithRelationDemo {
    //声明一个监视器锁
    private static final Object lock = new Object();

    //声明一个共享变量
    private static int sharedData;

    private static void threadStart()
    {
        Thread thread = new Thread(()->{

        });

        // start() 必然在 run()方法之前执行，因为start()会在JVM中回调 run()方法
        thread.start();
    }

    public static void main(String[] args) {

    }

    /**
     * 改变共享变量值
     *
     * @param data
     */
    private static void changeData(int data){

        // T1 和 T2线程进入
        // T1 获得锁
        // T1 -> lock -> unlock
        // T3 线程进入
        // T2 和 T3 线程竞争
        // T3 获得锁
        // T1#unlock -> T3#lock -> T3#unlock

        synchronized (lock){
            sharedData = data;
        }
    }


}
