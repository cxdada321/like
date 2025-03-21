package edu.hust.like;

import java.util.Stack;

/**
 * 字符串解码
 */
public class DecodingString {
    private static int index = 0;
    public static void main(String[] args) {
        /**
         * 给定一个经过编码的字符串，返回它解码后的字符串。
         * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
         * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
         * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
         */
        String s = "3[a2[c]]4[b]";
        //递归的方式
//        String res = decodeString(s);
        //还可以采用栈的方式，本题难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，这与栈的先入后出特性对应
        //栈里存放的是当前的字符串和重复次数，即遇到一个左括号，就把前面的res和重复次数入栈，用于之后的拼接
        //遇到右括号就可以从栈里取出之前的字符串和重复次数，与当前字符串拼接，直到遇到所有右括号，栈里就会清空了
        String res = decodeString1(s);
        System.out.println(res);
    }

    //通过栈的方式解决
    private static String decodeString1(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        int n = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                n = n * 10 + c - '0';
            } else if (c == '[') {
                stack.push(res.toString());
                stack.push(String.valueOf(n));
                //清空res和n，用于之后的拼接
                res = new StringBuilder();
                n = 0;
            } else if (c == ']') {
                //遇到右括号就把栈里的数据拿出来拼接
                int num = Integer.parseInt(stack.pop());
                StringBuilder temp = new StringBuilder(stack.pop());
                temp.append(res.toString().repeat(num));
                res = temp;
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        while (index < s.length()) {
            char c = s.charAt(index++);
            if (c >= '0' && c <= '9') {
                n = n * 10 + c - '0';
                //碰到左括号，内部的字符串可能还是带[]的，所以要把里面的字符串当作一个新字符处理
            } else if (c == '[') {
                sb.append(decodeString(s).repeat(n));
                n = 0;
                //如果遇到右括号说明内部的字符已经处理完了，直接返回
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
