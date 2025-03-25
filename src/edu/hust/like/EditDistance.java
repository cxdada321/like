package edu.hust.like;

/**
 * 编辑距离
 */
public class EditDistance {
    public static void main(String[] args) {
        /**
         * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
         * 你可以对一个单词进行如下三种操作：
         * 插入一个字符
         * 删除一个字符
         * 替换一个字符
         */
        String word1 = "horse", word2 = "ros";
        int res = minDistance(word1, word2);
        System.out.println(res);
    }

    //和最长公共子序列有点类似，但是这里是求最小操作数，所以动态规划方程不同
    //同样是用动态规划，横轴确定一个字符串目标字符串x，纵轴源字符串y不断遍历
    //由于是三种操作字符串，即源字符串在每次遍历到一个新字符的时候判断对这个字符如何操作，以此来实现到最后的时候获取的是最小操作数
    //用dp[i][i]来保存前i个字符到前i个字符的最小操作数
    //动态方程：假如x字符串遍历到一个新的字符c1，然后横轴遍历到c1
    //一：如果c1==c2，则不需要操作，直接等于前面没有加这个字符的时候的最小操作数
    //没有加上这个字符，即两个字符串长度都向前退一步的位置，dp[i][i] == dp[i - 1][i - 1]
    //二：如果c1!=c2，则需要对c1新的那个字符进行操作，有三种操作，插入，删除，替换
    //插入：假设经过dp[i][j - 1]次操作已经将 word1 的前 i 个字符转换成了 word2 的前 j-1 个字符，然后通过在 word1 的末尾插入一个字符，
    //使得 word1 的前 i 个字符最终与 word2 的前 i 个字符相同。这个插入操作需要一次额外的操作，所以是 dp[i][j-1] + 1
    //删除：假设经过dp[i - 1][j]次操作已经将 word1 的前 i-1 个字符转换成了 word2 的前 j 个字符，然后通过删除 word1 的第 i 个字符，
    //使得 word1 的前 i个字符最终与 word2 的前 i个字符相同。这个删除操作需要一次额外的操作，所以是 dp[i-1][j] + 1
    //替换：假设经过dp[i - 1][j - 1]次操作已经将 word1 的前 i-1 个字符转换成了 word2 的前 j-1 个字符，仅需将 word1 的第 i 个字符替换为 word2 的第 j 个字符，
    //使得 word1 的前 i 个字符最终与 word2 的前 j 个字符相同。这个替换操作需要一次额外的操作，所以是 dp[i-1][j-1] + 1

    //最终取三次操作中的最小值
    public static int minDistance(String word1, String word2) {
        int n = word2.length(), m = word1.length();
        //因为是将word1换word2，所以需要操作的是word1，因此竖轴是word2，横轴是word1，即不断遍历word1来满足word2
        int[][] dp = new int[n + 1][m + 1];
        //对dp边界初始化
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //假如遍历的字母相同，那么不需要操作，直接等于前面没有加这个字符的时候的最小操作数
                if (word1.charAt(j) == word2.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])) + 1;
                }
            }
        }
        return dp[n][m];
    }
}
