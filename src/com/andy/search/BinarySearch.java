package com.andy.search;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class BinarySearch {//二分查找

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 892));
        System.out.println(binarySearch(arr, 89));

        int arr2[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1000, 1234};
        System.out.println(binarySearch2(arr2, 0, arr2.length - 1, 1000));

    }

    /**
     * 二分查找-递归写法
     * 注意:使用二分查找的前提是该数组必须是有序的，按假定从小到大数组写
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //递归出口
        if (left >= right || findVal < arr[0] || findVal > arr[arr.length-1]) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (findVal < arr[mid]) {//目标值在左边
            return binarySearch(arr, left, mid, findVal);
        } else if (findVal > arr[mid]) {//目标值在右边
            return binarySearch(arr, mid + 1, right, findVal);
        } else {//目标值就是mid
            return mid;
        }
    }

    /**
     * 二分查找-非递归写法
     * 注意:使用二分查找的前提是该数组必须是有序的，按假定从小到大数组写
     * @param arr
     * @param findVal
     * @return
     */
    public static int binarySearch(int[] arr, int findVal) {
        //递归出口
        if (findVal < arr[0] || findVal > arr[arr.length-1]) {
            return -1;
        }

        int left = 0;
        int right = arr.length -1;

        while (left <= right){
            int mid = (left + right) / 2;

            if (findVal < arr[mid]) {//目标值在左边
                left = left;
                //坑 必须mid-1
                right = mid-1;
            } else if (findVal > arr[mid]) {//目标值在右边
                left = mid+1;
                right = right;
            } else {//目标值就是mid
                return mid;
            }
        }
        return -1;
    }




    /**
     * 课后思考题: {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中， 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        List<Integer> result = new ArrayList<>();
        //递归出口
        if (left >= right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;

        if (findVal < arr[mid]) {//目标值在左边
            return binarySearch2(arr, left, mid, findVal);
        } else if (findVal > arr[mid]) {//目标值在右边
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {//目标值就是mid
            result.add(mid);
            //找到后继续查找相同值
            result.addAll(binarySearch2(arr, left, mid, findVal));
            result.addAll(binarySearch2(arr, mid + 1, right, findVal));
        }

        return result;
    }



}
