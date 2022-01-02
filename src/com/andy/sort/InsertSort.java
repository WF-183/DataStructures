package com.andy.sort;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/31
 * @version: 1.0.0
 */
public class InsertSort {//插入排序

    public static void main(String[] args) {

        //功能测试
        int[] arr = {5, 2, 1, 4, 3};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，插入排序需要600ms左右，因为左部分有序 所以比到一个数满足就不用继续往左了 可以少比很多次，性能要比选择、冒泡强。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        insertSort(arr2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }


    //图示：
    //1 2 3 10 11 12 4    、、、
    //1 2 3    10 11 12   、、、
    //      4
    public static void insertSort(int[] arr) {
        //从第二个元素开始
        for (int i = 1; i < arr.length; i++) {
            //抽一格值 空出来一格位置
            int outValue = arr[i];
            //有序表的右边界
            int compareIndex = i - 1;

            //优化 若抽出来的这一格比左边所有都大，没必要再比，它就呆在这。有序表的最右边就是它最大的元素。
            if(outValue >= arr[compareIndex]){
                continue;
            }

            //从抽出来的位置一直往左比
            while (compareIndex >= 0) {
                //比我大的都要往后移一格
                if (arr[compareIndex] > outValue) {
                    arr[compareIndex + 1] = arr[compareIndex];
                    compareIndex = compareIndex -1;
                } else {
                    //碰到小的停止
                    break;
                }
            }
            //抽出的格就放在这个比它小的格后面
            arr[compareIndex + 1] = outValue;
        }
    }

}
