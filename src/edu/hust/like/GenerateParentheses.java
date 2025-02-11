package edu.hust.like;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 括号生成
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        /**
         * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
         */
        int n = 3;
        //dfs + 回溯
        //List<String> res = generateParenthesis(n);

        //插入，从最开始的n=1开始插入，利用set集合去除重复组合
        List<String> res = generateParenthesis1(n);
        res.forEach(System.out::println);
    }

    private static List<String> generateParenthesis1(int n) {
        if (n == 1) {
            List<String> result = new ArrayList<>();
            result.add("()");
            return result;
        }
        HashSet<String> res = new HashSet<>();
        for (String str : generateParenthesis1(n - 1)) {
            //对前面的所有可能的组合进行插入，会有重复，因此用set来去掉重复
            for (int i = 0; i < str.length(); i++) {
                res.add(str.substring(0, i) + "()" + str.substring(i));
            }
        }
        return new ArrayList<>(res);
    }

    //使用深度优先搜索（DFS）结合回溯来生成有效的括号组合
    //先加入左括号，再加入右括号，当左括号数量小于n时，可以加入左括号，当右括号数量小于左括号数量时，可以加入右括号
    //通过递归到底部又返回再重新进入递归就能出现很多新的组合
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        //深度搜索加回溯
        //dfs(n, n, "", res);

        //写成回溯过程
        backTrack(0, 0, new StringBuilder(), res ,n);

        return res;
    }


    private static void backTrack(int left, int right, StringBuilder curr, List<String> res, int n) {
        if (curr.length() == n * 2) {
            res.add(curr.toString());
            return;
        }

        if (left < n) {
            curr.append("(");
            backTrack(left + 1, right, curr, res, n);
            //回溯
            curr.deleteCharAt(curr.length() - 1);
        }

        if (right < left) {
            curr.append(")");
            backTrack(left, right + 1, curr, res, n);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    //回溯法（DFS）
    private static void dfs(int left, int right, String curr, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curr);
            return;
        }
        //剪枝，避免了递归进入一些不合法的分支
        //在满足条件时才继续递归进入新的分支，这样就不会浪费时间去生成不可能的或无效的括号组合
        //左括号数量小于n时，可以加入左括号
        if (left > 0) {
            //回溯过程在curr + "("这部分，递归回到这层的时候会退回原来的状态，就不需要重新再回溯了
            dfs(left - 1, right, curr + "(", res);
        }

        //右括号数量大于左括号数量时，可以加入右括号
        if (left < right) {
            dfs(left, right - 1, curr + ")", res);
        }
    }
}
