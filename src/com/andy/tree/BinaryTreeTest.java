package com.andy.tree;

import com.sun.source.tree.BinaryTree;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/4
 * @version: 1.0.0
 */
public class BinaryTreeTest {

    public static void main(String[] args) {

        //测试一把中序线索二叉树的功能
        ThreadedTreeNode root = new ThreadedTreeNode(1, "tom");
        ThreadedTreeNode node2 = new ThreadedTreeNode(3, "jack");
        ThreadedTreeNode node3 = new ThreadedTreeNode(6, "smith");
        ThreadedTreeNode node4 = new ThreadedTreeNode(8, "mary");
        ThreadedTreeNode node5 = new ThreadedTreeNode(10, "king");
        ThreadedTreeNode node6 = new ThreadedTreeNode(14, "dim");
        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);
        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.doThreadedTree(root);
        //10 左3右1，线索标记为均为1，实测可行
        System.out.println(root);
        System.out.println("-------------");
        threadedBinaryTree.midOrderThreadedTree();


        //二叉树
        //BinaryTreeByLinked binaryTree = new BinaryTreeByLinked();
        //TreeNode root = new TreeNode(1, "宋江");
        //TreeNode node2 = new TreeNode(2, "吴用");
        //TreeNode node3 = new TreeNode(3, "卢俊义");
        //TreeNode node4 = new TreeNode(4, "林冲");
        //TreeNode node5 = new TreeNode(5, "关胜");
        //TreeNode node6 = new TreeNode(6, "武松");
        //TreeNode node7 = new TreeNode(7, "杨志");
        //TreeNode node8 = new TreeNode(8, "鲁智深");
        //TreeNode node9 = new TreeNode(9, "史进");
        //
        ////手动构建树
        //root.setLeftNode(node2);
        //node2.setLeftNode(node6);
        //root.setRightNode(node3);
        //node3.setLeftNode(node5);
        //node3.setRightNode(node4);
        //node5.setLeftNode(node7);
        //node4.setRightNode(node8);
        //node2.setRightNode(node9);
        //binaryTree.setRoot(root);
        //
        //binaryTree.preOrder();
        //System.out.println();
        //binaryTree.midOrder();
        //System.out.println();
        //binaryTree.postOrder();

    }
}
