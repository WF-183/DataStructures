package com.andy.stack;

import java.util.Stack;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/27
 * @version: 1.0.0
 */
public class InfixCalculator {//中缀表达式



    public static void main(String[] args) {
        String expression = "12+34*56-78+90";
        System.out.println(expression + " = " + new InfixCalculator().calculator(expression));
    }


    public int calculator(String expression){
        int result = 0 ;
        //数栈
        ArrayStack numStack = new ArrayStack(100);
        //符号栈
        ArrayStack operStack = new ArrayStack(100);
        //辅助游标
        int index = 0;
        char ch = ' ';
        //辅助截取每一个多位数元素
        String numItemStr = "";
        //"12+34*56-78+90"
        while (true){
            ch = expression.substring(index,index+1).charAt(0);
            //若为数字
            if(!isOperator(ch)){
                //这一位是数字肯定要拼上
                numItemStr += ch;
                //边界条件，最后一位
                if(index == expression.length() - 1){
                    //numItem入数栈，辅助变量清空
                    numStack.push(Integer.parseInt(numItemStr));
                    numItemStr = "";
                    break;
                }
                //往后多看一位，后面一位是运算符，则一个多位数元素拼接结束，若不是则进入下一个ch的拼接
                char nextCh = expression.substring(index+1,index+2).charAt(0);
                if(isOperator(nextCh)){
                    //numItem入数栈，辅助变量清空
                    numStack.push(Integer.parseInt(numItemStr));
                    numItemStr = "";
                }
            }else {//若为运算符
                //首个运算符直接入符号栈
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else {
                    //非首个运算符，当前运算符优先级 小于等于 栈顶运算符优先级，则pop出栈顶两数和栈顶一运算符运算，结果入数栈，当前运算符入符号栈
                    if(getPriority(ch) <= getPriority(operStack.peek())){
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int oper = operStack.pop();
                        numStack.push(numCal(num1,num2,oper));
                        operStack.push(ch);
                    }else {
                        //非首个运算符，当前运算符优先级 大于 栈顶运算符优先级，则直接入符号栈
                        operStack.push(ch);
                    }
                }
            }
            index ++;
        }


        while (true){
            //结束条件，符号栈空
            if(operStack.isEmpty()){
                result = numStack.pop();
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int oper = operStack.pop();
            numStack.push(numCal(num1,num2,oper));
        }
        return result;
    }





    //辅助方法

    /**
     * 判断指定char是不是运算符
     * @param ch
     * @return
     */
    public boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * 获取运算符优先级
     * 自定义数值大的优先级高
     * @param ch
     * @return
     */
    public int getPriority(int ch){
        int res = -1;
        switch (ch){
            case '+':{
                res = 1;
                break;
            }
            case '-':{
                res = 1;
                break;
            }
            case '*':{
                res = 2;
                break;
            }
            case '/':{
                res = 2;
                break;
            }
            default:{
            }
        }
        return res;
    }

    /**
     * 局部计算
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int numCal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':{
                res = num2 + num1;
                break;
            }
            case '-':{
                //注意顺序，num2操作num1
                res = num2 - num1;
                break;
            }
            case '*':{
                res = num2 * num1;
                break;
            }
            case '/':{
                //注意顺序，num2操作num1
                res = num2 / num1;
                break;
            }
            default:{
            }
        }
        return res;
    }

}
