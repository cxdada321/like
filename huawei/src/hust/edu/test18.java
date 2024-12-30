package hust.edu;

public class test18 {
    public static void main(String[] args) {
        /**
         * 给你一个非负整数数组 nums ，你最初位于数组的第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
         * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
         */
        int[] nums = {0,2,3};
        System.out.println(canJump(nums));
    }
    public static boolean canJump(int[] nums) {
        boolean flag = false;
        int max = 0;
        //可以用能覆盖的最大区域来找出能否达到最后个下标
        for (int i = 0; i < nums.length - 1; i++) {
            //遇到0，且最大覆盖区域小于等于当前下标，返回false
            if (nums[i] == 0 && max <= i) {
               return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        if (max >= nums.length - 1) {
            flag = true;
        }
        return flag;
    }
}
