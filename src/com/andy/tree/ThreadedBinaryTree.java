package com.andy.tree;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/4
 * @version: 1.0.0
 */
public class ThreadedBinaryTree {//线索化二叉树

    //根节点
    private ThreadedTreeNode root;

    //pre 辅助节点 遍历过程中的上一个节点
    //和节点在树中的绝对上下位置无关，取决于当前遍历规则是前序遍历、中序遍历、后续遍历
    private ThreadedTreeNode pre = null;

    public void setRoot(ThreadedTreeNode root) {
        this.root = root;
    }

    //对二叉树进行中序线索化处理，得到一个中序线索化二叉树
    public void doThreadedTree(ThreadedTreeNode node) {
        if (node == null) {
            return;
        }

        //左
        doThreadedTree(node.getLeftNode());

        //根
        //当前节点线索处理 指针空才填充线索，指针用着则不动
        //node前驱左线索利用pre直接本轮设置node.setLeft(pre)，
        if (node.getLeftNode() == null) {
            node.setLeftNode(pre);
            node.setLeftType(1);
        }
        //node后继右线索本轮pass  在紧接着的下一轮node变成pre，next变node ，pre.setRight(node)相当于完成右线索；
        if (pre != null && pre.getRightNode() == null) {
            pre.setRightNode(node);
            pre.setRightType(1);
        }
        //游标后移 pre始终指向遍历过程中的前一个节点
        pre = node;

        //右
        doThreadedTree(node.getRightNode());

    }
    //同理，对二叉树进行前序线索化处理，得到一个前序线索化二叉树
    //同理，对二叉树进行后序线索化处理，得到一个后序线索化二叉树


    //中序线索化二叉树 的遍历
    public void midOrderThreadedTree() {
        //辅助游标 从root开始
        ThreadedTreeNode node = root;
        //while代替递归，这也算是线索的用处了，线索化后可以提高遍历效率
        while (node != null) {
            //一直找到左指针是线索的节点
            while (node.getLeftType() == 0) {
                node = node.getLeftNode();
            }
            System.out.println(node);

            //只要右指针是线索类型，那么右指针就是对应排序下的下一个节点
            while (node.getRightType() == 1) {
                node = node.getRightNode();
                System.out.println(node);
            }

            //右指针不是线索类型，则往右移，因为这个点是通过右线索找过来的，所以不能再往左移，死循环
            node = node.getRightNode();

            //进入下轮while
        }
    }
    //同理，前序线索化二叉树 的遍历
    //同理，后序线索化二叉树 的遍历

}

//线索化二叉树节点
class ThreadedTreeNode {

    private int id;
    private String name;
    private ThreadedTreeNode leftNode;
    private ThreadedTreeNode rightNode;

    //两个额外的标志位属性 0指节点，1指线索
    //leftType == 0 表示指向的是左子树, 1表示指向前驱结点
    private int leftType;
    //rightType == 0 表示指向是右子树, 1表示指向后继结点
    private int rightType;

    public ThreadedTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public ThreadedTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(ThreadedTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public ThreadedTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(ThreadedTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "ThreadedTreeNode{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
