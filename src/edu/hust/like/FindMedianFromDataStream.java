package edu.hust.like;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        obj.addNum(3);
        obj.addNum(6);
        obj.addNum(5);
        obj.addNum(4);
        double param_2 = obj.findMedian();
        System.out.println(param_2);
    }
}

//可以用优先队列维护两部分内部的排序，或者采用建堆，调整堆的方式自己建立，但是有个问题就是移除堆首后调整比较耗时
class MedianFinder {
    private PriorityQueue<Double> maxHeap;
    private PriorityQueue<Double> minHeap;


    public MedianFinder() {
        //大顶堆，放左半元素
        maxHeap = new PriorityQueue<>((o1, o2) -> (int) (o2 - o1));
        //小顶堆，放右半元素
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //如果新的元素比小顶堆堆顶大，说明要放在右半部分，但是如果右边长度过长，需要把堆顶的元素放到左半部分
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.offer((double) num);
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
        } else {
            maxHeap.offer((double) num);
            if (maxHeap.size() > minHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        }
    }

    public double findMedian() {
        //返回两个堆顶的元素
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }
}

//class MedianFinder {
//    //构造数据结构
//    //小顶堆存放较大的一半元素，即右半元素
//    private List<Double> min = new ArrayList<>();
//    //大顶堆存放较小的一半元素，即左半元素
//    //这样在堆的最上面就是最小一半元素的最大值和最大一半元素的最小值，即中间的元素
//    private List<Double> max = new ArrayList<>();
//
//    public MedianFinder(List<Double> min, List<Double> max) {
//        this.min = min;
//        this.max = max;
//    }
//
//    public MedianFinder() {
//    }
//
//    public void addNum(int num) {
//        //防止下面判断没有初值
//        if (max.isEmpty()) {
//            buildMaxHeap(num, max);
//            return;
//        }
//        //加入的元素根据两个堆的元素个数
//        //如果两个堆长度相同，则新加入的数比中间值大则放入小顶堆，否则放入大顶堆
//        //新的数如果比大顶堆堆顶小，说明要放到左半部分，是直接放还是放完要取出来取决于两个堆的长度
//        //如果长度不相同，并且新的数还要放入长的那个堆，就先放入长的堆，然后把长堆堆首的元素取出来放入短堆
//        if (num < max.getFirst()) {
//            if (max.size() <= min.size()) {
//                buildMaxHeap(num, max);
//            } else {
//                //说明左部分元素多了，需要把左部分的最大值放到右部分
//                buildMaxHeap(num, max);
//                buildMinHeap(max.getFirst(), min);
//                //由于最大堆移除了一个元素，需要重新调整最大堆
//                max.removeFirst();
//                maxHeapify(max.size(), 0, max);
//            }
//            //同理，对于右半部分处理，如果元素比最大堆堆顶大，说明要放到右半部分
//        } else {
//            if (min.size() <= max.size()) {
//                buildMinHeap(num, min);
//            } else {
//                //说明右部分元素多了，需要把右部分的最小值放到左部分
//                buildMinHeap(num, min);
//                //由于最小堆移除了一个元素，需要重新调整最大堆
//                buildMaxHeap(min.getFirst(), max);
//                min.removeFirst();
//                minHeapify(min.size(), 0, min);
//
//            }
//        }
//    }
//    //构建最大堆
//    private void buildMaxHeap(double num, List<Double> max) {
//        max.add(num);
//        int heapSize = max.size();
//        for (int i = heapSize / 2 - 1; i >= 0; i--) {
//            maxHeapify(heapSize, i, max);
//        }
//    }
//
//    private void maxHeapify(int heapSize, int i, List<Double> max) {
//        int maxIndex = i, left = 2 * i + 1, right = 2 * i + 2;
//        //如果堆顶元素比下面的元素小，则交换调整
//        if (left < heapSize && max.get(maxIndex) < max.get(left) ) {
//            maxIndex = left;
//        }
//        if (right < heapSize && max.get(maxIndex) < max.get(right)) {
//            maxIndex = right;
//        }
//        //如果前面有索引交换，说明交换后的子节点调整了，因此子节点对应的堆也要调整
//        if (maxIndex != i) {
//            swap(max, i, maxIndex);
//            maxHeapify(heapSize, maxIndex, max);
//        }
//    }
//
//    //构建小顶堆
//    private void buildMinHeap(double num, List<Double> min) {
//        min.add(num);
//        int heapSize = min.size();
//        //挨个遍历非叶子节点，调整堆
//        for (int i = heapSize / 2 - 1; i >= 0; i--) {
//            minHeapify(heapSize, i, min);
//        }
//    }
//
//    private void minHeapify(int heapSize, int i, List<Double> min) {
//        int minIndex = i, left = 2 * i + 1, right = 2 * i + 2;
//        //如果堆顶元素比下面的元素大，则交换调整
//        if (left < heapSize && min.get(minIndex) > min.get(left)) {
//            minIndex = left;
//        }
//        if (right < heapSize && min.get(minIndex) > min.get(right)) {
//            minIndex = right;
//        }
//        //如果前面有索引交换，说明交换后的子节点调整了，因此子节点对应的堆也要调整
//        if (minIndex != i) {
//            swap(min, i, minIndex);
//            minHeapify(heapSize, minIndex, min);
//        }
//    }
//
//    private void swap(List<Double> list, int i, int index) {
//        double temp = list.get(i);
//        list.set(i, list.get(index));
//        list.set(index, temp);
//    }
//
//    public double findMedian() {
//        //返回两个堆顶的元素
//        if (min.size() > max.size()) {
//            return min.getFirst();
//        } else if (min.size() < max.size()) {
//            return max.getFirst();
//        } else {
//            return (min.getFirst() + max.getFirst()) / 2;
//        }
//    }
//}
