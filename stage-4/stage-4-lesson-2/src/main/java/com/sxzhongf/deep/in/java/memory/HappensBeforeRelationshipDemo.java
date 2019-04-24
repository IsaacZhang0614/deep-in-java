package com.sxzhongf.deep.in.java.memory;

/**
 * what is the Happens-before order?
 * Happens-Before relationship
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class HappensBeforeRelationshipDemo {
    public static void main(String[] args) {

    }

    /**
     * first situation
     */
    private static void inSameThread(){
        // #1 : Thread1
        // #2 : Thread2
    }

    /**
     * second situation
     */
    private static void constructorHappensBeforeFinalizer(){
        // 构造 早于 销毁
        // 构造对象是在用户线程（main、子线程）中执行
        // Finalizer线程 操作是JVM线程（GC线程）中执行
        // 对象存放在Heap中。Heap对于线程是共享的
        // 假设Object刚创建，Finalizer 线程刚看到该对象，立刻回收，你会怎么认为？骂人是吧
    }

    private static void threadJoinMethod() throws InterruptedException {
        Thread t = new Thread(()->{

        });

        t.start();

        // main 线程调用 子线程t的join方法，在join方法返回之前，t 线程的l其他所有Actions都会执行完成
        t.join();
    }
}
