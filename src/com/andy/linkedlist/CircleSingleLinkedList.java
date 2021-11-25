package com.andy.linkedlist;

/**
 * 单向环形链表 约瑟夫问题
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/11/22
 * @version: 1.0.0
 */
public class CircleSingleLinkedList {

    //头指针
    private Node first;

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }



    //构建指定元素个数的单向环形链表
    public void build(int nums){
        if(nums < 1){
            System.out.println("节点个数不合理");
        }

        Node temp = null;
        for (int i = 1; i <= nums; i++) {
            //第一个节点加入
            if(i == 1){
                Node node = new Node(i);
                first = node;
                temp = node;
                first.next = first;
            }else {
                //后续节点加入
                Node node = new Node(i);
                temp.next = node;//加入
                node.next = first;//拼成环
            }

            //游标后移
            temp = temp.next;
        }

    }

    //打印输出
    public void  show(){
        if(first == null || first.next == null){
            System.out.println("空链表");
        }

        Node temp = first;
        while (true){
            //输出
            System.out.printf("当前节点 %d \n" , temp.getId());
            //结束条件
            if(temp.next == first){
                break;
            }
            //游标后移
            temp = temp.next;
        }
    }






    /**
     * 根据用户输入，进行约瑟夫出圈
     * @param k 起点，从第几个小孩开始数数 == 首个节点移动k-1步到起点
     * @param m 步长m-1，数几下
     * @param n 总节点数
     */
    public  void josephu(int k, int m, int n){
        //构建指定元素个数的单向环形链表
        build(n);

        //校验入参
        if(k > n || k < 1 || n < 1 || first == null){
            System.out.println("入参不合理");
        }

        //辅助删除节点helper ：单向链表不能自我删除，总是需要靠辅助节点 ，所以我们单向链表要删除节点时，总是找到待删除节点的前一个节点
        //辅助游标节点temp ：辅助遍历游标，first指向不动，就指向单向环形链表的首个节点
        Node helper = first;
        Node temp = first;

        //先让helper指向temp的前一个节点，后续操作中helper也始终保持指向待删除节点temp的前一个节点，用以辅助删除temp节点
        while (true){
            if(helper.next == temp){
                break;
            }
            helper = helper.next;
        }

        //处理起点
        for (int i = 0; i < k - 1 ; i++) {
            temp = temp.next;
            helper = helper.next;
        }

        while (true){
            //循环结束条件 还剩一个节点
            if(temp.next == temp){
                System.out.printf("出圈完毕，还剩最后一个节点 %d",temp.getId());
                break;
            }

            //执行一次删除 无限重复此过程 直到还剩一个节点
            //移动temp到删除位置
            for (int i = 0; i < m - 1; i++) {
                temp = temp.next;
                helper = helper.next;
            }
            //删除temp节点
            System.out.printf("该节点被删除 %d \n",temp.getId());
            helper.next = helper.next.next;
            temp = temp.next;
        }
    }



}
