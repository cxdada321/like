package edu.hust.like;

/**
 * 完全平方数
 */
public class PerfectSquares {
    public static void main(String[] args) {
        /**
         * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
         * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
         */
        int n = 12;
        //过于复杂
//        int res = numSquares(n);
        //简化一些，这些数必然落在区间 [1,根号i]，我们可以枚举这些数，假设当前枚举到 j，那么我们还需要取若干数的平方，构成 i−j^2
        //因此状态转移方程dp[i] = 1 + min(dp[i−j^2])，其中 1≤j≤根号i
        int res = numSquares1(n);
        System.out.println(res);
    }

    private static int numSquares1(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            //为什么加一，因为j*j是一个平方数，所以加一
            dp[i] = min + 1;
        }
        return dp[n];
    }

    //利用动态规划的方式把每一位最小的平方数的数量保存下来，然后根据这个数量来计算下一位的最小数量
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            //如果给的值能直接开方则直接退出，减少循环次数
            if (i * i == n) {
                return 1;
            }
            //给后面可以平方的位置赋值，防止溢出
            if (i * i <= n) {
                dp[i * i] = 1;
            }
            //如果当前位置已有值则跳过
            if (dp[i] == 1) {
                continue;
            }
            //否则遍历到当前值大小的中点，找出最少数量组合
            int mid = i / 2;
            if (i % 2 != 0) {
                mid++;
            }
            //获取最小值
            int min = Integer.MAX_VALUE;
            for (int j = i - 1; j >= mid; j--) {
                min = Math.min(min, dp[j] + dp[i - j]);
            }
            //最后保存当前位的最小值
            dp[i] = min;
        }
        return dp[n];
    }
}
