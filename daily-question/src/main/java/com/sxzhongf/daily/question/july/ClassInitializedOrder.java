package com.sxzhongf.daily.question.july;

/**
 * ClassInitializedOrder for : Java Classload Order Test
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/7/20
 */
public class ClassInitializedOrder {
    private static boolean initialized = false;
    static {
        println("static 代码块执行。");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //println("Runnable 代码块执行。");
                System.out.println("Runnable 代码块执行。");
                //initialized = true;
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        println("main 函数执行。");
        System.out.println("initialized = " + initialized);
    }

    private static void println(Object o){
        System.out.println(o);
    }
}
