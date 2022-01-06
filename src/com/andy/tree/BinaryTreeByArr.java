package com.andy.tree;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/4
 * @version: 1.0.0
 */
public class BinaryTreeByArr {//数组实现二叉树

    //存储树结点的数组
    private int[] arr;

    //前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //根 输出当前这个元素
        System.out.println(arr[index]);
        //左 向左递归遍历，左孩子2n+1
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //右 向右递归遍历，右孩子2n+2
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中序遍历，同理
    //后序遍历，同理

}
