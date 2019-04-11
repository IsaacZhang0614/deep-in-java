package com.sxzhongf.deep.in.java.collection.interfaces;

/**
 * 排序接口
 * <p/>
 * 原地(在地)排序 In-Place
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public interface Sort<T extends Comparable<T>> {

    void sort(T[] values);

    /**
     * 接收可变参数
     */
    static <T> T[] of(T... values){
        return values;
    }
}
