package com.andy.linkedlist;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class SingleLinkedListTest {

    public static void main(String[] args) {

        SingleLinkedList singleLinkedList = new SingleLinkedList(new Node(-1));
        //增
        singleLinkedList.add(new Node(0, "0"));
        singleLinkedList.add(new Node(1, "1"));
        singleLinkedList.add(new Node(2, "2"));
        singleLinkedList.add(new Node(3, "3"));
        singleLinkedList.add(new Node(4, "4"));
        singleLinkedList.add(new Node(5, "5"));
        //查
        singleLinkedList.show();
        System.out.println("id=3的node为 " + singleLinkedList.getById(3));

        //改
        singleLinkedList.updById(new Node(3, "3 upd"));
        singleLinkedList.show();

        //删
        singleLinkedList.delById(3);
        singleLinkedList.show();
        singleLinkedList.delById(7);
    }

}
