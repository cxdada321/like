package hust.edu;

import java.util.*;

public class test11 {
    public static void main(String[] args) {
        /*给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。
        不考虑答案输出的顺序。*/
        String s = "abacbabc", p = "abc";
        getIndex(s, p).forEach(str -> System.out.print(str + " "));
    }

    private static List<Integer> getIndex(String s, String p) {
        Map<Character, Integer> iniMap = new HashMap<>();
        Map<Character, Integer> winMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        //边界处理
        if (s.length() < p.length()) {
            return list;
        }
        // 初始化窗口
        for (char c : p.toCharArray()) {
            iniMap.put(c, iniMap.getOrDefault(c, 0) + 1);
        }
        //滑动窗口
        int left = 0, right = 0, count = 0;
        while (right < s.length()) {
            if (iniMap.containsKey(s.charAt(right))) {
                winMap.put(s.charAt(right), winMap.getOrDefault(s.charAt(right), 0) + 1);
                //滑窗里的字符数目等于p里的字符数目，算是一个匹配
                if (winMap.get(s.charAt(right)).equals(iniMap.get(s.charAt(right)))) {
                    count++;
                }
            }
            right++;
            //当窗口大小等于p的长度时，判断是否匹配
            while(right - left == p.length()) {
                if (count == iniMap.size()) {
                    list.add(left);
                }
                //不相等则移动左边界
                //移除窗口里的左边界字符，如果该字符在p里，且窗口里的字符数目等于p里的字符数目，count减一
                //如果左边界字符是在p里的，则需要对窗口处理，如果不在p里，不需要处理
                if (iniMap.containsKey(s.charAt(left))) {
                    if (winMap.get(s.charAt(left)).equals(iniMap.get(s.charAt(left)))) {
                        count--;
                    }
                    winMap.put(s.charAt(left), winMap.get(s.charAt(left)) - 1);
                }
                left++;
            }
        }
        return list;
    }
}
