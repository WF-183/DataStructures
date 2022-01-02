package com.andy.sort;

import java.util.Arrays;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/31
 * @version: 1.0.0
 */
public class ShellSort {//希尔排序

    public static void main(String[] args) {

        //功能测试
        int[] arr = {5, 2, 1, 4, 3};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));

        //性能测试
        //8万个数，希尔排序需要20ms左右，优化了直接插入法 较小数在较右边问题 需要对比太多次，性能要比直接插入、选择、冒泡强。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        shellSort(arr2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void shellSort(int[] arr) {
        //PART一 分组
        //首次所分组数=length/2，后续每次组数减半
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {

            //PART二 里面套一个直接插入排序 步长从1变成gap而已
            //从第二个元素开始
            for (int i = gap; i < arr.length; i++) {
                //抽一格值 空出来一格位置
                int outValue = arr[i];
                //有序表的右边界
                int compareIndex = i - gap;

                //优化 若抽出来的这一格比左边所有都大，没必要再比，它就呆在这。有序表的最右边就是它最大的元素。
                if (outValue >= arr[compareIndex]) {
                    continue;
                }

                //从抽出来的位置一直往左比
                while (compareIndex >= 0) {
                    //比我大的都要往后移一格
                    if (arr[compareIndex] > outValue) {
                        arr[compareIndex + gap] = arr[compareIndex];
                        compareIndex = compareIndex - gap;
                    } else {
                        //碰到小的停止
                        break;
                    }
                }
                //抽出的格就放在这个比它小的格后面
                arr[compareIndex + gap] = outValue;
            }
        }

    }

}
