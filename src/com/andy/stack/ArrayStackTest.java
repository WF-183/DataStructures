package com.andy.stack;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/20
 * @version: 1.0.0
 */
public class ArrayStackTest {

    public static void main(String[] args) {

        //测试 数组模拟栈
        ArrayStack arrayStack = new ArrayStack(4);
        System.out.println("一开始：");
        arrayStack.show();

        System.out.println("push：");
        arrayStack.push(0);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.show();

        System.out.println("pop：");
        int pop = arrayStack.pop();
        System.out.println("出栈值为： " + pop);
        arrayStack.show();


    }
}
