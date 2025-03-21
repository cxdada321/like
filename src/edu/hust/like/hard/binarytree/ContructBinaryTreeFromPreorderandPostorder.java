package edu.hust.like.hard.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与后序遍历序列构造二叉树
 */
public class ContructBinaryTreeFromPreorderandPostorder {
    public static void main(String[] args) {
        /**
         * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
         * 如果存在多个答案，您可以返回其中 任何 一个
         */
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
    }

    //这个题目由于根节点都在头部，不在中间，不能直接对比两个序列来分左右子树
    //但是由于pre[1]肯定是左节点或者右节点，但是如何区分呢
    //假想一下如果没有左节点，只有右子树的情况下前序和后序遍历结果还是和只有左子树是一样的，所以可以直接把pre[1]当做左子树的根节点
    //利用这个根节点去后序里找到这个根节点的位置，即可用来划分左右子树
    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, 0, postorder.length - 1, map);
    }

    private static TreeNode dfs(int[] preorder, int prel, int prer, int postl, int postr, Map<Integer, Integer> map) {
        if (prel > prer) {
            return null;
        }
        int index = map.get(preorder[prel + 1]);
        TreeNode root = new TreeNode(preorder[prel]);
        root.left = dfs(preorder, prel + 1, prel + 1 + index - postl, postl, index, map);
        root.right = dfs(preorder, prel + 1 + index - postl + 1, prer, index + 1, postr - 1, map);
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
