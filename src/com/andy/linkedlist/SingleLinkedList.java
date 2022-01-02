package com.andy.linkedlist;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/10/19
 * @version: 1.0.0
 */
public class SingleLinkedList {

    //约定：头结点手动set确保存在，头结点能动，且不用于存具体数据
    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public SingleLinkedList(Node head) {
        this.head = head;
    }

    public SingleLinkedList() {
    }

    //增 顺序添加
    //添加节点到单向链表 ，不管顺序版，找到最后节点，无脑加到链表末尾
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //辅助游标+单层循环
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //temp此刻指向最后一个元素
        temp.next = node;
    }

    //增 插入添加
    //根据指定属性id顺序将节点插入到指定位置 ，从前到后排名依次顺序递增
    public void addByIdOrder(Node node) {
        if (node == null) {
            return;
        }

        //先找到要插入位置的前一个节点
        //标志是否找到指定节点
        boolean flag = false;
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.id > node.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //如果找到，则temp此刻指向我要的位置 执行插入
        if (flag) {
            node.next = temp.next;
            temp.next = node;
        } else {//如果找不到，则temp指向链表尾元素，说明当前id大于现有所有节点，直接放到链表尾部
            temp.next = node;
        }
    }

    //删
    public void delById(int id) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        //先找到要删除节点的前一个节点
        //标志是否找到指定节点
        boolean flag = false;
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //temp此刻指向我要的位置 执行删除
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", id);
        }
    }

    //改
    public void updById(Node node) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        //辅助游标+单层循环 直接找指定节点
        //标志是否找到指定节点
        boolean flag = false;
        Node temp = head.next;
        while (temp != null) {
            if (temp.id == node.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //temp此刻指向我要的位置 执行upd
        if (flag) {
            temp.name = node.name;
        } else {
            System.out.printf("要更新的 %d 节点不存在\n", node.id);
        }
    }

    //查 查所有
    public void show() {
        if (head.next == null) {
            System.out.println("空链表");
        }
        //辅助游标+单层循环
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //查 查一个
    public Node getById(int id) {
        Node res = null;
        if (head.next == null) {
            System.out.println("空链表");
        }
        //辅助游标+单层循环
        Node temp = head.next;
        while (temp != null) {
            if (temp.id == id) {
                res = temp;
                break;
            }
            temp = temp.next;
        }
        return res;
    }

}
