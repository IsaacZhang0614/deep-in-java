package com.sxzhongf.deep.in.java.thread.posix;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronizedDemo {

    //加锁和不加锁的区别
    // Lock == pthread_mutex_t
    static Lock lock = new ReentrantLock();
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        // Condition = pthread_cond_t
        Condition condition = lock.newCondition();
        // await() 和signal() 或signalAll()
        // 使用前提 ：Lock#lock()

        // Object#wait() 和Notify()或NotifyAll()
        // 使用前提：synchronized(object) -> 中
        synchronized(Object.class){
//            Object.class.wait();
        }

        Thread t1 = new Thread(SynchronizedDemo::addCounter);
        Thread t2 = new Thread(SynchronizedDemo::addCounter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void addCounter() {
        lock.lock(); //pthread_mutex_lock()
        System.out.println(getThreadPrefix() + "Before Counter : " + counter);
        counter++;
        System.out.println(getThreadPrefix() + "After Counter : " + counter);
        lock.unlock();// pthread_mutex_unlock()
    }

    private static String getThreadPrefix() {
        return "Thread[" + Thread.currentThread().getId() + "] : ";
    }
}
