package hust.edu;

public class test12 {
    public static void main(String[] args) {
        /*给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。*/
        int[] height = {4,2,0,3,2,5};
        System.out.println(trap(height));
    }

    private static int trap(int[] height) {
        int peak = getMaxIndex(height);
        return findLeft(0, 0, peak, height) + findRight(height.length - 1, height.length - 1, peak, height);
    }

    private static int findRight(int right, int left, int peak, int[] height) {
        int sum = 0;
        //从右边找到最高的柱子
        while (right > peak) {
            //从最后一个非0柱子开始
            if (height[right] != 0) {
                //比最后一个柱子高或者相等的柱子则右边界移动
                while (height[right] > height[--left]) {
                    sum += height[right] - height[left];
                }
                //左指针直接移到右指针
                right = left;
            } else {
                left--;
                right--;
            }
        }
        return sum;
    }

    private static int findLeft(int left, int right, int peak, int[] height) {
        int sum = 0;
        while (right < peak) {
            //从第一个非0柱子开始
            if (height[left] != 0) {
                //比第一个柱子高或者相等的柱子则左边界移动
                while (height[left] > height[++right]) {
                    sum += height[left] - height[right];
                }
                //左指针直接移到右指针
                left = right;
            } else {
                left++;
                right++;
            }
        }
        return sum;
    }

    private static int getMaxIndex(int[] height) {
        int index = 0, max = 0;
        for (int i = 0; i < height.length; i++) {
            if (max < height[i]) {
                max = height[i];
                index = i;
            }
        }
        return index;
    }
}
