package com.andy.stack;

/**
 * 数组模拟栈
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/20
 * @version: 1.0.0
 */
public class ArrayStack {

    //栈最大容量
    private int maxSize;
    //数组模拟栈 数据存在此数组里
    private int[] arr;
    //栈顶游标
    private int top;

    /**
     * new对象 初始化
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        top = -1;
    }

    /**
     * 辅助判断
     * @return
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }
    public boolean isEmpty(){
        return top == -1;
    }


    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        if(isFull()){
            System.out.println("栈满，入不了了");
            return;
        }
        top ++;
        arr[top] = value;
    }

    /**
     * 出栈（推出栈顶一个元素）
     * @return
     */
    public int pop(){
        if(isEmpty()){
            System.out.println("栈空，出不了了");
        }
        int res = arr[top];
        top --;
        return res;
    }

    /**
     * 察看栈顶元素值（偷看一眼不出栈）
     * @return
     */
    public int peek(){
        return arr[top];
    }



    /**
     * 打印
     */
    public void show(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top ; i >= 0; i--) {
            System.out.printf("arr[%d]:%d\n",i,arr[i]);
        }
    }

}
