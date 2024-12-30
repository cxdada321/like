package hust.edu;

public class test15 {
    public static void main(String[] args) {
        /**
         * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
         * 请必须使用时间复杂度为 O(log n) 的算法。
         */
        int[] nums = {1};
        int target = 2;
        int index = searchInsert(nums, target);
        System.out.println(index);
    }

    public static int searchInsert(int[] nums, int target){
        int index = 0;
        //利用二分法
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (left == right) {
                if (nums[left] < target) {
                    index = left + 1;
                } else {
                    index = left;
                }
                break;
            }
            int mid = left + right >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return index;
    }
}
