package hust.edu;

import java.util.Arrays;

public class test16 {
    public static void main(String[] args) {
        /**
         * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
         * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
         */
        int[][] intervals = {{0, 0}};
        int[][] merge = merge(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(merge[i][0] + " " + merge[i][1]);
        }
    }
    public static int[][] merge(int[][] intervals) {
        int[][] res = new int[intervals.length][2];
        //对所有的区间进行排序
        quickSortArry(intervals, 0, intervals.length - 1);
        //记录新数组的索引
        int index = 0;
        for (int i = 0; i < intervals.length - 1; i++) {
            //如果后一个区间的左边界大于前一个区间的右边界，则不需要合并
            if (intervals[i][1] < intervals[i + 1][0]) {
                res[index] = intervals[i];
                index++;
            } else {
                //合并区间
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            }
        }
        //最后一个区间直接加入，因为即使最后个区间之前有合并，也是放到最后个区间
        res[index] = intervals[intervals.length - 1];
        return Arrays.copyOf(res, index + 1);
    }

    private static  void  quickSortArry(int[][] intervals, int left, int right) {
        int i = left, j = right;
        //递归出口
        if (i >= j) {
            return;
        }
        while(i < j) {
            //右边指针先动，找到一个比第一位小的数就停下来
            if (intervals[left][0] > intervals[j][0]) {
                //左边指针开始动，找到一个比第一位大的数就停下来，与之前比较大的值交换位置，再继续移动右边指针
                if (intervals[i + 1][0] < intervals[left][0]) {
                    i++;
                } else {
                    int[] temp = intervals[i + 1];
                    intervals[i + 1] = intervals[j];
                    intervals[j] = temp;
                    j--;
                }
            } else {
                j--;
            }
        }
        //如果全部找完还是没有就直接对换
        int[] temp = intervals[i];
        intervals[i] = intervals[left];
        intervals[left] = temp;


        //利用递归对左右两边的数组进行排序
        quickSortArry(intervals, left, i - 1);
        quickSortArry(intervals, i + 1, right);

    }
}
