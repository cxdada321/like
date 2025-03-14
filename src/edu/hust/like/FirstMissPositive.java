package edu.hust.like;

/**
 * 缺失的第一个正数
 */
public class FirstMissPositive {
    public static void main(String[] args) {
        /**
         * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
         * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
         */
        int[] nums = {3,4,-1,1};
//        int res = firstMissingPositive(nums);
        //不再创建一个数组，直接在原数组上进行操作，原地哈希表，即将原数组视为一个哈希表
        int res = firstMissingPositiveHold(nums);
        System.out.println(res);
    }

    //在原数组上进行交换操作，这样就能保证原数组对应位置放上相应的数，而且不会使数组里的元素缺失
    private static int firstMissingPositiveHold(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //要先判断当前数是不是在1~nums.length之间，不然会越界，并且当前数是否在正确位置上
            //这里要用while，直到当前位置上的数放了正确的数，才能继续往下走，不然可能会出错
            while (0 < nums[i] && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                //交换nums[i] - 1和i位置上的数
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        //第一个没有放对位置的数就是缺失的最小正数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    //创建一个长度为n的数组，将数组中的元素放到对应的位置上，然后遍历数组，找到第一个不符合的元素
    public static int firstMissingPositive(int[] nums) {
        //按照数组长度再创建一个数组用来表示0~nums-1，为什么只需要创建这么大的数组就可以
        //因为我们要找的是最小的正数，不管这个nums有多大，最小的正数一定是在1~nums之间的
        //假如都小于1，那最小正数就是1，假如都大于nums，那最小正数就是nums+1，如果在1~nums之间，那么1~nums中一定有一个数是缺失的
        int[] arr = new int[nums.length];

        //遍历nums，给arr赋值
        for (int num : nums) {
            if (num > 0 && num <= nums.length) {
                arr[num - 1]++;
            }
        }
        //找到arr中第一处为0的位置，返回这个位置+1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return i + 1;
            }
        }
        //如果上面都没有，说明数组中没有缺数，那就是最大数的下一位
        return nums.length + 1;
    }
}
