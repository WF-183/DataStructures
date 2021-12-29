package com.andy.recursion;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/28
 * @version: 1.0.0
 */
public class Queue8Test {//递归 八皇后问题

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        //直接输出一行行结果
        //0 4 7 5 2 6 1 3 ==》 第一个皇后放在第一行的0列、第二个皇后放在第二行的4列
        queue8.check(0);
        //解法数量： 92
        System.out.println("解法数量： " + queue8.resCount);
        //judge触发次数： 15720
        System.out.println("judge触发次数： " + queue8.judgeCount);
    }

}

class Queue8 {

    //8个皇后
    private int max = 8;
    //使用一维数组表示每一组解，约定：一维数组每个元素对应一个皇后位置，按序依次对应一行，其元素值对应所在列
    private int[] arr = new int[max];

    //辅助变量
    public int resCount;
    public int judgeCount;

    /**
     * 回溯思想，递归算法，寻求八皇后问题所有解
     * @param n
     */
    public void check(int n) {
        //n=8 则一组0-7已通过，找到一组解
        if (n == max) {
            printArr(arr);
            //出口
            return;
        }

        //尝试每一列 0-7
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judgeConflict(n)) {
                //冲突则跳过试下一列
            } else {
                //每一个皇后OK就去试下一个皇后，这样回溯穷举
                check(n + 1);
            }
        }
    }

    /**
     * 判断皇后n位置是否与之前所有皇后位置冲突
     * @param n
     * @return
     */
    private boolean judgeConflict(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //根据前提，不可能处在同一行，行冲突不用判断
            //同一列，冲突
            //同一斜线，冲突
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 辅助方法 控制台打印一维数组
     * @param arr
     */
    private void printArr(int[] arr) {
        resCount++;
        for (int i : arr) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }

}
