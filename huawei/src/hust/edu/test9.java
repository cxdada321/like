package hust.edu;

public class test9 {
    public static void main(String[] args) {
        /*给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
        子数组是数组中的一个连续部分。
        */
        int[] nums = {0, -2, -1, 0, 8};
        System.out.println(getMaxSum(nums));
    }

    private static int  getMaxSum(int[] nums) {
        //省去数组
        //int[] preFixSum = new int[nums.length + 1];
        int preFixSum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //记录前缀和的最小值
            min = Math.min(min, preFixSum);
            //之后再添加新的前缀值，就能保证最大值在min后面
            preFixSum += nums[i];
            //直接记录最大值
            max = Math.max(max, preFixSum - min);
        }

        return max;
    }
}
