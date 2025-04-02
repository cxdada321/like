package edu.hust.like;

import java.util.Stack;

/**
 * 二叉搜索树中第K小的元素
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法
 */
public class KthSmallestInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        right.left = new TreeNode(2);
        root.right = right;
        System.out.println(kthSmallest(root, 1));
    }
    //利用中序遍历，从尾部开始计数，遍历到指定k值即输出结果
    public static int kthSmallest(TreeNode root, int k) {
        int count = 1, res = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            //把最左边先放入，如果左边放完了，则取最后放入的节点的右节点开始继续放入
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            if (count == k) {
                res = temp.val;
                break;
            }
            count++;
            root = temp.right;
        }
        return res;
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
