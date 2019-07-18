package com.sxzhongf.daily.question.july;

/**
 * Ask20190718_ParentAndChildThread for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/18
 */
public class Ask20190718_ParentAndChildThread {

    private static boolean initialized = false;

    static {
        Thread t = new Thread(() -> initialized = true);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(initialized);
        // 主程序会一直等待子程序结束。等不到就一直挂起
    }

}
