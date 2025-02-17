package edu.hust.like;

/**
 * 不同路径
 */
public class UniquePaths {
    public static void main(String[] args) {
        /**
         * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
         * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
         * 问总共有多少条不同的路径？
         */
        int m = 4, n = 4;
//        int res = uniquePaths(m, n);
        int res = uniquePathsMath(m, n);
        System.out.println(res);
    }

    //采用组合数学解决
    //从左上角到右下角的过程中，需要移动 m+n−2 次，其中有 m−1 次向下移动，n−1 次向右移动。
    //因此路径的总数，就等于从 m+n−2 次移动中选择 m−1 次向下移动的方案数，即用概率论的知识即可，即C(m+n-2, m-1)
    private static int uniquePathsMath(int m, int n) {
        long res = 1;
        int k = m - 1;
        //减少遍历次数，因为是对称的选哪个都可以
        if (m - 1 > n - 1) {
            k = n - 1;
        }
        for (int i = 1; i <= k; i++) {
            //这里是为了把结果转为long类型，防止后面做除法的时候整除产生误差
            res = res * (m + n - 2 - k + i) / i;
        }
        return (int) res;
    }

    //可以采用回溯的思想做，每次到不同的入口就进入，一直找到终点后算是一跳路径，随后返回上个入口尝试别的入口，直到递归到最开始的入口即可
    //采用动态规划的思想，即把到达每个位置的路径数都找出来，到达终点的路径数即为到达旁边两个邻居的路径数之和
    //相当于只需要从一开始就把每个位置根据其邻居的路径数计算出来，最后返回终点的路径数即可
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //到达起始位置路径数为1
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //判断上边和左边邻居是否存在
                if (i - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
