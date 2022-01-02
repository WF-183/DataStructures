package com.andy.hash;

import com.andy.linkedlist.Node;
import com.andy.linkedlist.SingleLinkedList;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class HashTab {//哈希表

    private SingleLinkedList[] arr;
    private int size;

    public HashTab(int size) {
        this.size = size;
        arr = new SingleLinkedList[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new SingleLinkedList(new Node(-1));
        }
    }

    public int hashCode(int id) {
        return id % size;
    }

    public void add(Node node) {
        //先算hash值，确定数组格子，确定哪一条链表
        int hashCode = hashCode(node.id);
        //链表增删改查操作
        arr[hashCode].add(node);
    }

    public void del(Node node) {
        //先算hash值，确定数组格子，确定哪一条链表
        int hashCode = hashCode(node.id);
        //链表增删改查操作
        arr[hashCode].delById(node.id);
    }

    public Node getById(Node node) {
        //先算hash值，确定数组格子，确定哪一条链表
        int hashCode = hashCode(node.id);
        //链表增删改查操作
        return arr[hashCode].getById(node.id);
    }

    public void show() {
        //遍历所有链表
        for (int i = 0; i < size; i++) {
            SingleLinkedList singleLinkedList = arr[i];
            if (singleLinkedList != null) {
                singleLinkedList.show();
            }else {
                System.out.println("null");
            }
        }
    }

}
