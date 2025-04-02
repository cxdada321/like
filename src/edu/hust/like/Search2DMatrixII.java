package edu.hust.like;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Executors;

/**
 * 搜索二维矩阵 II
 */
public class Search2DMatrixII {
    static Stack<Integer> stack = new Stack<>();
    static int[][] memory;
    static boolean flag;
    public static void main(String[] args) {
        /**
         * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
         * 每行的元素从左到右升序排列。
         * 每列的元素从上到下升序排列。
         */
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30},};
        int target = 40;
        //dfs+剪枝，可能递归次数太多，时间复杂度高
//        boolean res = searchMatrix(matrix, target);
        //z字形查找
//        boolean res = searchMatrix1(matrix, target);
        boolean res = searchMatrix2(matrix, target);
        System.out.println(res);
    }

    //切分成多个矩阵
    private static boolean searchMatrix2(int[][] matrix, int target) {
        //防止空矩阵
        if (matrix.length == 0) {
            return false;
        }
        int row = matrix.length, col = matrix[0].length;
        int minLength = Math.min(row, col);
        int index = 0;
        while (index < minLength) {
            if (matrix[index][index] < target) {
                index++;
            } else if (matrix[index][index] > target){
                return findTarget(matrix, index, target);
            } else {
                return true;
            }
        }
        //上面的部分矩阵如果没有搜索到结果，就要从剩余的矩阵继续搜索
        int[][] remain;
        if (row < col) {
            remain = new int[row][col - minLength];
            for (int i = 0; i < row; i++) {
                remain[i] = Arrays.copyOfRange(matrix[i], minLength, col);
            }
        } else {
            remain = new int[row - minLength][col];
            for (int i = 0; i < row - minLength; i++) {
                remain[i] = Arrays.copyOfRange(matrix[i + minLength], 0, col);
            }
        }
        return searchMatrix2(remain ,target);
    }

    private static boolean findTarget(int[][] matrix, int index, int target) {
        int temp = index - 1;
        while (temp >= 0) {
            if (matrix[index][temp] == target || matrix[temp][index] == target) {
                return true;
            }
            if (matrix[index][temp] < target && matrix[temp][index] < target) {
                return false;
            }
            temp--;
        }
        return false;
    }

    //z字形查找的本质就是，从矩阵开始的位置比较特殊，从右上角开始，这样当前位置左边数是比当前位置值小，而下面的数比当前位置大
    //这样用target去与当前位置比较的时候，就可以确定一个方向，这样就避免了从左上方开始，位置右边和下面都是大的数，导致两边都需要遍历
    private static boolean searchMatrix1(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int x = 0, y = col - 1;
        //如果超出边界，即说明没有存在的target
        while (x < row && y >= 0) {
            //当前位置小，说明要往下找
            if (matrix[x][y] < target) {
                x++;
            } else if (matrix[x][y] > target) {
                y--;
            } else {
                return true;
            }

        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        memory = new int[matrix.length][matrix[0].length];
        //利用深度搜索的方式
        dfs(matrix, target, 0, 0);
        return flag;

    }

    //如果用bfs，则是用迭代的方式结合栈来保存每个节点的邻居，并且取出来用，相当于递归的逻辑
    //可以利用dfs方式，每到一个节点就比较当前两个节点的值，如果相等则返回，如果不相等，则从其邻居中小于target的值选择一个入口进行递归
    //并且用一个数组记录每个位置是否遍历过，记忆化搜索
    private static void dfs(int[][] matrix, int target, int row, int col) {

        if (matrix[row][col] == target) {
            flag = true;
            return;
        }
        memory[row][col] = 1;

        //检查当前位置右边是否等于目标值或者小于目标值
        //如果其邻居位置没有遍历过
        if (row + 1 < matrix.length && memory[row + 1][col] == 0) {
            int num = matrix[row + 1][col];
            memory[row + 1][col] = 1;
            if (num == target) {
                flag = true;
                return;
            } else if (num < target) {
                dfs(matrix, target, row + 1, col);
            }
        }
        if (col + 1 < matrix[0].length && memory[row][col + 1] == 0) {
            int num = matrix[row][col + 1];
            memory[row][col + 1] = 1;
            if (num == target) {
                flag = true;
            } else if (num < target) {
                dfs(matrix, target, row, col + 1);
            }
        }
    }
}
