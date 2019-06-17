package com.sxzhongf.deep.in.java.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * 多线程环境下 {@link SynchronousQueue}
 * SynchronousQueue 主要适用于互斥场景
 * 总结：
 * 使用{@link BlockingQueue} ，尽可能使用put，避免使用offer,最好不要用add
 * 否则会遇到N多坑
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronousQueueConcurrentDemo {
    public static void main(String[] args) throws InterruptedException {

        // SynchronousQueue put()之后，必须被其他线程take()/移除掉
        // SynchronousQueue 必须使用put(),不能使用offer(),否则会发生死锁
        BlockingQueue<Integer> sQueue = new SynchronousQueue<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            try {
                System.out.printf("线程ID : [%s],线程名称 : [%s], 插入线程 \n",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName());
                sQueue.put(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                System.out.printf("线程ID : [%s],线程名称 : [%s], 队列数据 : [%d] \n",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        sQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

        // sQueue.add(1); //会报错。因为必须得消费
        //sQueue.put(1); //会阻塞当前线程，因为必须得消费


    }


}
