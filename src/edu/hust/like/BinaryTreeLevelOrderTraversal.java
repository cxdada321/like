package edu.hust.like;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层序遍历
 */
public class BinaryTreeLevelOrderTraversal {


    public static void main(String[] args) {
        /**
         * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        root.right = right;
        //层序遍历，bfs
//        List<List<Integer>> res = levelOrder(root);
        //dfs
        List<List<Integer>> res = levelOrderDfs(root);
        res.forEach(System.out::println);
    }


    //还可以采用dfs的思想，递归的思想就是把List<List<Integer>>分批次补充完整，深入递归的时候会记录当前层数，根据当前层数添加到对应的list后面
    private static List<List<Integer>> levelOrderDfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private static void dfs(TreeNode root, int index, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        //如果当前层数还没有list，就新建一个list
        if (res.size() == index) {
            res.add(new ArrayList<>());
        }
        res.get(index).add(root.val);
        //向左
        dfs(root.left, index + 1, res);
        //向右
        dfs(root.right, index + 1, res);
    }

    //用栈的思想，相当于bfs，先进先出，把当前层下层的节点入栈，这样就需要两个栈来分别存储不同层
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            int len = stack.size();
            //把当前栈里的节点都出栈后，再处理下一层节点就不用用两个栈了
            for (int i = 0; i < len; i++) {
                TreeNode node = stack.pollFirst();
                //把当前节点的值放进当前层
                each.add(node.val);
                //把下一层节点入栈
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                if (node.right != null) {
                    stack.addLast(node.right);
                }
            }
            res.add(new ArrayList<>(each));
            each.clear();
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
