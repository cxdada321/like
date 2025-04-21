package edu.hust.like;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的右视图
 */
public class BinaryTreeRightSideView {
    static int index;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        List<Integer> result = rightSideView(root);
        result.forEach(System.out::println);
    }
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }
    //先从右边开始遍历，用深搜的方式遍历所有节点，根据遍历到的节点所在层数是否是第一次到达确定节点是否是结果
    private static void dfs(TreeNode root, int layer, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (layer == index) {
            list.add(root.val);
            index++;
        }
        //先遍历右边再遍历左边
        dfs(root.right, layer + 1, list);
        dfs(root.left, layer + 1, list);
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
