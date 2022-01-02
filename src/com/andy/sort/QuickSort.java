package com.andy.sort;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/31
 * @version: 1.0.0
 */
public class QuickSort {//快排

    public static void main(String[] args) {

        //功能测试
        int[] arr = {5, 2, 6, 7, 2, 10, 4, 11, 3, 9, 1, 5, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，快速排序需要20ms左右，递归优化了 冒泡排序需要交换太多次问题 ，性能要比 直接插入、选择、冒泡强，跟希尔差不多。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (arr == null || arr.length == 0 || startIndex >= endIndex) {
            //入参校验&递归出口
            return;
        }

        //一轮递归完成一个基准数的左右处理和自身放置，使得基准数左边都比它小，右边都比它大
        //约定首元素为基准数
        int baseVal = arr[startIndex];
        int cursorL = startIndex;
        int cursorR = endIndex;
        //一、左右处理
        //以基准数为标准，左右游标不断靠近，不断找一对对满足的互换，直到左右游标相遇，必然相遇。
        while (cursorL != cursorR) {
            //注意，约定首元素为基准数，这里必须先找cursorR，反了是不对的
            //注意，=是必须的，否则cursorL无法出发
            //注意，cursorR大于cursorL是必须的，禁止游标交错，cursorL永远不可能大于cursorR
            //右边比基准数大的忽略，继续找，找到一个满足的停下来
            while (arr[cursorR] >= baseVal && cursorL < cursorR) {
                cursorR--;
            }
            //左边比基准数小的忽略，继续找，找到一个满足的停下来
            while (arr[cursorL] <= baseVal && cursorL < cursorR) {
                cursorL++;
            }
            //互换
            //优化 相遇点没必要互换了
            if (cursorL != cursorR) {
                int temp = arr[cursorL];
                arr[cursorL] = arr[cursorR];
                arr[cursorR] = temp;
            }
            //把以这个基准数为标准的一对对互换完出外层循环
        }

        //二、自身放置
        //左右游标相遇后把相遇点和基准点元素互换，完成本轮所有操作，效果 基础数放到了相遇点，其左边元素都比它小，右边元素都比它大，不关注等于
        arr[startIndex] = arr[cursorL];
        arr[cursorL] = baseVal;

        //三、下一轮递归 互换后的相遇点cursorL=cursorR就是基准数位置，以此点将数组切成左右两段，分别递归
        quickSort(arr, startIndex, cursorL - 1);
        quickSort(arr, cursorL + 1, endIndex);
    }

}
