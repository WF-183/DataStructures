package com.andy.linkedlist;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/11/22
 * @version: 1.0.0
 */
public class CircleSingleLinkedListTest {

    public static void main(String[] args) {

        //测试一
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //构建
        circleSingleLinkedList.build(5);
        //输出
        circleSingleLinkedList.show();



        //测试二
        CircleSingleLinkedList circleSingleLinkedList2 = new CircleSingleLinkedList();
        //5个节点的圈，从第一个小孩开始，每数两个数出圈一个 ==》 2 4 1 5 3
        circleSingleLinkedList2.josephu(1,2,5);

    }
}
