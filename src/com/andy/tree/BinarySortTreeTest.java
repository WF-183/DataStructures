package com.andy.tree;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/11
 * @version: 1.0.0
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {
        int[] arr = new int[] {7, 3, 10, 12, 5, 1, 9, 2};
        //int[] arr = new int[] {7, 3, 10, 12, 5, 1, 9, 11, 13, 2};
        //int[] arr = new int[] {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = buildBinarySortTree(arr);
        binarySortTree.midOrder();
        System.out.println("--------");

        //实测OK
        //{7, 3, 10, 12, 5, 1, 9, 11, 13, 2} 各种节点的删除
        //删2 ，中序遍历 ，1 3 5 7 9 10 11 12 13 ，删叶子节点
        //删1，中序遍历 ，2 3 5 7 9 10 11 12 13 ，删单子节点节点
        //删7，中序遍历 ，1 2 3 5  9 10 11 12 13，删双子节点节点，右半树min顶上来
        //删10，中序遍历 ，1 3 5 7 9 11 12 13，删双子节点节点，右半树min顶上来
        binarySortTree.delNodeByValue(2);

        //{7, 3, 10, 12, 5, 1, 9, 2} 删光
        binarySortTree.delNodeByValue(5);
        binarySortTree.delNodeByValue(9);
        binarySortTree.delNodeByValue(12);
        binarySortTree.delNodeByValue(7);
        binarySortTree.delNodeByValue(3);
        binarySortTree.delNodeByValue(10);
        binarySortTree.delNodeByValue(1);
        binarySortTree.midOrder();

    }

    public static BinarySortTree buildBinarySortTree(int[] arr) {
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new BinarySortTreeNode(arr[i]));
        }
        return binarySortTree;
    }

}

//二叉排序树对象
class BinarySortTree {

    //树总是有一个root节点属性
    private BinarySortTreeNode root;

    //1、数组转二叉排序树
    //逐个添加节点，构建二叉排序树
    public void add(BinarySortTreeNode node) {
        if (root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }

    //2、二叉排序树的遍历和普通二叉树一样
    public void midOrder() {
        if (root == null) {
            System.out.println("空树");
        } else {
            root.midOrder();
        }
    }

    //3、二叉排序树删除指定节点，三种情况
    //根据value删除指定节点 兼容三种节点情况
    public void delNodeByValue(int value) {
        if (root == null) {
            System.out.println("树不存在");
            return;
        }
        if (root.getLeftNode() == null && root.getRightNode() == null) {
            root = null;
            System.out.println("树空了");
            return;
        }

        BinarySortTreeNode targetNode = root.searchTargetNode(value);
        if (targetNode == null) {
            return;
        }
        BinarySortTreeNode parentNode = root.searchParentNode(value);

        //1、删除叶子节点
        //找到targetNode和parentNode，根据左右关系把parentNode左或右节点置空。
        if (targetNode.getLeftNode() == null && targetNode.getRightNode() == null) {
            if (parentNode.getLeftNode() != null && (parentNode.getLeftNode().getId() == value)) {
                parentNode.setLeftNode(null);
            } else {
                parentNode.setRightNode(null);
            }
        } else if (targetNode.getLeftNode() != null && targetNode.getRightNode() != null) {
            //3、删除非叶子结点-有2个子节点
            //找到targetNode和parentNode，从 targetNode 的右半子树找到一个最小的结点删除，其值value覆写到targetNode  ，targetNode.value = temp。
            int overwriteValue = delRightTreeMinNode(targetNode.getRightNode());
            targetNode.setId(overwriteValue);
        } else {
            //2、删除非叶子结点-有1个子节点
            //找到targetNode和parentNode，等价于单向链表的节点删除，parentNode.next = parentNode.next.next 。
            //特殊情况处理，整颗树删到只剩两个节点，现在要删根节点
            if (parentNode == null && targetNode.getLeftNode() != null) {
                root = targetNode.getLeftNode();
                return;
            } else if (parentNode == null && targetNode.getRightNode() != null) {
                root = targetNode.getRightNode();
                return;
            }
            //一般情况
            if (parentNode.getLeftNode() != null && (parentNode.getLeftNode().getId() == value)) {
                if (targetNode.getLeftNode() != null) {
                    parentNode.setLeftNode(targetNode.getLeftNode());
                } else {
                    parentNode.setLeftNode(targetNode.getRightNode());
                }
            } else {
                if (targetNode.getLeftNode() != null) {
                    parentNode.setRightNode(targetNode.getLeftNode());
                } else {
                    parentNode.setRightNode(targetNode.getRightNode());
                }
            }

        }

    }

    /**
     * 辅助方法 删除右半子树最小节点，返回其节点权值
     * @param node
     * @return
     */
    public int delRightTreeMinNode(BinarySortTreeNode node) {
        BinarySortTreeNode temp = node;
        //二叉排序树本来就是有序的，左小右大
        //怎么从指定根节点开始找到一颗二叉排序的最小节点min，一直往左找即可
        while (temp.getLeftNode() != null) {
            temp = temp.getLeftNode();
        }
        //找到
        int value = temp.getId();
        //删除
        delNodeByValue(value);
        return value;
    }

}

//二叉排序树节点
class BinarySortTreeNode {

    private int id;
    private String name;
    private BinarySortTreeNode leftNode;
    private BinarySortTreeNode rightNode;

    //构建二叉排序树
    public void add(BinarySortTreeNode node) {
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
    }

    //辅助方法 查找指定节点
    public BinarySortTreeNode searchTargetNode(int value) {
        if (this == null) {
            return null;
        }
        if (this.getId() == value) {
            return this;
        }
        //比一下大小，使得不用双边遍历
        if (value < this.getId()) {
            if (this.leftNode == null) {
                return null;
            }
            return this.leftNode.searchTargetNode(value);
        } else {
            if (this.rightNode == null) {
                return null;
            }
            return this.rightNode.searchTargetNode(value);
        }
    }

    //辅助方法 查找指定节点父节点
    public BinarySortTreeNode searchParentNode(int value) {
        if (this == null) {
            return null;
        }
        //父节点的判定条件
        if ((this.leftNode != null && this.leftNode.getId() == value) || (this.rightNode != null && this.rightNode.getId() == value)) {
            return this;
        }
        //不是则进入左或右遍历
        if (this.leftNode != null && value < this.getId()) {
            return this.leftNode.searchParentNode(value);
        } else if (this.rightNode != null && value >= this.getId()) {
            return this.rightNode.searchParentNode(value);
        } else {
            return null;
        }
    }

    //构造器、getset、tostring
    public BinarySortTreeNode(int id) {
        this.id = id;
    }

    public BinarySortTreeNode(int id, String name) {
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

    public BinarySortTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinarySortTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinarySortTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinarySortTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    //遍历
    public void preOrder() {
        //前序遍历 根-左-右
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    public void midOrder() {
        //中序遍历 左-根-右
        if (this.leftNode != null) {
            this.leftNode.midOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.midOrder();
        }
    }

    public void postOrder() {
        //后序遍历 左-右-根
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

}
