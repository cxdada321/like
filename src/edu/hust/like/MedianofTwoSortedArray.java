package edu.hust.like;

/**
 * 寻找两个有序数组的中位数
 * 算法的时间复杂度应该为 O(log (m+n))
 */
public class MedianofTwoSortedArray {
    public static void main(String[] args) {
        int[] arr1 = {0,0,0,0,0}, arr2 = {-1,0,0,0,0,0,1};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
    //利用二分查找，寻找两个有序数组中第k小的数，k为(m+n)/2或者(m+n)/2 + 1
    //假设两个有序数组是A，B，可以比较A[k/2 - 1]和B[k/2 - 1]则两个索引前面都有k/2 - 1个数
    //较小值是A[k/2 - 1]而言，最多有k - 2个数比这个较小值小，即不可能是第k小的数，即A[0]到A[k/2 - 2]也可以排除
    //对于B同理，如果两个值相同，则排除A或者B均可
    //这样就可以通过排除元素不断缩小查找范围
    //对于A[k/2 - 1]和B[k/2 - 1]越界的情况，需要选取对应数组中的最后一个元素，在这种情况下，必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int n = n1 + n2;
        double res;
        int midIndex = n / 2;
        if (n % 2 == 1) {
            res = getKthElement(nums1, nums2, midIndex + 1);
        } else {
            res = (getKthElement(nums1, nums2, midIndex) + getKthElement(nums1, nums2, midIndex + 1)) / 2.0;
        }
        return res;
    }

    //查找两个升序数组中第k个小的元素
    private static int getKthElement(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        //记录两个数组上的索引起始位置
        int index1 = 0, index2 = 0;
        while (true) {
            //处理边界条件，如果起始索引位置已经超出了数组长度，则直接根据剩下k的大小，返回另外一个剩余数组第k个元素
            if (index1 == n1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == n2) {
                return nums1[index1 + k - 1];
            }
            //如果仅剩一个数组，即直接返回剩余两个数组的头元素即最小值
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            //根据k计算需要用来比较的位置，但是计算的位置的时候要注意越界的情况
            int newIndex1 = Math.min(index1 + k / 2, n1) - 1;
            int newIndex2 = Math.min(index2 + k / 2, n2) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                //减少k值，根据移除元素的长度计算k值在剩余元素里的大小
                k -= (newIndex1 - index1 + 1);
                //说明num1的前newIndex1 + 1个元素都不可能是，可以移除，所以起始索引变为newIndex1的下一位
                index1 = ++newIndex1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = ++newIndex2;
            }
        }
    }


    //利用归并排序
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] nums = new int[n1 + n2];
        int left = 0, right = 0, index = 0;
        while (left < n1 && right < n2) {
            if (nums1[left] < nums2[right]) {
                nums[index++] = nums1[left++];
            } else {
                nums[index++] = nums2[right++];
            }
        }
        while (left < n1) {
            nums[index++] = nums1[left++];
        }
        while (right < n2) {
            nums[index++] = nums2[right++];
        }
        double res = 0;
        int n = nums.length;
        if (n % 2 == 1) {
            res = nums[n / 2];
        } else {
            res = (nums[n / 2] + nums[n / 2 - 1]) / 2.0;
        }
        return res;
    }
}
