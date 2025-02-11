package edu.hust.like;

public class test20 {
    public static void main(String[] args) {
        /**
         *给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
         * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
         * 你可以认为每种硬币的数量是无限的。
         */
        int[] coins = {2};
        int amount = 0;
        System.out.println(coinChange(coins, amount));
    }
    public static int coinChange(int[] coins, int amount) {
        //利用动态规划的思想，把每个金额的最小硬币数存储在dp数组中，dp[i]表示金额为i时的最小硬币数
        //这样就能通过前面的结果推导出后面的结果
        int[] dp = new int[amount + 1];
        //定义第一位初始化为0，表示金额为0时，硬币数为0
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            //初始化每个金额的硬币数比全部用1元硬币还多，所以初始化为最大值
            dp[i] = amount + 1;
            for (int coin : coins) {
                //当前金额大于等于硬币面值时才计算
                if (i >= coin) {
                    dp[i]  = Math.min(dp[i] , dp[i - coin] + 1);
                }
            }
        }
        //如果最后还是最大值，说明没有硬币组合能组成总金额，返回-1
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
