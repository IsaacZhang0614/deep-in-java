package com.sxzhongf.deep.in.java.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 通过{@link ForkJoinPool}实现快速排序
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @see
 * @since
 */
public class QuickSortForkJoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Integer[] values = new Integer[]{80, 29, 14, 60, 8, 33, 66, 65, 93, 55, 15};

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new QuickSortTask(values));
        pool.awaitTermination(1, TimeUnit.SECONDS);
        pool.shutdown();

        System.out.println(Arrays.asList(values));
    }


    private static class QuickSortTask extends RecursiveAction {

        // 设定阈值
        private final int THRESHOLD = 4;

        private final Integer[] partValues;
        private final int low_index;
        private final int high_index;


        /**
         * 第一次初始化Task
         */
        private QuickSortTask(Integer[] partValues) {
            this.partValues = partValues;
            this.low_index = 0;
            this.high_index = partValues.length - 1;
        }

        private QuickSortTask(Integer[] partValues, int low_index, int high_index) {
            this.partValues = partValues;
            this.low_index = low_index;
            this.high_index = high_index;
        }


        @Override
        protected void compute() {

            //范围低于阈值时，直接排序
            if (high_index - low_index < THRESHOLD) {
                sort(partValues, low_index, high_index);
            } else {
                int pivot = partition(partValues, low_index, high_index);
                QuickSortTask task_left = new QuickSortTask(partValues, low_index, pivot - 1);
                QuickSortTask task_right = new QuickSortTask(partValues, pivot + 1, high_index);

                task_left.fork().join();
                task_right.fork().join();
            }

        }

        /**
         * 获取分区索引
         *
         * @param values 数组对象
         * @param low    低位索引
         * @param high   高位索引
         * @return 分区索引
         */
        private int partition(Integer[] values, int low, int high) {

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
            Integer pivot = values[high];
            int i = low;

            for (int j = low; j < high; j++) {

                /**
                 * {@link Integer} compare 结果是
                 * {@code (x < y) ? -1 : ((x == y) ? 0 : 1);}
                 */
                if (values[j].compareTo(pivot) < 1) { // means <=0

                    Integer temp = values[i]; //取出低位数字
                    values[i] = values[j]; // 将高位数值放入低位
                    values[j] = temp; //低位数值放入高位

                    i++;
                }
            }

            //最后交换中心轴值
            Integer temp = values[i];
            values[i] = values[high];
            values[high] = temp;

            return i;
        }

        private void sort(Integer[] partValues, int low_index, int high_index) {
            Arrays.sort(partValues, low_index, high_index + 1);
        }
    }
}
