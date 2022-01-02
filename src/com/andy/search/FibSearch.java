package com.andy.search;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class FibSearch {//斐波那锲查找

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(Arrays.toString(getFibArr(9)));
        System.out.println(fibSearch(arr, 1234));
    }

    public static int[] getFibArr(int sortArrlength) {
        //判断所需斐波那契数组长度
        int f1 = 1;
        int f2 = 1;
        int maxIndex = 1;
        while (true) {
            int f3 = f2 + f1;
            f1 = f2;
            f2 = f3;
            maxIndex++;

            if (f2 >= sortArrlength) {
                break;
            }
        }
        int length = maxIndex + 1;
        int[] fibArr = new int[length];

        //填充斐波那契数组元素
        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int j = 2; j < length; j++) {
            fibArr[j] = fibArr[j - 1] + fibArr[j - 2];
        }
        return fibArr;
    }

    public static int fibSearch(int[] arr, int findVal) {
        int left = 0;
        int rihgt = arr.length - 1;

        //获取待排序数组所需长度的菲波那切数列 要求菲波那切数列尾元素值 大于等于 待排序数组长度
        int[] fibArr = getFibArr(arr.length);

        //菲波那切数列的最后一个元素下标
        int k = fibArr.length - 1;

        //因为菲波那切数列尾元素值fibArr[k] 可能大于 待排序数组长度arr.length，所以复制一个数组，长度等于fibArr[k]，不足段用待排序数组最后一个值填充
        int[] temp = Arrays.copyOf(arr, fibArr[k]);
        for (int i = rihgt + 1; i < temp.length; i++) {
            temp[i] = arr[rihgt];
        }

        while (left < rihgt) {
            //分段点
            int mid = left + fibArr[k - 1];

            if (findVal < temp[mid]) {//在左半部分
                left = left;
                rihgt = mid;
                //改变k，使得mid以斐波那契数列一个个元素值为步长变化，实现黄金分割效果
                k = k - 1;
            } else if (findVal > temp[mid]) {//在右半部分
                left = mid + 1;
                rihgt = rihgt;
                //改变k，使得mid以斐波那契数列一个个元素值为步长变化，实现黄金分割效果
                k = k - 2;
            } else {//找到，返回下标即可
                //因为temp进行了不足段填充，所以index需要多个判断
                if (mid < arr.length) {
                    return mid;
                } else {
                    return arr.length - 1;
                }
            }
        }

        return -1;
    }

}
