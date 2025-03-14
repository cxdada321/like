package edu.hust.like;

import java.util.Arrays;

/**
 * 分割等和子集
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        /**
         * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
         */
        int nums[] = {1, 5, 11, 5};
        //传统背包问题的动态规划
//        boolean res = canPartition(nums);
        //利用动态规划加回溯的方法，通过多次减枝降低时间复杂度，并且可以使用于求k个子集和相等的问题
        //使用回溯法来尝试将数组中的每个数字分配到 k 个子集中，尝试所有可能的数字分配方案，如果找到了一种方案使得所有 k 个子集的和都等于 target，那么就返回 true
        //虽然回溯搜索的理论最坏时间复杂度是指数级别的，但由于剪枝策略的有效性，实际运行时间通常会远低于指数级别
        //这个方法的时间复杂度是O(n*2^n)，空间复杂度是O(n)，因此仅适用于很小的测试用例，但是能够解决k个子集和相等的问题
        boolean res = canPartition1(nums);

        System.out.println(res);
    }

    private static boolean canPartition1(int[] nums) {
        return canPartitionKSubsets(nums, 2);
    }

    private static boolean canPartitionKSubsets(int[] nums, int k) {
        //求出数组和
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        //每个子集和
        int target = sum / k;
        //给数组排序便于后面进行回溯操作以及剪枝
        Arrays.sort(nums);
        //记录数组尾部索引，用于提前剪枝
        //因为已经经过排序了，末尾是最大的数，如果最大数比目标值还大，那说明最大数不可能放到子集里，即不可能分成k个子集
        int unused = nums.length - 1;
        if (nums[unused] > target) {
            return false;
        }
        //判断是否末尾的数有刚好等于目标值的，有的话就单独一组可以减少回溯的过程
        while (unused >= 0 && nums[unused] == target) {
            unused--;
            k--;
        }
        //这里int[k]是记录k个子集当前的和
        return backTrack(new int[k], nums, unused, target);
    }

    //回溯操作
    private static boolean backTrack(int[] group, int[] nums, int unused, int target) {
        //递归出口，当所有数都分配完了，即所有数都分配到了子集里，那么就返回true
        if (unused < 0) {
            return true;
        }
        //记录当前层的数字
        int v = nums[unused--];
        //逆向遍历，从大到小，因为大的数更容易剪枝，大的数更有可能
        //放到不同的子集里
        for (int j = 0; j < group.length; j++) {
            //如果当前子集加上当前数小于等于目标值，那么就把当前数加到子集里，然后继续回溯
            if (group[j] + v <= target) {
                group[j] +=v;
                //如果递归调用返回 true，表示找到了一个有效的划分，返回 true
                //如果递归调用返回 false，表示将元素 v 放入子集 i 无法得到有效的划分，则需要回溯，将元素 v 从子集 i 中移除
                if (backTrack(group, nums, unused, target)) {
                    return true;
                }
                group[j] -= v;
            }
            //剪枝，如果v这个数字经过前面的递归调用之后，发现连空集都放不进去，就不用再继续放其他集合了，其他集合的空间只能比空集还小
            //每个集合空间都是相同的，一个数无法放到空集里的时候，说明任何一个集合不管有没有值，就是当作空集也放不进去这个数，直接结束
            if (group[j] == 0) {
                return false;
            }
        }
        return false;
    }

    //把数组总和求出来，然后看是否能找到一个子集的和等于总和的一半
    //这个问题可以转化为背包问题，即从数组中找到一个子集和为mid即可，如果找不到即返回false
    //传统背包问题是在不超过背包容量的情况下，找到最大价值，这里是找到一个子集和为mid
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        int maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        //在数组中找到一个子集和为mid即可，如果找不到即返回false
        int mid = sum / 2;
        //如果是偶数但是其中最大的数比一半还大，那剩下的数和肯定不能是一半
        if (maxNum > mid) {
            return false;
        }

        //利用背包问题，即在前i个数里能否找到和为mid的可能，从前面一直动态规划到最后个数
        //可以用dp[n+1][mid+1]保存，也可以用dp[mid+1]一行一行的保存，因为动态方程是根据当前位置前面一个位置和上一行来确定当前位置的值
        //根据背包问题，在前i个数能否找到j个数和为mid的可能，dp[i][j]如果选择不加这个数即为dp[i-1][j]，如果选择加上这个数即为dp[i-1][j-nums[i-1]]
        //因此如果只是用一维数组，那需要从后向前遍历，因为遍历过程中dp值会被改变，而新的值依赖上一次dp的值，所以需要将会改变的dp值放在后面

        boolean[] dp = new boolean[mid + 1];
        //初始化，因为前0个数只有j为0使得和为0，其他都是false，因此其他的位置使用默认值就可以
        dp[0] = true;
        for (int num : nums) {
            for (int j = mid; j >= num ; j--) {
                //根据默认值就可以得出i=1时dp的情况
                //一维数组的话，dp[i][j]=dp[i-1][j]|dp[i-1][j-nums[i-1]]
                //新的dp即为之前dp的值取或 dp[j] = dp[j] | dp[j - num]
                dp[j] |= dp[j - num];
                //提前结束，如果已经找到了一个子集和为mid，那么就可以直接返回true
                if (dp[mid]) {
                    return true;
                }
            }
        }
        return dp[mid];
    }
}
