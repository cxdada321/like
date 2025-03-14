package edu.hust.like;

import java.util.Arrays;

/**
 * 在排序数组中超找元素的第一个和最后一个位置
 */
public class FirstLastPositionInSortedArray {
    public static void main(String[] args) {
        /**
         * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
         * 如果数组中不存在目标值 target，返回 [-1, -1]。
         * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
         */
        int[] nums = {5,7,7,8,8,8,8, 8, 10};
        int target = 8;
        //int[] res = searchRange(nums, target);
        int[] res = searchRange1(nums, target);
        Arrays.stream(res).forEach(System.out::println);
    }

    //直接采用两个左右指针的左右偏移找到左右边界，为什么可以这样做，因为由于整除的特殊性，mid的值会向左偏移，所以最终会找到左边界
    //对于向右偏移直接在两边界的和上加一即可
    private static int[] searchRange1(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int[] res = new int[2];
        //定义左右指针
        int left = 0, right = nums.length - 1;
        while (left != right) {
            //定义中点
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if(nums[left] != target) {
            return new int[]{-1, -1};
        }
        res[0] = left;

        //找到左边界后，直接在左边界的基础上找到右边界
        //右边界重新赋值
        right = nums.length - 1;
        while (left != right) {
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        res[1] = right;
        return res;
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int[] res = new int[2];
        //定义左右指针
        int left = 0, right = nums.length - 1;
        int base = 0;
        while (left != right) {
            //定义中点
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //如果最终遍历完了全部数组，没有找到目标值，返回-1
        if (nums[left] != target) {
            return new int[] {-1, -1};
        } else {
            base = left;
        }
        //找到目标值后，分别找到左右边界
        res[0] = findIndexLeft(0, base, nums, target);
        res[1] = findIndexRight(base, nums.length - 1, nums, target);
        return res;
    }

    private static int findIndexRight(int left, int right, int[] nums, int target) {
        while (nums[right] != target) {
            //这里加一的原因是为了避免死循环，使mid的值会向右偏移
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] == target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private static int findIndexLeft(int left, int right, int[] nums, int target) {
        while (nums[left] != target) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
