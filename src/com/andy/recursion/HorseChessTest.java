package com.andy.recursion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/17
 * @version: 1.0.0
 */
public class HorseChessTest {//马踏棋盘算法

    public static void main(String[] args) {
        Chess chess = new Chess(8, 8);
        long start = System.currentTimeMillis();
        chess.jump(0, 0, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");
        //输出棋盘的最后情况 
        for (int[] rows : chess.arr) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }
}

//棋盘对象
class Chess {

    //棋盘总行数 行数rowNum轴
    private int rowNum;

    //棋盘总列数 列数colNum轴
    private int colNum;

    //表示棋盘
    int[][] arr;

    //一位数组表示棋盘中所有格子是否被访问，按顺序从上到下、从左到右来定义下标，下标从零开始。
    private boolean[] isvisited;

    //成功结束标志位
    private boolean isFinished;

    public Chess(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        arr = new int[rowNum][colNum];
        isvisited = new boolean[rowNum * colNum];
    }

    /**
     *
     * @param rowIndex 当前点行下标，从零开始
     * @param colIndex 当前点列下标，从零开始
     * @param step 当前点是第几步，初始放置点从1开始
     */
    public void jump(int rowIndex, int colIndex, int step) {
        //进递归上面的操作和判断代码  相等于 是在进递归的每一步时执行，根到叶子顺序，
        //标记当前这一步
        arr[rowIndex][colIndex] = step;
        isvisited[colNum * rowIndex + colIndex] = true;
        //看看都能往哪跳
        List<Point> nextPointList = getNextPointList(new Point(colIndex, rowIndex));

        //使用贪心思想优化，每走一步都选择后续分支更少的一个点
        //实测性能优化极大
        nextPointList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return getNextPointList(o1).size() - getNextPointList(o2).size();
            }
        });

        //逐个路线递归尝试
        while (nextPointList.size() != 0) {
            Point nextPoint = nextPointList.remove(0);
            if (!isvisited[colNum * nextPoint.y + nextPoint.x]) {
                jump(nextPoint.y, nextPoint.x, step + 1);
            }
        }

        //进递归下面的操作和判断代码  相等于 是在回溯的每一步时执行，是叶子到根顺序，
        //第一次得到执行是在其中一条路线的叶子节点处，倒着往前来。
        if (step == colNum * rowNum) {
            isFinished = true;
        } else if (step < colNum * rowNum && !isFinished) {
            //非成功路线 失败方案的痕迹清掉，只留一种成功路线的痕迹
            arr[rowIndex][colIndex] = 0;
            isvisited[rowIndex * colNum + colIndex] = false;
        }
    }

    //辅助方法  输入一个点，返回这个点能往下走的所有日字点
    public List<Point> getNextPointList(Point point) {
        ArrayList<Point> list = new ArrayList<Point>();
        //枚举找到点四周不超出棋盘边界的所有日字点
        Point p1 = new Point();
        //表示马儿可以走 5 这个位置
        if ((p1.x = point.x - 2) >= 0 && (p1.y = point.y - 1) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 6 这个位置
        if ((p1.x = point.x - 1) >= 0 && (p1.y = point.y - 2) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 7 这个位置
        if ((p1.x = point.x + 1) < colNum && (p1.y = point.y - 2) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 0 这个位置
        if ((p1.x = point.x + 2) < colNum && (p1.y = point.y - 1) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 1 这个位置
        if ((p1.x = point.x + 2) < colNum && (p1.y = point.y + 1) < rowNum) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 2 这个位置
        if ((p1.x = point.x + 1) < colNum && (p1.y = point.y + 2) < rowNum) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 3 这个位置
        if ((p1.x = point.x - 1) >= 0 && (p1.y = point.y + 2) < rowNum) {
            list.add(new Point(p1));
        }
        //判断马儿可以走 4 这个位置
        if ((p1.x = point.x - 2) >= 0 && (p1.y = point.y + 1) < rowNum) {
            list.add(new Point(p1));
        }
        return list;
    }

}





