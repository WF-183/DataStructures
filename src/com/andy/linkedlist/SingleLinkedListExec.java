package com.andy.linkedlist;

import java.util.Stack;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/10/19
 * @version: 1.0.0
 */
public class SingleLinkedListExec {

    //明天写一下 练习还是不能少的 OK 完成
    public static void main(String[] args) {

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.setHead(new Node(0));
        singleLinkedList.add(new Node(1,"1"));
        singleLinkedList.add(new Node(2,"2"));
        singleLinkedList.add(new Node(3,"3"));
        singleLinkedList.add(new Node(4,"4"));
        singleLinkedList.add(new Node(5,"5"));
        singleLinkedList.show();

        SingleLinkedListExec test = new SingleLinkedListExec();
        System.out.println(test.count(singleLinkedList));
        System.out.println(test.getRevsKNode(singleLinkedList,2));

        System.out.println("反转：");
        test.reverse(singleLinkedList);
        singleLinkedList.show();

        //System.out.println("反向打印：");
        //test.reversePrint(singleLinkedList);



        //SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        //singleLinkedList1.setHead(new Node(0));
        //singleLinkedList1.add(new Node(1,"1"));
        //singleLinkedList1.add(new Node(3,"3"));
        //singleLinkedList1.add(new Node(5,"5"));
        //singleLinkedList1.add(new Node(6,"6"));
        //singleLinkedList1.add(new Node(7,"7"));
        //singleLinkedList1.show();
        //SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //singleLinkedList2.setHead(new Node(0));
        //singleLinkedList2.add(new Node(2,"2"));
        //singleLinkedList2.add(new Node(4,"4"));
        //singleLinkedList2.add(new Node(8,"8"));
        //singleLinkedList2.show();
        //System.out.println("合并：");
        //SingleLinkedList merge = test.mergeOrder2Order(singleLinkedList1, singleLinkedList2);
        //merge.show();


        //SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        //singleLinkedList1.setHead(new Node(0));
        //singleLinkedList1.add(new Node(1,"1"));
        //singleLinkedList1.add(new Node(5,"5"));
        //singleLinkedList1.add(new Node(3,"3"));
        //singleLinkedList1.add(new Node(7,"7"));
        //singleLinkedList1.add(new Node(6,"6"));
        //System.out.println("1：");
        //singleLinkedList1.show();
        //SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //singleLinkedList2.setHead(new Node(0));
        //singleLinkedList2.add(new Node(8,"8"));
        //singleLinkedList2.add(new Node(2,"2"));
        //singleLinkedList2.add(new Node(4,"4"));
        //System.out.println("2：");
        //singleLinkedList2.show();
        //System.out.println("合并：");
        //SingleLinkedList merge = test.mergeNoOrder2Order(singleLinkedList1, singleLinkedList2);
        //merge.show();




    }


    //1、求指定单向链表有效节点总数量
    public  int count(SingleLinkedList singleLinkedList){
        if(singleLinkedList == null){
            return -1;
        }

        Node head = singleLinkedList.getHead();
        Node temp = head.next;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }


    //2、求单向链表中倒数第k个节点
    public Node getRevsKNode(SingleLinkedList singleLinkedList,int k){
        if (singleLinkedList == null) {
            return null;
        }

        Node head = singleLinkedList.getHead();
        if(head.next == null){
            return null;
        }

        //先求单向链表有效节点总个数
        int count = 0;
        Node temp = head.next;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        if(k > count){
            System.out.println("超过合理范围");
            return null;
        }

        //找规律 倒数第k个就是整数第count+1-k个 （ head 1 2 3 4 5）
        temp = head.next;
        for (int i = 0; i < count - k ; i++) {
            temp = temp.next;
        }
        return temp;
    }




    //3、单向链表的反转（整个链表的结构被改变了） （通用技能点：在指定节点后挤进来一个节点）
    //有头结点的单向链表反转
    public void reverse(SingleLinkedList singleLinkedList){
        if (singleLinkedList == null) {
            return;
        }
        Node head = singleLinkedList.getHead();
        if(head.next == null || head.next.next == null){
            System.out.println("空的或一个有效节点，不需要反转");
            return;
        }

        //辅助头节点
        Node newHead = new Node(0);
        //辅助游标
        Node temp = head.next;
        Node tempBak = null;
        while (temp != null){
            //保存指向
            tempBak = temp.next;

            //对temp做些操作 把当前temp节点插到辅助头结点后面
            temp.next = newHead.next;
            newHead.next = temp;

            //temp后移
            temp = tempBak;
        }

        //head 1 2 3 4 5
        //newhead 5 4 3 2 1 newHead后面的节点指向head.next
        head.next = newHead.next;
    }


