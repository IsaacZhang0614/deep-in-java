package com.sxzhongf.deep.in.java.thread;

/**
 * Interrupt Demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ThreadInterruptDemo {

    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(ThreadInterruptDemo::sayHello);
        thread.start();

        //1. interrupt在主线程start方法调用之前，设置不会生效
        //2. main 线程interrupt 子线程
        //3. interrupt() 并不能终止线程，是用来传递interrupt 状态
        thread.interrupt();

        thread.join();//等待线程结束
    }

    public static void sayHello() {
        //第一种
//        if (Thread.currentThread().isInterrupted()){
//            System.out.println("线程被中止！");
//            return;
//        }

        //interrupt() 验证报错
        synchronized (object) {
            try {
                object.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.printf("当前线程[ID : %s] : Hello. Interrupt Status: %s",
                        Thread.currentThread().getId(),
                        Thread.currentThread().isInterrupted());//异常之后会清除状态

                return;
            }
        }
//        System.out.printf("当前线程[ID : %s] : Hello.", Thread.currentThread().getId());
    }

}
