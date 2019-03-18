package com.sxzhongf.deep.in.java.thread;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class DumpThreadDemo {
    public static void main(String[] args) {

        //Throwable API
        new Throwable("Stack trace1").printStackTrace();

        //Thread API
        Thread.dumpStack();

        //Java 9 Stack 实现
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.forEach(System.out::println);
    }
}
