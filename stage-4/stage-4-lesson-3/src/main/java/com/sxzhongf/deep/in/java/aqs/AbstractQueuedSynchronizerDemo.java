package com.sxzhongf.deep.in.java.aqs;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link AbstractQueuedSynchronizer} demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class AbstractQueuedSynchronizerDemo {

    //添加非公平锁
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(AbstractQueuedSynchronizerDemo::action); //thread-1
        executorService.submit(AbstractQueuedSynchronizerDemo::action); //thread-2
        executorService.submit(AbstractQueuedSynchronizerDemo::action); //thread-3

        //非公平锁
        //thread-1 unlock -> release ->unpark thread-2 -> thread-2 tryAcquire
        //此时thread-4 or thread-5 入队竞争
        //thread-4 -> lock ->tryAcquire

        //PS: unpark = LockSupport.unpark

        //公平锁
        //thread-1 unlock ->unpark thread-2 ->thread-2 tryAcquire
        //thread-2 lock ->...
        //thread-3 wait
        //thread-4 wait
        //thread-5 wait

        //等待200秒
        executorService.awaitTermination(200, TimeUnit.SECONDS);
        //关闭线程池
        executorService.shutdown();
    }

    private static void action() {
        System.out.printf("当前线程[%s],正在等待您的输入.\n", Thread.currentThread().getName());

        try {
            //利用 ReentrantLock 作为AQS的实现，理解内部数据结构
            lock.lock();
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
