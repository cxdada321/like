package edu.hust.like;

/**
 * 旋转图像
 */
public class RotateImage {
    public static void main(String[] args) {
        /**
         * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
         * 必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
         */
        //4*4的矩阵
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        //还可以用水平翻转再加上对角线翻转即可
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    //一层一层的旋转
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        dfs(0, n, matrix);
    }

    private static void dfs(int i, int n, int[][] matrix) {
        //递归出口
        if (i == n || i + 1 == n) {
            return;
        }
        for (int j = 0; j < n - 1 - i; j++) {
            //记录当前位置
            int temp = matrix[i][i + j];
            matrix[i][i + j] = matrix[n - 1 - j][i];
            matrix[n - 1 - j][i] = matrix[n - 1][n - 1 - j];
            matrix[n - 1][n - 1 - j] = matrix[i + j][n - 1];
            matrix[i + j][n - 1] = temp;
        }
        dfs(i + 1, n - 1, matrix);
    }
}
