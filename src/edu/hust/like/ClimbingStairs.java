package edu.hust.like;

/**
 * 爬楼梯
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(climbStairs(n));
    }
    public static int climbStairs(int n) {
        //记录到达每个台阶需要总步数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
