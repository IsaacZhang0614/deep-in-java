package com.sxzhongf.deep.in.jvm.stage3;

/**
 * BigObjectGCAllocate for
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/3/22
 **/
public class BigObjectGCAllocate {

    private static final int _1M = 1024 * 1024;
    /**
     * -XX: PretenureSizeThreshold 指定大于该阈值的对象直接分配到老年代
     * 参数必须为字节整数,该设置只能应用于Serial 和 ParNew 新生代收集器
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728 (3M * 1024 * 1024)
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1M];
    }
}
