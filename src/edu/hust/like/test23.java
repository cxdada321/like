package edu.hust.like;

import java.util.ArrayList;
import java.util.List;

public class test23 {
    public static void main(String[] args) {
        /**
         * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
         * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
         */
        String digits = "23";
        List<String> res = letterCombinations(digits);
        res.forEach(System.out::println);
    }
    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.isEmpty()) {
            return list;
        }
        //定义一个映射表
        String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //定义一个StringBuffer用于存储每次的结果
        StringBuffer sb = new StringBuffer();
        //回溯
        backTrack(digits, 0, sb, list, map);
        return list;
    }

    private static void backTrack(String digits, int i, StringBuffer sb, List<String> list, String[] map) {
        //出口
        if (digits.length() == i) {
            list.add(sb.toString());
            return;
        }
        //获取当前字符里面的数字
        int index = digits.charAt(i) - '0';
        //获取映射里的字符串里的每个字符来做回溯
        map[index - 2].chars().forEach(c -> {
            sb.append((char) c);
            backTrack(digits, i + 1, sb, list, map);
            //移除刚刚加入的字符，进行下一次的回溯
            sb.deleteCharAt(sb.length() - 1);
        });
    }
}
