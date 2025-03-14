package edu.hust.like;

/**
 * 将有序数组转换为二叉搜索树
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵平衡二叉搜索树。
         */
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
        while (root != null) {
            System.out.println(root.val);
            root = root.left;
        }
    }


    //递归的方式，每次取中间的数作为根节点，然后递归左右两边的数组
    public static TreeNode sortedArrayToBST(int[] nums) {
        int left = 0, right = nums.length - 1;
        return dfs(nums, left, right);
    }

    private static TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, left, mid - 1);
        root.right = dfs(nums, mid + 1, right);
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
