package com.andy.sort;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class RadixSort {//基数排序 桶排序


    public static void main(String[] args) {
        //功能测试
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，基数排序需要0ms左右，很强，借助了大量额外空间换时间 ，性能要比 归并、快排、希尔、直接插入、选择、冒泡都强。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr );
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void radixSort(int[] arr) {

        //求极值 处理轮数==最大元素值位数
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        int maxValueLength = (maxValue + "").length();


        //辅助二维数组 充当十个桶
        //每个桶都有可能放入所有元素，长度需要设为排序数组长度
        int[][] buckets = new int[10][arr.length];
        //辅助一维数组 充当每个桶中放入元素游标
        int[] bucketIndexArr = new int[10];


        //每一轮对应一位 从低到高
        for (int i = 0; i < maxValueLength; i++) {
            //原数组放到十个桶
            for (int j = 0; j < arr.length; j++) {
                int wei = arr[j] / (int)Math.pow(10, i) % 10;
                buckets[wei][bucketIndexArr[wei]] = arr[j];
                bucketIndexArr[wei]++;
            }
            //十个桶放回原数组 二维数组遍历
            int index = 0;
            for (int l = 0; l < buckets.length; l++) {
                //根据元素游标取出bucket本轮放入的元素，游标以后的不关注也不清除
                int bucketIndex = bucketIndexArr[l];
                for (int m = 0; m < bucketIndex; m++) {
                    arr[index] = buckets[l][m];
                    index++;
                }
                //每个bucket游标位置清零
                bucketIndexArr[l] = 0;
            }
        }
    }
}
