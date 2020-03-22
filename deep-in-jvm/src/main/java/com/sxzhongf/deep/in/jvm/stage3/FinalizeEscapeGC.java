package com.sxzhongf.deep.in.jvm.stage3;

/**
 * FinalizeEscapeGC for 测试如何通过finalize讨论被GC
 *
 * -XX:+PrintGC  查看 gc 基本信息
 * -XX:+PrintGCDetails 查看 gc 详细信息
 * -XX:+PrintHeapAtGC 查看 gc 前后堆、方法区可用容量变化
 * -XX:+PrintGCApplicationConcurrentTime 查看GC过程中用户线程并发时间以及停顿时间
 * -XX:+PrintAdaptiveSizePolicy 查看收集器Ergonomics机制（自动设置堆空间各分代区域大小、
 *                              收集目标等内容）
 * -XX:+PrintTenuringDistribution 查看熬过收集后剩余对象的年龄分布信息
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/3/19
 **/
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC HOOK = null;

    public void isAlive() {
        System.out.println("我还存活着呢～～");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize 我被调用了哦～");
        FinalizeEscapeGC.HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        HOOK = new FinalizeEscapeGC();

        // 第一次不可触达
        HOOK = null;
        System.gc();
        // 因为要等待finalizer的执行线程执行，因为它优先级低，等待其执行
        Thread.sleep(500);

        if (HOOK != null) {
            HOOK.isAlive();
        } else {
            System.out.println("我被回收了。。。");
        }

        // 第二次不可触达，和上述代码一模一样
        HOOK = null;
        System.gc();
        // 因为要等待finalizer的执行线程执行，因为它优先级低，等待其执行
        Thread.sleep(500);

        if (HOOK != null) {
            HOOK.isAlive();
        } else {
            System.out.println("我被回收了。。。");
        }
    }
}
