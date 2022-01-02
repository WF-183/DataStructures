package com.andy.search;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class InsertValSearch {//插值查找

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        System.out.println(insetValSearch(arr, 0, arr.length - 1, 89));
    }

    /**
     * 注意:使用插值查找的前提是 该数组是有序的、等差的.
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insetValSearch(int[] arr, int left, int right, int findVal) {
        //递归出口
        //findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须加，下面要计算，防止输入极大数导致int溢出
        if (left >= right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        if (findVal < arr[mid]) {//目标值在左边
            return insetValSearch(arr, left, mid, findVal);
        } else if (findVal > arr[mid]) {//目标值在右边
            return insetValSearch(arr, mid + 1, right, findVal);
        } else {//目标值就是mid
            return mid;
        }
    }

}
