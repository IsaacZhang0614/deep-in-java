package com.sxzhongf.deep.in.java.thread;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ThreadWaitAndNotifyDemo {

    //synchronized 监控对象
    private static Object monitor = Object.class;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ThreadWaitAndNotifyDemo::sayHello);
        t1.setName("T1");
        t1.start();

        Thread t2 = new Thread(ThreadWaitAndNotifyDemo::sayHello);
        t2.setName("T2");
        t2.start();

        /*
            调用Object#wait 的对象，和调用Object#notify的对象必须是同一对象
            因此以下调用为错误示范
            <blockquote><pre>
               synchronized(monitor){
                t1.notify();
                t2.notify();
               }
            </pre></blockquote>
         */
        Object monitor2 = Object.class;
        synchronized(monitor2){
            //为什么monitor#notify不能保证完全释放
//            monitor2.notify();
            monitor2.notifyAll();
        }

        //Thread#join(); 等同与 Object#wait()

        //

    }

    public static void sayHello() {
        Thread thread = Thread.currentThread();
        synchronized(monitor){
            try {
                System.out.printf("线程[%s] 进入等待状态...\n",thread.getName());
                /*
                 * Object#wait 与 Thread#join 看起来效果类似
                 * 实际上 Thread#join 方法就是调用了Thread对象集成自Object对象的wait(int)方法
                 */
                monitor.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("线程[%s] 恢复执行状态...\n",thread.getName());
        }
    }
}
