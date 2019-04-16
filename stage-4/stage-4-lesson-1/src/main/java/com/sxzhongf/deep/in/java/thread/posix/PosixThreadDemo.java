package com.sxzhongf.deep.in.java.thread.posix;

/**
 * study posix thread
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class PosixThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        //创建java线程
        Thread t1 =  new Thread(PosixThreadDemo::HelloPosix);

        //显示启动线程
        t1.start(); //pthread_create()

        //等待线程执行结束
        t1.join(); //pthread_join()

        //对应C++中指令代码如下
        //pthread_t t1; //pthread_t 为线程类型
        //pthread_create(&t1,NULL,HelloPosix,NULL);
        //pthread_join(t1,NULL);
    }

    static void HelloPosix(){
        System.out.println("Hello Posix Thread.");
    }
}