    //无头结点的单向链表反转
    public Node ReverseList(Node head) {
        //空链表 或 单节点链表，没必要反转
        if(head == null || head.next == null){
            return null;
        }
        //辅助链表头
        Node reverseHead = new Node(0);
        //辅助遍历游标
        Node cur = head;
        Node next = null;
        while(cur != null){
            //保存游标
            next = cur.next;

            //指定节点后插到辅助链表的首元素位置
            cur.next = reverseHead.next;
            reverseHead.next = cur;

            //游标后移
            cur = next;
        }
        //无头节点
        head = reverseHead.next;
        return head;
    }


    //无头结点的链表反转 接法二
    /*
    public class Node {
        int val;
        Node next = null;

        Node(int val) {
            this.val = val;
        }
    }*/
    public class Solution {
        public Node ReverseList(Node head) {
            if (head == null) {
                return null;
            }
            Node pre = null;
            Node curr = head;
            while (curr != null) {
                Node next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }
    }


    //4、从未到头打印单向链表（整个链表的结构没变）
    public  void reversePrint(SingleLinkedList singleLinkedList){
        if(singleLinkedList == null){
            return ;
        }

        Node head = singleLinkedList.getHead();

        //技能点：利用栈先进后出的特点实现
        Stack<Node> stack = new Stack<>();

        //辅助遍历游标
        Node temp = head.next;
        while (temp != null){
            stack.push(temp);

            //游标后移
            temp = temp.next;
        }

        while (stack.size() > 0) {
            Node pop = stack.pop();
            System.out.println(pop);
        }
    }


    //5、合并两个有序的单向链表，要求合并后依然有序
    //不借助辅助链表，直接链表二往链表一逐个节点插入
    //head1 1 3 5 6 7
    //head2 2 4 8
    public SingleLinkedList mergeOrder2Order(SingleLinkedList list1,SingleLinkedList list2){
        if(list1 == null || list2 == null){
            return null;
        }

        Node head1 = list1.getHead();
        Node head2 = list2.getHead();

        Node temp2 = head2.next;
        Node temp2Bak = null;
        while (temp2 != null){
            //先备份链表二的游标指向
            temp2Bak = temp2.next;

            //将temp2指向节点按值大小插入到链表一指定位置
            Node temp1 = head1.next;
            while (temp1.next != null){
                //插入两节点之间
                if(temp2.id > temp1.id  && temp2.id < temp1.next.id){
                    temp2.next = temp1.next;
                    temp1.next = temp2;
                    break;
                }
                temp1 = temp1.next;

                //特殊情况兼容 链表2中的这个节点值比链表一中的所有节点值都大
                if(temp1.next == null && temp2.id > temp1.id){
                    temp1.next = temp2;
                }
            }

            //链表二游标后移
            temp2 = temp2Bak;
        }

        return list1;
    }




    //6、合并两个乱序的单向链表，要求合并后有序 从小到大
    //创建一个辅助新链表，两个一层遍历，问题转化为拿着指定一个节点按其值大小插入到链表合适位置
    //head1 1 5 3 7 6
    //head2 8 2 4
    //newHead
    public SingleLinkedList mergeNoOrder2Order(SingleLinkedList list1,SingleLinkedList list2){
        if (list1 == null || list2 == null) {
            return null;
        }

        //获取头结点
        Node head1 = list1.getHead();
        Node head2 = list2.getHead();

        //辅助新链表
        SingleLinkedList result = new SingleLinkedList();
        Node newHead = new Node(0);
        result.setHead(newHead);

        //链表一处理
        Node temp1 = head1.next;
        Node temp1Bak = null;
        while (temp1 != null){
            //游标保存
            temp1Bak = temp1.next;

            //链表一逐个元素处理
            //通用技能点 拿着指定一个节点按其值大小插入到链表合适位置 可提取为一个独立方法
            Node newTemp = newHead;
            while (newTemp.next != null){
                //1、找位置
                if(temp1.id > newTemp.id && temp1.id < newTemp.next.id){
                    break;
                }
                if(newTemp.next == null){
                    break;
                }
                newTemp = newTemp.next;
            }

            //2、执行插入  temp1插入到newTemp后
            temp1.next = newTemp.next;
            newTemp.next  =temp1;

            //游标后移
            temp1 = temp1Bak;
        }


        //链表二处理
        Node temp2 = head2.next;
        Node temp2Bak = null;
        while (temp2 != null){
            //游标保存
            temp2Bak = temp2.next;

            //链表一逐个元素处理
            Node newTemp = newHead;
            while (newTemp.next != null){
                //找位置
                if(temp2.id > newTemp.id && temp2.id < newTemp.next.id){
                    break;
                }
                if(newTemp.next == null){
                    break;
                }
                newTemp = newTemp.next;
            }

            //temp2插入到newTemp后
            temp2.next = newTemp.next;
            newTemp.next  =temp2;

            //游标后移
            temp2 = temp2Bak;
        }
        return result;
    }






}
