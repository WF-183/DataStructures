package com.andy.sort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/31
 * @version: 1.0.0
 */
public class BubbleSort {//冒泡排序

    /**
     *
     桶排序 0ms> 归并 1ms，借助额外空间，借助的越多就越快，
     >
     堆排序 12ms> 快排 20ms ，堆排序不用递归，
     >
     希尔 20ms>直接插入 600ms，希尔是对直接插入的改进，
     >
     >选择 2s、冒泡 10s ，O(n^2) 最慢
     * @param args
     */
    public static void main(String[] args) {

        //功能测试
        int[] arr = new int[] {5, 3, 1, 2, 4};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，冒泡排序需要10s左右，算是比较慢。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void bubbleSort(int[] arr) {
        boolean swapFlag = false;
        int temp = 0;
        //总轮数
        for (int i = 0; i < arr.length - 1; i++) {
            //每轮比较次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //交换 从左到右，两两比较，大的放后面；要大到小还是小到大改一个符号即可；
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapFlag = true;
                }
            }

            //优化
            if (swapFlag) {
                //本轮存在交换则进入下一轮 清空标志位
                swapFlag = false;
            } else {
                //本轮不存在任何交换则直接全部结束
                break;
            }
        }
    }

}
