package hust.edu;

public class test17 {
    public static void main(String[] args) {
        /**
         * 给定一个数组 prices，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
         * 你只能选择 某一天 买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
         * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
         */
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int max = 0, min = prices[0];
        //利用动态规划的知识，最大收益是前些天的最大收益或者是今天卖出的最大收益
        //买的那天一定是卖的那天最小的值，维护卖的时候最小的值
        for (int i = 1; i < prices.length; i++) {
            if (min > prices[i]) {
                //当天比买入还小，不卖，利润为0
                min = prices[i];
            } else {
                //当天比买入大，卖出，计算利润
                max = Math.max(max, prices[i] - min);
            }
        }
        return max;
    }
}
