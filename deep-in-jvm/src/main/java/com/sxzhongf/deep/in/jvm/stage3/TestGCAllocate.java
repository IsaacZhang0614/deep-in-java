package com.sxzhongf.deep.in.jvm.stage3;

/**
 * TestGCAllocate for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020 /3/22
 */
public class TestGCAllocate {

    private static final int _1M = 1024 * 1024;

    /**
     * -Xms10m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        // 因为young 总共10M，eden:8M
        // 因此不足以分配4M,会Minor GC 一次
        // 因为survivor：from 和 to 都是1M，不足以放下6M，
        // 所以上述3个存活对象直接移到老年代
        allocation4 = new byte[4 * _1M];
        /**
         * [GC (Allocation Failure) [PSYoungGen: 7940K->488K(9216K)] 7940K->6824K(17408K), 0.0031771 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (Ergonomics) [PSYoungGen: 488K->0K(9216K)] [ParOldGen: 6336K->6743K(10240K)] 6824K->6743K(19456K), [Metaspace: 3187K->3187K(1056768K)], 0.0049988 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * Heap
         *  PSYoungGen      total 9216K, used 4446K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         *   eden space 8704K, 51% used [0x00000000ff600000,0x00000000ffa579f8,0x00000000ffe80000)
         *   from space 512K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x00000000fff00000)
         *   to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
         *  ParOldGen       total 10240K, used 6743K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
         *   object space 10240K, 65% used [0x00000000fec00000,0x00000000ff295f40,0x00000000ff600000)
         *  Metaspace       used 3213K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 348K, capacity 388K, committed 512K, reserved 1048576K
         */
    }
}
