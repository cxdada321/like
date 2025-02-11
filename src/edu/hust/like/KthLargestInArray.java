package edu.hust.like;

import java.util.Random;

/**
 * 数组中的第K个最大元素
 */
public class KthLargestInArray {
    public static void main(String[] args) {
        /**
         * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
         * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
         * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
         */
        int[] nums = {3,2,1,5,6,4};
        int k = 3;
        //int res = findKthLargest(nums, k);
        //int res = findKthLargestBucket(nums, k);
        int res = findKthLargestHeap(nums, k);
        System.out.println(res);
    }

    //基于堆排序的选择方法，建立一个大根堆，做 k−1 次删除操作后堆顶元素就是我们要找的答案
    //掌握建立大根堆的过程，「建堆」、「调整」和「删除」
    private static int findKthLargestHeap(int[] nums, int k) {
        //当前堆的大小
        int heapSize = nums.length;
        //构建最大堆
        buildMaxHeap(nums, heapSize);

        //删除堆顶节点k - 1次剩下的堆顶节点就是第k大的元素
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            //堆顶元素换到数组末尾，为什么后面堆处理没有处理数组末尾元素，因为堆的大小也减少了，即后面处理堆的时候不会再处理到移到数组后面去的元素
            swap(nums, 0, i);
            heapSize--;
            //重新维护最大堆
            maxHeapify(nums, 0 ,heapSize);
        }
        //第k大元素位于堆顶
        return nums[0];
    }

    private static void buildMaxHeap(int[] nums, int heapSize) {
        //最后一个非叶子节点 开始（heapSize / 2 - 1）遍历到根节点（0）
        //因为从最后一个非叶子节点 向上调整可以确保在调整每个节点时，子节点已经是一个最大堆
        for (int i = (heapSize / 2 - 1); i >= 0 ; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    //维护最大堆的性质。当一个节点的值不满足堆性质（即比其子节点小），需要调整该节点和其子节点的值，确保子树成为一个最大堆
    private static void maxHeapify(int[] nums, int i, int heapSize) {
        //左右子节点
        int left = 2 * i + 1, right = 2 * i + 2, largest = i;
        //左节点比父节点大，加入长度的验证是为了在递归过程中，对于新的子节点的调整时候防止越界
        if (left < heapSize && nums[largest] < nums[left]) {
            largest = left;
        }
        if (right < heapSize && nums[largest] < nums[right]) {
            largest = right;
        }
        //需要调整
        if (largest != i) {
            swap(nums, i, largest);
            //递归调整，对于新的子节点继续调整
            maxHeapify(nums, largest, heapSize);
        }
    }

    private static int findKthLargestBucket(int[] nums, int k) {
        //把数组中的数都放到桶里，桶的下标就是数值，桶里的值就是数值出现的次数
        //用k不断减去桶里的值，当k减到0的时候就是目标值
        //这里用20001是因为题目数组元素范围是[-10^4, 10^4]，所以最大值是20000，再加上0
        int[] bucket = new int[20001];
        for (int num : nums) {
            bucket[num + 10000]++;
        }
        for(int i = 20000; i >=0 ; i--) {
            k -= bucket[i];
            if (k <= 0) {
                return i - 10000;
            }
        }
        return 0;
    }

    //利用快排加上快速选择算法来提高效率，因为只需要目标下标的元素，因此快排划分区间的时候如果目标下标在左边就只需要对左边进行递归，反之亦然
    public static int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        return quickSelect(nums, 0, length - 1, length - k);
    }

    //在不考虑空间的情况下，用空间换时间，利用桶排思想
    //快选算法
    private static int quickSelect(int[] nums, int left, int right, int target) {
        if (left == right) {
            return nums[target];
        }
        //随机选择 base，防止最坏情况
        int base = nums[left + new Random().nextInt(right - left + 1)], i = left , j = right;
        while (i <= j) {
            //这里加入i <= j是为了防止i和j越界
            while (base < nums[j] && i <= j) {
                j--;
            }
            while (base > nums[i] && i <= j) {
                i++;
            }
            //交换，避免重复交换打乱顺序
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        if (target <= j) {
            return quickSelect(nums, left, j, target);
        } else {
            return quickSelect(nums, j + 1, right, target);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
