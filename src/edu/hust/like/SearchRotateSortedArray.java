package edu.hust.like;

/**
 * 搜索旋转排序数组
 */
public class SearchRotateSortedArray {
    public static void main(String[] args) {
        /**
         * 整数数组 nums 按升序排列，数组中的值 互不相同 。
         * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
         * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
         * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
         * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
         * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
         */
        int[] nums = {5,1,3};
        int target = 5;
        int res = search(nums, target);
        System.out.println(res);
    }
    //可以旋转回来再二分查找，但是时间复杂度好像上去了
    //可以直接用二分查找，查找到的数字根据前后数字大小来判断在哪个区间，然后继续二分查找
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            //直接找到便返回
            if (nums[mid] == target) {
                return mid;
            }
            //判断左右指针应该怎么缩小
            //如果中值比目标值小，左指针是否要放到中值右边，需要满足右指针的值比目标值大，否则左指针不动，移动右指针
            //这样判断的前提是左指针值比右指针值大，即数组是旋转过的，左右区间是有个是直线，有个是曲折的，根据这样的特性来判断
            if (nums[left] > nums[right]) {
                //这里需要分情况，因为如果中值大的话也是需要分左右区间的
                if (nums[mid] < target) {
                    //中值比目标值小，那左指针是否要右移，如果左边是直线，直接右移
                    //左边如果是曲线，那右边就是直线，如果右边边的值大于等于目标值，那还是右移
                    if (nums[mid] > nums[left] || nums[right] >= target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                } else {
                    //中值比目标值大，那右指针是否要左移，如果右边是直线，直接左移
                    //右边如果是曲线，那左边就是直线，如果左边边的值小于等于目标值，那还是左移
                    if (nums[right] > nums[mid] || nums[left] <= target) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                //说明是正常顺序
            } else {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
