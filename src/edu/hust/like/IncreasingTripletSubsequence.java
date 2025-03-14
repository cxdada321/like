package edu.hust.like;

import java.util.Arrays;

/**
 * 递增的三元子序列
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
         * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
         *
         * 能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗
         */
        int[] nums = {1, 2, 1, 1, 5,5, 6, 4};
//        boolean res = increasingTriplet(nums);
        //双向遍历结合动态规划
//        boolean res = increasingTriplet1(nums);
        //贪心算法
        boolean res = increasingTriplet2(nums);
        System.out.println(res);
    }

    //什么是贪心，贪心就是在每次取结果的时候尽量取最优的结果，这样在最后的结果就是最优的，贪心算法都选择当前看起来最好的选项，而不考虑整体情况
    //贪心的思想：为了找到递增的三元子序列，first 和 second 应该尽可能地小，此时找到递增的三元子序列的可能性更大
    //为什么呢，假如(first,second,num) 是一个递增的三元子序列，如果存在 second’ 满足 first<second’<second 且 second’ 的下标位于 first 的下标和 num 的下标之间
    //那么 (first,second’,num) 也是一个递增的三元子序列
    //但是，假如(first,second’,num) 是递增的三元子序列时，由于 num 不一定大于 second，所以并不能说明(first,second,num) 也是递增的三元子序列
    //为了使找到递增的三元子序列的可能性更大，三元子序列的第二个数应该尽可能地小，将 second’ 作为三元子序列的第二个数优于将 second 作为三元子序列的第二个数
    //同理，第一个数也是越小越越有可能找到三元子序列

    //运用这个贪心的思想，假如是要找递增的四元子序列，那就是要维护三个数，第一个数，第二个数，第三个数，然后找到第四个数，这样就能找到递增的四元子序列

    private static boolean increasingTriplet2(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                //如果现在有了first < second，现在有了更小的first'，那就顺序就变成了second，first'，后面即使来了比second大的数也是满足要求的
                //因为second前面还有first比second小的，所以下个num即使不改变second位置，也是满足要求的
                first = num;
            } else if (num <= second) {
                //因为是先判断了比first大才到这里，因此second变小了也还是比first大的，符合要求
                second = num;
            } else {
                //因为新的数比前面两个数都大，前两个数是维持了递增的状态，那么就找到了三个递增的数
                return true;
            }
        }
        return false;
    }

    //像是接雨水的问题，因为是要找三个递增的数，那么可以用两个数组保存每个数左边最小的数和右边最大的数，然后判断当前数是否符合条件
    //这样就能用O(n)的时间复杂度和O(n)的空间复杂度
    private static boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(nums[i], leftMin[i - 1]);
        }
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(nums[j], rightMax[j + 1]);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > leftMin[i] && nums[i] < rightMax[i]) {
                return true;
            }
        }
        return false;
    }

    //利用递归的方式，递归就是每个数字进入，不断找下一个递增的数，如果后面没有找到说明当前入口没有符合条件的数，会回到上层递归入口，选择下个数字重新查找
    //或者利用动态数组的方式，保存每个位置前面有几个递增的数，这样写会超时
    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //记录遍历过的数中最小数，用来判断是否有递增的数，要不要向前遍历
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        if (dp[i] >= 3) {
                            return true;
                        }
                    }
                }
            }
        }
        //如果没有找到长度为3的递增子序列，那么就返回false
        return false;
    }
}
