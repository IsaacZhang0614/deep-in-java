package com.sxzhongf.deep.in.java.collection;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        String[] strings1 = new String[]{"Hello", "Isaac"};
        arrayCopy(strings1, strings1);

        int value = Integer.MAX_VALUE;

        System.out.println(value + 1);
        System.out.println(value + 1 == Integer.MIN_VALUE + 1);
        System.out.println(value + 2 == Integer.MIN_VALUE + 2);

        // int 在Java中只有4个字节（32位）
        // OS 有32位（4字节）以及64位（8字节）
        // long 和double是非线程安全的原因，是两个4字节，分为高位和低位
        // Java 中默认没有正整数（无符号整数）

        // 判断条件一般使用

        List<String> values = List.of();
        // {@code} values.size() < 1 而少使用 values.size() == 0
    }

    /**
     * 数组设置为Object的原因
     */
    private static void arrayCopy(Object[] source, Object[] destination) {

    }
}

