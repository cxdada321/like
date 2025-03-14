package edu.hust.like;

/**
 * 二叉树的直径
 */
public class DiameterBinaryTree {
    private static int max = 0;
    public static void main(String[] args) {
        /**
         * 给你一棵二叉树的根节点，返回该树的 直径 。
         * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
         * 两节点之间路径的 长度 由它们之间边数表示
         */
        //用例：1,2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        int res = diameterOfBinaryTree(root);
        System.out.println(res);
    }

    //利用dfs不断向着树的深处搜索，对每个节点的左右子树都往深处搜索，将左右深度返回回来，左右深度求和就是最大值，用一个全局变量max来记录最大值
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    public static int dfs(TreeNode root) {
        //如果当前层节点为null，即返回上层
        if (root == null) {
            return 0;
        }
        //向下一层继续搜索
        //这里count不用自增，因为返回这一层count还是要用这一层的值
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right);
        //返回左右子树的深度中的最大深度，返回时为什么要加1，因为当前层的深度也要算上
        return Math.max(left, right) + 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }
}
