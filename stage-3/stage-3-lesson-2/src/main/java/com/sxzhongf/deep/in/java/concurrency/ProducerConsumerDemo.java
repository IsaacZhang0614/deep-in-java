package com.sxzhongf.deep.in.java.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 生产消费模型DEMO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Container container = new Container();

        Future futureProducer = executorService.submit(() -> {
            container.producer();
        });

        Future futureConsumer = executorService.submit(() -> {
            container.consumer();
        });

        //线程等待方式1
        //这里会有问题，会将线程从并行转为串形执行
//        futureProducer.get();
//        futureConsumer.get();

        //线程等待方式2
        Thread.sleep(500L);


        executorService.shutdown();
    }

    public static class Container {
        private List<Integer> data = new LinkedList<>();
        private static final Integer MAX_SIZE = 5;
        private Random random = new Random();

        public void producer() {
            while (true) {
                try {
                    synchronized (this) {
                        while (data.size() >= MAX_SIZE) {
                            wait();
                        }
                        int value = random.nextInt(100);
                        System.out.printf("[线程[%s]]正在生产数据 : %d \n",
                                Thread.currentThread().getName(),
                                value);

                        data.add(value);

                        //唤起消费线程
                        notify();
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }

        public void consumer() {
            while (true) { //永久执行
                try {
                    synchronized (this) {
                        //当没有数据时，停止消费
                        while (data.isEmpty()) {
                            wait();
                        }
                        int value = data.remove(0);
                        System.out.printf("[线程[%s]]正在消费数据 : %d \n",
                                Thread.currentThread().getName(),
                                value);

                        notify();
                        Thread.sleep(1000);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
