package edu.hust.like.hard.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 */
public class ContructBinaryTreeFromPreorderandInorderTraversal {
    public static void main(String[] args) {
        /**
         * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
         */
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
    }
    //这个和根据后序和中序遍历构造二叉树的思路是一样的，只是根据前序遍历的根节点在前面，所以先找到根节点，再根据根节点在中序遍历中的位置来确定左右子树的长度
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, inorder.length - 1, preorder, 0, preorder.length - 1, map);
    }

    private static TreeNode dfs(int il, int ir, int[] preorder, int pl, int pr, Map<Integer, Integer> map) {
        if (pl > pr) {
            return null;
        }
        int index = map.get(preorder[pl]);
        TreeNode root = new TreeNode(preorder[pl]);
        root.left = dfs(il, index - 1, preorder,pl + 1, pl + index - il, map);
        root.right = dfs(index + 1, ir, preorder, pl + index - il + 1, pr, map);
        return root;
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
