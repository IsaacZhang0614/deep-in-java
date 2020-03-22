package com.sxzhongf.deep.in.jvm.stage3;

/**
 * GCAllocate2Old for
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/3/22
 **/
public class GCAllocate2Old {
    private static final int _1M = 1024 * 1024;
    /**
     * -XX: MaxTenuringThreshold 指定从新生代到老年代的Age阈值，默认为15
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3;
        allocation1 = new byte[ _1M / 4];
        allocation2 = new byte[4 * _1M];
        allocation3 = new byte[4 * _1M];
        allocation3 = null;
        allocation3 = new byte[4 * _1M];
    }
}
