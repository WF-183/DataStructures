package com.andy.tree;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/4
 * @version: 1.0.0
 */
public class BinaryTreeByLinked {//链表实现二叉树

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public void preOrder() {
        //前序遍历 根-左-右
        if (this.root != null) {
            this.root.preOrder();
        }
    }

    public void midOrder() {
        //中序遍历 左-根-右
        if (this.root != null) {
            this.root.midOrder();
        }
    }

    public void postOrder() {
        //后序遍历 左-右-根
        if (this.root != null) {
            this.root.postOrder();
        }
    }
}

//二叉树节点
class TreeNode {

    private int id;
    private String name;
    private TreeNode leftNode;
    private TreeNode rightNode;

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

    //前序查找，中序、后续查找同理
    public TreeNode preOrderSearch(int id) {
        TreeNode resNode = null;
        //根
        if (this.id == id) {
            resNode = this;
        }
        if (resNode != null) {
            return resNode;
        }
        //左
        if (this.leftNode != null) {
            resNode = this.leftNode.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        //右
        if (this.rightNode != null) {
            resNode = this.rightNode.preOrderSearch(id);
        }
        return resNode;
    }

    //递归删除结点
    //如果删除的节点是叶子节点，则删除该节点
    //如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int id) {
        //1.因为我们的二叉树是单向的，所以我们需要提前判断当前结点的子结点是否是需要删除结点，而不能拿到某个节点后再判断，删不了了.
        //2.如果当前结点的左子结点不为空且左子结点就是要删除结点，就将this.left = null;即可删除此节点或子树，然后直接返回结束递归
        if (this.leftNode != null && this.leftNode.id == id) {
            this.leftNode = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null;即可删除此节点或子树，然后直接返回结束递归
        if (this.rightNode != null && this.rightNode.id == id) {
            this.rightNode = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除 
        if (this.leftNode != null) {
            this.leftNode.delNode(id);
        }
        //5.则应当向右子树进行递归删除 
        if (this.rightNode != null) {
            this.rightNode.delNode(id);
        }
    }

    public TreeNode(int id) {
        this.id = id;
    }

    public TreeNode(int id, String name) {
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

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
