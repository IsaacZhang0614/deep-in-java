package com.sxzhongf.deep.in.jvm.stage4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * MonitorThread for 线程监控demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/4/1
 **/
public class MonitorThread {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        new Thread(() -> {
            while (true) {
                ;
            }
        }, "busy-thread-demo").start();
    }

    /**
     * 锁等待演示
     */
    public static void createLockThread(final Object lockObj) {
        new Thread(() -> {
            synchronized (lockObj) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "locked-thread-demo").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        createBusyThread();
        reader.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }
}
