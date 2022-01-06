package com.andy.sort;

import java.util.Arrays;
import java.util.logging.Level;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/6
 * @version: 1.0.0
 */
public class HeapSort {//堆排序

    public static void main(String[] args) {

        //功能测试
        //int[] arr = new int[] {4, 8, 6, 5, 9, -1, -100, 78, 111};
        int[] arr = new int[] {4, 8, 6, 5, 9};
        adjust(arr, 1, 5);
        System.out.println("1 " + Arrays.toString(arr));
        adjust(arr, 0, 5);
        System.out.println("2 " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("3 " + Arrays.toString(arr));

        //性能测试
        //8万个数，堆排序需要12ms左右，堆排序不用递归 ，性能要比 快排、希尔、直接插入、选择、冒泡都强。
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,1) * 80000
            arr2[i] = (int)(Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        heapSort(arr2);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void heapSort(int arr[]) {
        //第一步 无序数组转大顶堆
        //从最后一个非叶子结点开始(arr.length/2-1)，从左至右，从下至上进行调整
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }

        //后续步
        for (int j = arr.length - 1; j > 0; j--) {
            //交换 每次得到的最大值将放到完全二叉树的右下方 == 有效数组的最右方
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            //下沉调整有效树
            //下一轮，重新调整结构，使剩下元素满足大顶堆定义
            //第一步后整个树已经是个有序大顶堆，其左右已经分别都满足大顶堆，
            //最后一个元素被根换到index=0来，无论其与左、右节点发生交换，都只需要一次adjust即可，
            //一次adjust就能处理好根节点所在三节点子树+每一次与根发生交换的一侧三节点子树，不发生交换的一侧不用处理，已经是有序的
            adjust(arr, 0, j);
        }
    }

    /**
     * 下沉调整
     * 一次adjust是把本三节点子树处理成大顶堆（以i为根）+ 把处理过程中每次与根发生交换的一侧三节点子树处理成大顶堆（以交换节点为根）
     * @param arr 待排序目标数组
     * @param i 要下沉处理的父节点index
     * @param adjustLength 堆的有效大小，逐渐减少
     */
    public static void adjust(int[] arr, int i, int adjustLength) {
        //temp保存父节点值，用于最后的赋值
        int temp = arr[i];

        //childMax用以表示三节点子树中较大子节点，完全二叉树左节点优先 先指向左
        int childMax = 2 * i + 1;
        while (childMax < adjustLength) {
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childMax + 1 < adjustLength && arr[childMax + 1] > arr[childMax]) {
                childMax = childMax + 1;
            }
            //check 如果父节点大于等于任何一个孩子的值，则不用调整，直接跳出
            if (temp >= arr[childMax]) {
                break;
            }
            //check通过 不是交换，单向赋值，大值放到父节点
            arr[i] = arr[childMax];
            //指向与根发生交换的一侧子节点，处理下一个三节点子树
            i = childMax;
            childMax = 2 * childMax + 1;
        }

        //循环结束后，已经将以i为父结点的树的最大值移动到了顶部，
        //i此时指向最后一个被处理的节点位置，把一开始的根挤到这里来
        arr[i] = temp;
    }
}

//
//int left = 0;
//int right = 0;
//while (true) {
//    left = 2 * i + 1;
//    right = 2 * i + 2;
//    if (left >= adjustLength || right >= adjustLength) {
//        break;
//    }
//    //三个数比较最大值，最大的放到根节点 TODO 待优化 三个数的比较 虽然行但有点low pass
//    //如果父节点大于所有孩子的值，则直接跳出
//    if (arr[left] >= arr[right]) {
//        if (arr[left] > temp) {
//            arr[i] = arr[left];
//            i = left;
//        }else{
//            break;
//        }
//    } else {
//        if (arr[right] > temp) {
//            arr[i] = arr[right];
//            i = right;
//        }else{
//            break;
//        }
//    }
//}