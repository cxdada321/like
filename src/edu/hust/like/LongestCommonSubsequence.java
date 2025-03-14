package edu.hust.like;

/**
 * 最长公共子序列
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        /**
         * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
         * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
         * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
         * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
         */
        String text1 = "abcde", text2 = "ace";
        int res = longestCommonSubsequence(text1, text2);
        System.out.println(res);
    }
    //为什么这样动态规划可行，因为每次dp里保存的就是当前长度下的的字符串的最长子序列
    //动态方程应该是，假如出现test1[i] == test2[j]，那么dp[i][j] = dp[i-1][j-1] + 1
    //为什么呢，因为这个字母是一个公共字母，即test1[0:i - 1]和test2[0:j - 1]的最长子序列+1即得到test1[0:i]和test2[0:j]的最长子序列
    //假如不相等，那么dp[i][j] = max(dp[i-1][j], dp[i][j-1])，因为dp[i][j]涵盖了dp[i-1][j], dp[i][j-1]这两个问题表示的字符串，因此是取最大值
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] dp = new int[m + 1];
        //但是其实一维数组即可，因为新的值dp[i][j]就是在当前位置前面的dp[i][j-1]和dp[i-1][j]推出来的
        //一维数组就是一行一行获取结果，dp[j]表示当前长度下的的字符串的最长子序列
        //两个字符串不断遍历来填充dp数组
        //dp保存的是每行的最长子序列
        //横轴是text1，纵轴是text2
        for (int i = 0; i < n; i++) {
            //逆序遍历，因为要从前往后推，因为dp[j]是由dp[j-1]推出来的
            //但是存在dp[j]的值需要上一层的dp[j] 和 dp[j-1]，也需要下一层的dp[j-1]，因此不能单纯逆序
            //可以保存上层的dp[j]
            int prev = 0;
            for (int j = 0; j < m; j++) {
                //保存这一行这个位置的旧值，防止后面改变了不能保存了
                int temp = dp[j + 1];
                //不用处理第一行，因为第一行即便存在相同字符，也是1
                if (text2.charAt(i) == text1.charAt(j)) {
                    //上一层的旧值
                    dp[j + 1] = prev + 1;
                } else {
                    dp[j + 1] = Math.max(dp[j], dp[j + 1]);
                }
                //保存这一行这个位置的旧值，用于下一行计算
                prev = temp;
            }
        }
        return dp[m];
    }
}
