package edu.hust.like.hard.binarytree;

import java.util.HashMap;
import java.util.Map;


/**
 * 从中序与后序遍历序列构造二叉树
 */
public class ContructBinaryTreeFromInorderandPostorderTraversal {
    public static void main(String[] args) {
        /**
         * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历
         * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树
         */
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode res = buildTree(inorder, postorder);
    }
    //利用递归的方式解决
    //由于后序遍历构造树原理是左，右，根，因此最后遍历结果整体应该是左子树，右子树，根节点
    //由于中序遍历构造树原理是左，根，右，因此中序遍历结果整体应该是左子树，根节点，右子树
    //因此通过后序数组末尾的元素可知根节点，再用根节点去中序数组里查找位置，根据位置分为左右字数，根据中序的左右子树的长度去找出后序数组的左右子树边界
    //再根据新的边界的后序数组和中序数组分治递归
    //为了提高查询位置效率可以采用hash保存位置
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    private static TreeNode dfs(int il, int ir, int[] postorder, int pl, int pr, Map<Integer, Integer> map) {
        //递归出口
        if (pr > pl || ir > il) {
            return null;
        }
        int index = map.get(postorder[pr]);
        //根据后序遍历右边界来确定当前根节点的值
        TreeNode root = new TreeNode(postorder[pr]);
        //根据根节点在中序遍历中的位置来确定左右子树的长度
        //中序数组里根节点左边的是左子树，右边的是右子树，因此坐标比较好找，后序里的坐标需要根据中序的左右子树长度来确定
        //因为左右子树不管是中序还是后序长度都是固定的
        root.left = dfs( il, index - 1, postorder, pl, pl + index - il - 1, map);
        root.right = dfs( index + 1, ir, postorder, pl + index - il, pr - 1, map);
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
