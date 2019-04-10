package com.sxzhongf.deep.in.java.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程环境下 {@link ArrayBlockingQueue}
 *
 * 总结：
 * 使用{@link BlockingQueue} ，尽可能使用put，避免使用offer,最好不要用add
 * 否则会遇到N多坑
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ArrayBlockingQueueConcurrentDemo {
    public static void main(String[] args) throws InterruptedException {

        //最大允许2个元素在队列中
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        //申请2个大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (AtomicInteger i = new AtomicInteger(1); i.get() < 100; i.incrementAndGet()) {

            executorService.submit(() -> { //写入线程[1]
                // queue.offer(1); //不安全的

                try {
                    queue.put(i.get()); //安全方式
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

//        executorService.submit(() -> { //写入线程[2]
//            // queue.offer(2); //不安全的
//
//            try {
//                queue.put(2); //安全方式
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

            executorService.submit(() -> { //读取线程[1]
                try {
                    System.out.printf("序号 : [%d], 线程ID : [%s],线程名称 : [%s], 队列数据 : [%d] \n",
                            i.get(),
                            Thread.currentThread().getId(),
                            Thread.currentThread().getName(),
                            queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        //等待2秒钟
        executorService.awaitTermination(2, TimeUnit.SECONDS);

        //关闭线程池
        executorService.shutdown();

    }
}
