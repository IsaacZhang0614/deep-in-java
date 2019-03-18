package com.sxzhongf.deep.in.java.thread;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        //Thread 实现 Runnable
        //如果没有传递Runnable 对象实现，空执行

//        final Thread thread = new Thread(new Runnable() {
//            public void run() {
//                System.out.printf("线程[Id : %s]:Hello Yi23!\n", Thread.currentThread().getId());
//            }
//        });


        Thread thread = new Thread(ThreadDemo::print); //方法引用
        thread.start();//启动线程
        thread.join();//等待线程执行结束
        System.out.println("Hello Next...");
        System.out.println(thread.getState());
    }

    public static void print(){
        System.out.printf("线程[Id : %s]:Hello Lambda!\n", Thread.currentThread().getId());
    }


}
