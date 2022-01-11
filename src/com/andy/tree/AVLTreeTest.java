package com.andy.tree;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/11
 * @version: 1.0.0
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        //int[] arr = new int[] {4,3,6,5,7,8};//看左旋
        //int[] arr = new int[] {10,12,8,9,7,6};//看右旋
        //int[] arr = new int[] {10,11,7,6,8,9};//看先对根节点左节点左旋一次，再对根节点右旋
        int[] arr = new int[] {2, 1, 6, 5, 7, 3};//看先对根节点右节点右旋一次，再对根节点左旋
        //debug看树样子
        AVLTree avlTree = buildAVLTree(arr);
        System.out.println("--------");
    }

    public static AVLTree buildAVLTree(int[] arr) {
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AVLTreeNode(arr[i]));
        }
        return avlTree;
    }

}

//平衡二叉树对象
class AVLTree {

    //树总是有一个root节点属性
    private AVLTreeNode root;

    //1、数组转平衡二叉树
    //逐个添加节点，期间不断进行左旋右旋，构建平衡二叉树
    public void add(AVLTreeNode node) {
        if (root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }

}

//平衡二叉树节点
class AVLTreeNode {

    private int id;
    private String name;
    private AVLTreeNode leftNode;
    private AVLTreeNode rightNode;

    //构建平衡二叉树
    public void add(AVLTreeNode node) {
        if (node == null) {
            return;
        }
        //小于放左边
        if (node.getId() < this.getId()) {
            if (this.getLeftNode() == null) {
                this.setLeftNode(node);
            } else {
                //递归
                this.getLeftNode().add(node);
            }
        }
        //大于放右边
        if (node.getId() >= this.getId()) {
            if (this.getRightNode() == null) {
                this.setRightNode(node);
            } else {
                //递归
                this.getRightNode().add(node);
            }
        }

        //这段注掉与开启，决定构建出来的是： 二叉排序树 或 平衡二叉树
        //根节点右比左高大于1，进行左旋降低右边高度，相当于从右边顶上来一个新的根节点，
        if (this.getRightTreeHeight() - this.getLeftTreeHeight() > 1) {
            //1、看根节点的右节点，若其左子树高度大于其右子树的高度，则先对根节点右节点右旋一次，再对根节点左旋
            if (rightNode != null && rightNode.getLeftTreeHeight() > rightNode.getRightTreeHeight()) {
                rightNode.rightRotate();
                this.leftRotate();
            } else {//否则直接对根节点左旋
                this.leftRotate();
                //必须
                return;
            }
        }

        //根节点左比右高大于1，进行右旋降低左边高度，相当于从左边顶上来一个新的根节点，
        if (this.getLeftTreeHeight() - this.getRightTreeHeight() > 1) {
            //1、看根节点的左节点，若其右子树高度大于其左子树的高度，则先对根节点左节点左旋一次，再对根节点右旋
            if (leftNode != null && leftNode.getRightTreeHeight() > leftNode.getLeftTreeHeight()) {
                leftNode.leftRotate();
                this.rightRotate();
            } else {//否则直接对根节点右旋
                this.rightRotate();
                //必须
                return;
            }
        }
    }

    //左旋
    public void leftRotate() {
        //以根节点值创建辅助新节点，
        AVLTreeNode temp = new AVLTreeNode(this.id);
        //新节点左节点设为根节点左节点，新节点右节点设为根节点右节点的左节点，
        temp.setLeftNode(this.leftNode);
        temp.setRightNode(this.rightNode.leftNode);
        //根节点值覆写为右节点值，
        id = rightNode.id;
        //根节点右节点设为右节点的右节点，根节点左节点设为新节点，完成。
        this.rightNode = this.rightNode.rightNode;
        this.leftNode = temp;
    }

    //右旋
    public void rightRotate() {
        //以根节点值创建辅助新节点，
        AVLTreeNode temp = new AVLTreeNode(this.id);
        //新节点右节点设为根节点右节点，新节点左节点设为根节点左节点的右节点，
        temp.setRightNode(this.rightNode);
        temp.setLeftNode(this.leftNode.rightNode);
        //根节点值覆写为左节点值，
        id = leftNode.id;
        //根节点左节点设为左节点的左节点，根节点右节点设为新节点，完成，右旋操作和左旋操作完全镜像。
        this.leftNode = this.leftNode.leftNode;
        this.rightNode = temp;
    }

    //求高度
    //以当前结点为根结点的树的高度
    public int getTreeHeight() {
        return Math.max(leftNode == null ? 0 : leftNode.getTreeHeight(), rightNode == null ? 0 : rightNode.getTreeHeight()) + 1;
    }

    public int getLeftTreeHeight() {
        if (leftNode == null) {
            return 0;
        }
        return leftNode.getTreeHeight();
    }

    public int getRightTreeHeight() {
        if (rightNode == null) {
            return 0;
        }
        return rightNode.getTreeHeight();
    }

    //构造器、getset、tostring
    public AVLTreeNode(int id) {
        this.id = id;
    }

    public AVLTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AVLTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(AVLTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public AVLTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(AVLTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

}