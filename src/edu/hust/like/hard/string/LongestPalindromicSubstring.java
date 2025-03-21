package edu.hust.like.hard.string;

/**
 * 最长回文子串
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        /**
         * 给你一个字符串 s，找到 s 中最长的 回文 子串
         */
        String s = "abbd";
        String res = longestPalindrome(s);
        System.out.println(res);
    }

    //利用动态规划的方式，dp[i][j]表示s[i:j]是否是回文串，在判断为一个回文串的时候，记录最长的回文串，这样就能在求出所有动态规划的值后得到最长回文串
    //还可以利用中心扩展的方式，从每个字符开始，向两边扩展，判断是否是回文串，这样可以得到最长回文串，注意扩散要分为奇数和偶数两种情况
    //即扩展的时候如果出现两个相同的字符，就要继续扩展，直到出现不一样的字符，返回扩展的长度，并且利于入口和长度来计算回文串的起始位置和长度

    //扩展的时候会发现前面已经扩展过的回文串，可以利用这个回文串的对称性来减少扩展的次数
    //即manacher算法，利用回文串的对称性，可以减少扩展的次数，这样就可以在O(n)的时间复杂度内求出最长回文串
    //利用的是每个位置扩散的时候，记录扩散的臂长length，到了一个新位置的时候就可以利用这个臂长来减少扩散的次数
    //假如当前在j，臂长为length，现在一个新的i的臂长计算，如果j+length>i，找到i关于j的对称点，即2j-i，假使这个点臂长为n
    //可以想象，i和2j-i的臂长是一样的，但是不能超出j的臂长，因为i和2j-i是关于j对称的，那么i的臂长就是min(j+length-i,n)
    //所以i点拓展的时候可以跳过i到i+min(j+length-i,n)的位置，从i+min(j+length-i,n)+1开始拓展
    //只需要记录右臂在最右边的回文串的中心作为j，至于奇偶的问题，可以向字符串的头尾以及每两个字符中间添加一个特殊字符 #，比如字符串 aaba 处理后会变成 #a#a#b#a#
    public static String longestPalindrome(String s) {
        //定义右边界和中心
        int right = 0, center = 0;
        //定义最长回文串的起始位置和停止位置
        int start = 0, end = 0;
        int n = s.length();

        //初始化字符串，将字符串全部转为奇数个字符，由于进行了初始化，就不需要像之前拓展一样需要分为两个字符和一个字符拓展
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        sb.append("#");
        s = sb.toString();
        n = s.length();

        //记录每个位置的臂长
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int curArmLength;
            //如果当前位置在前一个中心的右边界内就可以计算其对称位置来减少扩展次数
            if (right >= i) {
                int j = 2 * center - i;
                //即最小臂长最大不能超过右边界
                int minArmLength = Math.min(dp[j], right - i);
                curArmLength = expand(s, i - minArmLength - 1, i + minArmLength + 1);
            } else {
                //如果不在右边界内，就直接扩展，为什么不用两个字符拓展，因为已经初始化了，所以只需要一个字符拓展
                curArmLength = expand(s, i, i);
            }
            dp[i] = curArmLength;
            //更新右边界和中心
            if (curArmLength + i > right) {
                right = curArmLength + i;
                center = i;
            }
            //更新最长回文串的起始位置和停止位置
            if (2 * curArmLength + 1 > end - start + 1) {
                end  = i + curArmLength;
                start = i - curArmLength;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) != '#') {
                res.append(s.charAt(i));
            }
        }
        return res.toString();

    }

    private static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //这里right - left - 1返回的是回文串的长度，由于这个回文串肯定是奇数个字符，所以减一或者不减一都可以
        return (right - left - 1) / 2;
    }
}
