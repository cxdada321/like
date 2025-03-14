package edu.hust.like;

/**
 * 翻转二叉树
 */
public class InvertBinaryTree {
    public static void main(String[] args) {
        /**
         * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
         */
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);

        root.left = node1;
        TreeNode res = invertTree(root);
    }

    //利用dfs解决
    private static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return root;
    }

    public static void dfs(TreeNode root) {
        if (root == null || root.right == null && root.left == null) {
            return;
        }
        //进入下一层，如果到了最后一层，就会返回，返回之后即开始交换左右子树
        dfs(root.left);
        dfs(root.right);
        //交换左右子树
        swap(root);
    }

    private static void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
