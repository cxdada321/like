package edu.hust.like.hard.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 删除无效的括号
 */
public class RemoveInvalidParentheses {
    private static List<String> res = new ArrayList<>();
    public static void main(String[] args) {
        /**
         * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
         * 返回所有可能的结果。答案可以按 任意顺序 返回。
         */
        String s = "(a)())()";
        //dfs + 剪枝
//        List<String> res = removeInvalidParentheses(s);
        //bfs
        List<String> res = removeInvalidParentheses1(s);
        res.forEach(System.out::println);
    }

    //还可以采用bfs的思想，按照第一轮删除每个位置一次，结果保留，如果结果里面有符合要求的字符串，即返回结果，利用set去重
    //如果第一轮没有找到，说明至少要删除两个，因此把上一轮的结果到第二轮每个位置再删除一次，以此重复
    private static List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();
        //保存上一层删除完了后的结果，用于下一层继续删除
        Set<String> set = new HashSet<>();
        set.add(s);
        while (true) {
            set.forEach(str -> {
                if (isValid(str)) {
                    res.add(str);
                }
            });
            //由于是最少删除，因此提前找到就可以返回
            if (!res.isEmpty()) {
                return res;
            }
            //为了剔除不同层的字符串，用temp保存当前层的结果
            Set<String> temp = new HashSet<>();
            set.forEach(str -> {
                for (int i = 0; i < str.length(); i++) {
                    if (i != 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    if (str.charAt(i) =='(' || str.charAt(i) == ')') {
                        temp.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            });
            set = temp;
        }
    }

    //从左往右遍历的时候，右括号的个数不能大于左括号的个数，这样才有可能组成有效字符串
    //可以利用这个特性对深度搜索剪枝

    public static List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        //left和right里面就是至少要删除的左右括号的个数
        dfs(s, 0, left, right);
        return res;
    }

    //递归方法里面就是按照left和right的个数来删除括号，如果当前位置的括号删除了，就递归进去，把删除剩下的字符串拼接在一起
    //如果不需要删除了，就检查是否是合法字符串，如果不是则返回上一层
    //如果还需要删除，就继续递归，从当前位置继续往后找，并且通过回溯的方式遍历所有可能
    //删除括号可能会有重复的，对于连续相同的括号可以跳过，或者采用set去重
    private static void dfs(String s, int index, int left, int right) {
        //递归出口，即不再需要删除括号
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }
        //递归删除每个所有字符
        for (int i = index; i < s.length(); i++) {
            //对于连续重复出现的字符串不需要重复删除，减少递归次数
            if (i != index && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            //如果需要删除的个数大于剩余可遍历删除个数，即不能找到了，直接返回，剪枝
            if (left + right > s.length() - i) {
                return;
            }
            //删除当前左括号
            if (left > 0 && s.charAt(i) == '(') {
                //left在递归里面减就不需要外面重新回溯了
                dfs(s.substring(0, i) + s.substring(i + 1), i ,left - 1, right);
            }
            if (right > 0 && s.charAt(i) == ')') {
                //删除当前右括号
                dfs(s.substring(0, i) + s.substring(i + 1), i, left, right - 1);
            }
        }
    }

    private static boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count > 0) {
                    count--;
                } else {
                    return false;
                }
            }
        }
        return count == 0;
    }


}
