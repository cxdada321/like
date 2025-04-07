package edu.hust.like;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最长有效括号
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        String s = "()(()()";
        //左右遍历的方式
//        int res = longestValidParentheses(s);
        //动态规划的方式
//        int res = longestValidParentheses1(s);
        //栈的方式
        int res = longestValidParentheses2(s);
        System.out.println(res);
    }

    private static int longestValidParentheses2(String s) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if ()
            }
        }
    }

    //dp[i]表示以i索引结尾的最长有效括号的长度，因此只能是')'，不可能是'('结尾
    //情况一：假如s[i - 1]是'('，则dp[i] = dp[i - 2] + 2
    //情况二：假如s[i - 1]是')'，那dp[i - 1]表示的是以i - 1结尾的最长有效括号的长度
    //假如s[i - 1 - dp[i - 1]]是'('，那dp[i] = dp[i - 1 - dp[i - 1] - 1] + dp[i - 1] + 2
    private static int longestValidParentheses1(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - 1 - dp[i - 1] - 1 >= 0 ? dp[i - 1 - dp[i - 1] - 1] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    //分左右两次遍历，目的是为了防止从左到右遍历的时候，左括号大于右括号，导致无法计算结果的情况
    //每次左括号数量被减为0即记录一次当前有效括号数量，如果右括号数量大于左括号，即更新数量重新计算
    public static int longestValidParentheses(String s) {
        int res = 0, temp = 0, count = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
                temp++;
            }
            if (count == 0) {
                res = Math.max(res, 2 * temp);
            } else if (count < 0) {
                count = 0;
                temp = 0;
            }
        }
        count = 0;
        temp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                count++;
            } else {
                count--;
                temp++;
            }
            if (count == 0) {
                res = Math.max(res, 2 * temp);
            } else if (count < 0) {
                count = 0;
                temp = 0;
            }
        }
        return res;
    }
}
