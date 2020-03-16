package com.sxzhongf.deep.in.jvm.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapOOM for testing heap oom
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/3/16
 **/
public class HeapOOM {

    static class OOMObject{}

    /**
     * -Xms20m -Xmx20m
     * -XX:+HeapDumpOnOutOfMemoryError 设置内存溢出时，dump出当前内存堆快照
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true)
            list.add(new OOMObject());
    }
}
