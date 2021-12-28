package com.andy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/28
 * @version: 1.0.0
 */
public class Infix2Suffix {//中缀转后缀表达式


    public static void main(String[] args) {
        String str = "12+((2+3)*4)-5";
        //中缀"12+((2+3)×4)-5"  ==> list [12,+,(,(,2,+,3,),*,4,),-,5]
        System.out.println(new Infix2Suffix().parseInfix2List(str));
        //中缀"12+((2+3)*4)-5" ==> 后缀"12 2 3 + 4 * + 5 -"
        System.out.println(new Infix2Suffix().infix2SuffixExpression(str));
    }

    //利用两个辅助栈(符号栈s1、中间结果栈s2) ，
    //从左至右扫描中缀表达式元素，
    //1、遇到数时，则直接压入s2
    //2、遇到左括号时，则直接压入 s1
    //3、遇到右括号时，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，然后将这一对括号丢弃
    //4、遇到运算符时，
    //首个运算符直接入符号栈，栈顶为左括号"(" 直接入符号栈s1，
    //若当前运算符优先级 大于 s1栈顶运算符, 则直接入符号栈s1，
    //若当前运算符优先级 小于等于 s1栈顶运算符, 则将s1栈顶的运算符弹出并加入到s2中，循环比较，直到不满足，把当前运算符入符号栈s1，
    //5、重复扫完整个表达式后将 s1 中剩余的运算符依次弹出并压入 s2 ，s2结果逆序就是所求的后缀表达式
    public String infix2SuffixExpression(String infixStr) {
        //预处理 中缀表达式转list
        List<String> list = parseInfix2List(infixStr);

        //符号栈
        Stack<String> s1 = new Stack<>();
        //储存中间结果栈 s2
        Stack<String> s2 = new Stack<>();
        for (String item : list) {
            //1、遇到数时，则直接压入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                //2、遇到左括号时，则直接压入 s1
                s1.push(item);
            } else if (item.equals(")")) {
                //3、遇到右括号时，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，然后将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //将 ( 弹出 s1 栈 ，实现丢弃小括号效果
                s1.pop();
            } else {
                //4、遇到运算符时，
                //若当前运算符优先级 小于等于 s1栈顶运算符, 则将s1栈顶的运算符弹出并加入到s2中，循环比较，直到不满足，把当前运算符入符号栈
                while (s1.size() != 0 && getPriority(s1.peek()) >= getPriority(item)) {
                    s2.add(s1.pop());
                }
                //运算符肯定要入s1栈
                s1.push(item);
            }
        }

        //5、重复扫完整个表达式后将 s1 中剩余的运算符依次弹出并压入 s2
        while (s1.size() != 0) {
            s2.push(s1.pop());
        }

        String result = "";
        while (s2.size() != 0) {
            String pop = s2.pop();
            result = pop + " " + result;
        }
        return result;
    }




    //辅助方法


    /**
     * 通用辅助方法 中缀表达式字符串元素拆解，支持多位数、小括号
     * 中缀"12+((2+3)×4)-5"  ==> list [12,+,(,(,2,+,3,),*,4,),-,5]
     * @param expression
     * @return
     */
    public List<String> parseInfix2List(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0;
        do {
            //非数字，直接加进去
            if ( !isNumChar(expression.charAt(i)) ) {
                list.add("" + expression.charAt(i));
                i++;
            } else {
                //数字，需要考虑多位数
                String numItemStr = "";
                //共用游标，游标后移，正好移到下一个非数字进入外层循环判断
                while (i < expression.length() && isNumChar(expression.charAt(i))) {
                    numItemStr += expression.charAt(i);
                    i++;
                }
                list.add(numItemStr);
            }
        } while (i < expression.length());
        return list;
    }

    /**
     * 判断指定char是不是数字
     * @param ch
     * @return
     */
    public boolean isNumChar(char ch){
        //0 - 9
        return ch >= 48 && ch <= 57;
    }

    /**
     * 获取运算符优先级
     * 自定义数值大的优先级高
     * @param ch
     * @return
     */
    public int getPriority(String ch){
        int res = -1;
        switch (ch){
            case "+":{
                res = 1;
                break;
            }
            case "-":{
                res = 1;
                break;
            }
            case "*":{
                res = 2;
                break;
            }
            case "/":{
                res = 2;
                break;
            }
            default:{
            }
        }
        return res;
    }


}
