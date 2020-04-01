package com.sxzhongf.deep.in.jvm.stage4;

import java.util.ArrayList;
import java.util.List;

/**
 * TestGcHeapUsingJConsole for Monitor memory
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/4/1
 **/
public class TestGcHeapUsingJConsole {

    static class OObject {

        // 对象大小 64KB
        private static byte[] OBJ_SIZE = new byte[64 * 1024];
    }

    /**
     * 填充堆内存
     */
    public static void fillHeap(int count) throws InterruptedException {
        List<OObject> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Thread.sleep(50);
            list.add(new OObject());
        }

        // 这里GC，不会释放内存，因为list对象还存活，fillHeap函数还处于作用域之内，因此它不会被回收
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        //这里才会回收
        //System.gc()
    }
}
