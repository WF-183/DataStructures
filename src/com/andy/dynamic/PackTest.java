package com.andy.dynamic;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/13
 * @version: 1.0.0
 */
public class PackTest {//动态规划-背包问题

    public static void main(String[] args) {

        int n = 3; //物品的个数
        int[] w = {0, 1, 4, 3};//物品重量数组 约定第零个物品重量为0
        int[] v = {0, 1500, 3000, 2000}; //物品价值数组 约定第零个物品价值为0
        int m = 4;// 背包的容量
        //sout表示方案输出
        dynamicPack(n, w, v, m);
    }

    /**
     *
     * @param n 物品个数
     * @param w 物品重量数组
     * @param v 物品价值数组
     * @param m 背包总容量
     */
    public static void dynamicPack(int n, int[] w, int[] v, int m) {
        //辅助二维数组 动态规划方案表：对 要添加商品是i（前i个商品可选），容量是j的背包，最优解方案能装的价值最大值，
        int[][] maxValArr = new int[n + 1][m + 1];
        //辅助二维数组 对 要添加商品是i（前i个商品可选），容量是j的背包，在最优解下是否放入
        int[][] path = new int[n + 1][m + 1];

        //为使得下标从零开始方便构建二维数组，借助辅助物品零  其重量和价值都是零，只占位不参与，maxArr第一行第一列全都是0
        //maxValArr[i][0]=maxValArr[0][j]=0; ，int数组元素默认值就是0，不用动

        //注意，i、j都从1开始
        for (int i = 1; i < maxValArr.length; i++) {
            for (int j = 1; j < maxValArr[i].length; j++) {
                //若当前商品重量超出整个背包容量，放不进去，则最优方案同此容量下上一行方案
                if (w[i] > j) {
                    maxValArr[i][j] = maxValArr[i - 1][j];
                } else {
                    //若当前商品能放进去，则
                    //按把它放进去计算最优解方案价值=它的价值+除去它的占位&除去它的重量下最优解价值，
                    //按不把它放进去最优方案=同此容量下上一行方案
                    //两者比较max，更优者入选
                    maxValArr[i][j] = Math.max(maxValArr[i - 1][j], v[i] + maxValArr[i - 1][j - w[i]]);
                    //如果当前商品入选，则记入path
                    if (v[i] + maxValArr[i - 1][j - w[i]] > maxValArr[i - 1][j]) {
                        path[i][j] = 1;
                    }
                }
            }
        }

        //二维数组遍历 动态规划方案表
        System.out.println("动态规划方案表:");
        for (int i = 0; i < maxValArr.length; i++) {
            for (int j = 0; j < maxValArr[i].length; j++) {
                System.out.printf(maxValArr[i][j] + " ");
            }
            System.out.println();
        }

        //二维数组遍历 path
        System.out.println("path:");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.printf(path[i][j] + " ");
            }
            System.out.println();
        }

        //二维数组遍历 path，倒着遍历，输出方案
        System.out.println("输出动态规划结果，最终最优解方案:");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("下标%d商品放入背包\n", i);
                j = j - w[i];
            }
            i--;
        }

    }

}
