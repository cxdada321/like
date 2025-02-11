package edu.hust.like;

import java.util.HashMap;

/**
 * 最小覆盖子串
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        /**
         * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
         * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
         */
        String s = "ADOBECODEBANC", t = "ABC";
        //String result = minWindow(s, t);

        String result = minWindowBetter(s, t);
        System.out.println(result);
    }

    //改用 int[128] 数组，优化时间和空间复杂度
    private static String minWindowBetter(String s, String t) {
        //转换为数组
        char[] sArr = s.toCharArray(), tArr = t.toCharArray();
        int sLen = sArr.length, tLen = tArr.length;
        int[] hash = new int[128];
        //初始化处理数组里的元素，便于之后识别
        for (char c : tArr) {
            hash[c]--;
        }
        String res = "";
        for(int left = 0, right = 0, count = 0; right < sLen; right++) {
            hash[sArr[right]]++;
            //如果hash[right] <= 0，说明当前字符是t中的字符
            if (hash[sArr[right]] <= 0 ) {
                count++;
            }
            //如果count == tLen，说明当前窗口包含了t中的所有字符，并且当前窗口第一位即使是t中的字符，但是多了，所以要去掉
            while (count == tLen && hash[sArr[left]] > 0) {
                hash[sArr[left++]] --;
            }
            //如果符合要求后，长度比之前的长度小，更新res
            if (count == tLen && (res.equals("") || res.length() > right - left + 1)) {
                res = s.substring(left, right + 1);
            }
        }
        return res;
    }

    //利用滑动窗口解决，右指针扩张，等包含所有对应t的字符后，左指针收缩，直到不满足条件，然后再次扩张右指针
    private static String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        //把t中的字符放入need中
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0 ) + 1);
        }
        //左右指针
        int left = 0, right = 0;
        //window中满足need条件的字符个数
        int count = 0;
        //最小覆盖子串的起始索引和长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {
            //当前字符
            char c = s.charAt(right);
            right++;//右指针右移，此时right 代表窗口的右边界的下一位，不属于窗口因此后面长度计算是正确的
            //如果当前字符是t中的字符，就把它放入window中
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                //如果当前字符在window中的个数等于need中的个数，count++
                if (window.get(c).equals(need.get(c))) {
                    count++;
                }
            }

            //缩小边界寻找最优解
            //计算当前窗口长度，如果更短，则更新 start 位置和 len 记录
            //left 右移缩小窗口，并更新 window，如果 window[d] 下降到 need[d] 之下，则 count--
            while (count == need.size()) {
                //比较的方式得到最小长度
                if (len > right - left) {
                    len = right - left;
                    start = left;
                }

                //开始缩小窗口
                char d = s.charAt(left);
                left++;
                //如果缩小的位置是need中的一部分，则窗口中要移除对应内容
                if (need.containsKey(d)) {
                    //避免在 window[d] > need[d] 时就减少 count，从而影响收缩窗口的过程，错失更优解
                    if (window.get(d).equals(need.get(d))) {
                        count--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
