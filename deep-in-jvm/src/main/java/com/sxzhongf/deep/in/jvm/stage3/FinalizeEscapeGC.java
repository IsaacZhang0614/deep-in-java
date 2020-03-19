package com.sxzhongf.deep.in.jvm.stage3;

/**
 * FinalizeEscapeGC for 测试如何通过finalize讨论被GC
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
