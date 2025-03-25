package edu.hust.like;

import java.util.Arrays;

/**
 * 最短回文串
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        /**
         * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
         * 找到并返回可以用这种方式转换的最短回文串。
         */
        String s = "a";
        //重复字符过多的时候会出现超出内存溢出
//        String res = shortestPalindrome(s);
        //采用KMP算法
        String res = shortestPalindrome1(s);
        //输出：
        System.out.println(res);
    }

    private static String shortestPalindrome1(String s) {
        return "";
    }

    //还可以再集合记忆化搜索，即dp的值除了0，1还可以是0，1，-1，-1表示不是回文数
    //首先把字符串每个部分是否是回文数用动态规划的方式保存下来，dp[i][j]表示i到j是否是回文数
    //dp[i][j]是否是回文数取决于i和j位置元素是否相同，以及dp[i+1][j-1]是否是回文数，逐渐往内部判断
    //dp[i][j] = dp[i+1][j-1] && s[i] == s[j],i >= j的情况是必为回文数可以先赋值为true
    //将i作为竖轴，因为新的数是从靠近下面层的数得来的，因此从后往前遍历
    //然后在前面从挨个添加和末尾相同的元素，逐渐往内部找是否是回文数，如果是则返回
    public static String shortestPalindrome(String s) {
        int n  = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] == 1 && s.charAt(i) == s.charAt(j) ? 1 : 0;
            }
        }
        System.out.println(dp[0][0]);
        String temp = "";
        //从末尾开始检查是否要在前面添加元素，添加一个后需要判断是否是回文数的区间就向左移动一位
        for (int i = n - 1; i >= 0; i--) {
            if (dp[0][i] == 1) {
                break;
            }
            temp += s.charAt(i);
        }
        return temp + s;
    }
    //快速判断是否是回文数，利用动态规划的方式
}
