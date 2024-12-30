package hust.edu;

public class test13 {
    public static void main(String[] args) {
        /**
         * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
         * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
         * 返回容器可以储存的最大水量。
         */
        int[] height = {1,1};
        System.out.println(maxArea(height));
    }
    public static int maxArea(int[] height) {
        int max = 0;
        //设置两个左右双指针，不断移动小的那个边界，把最大值保存下来，采用贪心算法
        int left = 0 , right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
