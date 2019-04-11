package com.sxzhongf.deep.in.java.collection;

import com.sxzhongf.deep.in.java.collection.interfaces.Sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class QuickSortDemo<T extends Comparable<T>> implements Sort<T> {

    public static void main(String[] args) {
        Integer[] values = Sort.of(80, 29, 14, 60, 8, 33, 66, 65, 93, 55, 15);
        //Java7 Diamond 语法
        Sort<Integer> sort = new QuickSortDemo<>();
        sort.sort(values);
        System.out.println(Arrays.asList(values));
    }

    @Override
    public void sort(T[] values) {
        int n = values.length;
        int low = 0;
        int high = n - 1;

        // [3, 1, 2, 5, 4]
        // 中心轴 pivot = 4，以4为轴左右分割
        // => [(3, 1, 2), (4), (5)]
        // pIndex = 3
        // [0...2] = (3, 1, 2)
        // [3] = 4
        // [4] = 5

        // [0...2] = (3, 1, 2)
        // 接着第二次中心轴 pivot = 2，以2为轴左右分割
        // => [(1), (2) , (3)]
        // pIndex = 1
        // [0] = 1
        // [1] = 2(pivot)
        // [2] = 3

        // [0] = 1, [1] = 2, [2] = 3, [3] = 4, [4] = 5

        sort(values, low, high);


    }

    /**
     * 递归处理排序
     * 需要计算中心轴，也就是分片
     *
     * @param values 数组
     * @param low    低位索引
     * @param high   高位索引
     */
    private void sort(T[] values, int low, int high) {
        if (low < high) {
            // [3, 1, 2, 5, 4]
            // pivot = 4
            // pIndex = 第五位，也是4
            int pIndex = partition(values, low, high); //中心轴所在位置
            // 数组位数 [0...4]
            sort(values, low, pIndex - 1); //向左移动
            sort(values, pIndex + 1, high); //第二组向右移动
        }
    }

    private int partition(T[] values, int low, int high) {

        // [3, 1, 2, 5, 4]
        // pivot = 4
        //              -1
        // [0] = 3 < 4 (0)
        // [1] = 1 < 4 (1)
        // [2] = 2 < 4 (2)
        // [3] = 5 > 4 (3)
        // => [(3, 1, 2), (4), (5)]
        // pIndex = 3

        // 获取 pivot = values[high] 分割轴为每一次的高位数字
        T pivot = values[high];
        int i = low;

        for (int j = low; j < high; j++) {

            /**
             * {@link Integer} compare 结果是
             * {@code (x < y) ? -1 : ((x == y) ? 0 : 1);}
              */
            if (values[j].compareTo(pivot) < 1){ // means <=0

                T temp = values[i]; //取出低位数字
                values[i] = values[j]; // 将高位数值放入低位
                values[j] = temp; //低位数值放入高位

                i++;
            }
        }

        //最后交换中心轴值
        T temp = values[i];
        values[i] = values[high];
        values[high] = temp;

        return i;
    }
}
