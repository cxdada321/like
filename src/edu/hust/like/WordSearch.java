package edu.hust.like;

/**
 * 单词搜索
 */
public class WordSearch {
    public static void main(String[] args) {
        /**
         * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
         * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
         * 同一个单元格内的字母不允许被重复使用。
         * 使用搜索剪枝的技术来优化解决方案
         */
        char[][] board = {{'a','a'}};
        String word = "aaa";
        boolean exist = exist(board, word);
        System.out.println(exist);
    }

    //可以加入优化
    //优化一：判断字符出现次数，不一样的直接失败
    //优化二：根据表格中的字符出现次数，从前后两个方向进行判断
    //采用dfs的方式，递归搜索，搜索过程中需要剪枝，即如果当前位置不是目标字符，直接返回false，并且在搜索过程中需要标记已经搜索过的位置，避免重复搜索
    public static boolean exist(char[][] board, String word) {
        //优化一：判断字符出现次数，board中较少则直接失败
        int[] cnt = new int[128];
        for (char[] chars : board) {
            for (char each : chars) {
                //记录每个字符的次数，后面用字符串来减，如果减到小于0，说明这个字符在字符串中出现的次数比在表格中多，直接返回false
                cnt[each]++;
            }
        }
        for (int i = 0; i < word.length(); i++) {
            if (--cnt[word.charAt(i)] < 0) {
                return false;
            }
        }

        //优化二：首尾字母在board中谁对应最少，选哪个当开头，这样更多的时候一开始就匹配失败，可以避免许多不必要的匹配，递归的总次数更少
        if (cnt[word.charAt(0)] > cnt[word.charAt(word.length() - 1)]) {
            word = new StringBuilder(word).reverse().toString();
        }
        //遍历数组，找到第一个字符，然后进行深度搜索
        int row = board.length, col = board[0].length;
        //字符串的位置
        int index = 0;
        //标记查找过的位置，防止重复查找
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //如果从初始位置的字符串开始查找就能找到，直接返回true
                if (word.charAt(index) == board[i][j] && dfs(board, word, i, j, index, visited)) {
                    return true;
                }
            }
        }
        //如果遍历完数组还没有找到，返回false
        return false;
    }
    private static boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        //递归终止条件
        //如果查找的字符已经是字符串最后一个字符，则说明满足条件，直接返回，不用继续递归查找别的字符了
        //为什么这里不减1，因为减1了，index就是最后一个字符的位置，这个位置还没有查找，所以这里不减1
        if (index == word.length()) {
            return true;
        }
        int row = board.length, col = board[0].length;
        //边缘匹配和字符串检查
        if (i < 0 || i >= row || j < 0 || j >= col || word.charAt(index) != board[i][j] || visited[i][j]) {
            return false;
        }

        //标记该位置已经被查找过了，防止重复查找
        visited[i][j] = true;
        // 向上下左右四个方向进行递归查找
        boolean result =
                // 向上查找
                dfs(board, word, i - 1, j, index + 1, visited) ||
                // 向下查找
                dfs(board, word, i + 1, j, index + 1, visited) ||
                // 向左查找
                dfs(board, word, i, j - 1, index + 1, visited) ||
                // 向右查找
                dfs(board, word, i, j + 1, index + 1, visited);

        // 恢复当前坐标为未访问状态，方便其他路径的查找
        visited[i][j] = false;

        //如果上下左右都没有找到，说明这个位置不是目标字符，返回false
        return result;
    }
}
