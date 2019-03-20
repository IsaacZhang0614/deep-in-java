package com.sxzhongf.deep.in.java.concurrency;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronizedDemo {
    public static void main(String[] args) {

        synchronized(SynchronizedDemo.class){

        }

    }


    private static void changeValue(int value) {

        // 线程私有对象，尽管它也在堆（Heap）中
        // 栈(stack)保存value的名称，data 变量名称
        // 堆共享(被其他线程可见)是线程不安全的，保存在内存
        // 当线程不加以控制数量的话，容易导致内存泄漏(OOM->Out Of Memory)

        Data data = new Data();
        data.setValue(1);
    }

    private static class Data {

        private volatile int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
