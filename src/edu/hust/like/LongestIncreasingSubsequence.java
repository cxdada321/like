package edu.hust.like;

import java.util.Arrays;

/**
 * 最长递增子序列
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
         * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
         * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
         * 算法的时间复杂度降低到 O(n log(n)) 吗
         */
        int[] nums = {3,5,6,2,5,4,19,5,6,7,12};
//        int res = lengthOfLIS(nums);
        int res = lengthOfLISSort(nums);
        System.out.println(res);

    }

    //采用二分查找的思想是，维护一个有序数组，在这个有序数组里采用二分查找加快速度
    //用贪心的思想：要使上升子序列尽可能的长，则需要让序列上升得尽可能慢，因此希望每次在上升子序列最后加上的那个数尽可能的小
    //用另外一个数组d[i] ，表示长度为 i 的最长上升子序列的末尾元素的最小值，用 len 记录目前最长上升子序列的长度
    //单调性可证明，如果 d[j]≥d[i] 且 j<i，考虑从长度为 i 的最长上升子序列的末尾删除 i−j 个元素，那么这个序列长度变为 j
    //且第 j 个元素 x（末尾元素）必然小于 d[i]（因为是在长度为 i 的最长上升子序列中的），且d[j]≥d[i]，即x也小于d[j]，说明d[j]不是在j位置最小的数，与题设矛盾
    //这样维护出来的数组是单调递增的因此可以用二分查找
    private static int lengthOfLISSort(int[] nums) {
        int len = 0;
        int[] d = new int[nums.length];
        //赋初值
        d[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //如果新的元素比当前元素大，则直接长度加一，添加到d数组新的位置
            if (d[len] < nums[i]) {
                d[++len] = nums[i];
            } else {
                if (d[0] > nums[i]) {
                    //如果比第一个元素都小，则直接放入第一个位置，不再继续向后搜索
                    d[0] = nums[i];
                    continue;
                }
                //如果没有比末尾元素小，看看前面有无元素比当前元素小
                //找到第一个比当前元素小的位置x，则把当前元素放入x+1的位置，因为x+1比当前元素大，而x位置的元素和当前元素可以形成序列
                //说明当前元素是x+1处更好的选择，即使最长子序列末尾元素尽可能小
                //如果比所有元素都小，说明要放入开头元素，作为长度为1的子序列末尾最小的元素
                //定义左右指针
                int left = 0, right = len - 1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (d[mid] < nums[i] ) {
                        if (d[mid + 1] >= nums[i]) {
                            d[mid + 1] = nums[i];
                            break;
                        } else {
                            left = mid + 1;
                        }
                    } else {
                        //最后停的位置上的数是比nums[i]的，因此现在大的数就不用放入之后的搜索了，也可以避免死循环
                        right = mid - 1;
                    }
                }
            }

        }
        //为什么要加1，因为前面计算长度的时候是从0开始的，所以长度要加1，因为初始长度就为1了，但是为了索引方便就没有加1
        return len + 1;
    }


    //这样时间较长，可以优化，结合二分查找
    //向后搜索，记录一个最小数字，判断当前搜索对象还需不需要向前搜索，即前面有无比当前数小的数字
    public static int lengthOfLIS(int[] nums) {
        int min = nums[0];
        int count = 1;
        //保存到当前位置的最大序列
        int[] dp = new int[nums.length];
        //每个位置只看自己的话序列最大数都是1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            //说明前面有比当前数字小的数字，可以向前搜索
            if (nums[i] > min) {
                for (int j = i; j >= 0; j--) {
                    //说明j这个位置的数字小于当前数字，可以获取j位置保存的最大序列数
                    if (nums[j] < nums[i]) {
                        //如果j位置保存的就是当前记录的最大序列的最大长度，则不需要再往前搜索
                        if (dp[j] == count) {
                            dp[i] = ++count;
                            break;
                        }
                        //若存在比当前数字小的数字，但是最大序列数不是记录的最大序列长度，则需要继续向前搜索
                        //记录下来，因为要保证每个位置都是最大序列数
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        return count;
    }
}
