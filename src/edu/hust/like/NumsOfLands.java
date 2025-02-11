package edu.hust.like;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NumsOfLands {
    public static void main(String[] args) {
        /**
         * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
         * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
         * 此外，你可以假设该网格的四条边均被水包围。
         */
        char[][] grid = {{'1','1','1','1','0'},
                         {'1','1','0','1','0'},
                         {'1','1','0','0','0'},
                         {'0','0','0','0','0'}};
        int re = numIslands(grid);
        System.out.println(re);
    }
    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //遇到1就将其周围的1都变为0，避免重复搜索
                //并且开始深度搜索
                if (grid[i][j] == '1') {
                    res++;
                    //深度搜索
                    //dfs(grid, i, j);
                    //广度搜索
                    bfs(grid, i, j);
                }

            }
        }
        return res;
    }

    //深度优先搜索dfs
    //深度优先就是用递归一直往里面搜索，直到边界再开始返回
    private static void dfs(char[][] grid, int i, int j) {
        int row = grid.length, col = grid[0].length;
        //边界条件
        if(i < 0 || j < 0 || i > row - 1 || j > col - 1 || grid[i][j] == '0') {
            return;
        }
        //把当前位置变为0
        grid[i][j] = '0';

        //继续搜索
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    //广度优先搜索bfs
    //广搜就是用队列，找到一个点，就把周围邻居放入队列中，再逐个找邻居的邻居，按层来搜索
    private static void bfs(char[][] grid, int i, int j) {
        int length  = grid.length, width= grid[0].length;
        grid[i][j] = '0';
        //定义一个队列，来存放坐标，除了可以用一维数组保存，还可以用一个数字来保存，用取整和取余来得到坐标
        //用linklist插入删除高效
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * width + j);
        while(!queue.isEmpty()) {
            int index = queue.poll();
            int row = index / width, col = index % width;
            //分别找四个方向，如果有满足要求的就再放入队列中
            if(row - 1 >= 0 && grid[row - 1][col] == '1') {
                queue.offer((row - 1) * width + col);
                grid[row - 1][col] = '0';
            }
            if(row + 1 < length && grid[row + 1][col] == '1') {
                queue.offer((row + 1) * width + col);
                grid[row + 1][col] = '0';
            }
            if(col - 1 >= 0 && grid[row][col - 1] == '1') {
                queue.offer(row * width + (col - 1));
                grid[row][col - 1] = '0';
            }
            if(col + 1 < width && grid[row][col + 1] == '1') {
                queue.offer(row * width + (col + 1));
                grid[row][col + 1] = '0';
            }
        }
    }
}
