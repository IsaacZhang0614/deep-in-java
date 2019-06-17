package com.sxzhongf.deep.in.java.concurrency;

/**
 * synchronized 关键词讲解
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronizedAObjectAndClassDemo {

    private static Object object = new Object();

    public static void main(String[] args) {

        //对象锁
        synchronized (object) { //Monitor 只会 监视 object
            //Todo...
        }

        //类锁
        synchronized (Object.class) {
            //JVM中，类对象存在于 Metadata 区域（Java 8+）
            //或者Perm 区域（<=Java 7）

            //Todo...
        }

        /**
         * Class 对象本身就是 Object 实例
         */
        Object objectClass = Object.class;
    }

}
