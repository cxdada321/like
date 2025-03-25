package edu.hust.like;

/**
 * 数据流中的中位数
 */
public class FindMedianFromDataStream {
    public static void main(String[] args) {
        /**
         * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
         * 例如 arr = [2,3,4] 的中位数是 3 。
         * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
         * 实现 MedianFinder 类:
         * MedianFinder() 初始化 MedianFinder 对象。
         * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
         * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
         */
        int num = 1;
        MedianFinder obj = new MedianFinder();
        obj.addNum(num);
        double param_2 = obj.findMedian();
    }
}

class MedianFinder {

    public MedianFinder() {

    }

    public void addNum(int num) {

    }

    public double findMedian() {
        return 0;
    }
}
