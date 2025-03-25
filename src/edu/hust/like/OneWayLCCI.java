package edu.hust.like;

/**
 * 面试题 01.05. 一次编辑
 */
public class OneWayLCCI {
    public static void main(String[] args) {
        /**
         * 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。
         * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑
         */
        String first = "b", second = "";
//        boolean res = oneEditAway(first, second);
        //单纯用最后的那种判断有多少个不同字母即可，不用动态规划来增加时间复杂度
        boolean res = oneEditAway1(first, second);
        System.out.println(res);
    }
    //分情况：两个字符串长度分别为n和m
    //n == m时，只能有一个字符不同或者都相同，否则返回false
    //n != m时，稍微长的字符串只有有一个多的字符，其余字符要和少的字符串相同，否则返回false
    private static boolean oneEditAway1(String first, String second) {
        int n = first.length(), m = second.length();
        if (Math.abs(n - m) > 1) {
            return false;
        }
        int count = 0;
        int i = 0, j = 0;
        while (i < n && j < m) {
            //如果相同则继续比较相同位置的下一位
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else {
                count++;
                //如果不同，则记录一次，长的字符串向挪动一位，短的不移动，再次比较下一位，允许有一次的不相同
                //这样不同时移动就是为了将长的字符串多的不同字符移去后再和短的比较，如果后面都相同就不会再增加count，说明最后就是符合条件的
                if (m > n) {
                    j++;
                } else if (m < n) {
                    i++;
                //如果长度相同也是允许一次不同的
                } else {
                    i++;
                    j++;
                }
            }
            //如果出现count>=1的情况，说明有两个字符不同，提前终止，返回false
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    //首先判断两个字符串长度是否相差大于1，如果大于1则返回false
    //其次如果两个字符串的最长公共子序列长度比最短的字符串长度还短，那么返回false
    public static boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        int n = first.length(), m = second.length();
        //获取最长子序列的长度
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            //保存上一层的dp[j]
            int prev = 0;
            for (int j = 0; j < n; j++) {
                //保存这层旧的值，防止后面更改后找不到了
                int temp = dp[j + 1];
                if (first.charAt(j) == second.charAt(i)) {
                    dp[j + 1] = prev + 1;
                } else {
                    dp[j + 1] = Math.max(dp[j], dp[j + 1]);
                }
                prev = temp;
            }
        }
        int res = Math.min(m, n) - dp[n];
        if (m != n) {
            return res < 1;
        } else {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
