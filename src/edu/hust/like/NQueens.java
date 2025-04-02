package edu.hust.like;

import java.util.*;

/**
 * N皇后
 */
    public class NQueens {
    static boolean[] columns;
    static boolean[] diagonals1;
    static boolean[] diagonals2;
    static int[] queens;

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> res = solveNQueens(n);
        res.forEach(list -> {
            list.forEach(System.out::println);
            System.out.println();
        });

    }

    //经典的回溯问题，关键在于如何处理加了一个皇后，下一行可用位置的处理
    //可以用三个集合来表示被占用的位置，即一个是表示列，另外两个表示斜着方向，有一个巧妙的地方就左斜的时候每个位置的坐标和相同，右斜的时候每个位置的坐标差相同
    //这样就可以用两个集合来表示斜着的方向
    //还可以利用位运算来表示，一个整数的二进制表示中1的个数就是皇后的个数，然后用位运算来表示占用的位置
    //涉及位的取反，用皇后个数确定取反后的范围
    //性质：x & (−x) 可以获得 x 的二进制表示中的最低位的 1 的位置
    //x & (x−1) 可以将 x 的二进制表示中的最低位的 1 置成 0
    //位运算的过程涉及位的转换，比较复杂
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        queens = new int[n];
        columns = new boolean[n];
        diagonals1 = new boolean[2 * n - 1];
        diagonals2 = new boolean[2 * n - 1];
        dfs(res, n, 0);
        return res;
    }

    private static void dfs(List<List<String>> res, int n, int layer) {
        if (layer == n) {
            res.add(generate(n));
            return;
        }
        for (int i = 0; i < n; i++) {
            //当前层每个位置都遍历一次，判断当前位置是否可用，不可用则判断下个位置
            //数组索引不能为负，所以整体右移
            if (!columns[i] && !diagonals1[layer + i] && !diagonals2[layer - i + n - 1]) {
                queens[layer] = i;
                columns[i] = diagonals1[layer + i] = diagonals2[layer - i + n - 1] = true;
                //递归下一层
                dfs(res, n, layer + 1);
                //回溯回来
                columns[i] = diagonals1[layer + i] = diagonals2[layer - i + n - 1] = false;
            }
        }
    }

    private static List<String> generate(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[queens[i]] = 'Q';
            res.add(new String(chars));
        }
        return res;
    }
}
