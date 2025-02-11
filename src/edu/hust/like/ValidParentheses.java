package edu.hust.like;

import java.util.Stack;

/**
 * 有效的括号
 */
public class ValidParentheses {
    public static void main(String[] args) {
        /**
         * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
         * 有效字符串需满足：
         * 左括号必须用相同类型的右括号闭合。
         * 左括号必须以正确的顺序闭合。
         * 每个右括号都有一个对应的相同类型的左括号。
         */
        String s = "[()]";
        //boolean res = isValid1(s);
        boolean res = isValid2(s);
        System.out.println(res);
    }

    //利用字符数组来模拟堆栈，即用索引来模拟栈顶指针
    private static boolean isValid2(String s) {
        char[] stack = new char[s.length()];
        int top = -1;
        for (char c : s.toCharArray()) {
            if (top > -1) {
                switch (c) {
                    case ')':
                        if (stack[top] == '(') {
                            top--;
                        } else {
                            return false;
                        }
                        break;
                    case ']':
                        if (stack[top] == '[') {
                            top--;
                        } else {
                            return false;
                        }
                        break;
                    case '}':
                        if (stack[top] == '{') {
                            top--;
                        } else {
                            return false;
                        }
                        break;
                    default:
                        stack[++top] = c;
                        break;
                }
            }
            else {
                stack[++top] = c;
            }
        }
        return top == -1;
    }

    //利用栈解决，即遍历字符串，对于识别到的左括号就入栈，对于识别到的右括号就出栈，如果出栈的括号和当前括号不匹配，就返回false
    public static boolean isValid1(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)== '(' || s.charAt(i)== '[' || s.charAt(i)== '{') {
                stack.push(i);
            } else {
                if (stack.empty()) {
                    //右括号多于左括号
                    return false;
                }
                if (s.charAt(i) == ')' && s.charAt(stack.peek()) != '(') {
                    return false;
                }
                if (s.charAt(i) == ']' && s.charAt(stack.peek()) != '[') {
                    return false;
                }
                if (s.charAt(i) == '}' && s.charAt(stack.peek()) != '{') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }
}
