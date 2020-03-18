package com.sxzhongf.deep.in.jvm.stage2;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * DirectMemoryOOM for test direct memory oom
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/3/18
 **/
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    /**
     * -Xmx20M -XX:MaxDirectMemorySize=10M
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }

    private static class OOMobj{}
}
