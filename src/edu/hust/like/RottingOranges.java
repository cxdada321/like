package edu.hust.like;

import java.util.Stack;

/**
 * 腐烂的橘子
 */
public class RottingOranges {

    public static void main(String[] args) {
        /**
         * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
         * 值 0 代表空单元格；
         * 值 1 代表新鲜橘子；
         * 值 2 代表腐烂的橘子。
         * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
         * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
         */
        int[][] grid = {{0,0}};
//        int time = orangesRotting(grid);
        int time = orangesRottingDFS(grid);
        System.out.println(time);
    }

    private static int orangesRottingDFS(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        //深度优先搜索腐烂的橘子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    dfs(grid , i, j, 3);
                }
            }
        }
        int res = -1;
        //遍历网格，找到最大的时间，即最后感染的橘子的时间，如果还存在没有感染到的橘子，即为-1
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
                //取最深的深度
                res = Math.max(res, grid[i][j]);
            }
        }
        //res == 0 的情况是指一开始没有任何新鲜橘子（即 grid[i][j] 全部是 0 或 2），因此 res 应该直接返回 0，表示不需要任何时间来让橘子腐烂
        return res == 0 ? 0 : res - 2;
    }

    //利用深度优先搜索，只是每次搜索的时候需要把之前遍历过的橘子做判断，这样可以避免重复遍历
    //可以加快运行速度，并且减少空间的使用
    //用temp来表示当前感染到的橘子是否之前处理过，即temp表示时间，如果之前被感染过，就不再处理，按着之前的时间，最后取最大感染时间即可
    private static void dfs(int[][] grid, int i, int j, int temp) {
        int row = grid.length, col = grid[0].length;
        //下面判断grid[i-1][j] > temp的原因是，如果深度遍历之后会导致深度很大，但是实际不用这么深就能找到，如果之前的深度大于现在能到的深度，取现在小的那个深度。
        if (i - 1 >= 0 && (grid[i - 1][j] > temp || grid[i - 1][j] == 1)) {
            grid[i - 1][j] = temp;
            dfs(grid, i - 1, j, temp + 1);
        }
        if (j + 1 <= col - 1 && (grid[i][j + 1] > temp || grid[i][j + 1] == 1)) {
            grid[i][j + 1] = temp;
            dfs(grid, i, j + 1, temp + 1);
        }
        if (i + 1 <= row - 1 && (grid[i + 1][j] > temp || grid[i + 1][j] == 1)) {
            grid[i + 1][j] = temp;
            dfs(grid, i + 1, j, temp + 1);
        }
        if (j - 1 >= 0 && (grid[i][j - 1] > temp || grid[i][j - 1] == 1)) {
            grid[i][j - 1] = temp;
            dfs(grid, i, j - 1, temp + 1);
        }
    }

    //用两个栈来保存腐烂的橘子的位置，一个栈保存当前分钟的腐烂橘子，一个栈保存下一分钟的腐烂橘子，两个栈的切换就是时间的切换
    public static int orangesRotting(int[][] grid) {
        Stack<Integer> now = new Stack<>();
        Stack<Integer> next = new Stack<>();
        int row = grid.length, col = grid[0].length;
        int total = 0, time = 0;
        //遍历网格，把所有腐烂橘子都放入栈中
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    //用一个数字记录所在位置，方便计算
                    //这里用用行列的最大值，是防止数字太小，导致相加后的数字不唯一，比如1*3+2和2*3+1
                    now.push(i * Math.max(row, col) + j);
                } else if (grid[i][j] == 1) {
                    //记录需要感染的橘子总数，便于之后做对比，检验能否全部感染
                    total++;
                }
            }
        }
        int res = spread(now, next, grid, row, col, total, time);
        return res;
    }

    private static int spread(Stack<Integer> now, Stack<Integer> next, int[][] grid, int row, int col, int total, int time) {
        //递归出口
        if (now.isEmpty() && next.isEmpty()) {
            return total == 0 ? time : -1;
        }
        //当栈不为空的时候，不断出栈作为当前时间下可感染的橘子
        while (!now.isEmpty()) {
            int index = now.pop();
            total = bfs(grid, index, row, total, next, col);
        }
        //下个栈不为空，说明还有橘子可以感染，否则则直接返回结果
        if (!next.isEmpty()) {
            time++;
            //栈切换
            return spread(next, now, grid, row, col, total, time);
        }
        //如果一开始都进入不到上个if，说明没有橘子可以感染，直接返回0
        return total == 0 ? time : -1;
    }


    private static int bfs(int[][] grid, int index, int row, int total, Stack<Integer> next, int col) {
        int i = index / Math.max(row, col), j = index % Math.max(row, col);
        //向上
        if (i > 0 && grid[i - 1][j] == 1) {
            grid[i - 1][j] = 2;
            total--;
            //新感染的橘子继续入下个时间点的栈
            next.push((i - 1) * Math.max(row, col) + j);
        }
        //向右
        if (j < col - 1 && grid[i][j + 1] == 1) {
            grid[i][j + 1] = 2;
            total--;
            next.push(i * Math.max(row, col) + j + 1);
        }
        //向下
        if (i < row - 1 && grid[i + 1][j] == 1) {
            grid[i + 1][j] = 2;
            total--;
            next.push((i + 1) * Math.max(row, col) + j);
        }
        //向左
        if (j > 0 && grid[i][j - 1] == 1) {
            grid[i][j - 1] = 2;
            total--;
            next.push(i * Math.max(row, col) + j - 1);
        }
        return total;
    }
}
