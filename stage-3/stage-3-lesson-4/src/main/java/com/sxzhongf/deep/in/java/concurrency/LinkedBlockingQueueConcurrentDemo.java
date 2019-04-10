package com.sxzhongf.deep.in.java.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程环境下 {@link LinkedBlockingQueue}
 * 总结：
 * 使用{@link BlockingQueue} ，尽可能使用put，避免使用offer,最好不要用add
 * 否则会遇到N多坑
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class LinkedBlockingQueueConcurrentDemo {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        //申请2个线程大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        AtomicInteger a = new AtomicInteger(1);
        for (AtomicInteger i = new AtomicInteger(1); i.get() < 100; i.incrementAndGet()) {
            executorService.submit(() -> {
                try {
                    queue.put(i.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            executorService.submit(()->{
                try {
                    System.out.printf("序号 : [%d], 线程ID : [%s],线程名称 : [%s], 队列数据 : [%d] \n",
                            a.incrementAndGet(),
                            Thread.currentThread().getId(),
                            Thread.currentThread().getName(),
                            queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
