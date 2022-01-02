package com.andy.sort;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/1
 * @version: 1.0.0
 */
public class MergeSort {//归并排序

    public static void main(String[] args) {
        //功能测试
        int[] arr = {5, 2, 6, 7, 2, 10, 4, 11, 3, 9, 1, 5, 8};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，归并排序需要1ms左右，很强，借助了额外空间换时间temp ，性能要比 快排、希尔、直接插入、选择、冒泡都强。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //递归出口
        if (left >= right) {
            return;
        }
        //递归分解
        //对left-right段无限二分
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);

        //递归干活
        merge(arr, left, mid, right, temp);
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //三个辅助游标
        //把left-right段看成两个数组，左半边数组[left,mid] ，右半边数组[mid+1,right]
        int i = left;
        int j = mid + 1;
        int t = 0;

        //左右数组逐个元素比较，较小者逐渐放到temp，直到一边走到头
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //把没走到头的另一边元素全填充过去temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //temp拷贝覆写回指定arr段
        int copyI = left;
        int copyT = 0;
        while (copyI <= right) {
            arr[copyI] = temp[copyT];
            copyI++;
            copyT++;
        }

    }

}
