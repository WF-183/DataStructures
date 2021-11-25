package com.andy.linkedlist;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/10/19
 * @version: 1.0.0
 */
public class SingleLinkedList {

    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }


    //添加节点 顺序添加
    public void add(Node node){
        if(node == null){
            return;
        }

        Node temp = head;
        boolean flag = true;
        while (flag){
            if(temp.next == null){
                flag = false;
                break;
            }
            temp = temp.next;
        }

        //temp此刻指向最后一个元素
        temp.next = node;
    }



    public void show(){
        if(head.next==null){
            System.out.println("空单向链表");
        }

        Node temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
