package com.andy.divideconquer;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/12
 * @version: 1.0.0
 */
public class HanNuoTaTest {//分治算法-汉诺塔

    public static void main(String[] args) {
        //2个盘，A搬到C
        move(2, "A", "B", "C");
        //5个盘，A搬到C
        move(5, "A", "B", "C");
    }

    public static void move(int num, String source, String assist, String target) {
        if (num > 1) {
            //两个盘及以上，分治思想，始终看成上下两部分，下部分一个盘和上部分所有盘，抽象为三步
            //每一步时不作为源节点和目标节点的节点自动作为辅助节点
            //1、上半部分所有盘移动到辅助节点
            move(num - 1, source, target, assist);
            //2、下半部分一个盘移动到目标节点
            System.out.println(source + " -> " + target);
            //3、上半部分所有盘从辅助节点移动到目标节点
            move(num - 1, assist, source, target);
        } else {
            //还剩一个盘，则直接从源节点移到目标节点
            //用打印模拟移动操作
            System.out.println(source + " -> " + target);
        }
    }
}
