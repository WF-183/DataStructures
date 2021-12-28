package com.andy.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2021/12/27
 * @version: 1.0.0
 */
public class SuffixCalculator {//后缀表达式

    public static void main(String[] args) {
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / + = 76
        String expression = "4 5 * 8 - 60 + 8 2 / +";
        System.out.println(expression + " = " + new SuffixCalculator().calculator(expression));
    }

    public int calculator(String expression) {
        List<String> list = Arrays.asList(expression.split(" "));
        Stack<Integer> stack = new Stack<>();
        //从左至右扫描表达式，
        for (String item : list) {
            //遇到数字时，将数字压入堆栈，
            if (item.matches("\\d+")) {
                stack.push(Integer.parseInt(item));
            } else {
                //遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 和 栈顶元素），并将结果入栈；
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(numCal(num1, num2, item));
            }
        }
        //重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
        return stack.pop();
    }

    //辅助方法

    /**
     * 局部计算
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int numCal(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "+": {
                res = num2 + num1;
                break;
            }
            case "-": {
                //注意顺序，num2操作num1
                res = num2 - num1;
                break;
            }
            case "*": {
                res = num2 * num1;
                break;
            }
            case "/": {
                //注意顺序，num2操作num1
                res = num2 / num1;
                break;
            }
            default: {
            }
        }
        return res;
    }

}
