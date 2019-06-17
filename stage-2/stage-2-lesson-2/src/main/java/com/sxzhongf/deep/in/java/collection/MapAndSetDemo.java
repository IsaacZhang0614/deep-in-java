package com.sxzhongf.deep.in.java.collection;

import java.util.*;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class MapAndSetDemo {
    public static void main(String[] args) {

        // Set 是 Map 的key实现，Set底层运用了Map实现
        // 比如HashSet 底层运用了HashMap实现
        Map<String, Object> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        /**
         *     public TreeSet() {
         *         this(new TreeMap<>());
         *     }
         */
        map = new TreeMap<>();
        set = new TreeSet<>();
    }
}
