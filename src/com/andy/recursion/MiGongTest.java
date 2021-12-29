package com.andy.recursion;

/**
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/28
 * @version: 1.0.0
 */
public class MiGongTest {//递归 - 迷宫回溯

    public static void main(String[] args) {

        //二维数组构建迷宫
        int[][] arr = new int[8][7];
        for (int i = 0; i < 7; i++) {
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            arr[i][0] = 1;
            arr[i][6] = 1;
        }
        arr[3][1] = 1;
        arr[3][2] = 1;

        //找路 起点左上角
        setWay(arr, 1, 1);

        //输出结果 二维数组遍历
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 递归方法 判断当前点能否走通
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] arr, int i, int j) {
        //终点
        if (arr[6][5] == 2) {
            return true;
        }

        //0 第一次被探索的点
        if (arr[i][j] == 0) {
            //先假定此点能走通
            arr[i][j] = 2;
            //往四周多走一步，任一步能走通则此点就是通的
            //自定义策略：下右上左 ，这块代码决定策略，排列组合这块可找最短路径
            if (setWay(arr, i + 1, j)) {
                return true;
            } else if (setWay(arr, i, j + 1)) {
                return true;
            } else if (setWay(arr, i - 1, j)) {
                return true;
            } else if (setWay(arr, i, j - 1)) {
                return true;
            } else {
                //四周都走不通才进这里，此点标记为走不通的点
                arr[i][j] = 3;
                return false;
            }
        } else {
            //不为0，则为1 2 3，此点已被探索过，无需再往下判断
            return false;
        }

    }

}
