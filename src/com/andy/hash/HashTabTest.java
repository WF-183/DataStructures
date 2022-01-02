package com.andy.hash;

import com.andy.linkedlist.Node;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/2
 * @version: 1.0.0
 */
public class HashTabTest {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        hashTab.add(new Node(0, "tom"));
        hashTab.add(new Node(1, "jery"));
        hashTab.add(new Node(2, "andy"));
        hashTab.show();

        System.out.println("id=2的节点 " + hashTab.getById(new Node(2)));

        hashTab.del(new Node(2));
        hashTab.show();

    }

}
