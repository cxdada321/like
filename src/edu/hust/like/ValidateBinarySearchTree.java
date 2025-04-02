package edu.hust.like;

import java.util.Stack;

/**
 * 验证二叉搜索树
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2147483648);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2147483647);

        root.right = node2;
        boolean res = isValidBST(root);
        System.out.println(res);
    }
    //利用中序遍历的方式，如果遍历过程中存在逆序对，说明不是二叉搜索树
    //迭代的过程中记录上一个数，然后和下一次数比较，出现逆序即返回，否则最后返回true
    public static boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }

        long cur = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (cur < node.val) {
                cur = node.val;
            } else {
                return false;
            }
            root = node.right;
        }
        return true;
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
