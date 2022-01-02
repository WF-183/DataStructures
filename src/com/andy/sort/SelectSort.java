package com.andy.sort;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/31
 * @version: 1.0.0
 */
public class SelectSort {//选择排序

    public static void main(String[] args) {

        //功能测试
        int[] arr = {5, 2, 1, 4, 3};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，选择排序需要2s左右，因为比冒泡少了很多temp变量交换操作，性能要比冒泡强。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        selectSort(arr2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void selectSort(int[] arr) {
        //总轮数
        for (int i = 0; i < arr.length - 1; i++) {
            //求极值 两个辅助变量
            int minIndex = i;
            int min = arr[i];
            //每轮比较次数
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    minIndex = j;
                    min = arr[j];
                }
            }

            //极值交换队首 相等时没必要
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

}
