package hust.edu;

import java.util.Deque;
import java.util.LinkedList;

public class test14 {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
         * 滑动窗口每次只向右移动一位。
         * 返回 滑动窗口中的最大值 。
         */
        int[] nums = {1,3,1,2,0,5};
        int k = 3;
        //int[] res = maxSlidingWindow(nums, k);
        int[] res = maxSlidingWindow2(nums, k);
        for (int i : res) {
            System.out.println(i);
        }
    }
    //利用双指针来找滑动窗口的最大值，用两个指针固定窗口
    private static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        int left = 0, right = k - 1, max = Integer.MIN_VALUE, maxIndex = -1;
        while (right < nums.length) {
            //表示最大值在窗口内
            if (maxIndex >= left) {
                //如果新加入的值比最大值大，则更新最大值
                if (nums[right] >= nums[maxIndex]) {
                    max = nums[right];
                    maxIndex = right;
                }
                //如果最大值不再滑窗内了，则重新找最大值，减少需要重新遍历的可能，这里减一是避免第一次的时候直接把两边作为最大值
                //Integer.MIN_VALUE - 1 会导致整数溢出，结果是 2,147,483,647，即 Integer.MAX_VALUE
                //Integer.MIN_VALUE + 1 会导致整数溢出，结果是 -2,147,483,648，即 Integer.MIN_VALUE
            } else if (nums[right] >= max - 1){
                max = nums[right];
                maxIndex = right;
            } else if (nums[left] >= max - 1) {
                max = nums[left];
                maxIndex = left;
            } else {
                //赋初值
                max = nums[left];
                //遍历滑窗找到最大值
                for (int i = left + 1; i < right + 1; i++) {
                    if (max <= nums[i]) {
                        max = nums[i];
                        maxIndex = i;
                    }
                }
            }
            res[left] = max;
            left++;
            right++;

        }
        return res;
    }

    //利用单调队列来找滑动窗口的最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k == 0) {
            return new int[0];
        }
        //用单调递减队列来找最大值
        //队列中存放的是下标
        int[] res = new int[nums.length - k + 1];
        //利用双端队列存储下标
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //移除过期的下标，即超出左边界
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            //移除比当前值小的下标
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            //加入新的下标
            dq.offerLast(i);
            //当一个滑窗结束时获取最大值
            if (i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}
