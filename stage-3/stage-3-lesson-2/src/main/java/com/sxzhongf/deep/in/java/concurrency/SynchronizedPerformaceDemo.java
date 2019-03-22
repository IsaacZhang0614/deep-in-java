package com.sxzhongf.deep.in.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 对比Vector & ArrayList
 * 查看synchronized对性能的影响
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class SynchronizedPerformaceDemo {
    public static void main(String[] args) {
        //对比Vector & ArrayList
        //相同点：两者都是使用数组（Array）作为存储，集合算法类似
        Vector vector = new Vector();
        ArrayList list = new ArrayList();

        addTest(vector);
        addTest(list);
        System.gc();
    }

    private static void addTest(Vector vector) {
        doTest(10000000, vector);
    }

    private static void addTest(ArrayList list) {
        doTest(10000000, list);
    }

    private static void doTest(int count, List list) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add(new Object());
        }

        long costTime = System.currentTimeMillis() - startTime;

        System.out.printf("%s add %s costs %s ms.\n", list.getClass().getName(), count, costTime);
    }
}
