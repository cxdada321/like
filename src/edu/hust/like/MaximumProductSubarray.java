package edu.hust.like;

/**
 * 乘积最大子数组
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
         * 测试用例的答案是一个 32-位 整数。
         */
        int[] nums = {-2,-3, 0,7,-1,-2};
        //可以采用动态规划的解法，但是要维护两个数组，表示当前位置下的最大值和最小值，因为当前位置是正或负数都有可能是最大值
//        int res = maxProduct(nums);
        //采用左右遍历的方式，左→右遍历：计算以左边界开始的连续子数组乘积，右→左遍历：计算以右边界结束的连续子数组乘积
        //处理0值，当乘积变为零时，重置 product = 1，相当于从下一个元素重新开始计算子数组
        //偶数个负数：整个区间乘积会被自然累积为正数
        //奇数个负数：双向遍历保证至少有一个方向能排除最左或最右的负数
        int res = maxProduct1(nums);
        System.out.println(res);
    }

    //还有种思想是：数组中任意不出现零的子数列可能出现的负数有可能为偶数个或者奇数个，如果是偶数个，那么最大数是所有连续非零数相乘，
    //如果是奇数个负数，那么有可能是从左往右数，少算一个负数的子数列连续相乘，
    //也有可能是从右往左数的少算一个负数的子数列连续相乘，所以两边各遍历一次，取两次遍历的最大值
    private static int maxProduct1(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int product = 1;
        for (int num : nums) {
            product *= num;
            max = Math.max(max, product);
            //出现0则跳过0，并且重置计算乘积的变量，并且0元素和前面元素的乘积也已经保留了
            if (num == 0) {
                product = 1;
            }
        }
        product = 1;
        for (int i = n - 1; i >= 0; i--) {
            product *= nums[i];
            max = Math.max(max, product);
            if (nums[i] == 0) {
                product = 1;
            }
        }
        return max;
    }

    //先统计有多少个负数，因为除了负数和零，乘上任何数字都是增加的。因此如果负数是偶数个且没有0，那么整个数组的乘积就是最大的，如果遇到0，那么就从0开始重新统计

    private static int maxProduct(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int[] min = new int[n];
        max[0] = nums[0];
        min[0] = nums[0];
        int res = max[0];
        for (int i = 1; i < nums.length; i++) {
            //这里还要比较当前位置的数字大小，相当于用两个Math.max把三个比较数组合起来
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(min[i - 1] * nums[i], nums[i]));
            min[i] = Math.min(max[i - 1] * nums[i], Math.min(min[i - 1] * nums[i], nums[i]));
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
