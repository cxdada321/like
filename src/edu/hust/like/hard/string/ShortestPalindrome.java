package edu.hust.like.hard.string;

import java.util.Arrays;

/**
 * 最短回文串
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        /**
         * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串
         */
        String s = "aaacecaaa";
        String res = shortestPalindrome(s);
        System.out.println(res);
    }
    //利用字符串匹配来解决，即构建pmt表，然后利用pmt表来匹配字符串
    //pmt表示前i + 1个字母的最长前缀和后缀相等的长度
    //记s为原字符串，s^为s的逆序字符串，则s1是s的前缀，则s1^是s^的后缀
    //如果s1是回文串，即s1=s1^
    //就可以通过把s作为模式串构建pmt表，然后把s^作为查询串，然后匹配s和s^
    //从s^首字母开始用pmt表来匹配，最后s^字母都匹配完，pmt上移动的位数就是s^后缀的长度，即s前缀的最长回文串
    public static String shortestPalindrome(String s) {
        int n = s.length();
        int[] pmt = new int[n];
        buildPMT(s, pmt);
        //构建好pmt作为模式串，把反序后的字符串作为查询串
        //记录模式串在查询串匹配的长度，最后用来作为翻转的起始位置
        int best = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (best > 0 && s.charAt(best) != s.charAt(i)) {
                best = pmt[best - 1];
            }
            if (s.charAt(best) == s.charAt(i)) {
                best++;
            }
        }
        //最后的结果就是s的best位置到末尾的字符串翻转后加到s前面
        return new StringBuilder(s.substring(best)).reverse().append(s).toString();
    }

    private static void buildPMT(String s, int[] pmt) {
        pmt[0] = 0;
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            //这里是利用pmt表前后缀相等的长度来匹配字符串
            //因为j上一个位置记录的是前一个字符的最长前缀和后缀相等的长度，然后用这个长度作为索引，新位置的前面的字符就相当于原位置前面已经匹配了部分的字符
            //假如j为3，表示已经pmt表的前三个字符和查询串前面三个字符aba匹配了
            //但是新的字符a和查询串b当前字符不同，这时候回溯的时候发现pmt[2]即aba前面三个字符里最长前后缀相同长度为2，说明j = 2的位置前面一个字符和查询串前面一个字符相同，可以从当前位置开始
            //这样就可以减少回溯的次数
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pmt[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pmt[i] = j;
        }
    }
}
