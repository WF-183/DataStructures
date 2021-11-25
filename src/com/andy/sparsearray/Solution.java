package com.andy.sparsearray;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/10/18
 * @version: 1.0.0
 */
public class Solution {

    /**
     * 数组找相同元素
     * 给定两个整数数组（元素允许重复），编写程序找出两个数组中相同的元素
     * 输入：两个整数数组
     * 输出：相同元素组成的数组
     * 说明：
     * 输出结果中每个元素出现的次数为该元素在两个数组中出现次数的最大值
     * 可以不考虑输出结果的顺序
     * 给出时间复杂度和空间复杂度分析
     * 示例 1：
     * 输入：nums1 = [1,6,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2：
     * 输入：nums1 = [7,3,5], nums2 = [3,7,3,8,7]
     * 输出：[3,7,3,7]
     * @param args
     */
    public static void main(String[] args) {
        int[] a = new int[] {7, 3, 5, 7, 7, 4, 3, 3, 3};
        int[] b = new int[] {3, 7, 3, 8, 7};
        int[] solve = solve(a, b);
        for (int i = 0; i < solve.length; i++) {
            System.out.println(solve[i] + " ");
        }
    }

    /**
     * 数组找相同元素 第一版
     * @param a
     * @param b
     * @return
     */
    public static int[] solve(int[] a, int[] b) {
        int[] res = null;
        if (a == null || a.length == 0 || b == null || b.length == 0) {
            return res;
        }
        int prePoint = 0;
        int tempPoint = 0;
        int resLength = 0;
        int[] pre = new int[a.length > b.length ? a.length : b.length];
        int[] temp = new int[a.length > b.length ? a.length : b.length];
        for (int i = 0; i < a.length; i++) {
            if (count(a[i], temp) > 0) {
                break;
            }
            temp[tempPoint] = a[i];
            tempPoint++;
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    if (count(a[i], pre) > 0) {
                        break;
                    }
                    int aCount = count(a[i], a);
                    int bCount = count(a[i], b);
                    int copyCount = aCount > bCount ? aCount : bCount;
                    for (int k = 0; k < copyCount; k++) {
                        pre[prePoint] = a[i];
                        prePoint++;
                    }
                    resLength += copyCount;
                }

            }
        }
        res = new int[resLength];
        for (int i = 0; i < resLength; i++) {
            res[i] = pre[i];
        }
        return res;
    }

    public static int count(int ele, int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ele) {
                count++;
            }
        }
        return count;
    }

}
