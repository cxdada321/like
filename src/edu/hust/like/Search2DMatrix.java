package edu.hust.like;

/**
 * 搜索二维矩阵
 */
public class Search2DMatrix {
    public static void main(String[] args) {
        /**
         * 给你一个满足下述两条属性的 m x n 整数矩阵：
         * 每行中的整数从左到右按非严格递增顺序排列。
         * 每行的第一个整数大于前一行的最后一个整数。
         * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
         */
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}, {61, 62, 63, 64}};
        int target = 70;
        boolean res = searchMatrix(matrix, target);
        System.out.println(res);
    }
    //二分查找
    public static boolean searchMatrix(int[][] matrix, int target) {
        int blow = 0, high = matrix.length - 1;
        while (blow != high) {
            int mid = (high + blow) >>> 1;
            if (target > matrix[mid][matrix[0].length - 1]) {
                //这里做判断是防止进入死循环，因为这个不同于普通的二分查找，这里的mid是在二维数组上变化的
                if (mid == blow) {
                    blow = mid + 1;
                } else {
                    blow = mid;
                }
            } else {
               if (mid == high) {
                   high = mid - 1;
               } else {
                   high = mid;
               }
            }
        }
        return binarySearch(matrix[blow], target);
    }

    private static boolean binarySearch(int[] matrix, int target) {
        //左右指针
        int left = 0, right = matrix.length - 1;
        while (left != right) {
            int mid = (left + right) >>> 1;
            if (target >matrix[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return matrix[left] == target;
    }
}
