package edu.hust.like;

/**
 * 最小路径和
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        /**
         * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
         * 说明：每次只能向下或者向右移动一步。
         */
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
//        int res = minPathSum(grid);
        //这个动态规划过程还可以优化时间复杂度
        int res = minPathSum1(grid);
        System.out.println(res);
    }

    //优化时间复杂度，动态数组不需要一整个表，只需要每一行，一行一行的更新，因为每一行dp只需要上面一行的dp和前一个数的dp
    //这样一行一行更新到最后行，结果就是dp数组的末尾
    private static int minPathSum1(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        //第一行给dp赋初值
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }

        //现在的dp表示了grid第一行每个数字当前的最小和
        //现在用dp来求后面每一行的dp，每行都是用上一行的dp来更新dp
        for (int i = 1; i < row; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[col - 1];
    }

    //利用动态规划，每次只能向下或者向右移动一步，那么到达每个位置的最小路径和就是上面和左边的最小路径和加上当前位置的值
    //如果还要返回路径，即从右下角开始，每次选择上面和左边的最小值，然后回溯到左上角
    public static int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        //每个位置上的最小值就是当前位置的值加上左边或者上面的值其中的最小值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0 && i != 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i != 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
