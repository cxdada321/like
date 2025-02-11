package edu.hust.like;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    // 定义全局变量
    private static List<Integer> list;
    private static TreeNode originalRoot;

    public static void main(String[] args) {
        /**
         * 给定一个二叉树的根节点 root ，返回它的中序遍历。
         * 中序遍历：左 -> 根 -> 右
         */
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //转换数组为二叉树
        TreeNode root = arrayToTree(arr, 0);
        System.out.println(inorderTraversal(root));
    }

    private static TreeNode arrayToTree(int[] root, int index) {
        //递归出口，超出数组长度
        if (index >= root.length) {
            return null;
        }
        //创建一个新的节点
        TreeNode node = new TreeNode(root[index]);

        //递归创建左支树
        node.left = arrayToTree(root, 2 * index + 1);
        //递归创建右支树
        node.right = arrayToTree(root, 2 * index + 2);
        return node;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        // 初始化全局变量
        if (list == null) {
            list = new ArrayList<>();
        }
        // 递归终止条件
        if (root == null) {
            return list;
        }
        // 遍历左子树
        inorderTraversal(root.left);
        // 添加当前节点值
        list.add(root.val);
        // 遍历右子树
        inorderTraversal(root.right);
        // 返回最终结果
        return list;
    }

    static class TreeNode {


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
