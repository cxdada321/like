package edu.hust.like;

import java.util.*;

/**
 * 前 K 个高频元素
 */
public class TopKFrequent {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
         * 你可以按 任意顺序 返回答案
         * 你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小
         */

        int[] nums = {1,1,1,1,2,2,2,4};
        int k = 2;
//        int[] res = topKFrequentStack(nums, k);
//        int[] res = topKFrequentHeap(nums, k);
        int[] res = topKFrequentBucket(nums, k);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }


    //利用桶排列，先获取数组的最大最小值来建立桶，防止过多空间浪费，用桶对应的坐标来表示每位数字的频率
    private static int[] topKFrequentBucket(int[] nums, int k) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
            if (min > num) {
                min = num;
            }
        }
        int[] bucket = new int[max - min + 1];
        int max_count = 0;
        //再次遍历数组，把数字频率对应到桶里，需要索引偏移
        for (int i = 0; i < nums.length; i++) {
            //找到对应数字出现的频率，将 nums[i] 对应的桶的计数加 1
            //因为桶的索引是 num - min，所以这里通过 nums[i] - min 找到对应的桶
            bucket[nums[i] - min]++;
            //获取桶里元素的最大频率值
            max_count = Math.max(max_count, bucket[nums[i] - min]);
        }

        int index = 0;
        int[] res = new int[k];
        while (max_count > 0 && index < k) {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] == max_count) {
                    //这里把位置偏移回去
                    res[index++] = i + min;
                }
                //如果已经找到k个元素，就不再继续找了
                if (index == k) {
                    break;
                }
            }
            //最大频率减 1，继续查找下一个频率的元素
            max_count--;
        }
        return res;
    }

    //利用最小堆，对于 k 频率之后的元素不用再去处理，进一步优化时间复杂度
    //先hashMap再堆排序
    //每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
    //如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
    //还是用的类似栈，只不过是优先队列，其中的元素按照某种优先级顺序排列（区别于deque），不保证先进先出，内部用comparator定义了排序规则
    private static int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //升序操作，使次数最小的在堆顶，每次新加入的元素都与堆顶元素比较
                return map.get(o1) - map.get(o2);
            }
        });
        //遍历map的key集合
        for (Integer key : map.keySet()) {
            if (heap.size() < k) {
                heap.add(key);
            } else {
                //如果新元素比堆顶元素大，则出栈进栈
                if (map.get(key) > map.get(heap.peek())) {
                    heap.poll();
                    heap.add(key);
                }
            }
        }
        return heap.stream().mapToInt(Integer::intValue).toArray();
    }


    //利用栈排序的方法比较依赖k的大小，时间复杂度为O(nlogk)
    //利用map保存每个元素的次数，利用栈保存次数最多的k个元素
    public static int[] topKFrequentStack(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        //Map.Entry获取Map中的每个键值对，entrySet() 是 Map 接口中的一个方法，它返回 Map 中所有键值对的集合
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //如果栈不为空，则在放入的时候需要排序，使最小的元素在栈顶
            if (!stack.isEmpty()) {
                inStack(entry, map, stack, k);
                //为空则先加入一个元素
            } else {
                stack.push(entry.getKey());
            }
        }
        //这里mapToInt() 是 Stream 接口的方法，它用于将流中的元素转换为 int 类型的流
        //mapToInt() 接受一个函数作为参数，用来转换每个元素，会自动对Integer拆箱得到int，隐式拆箱
        //Integer::valueOf 是一个 静态方法，用于将一个 int 值转换成一个 Integer 对象
        //Integer::intValue 是 Integer 类的 实例方法，用于将 Integer 对象拆箱成原始类型 int
        //如果这里面用的Integer::valueOf，则不需要再显式拆箱，因为已经告知了是Integer类型
        //如果调用的是Integer::intValue，则需要显式拆箱，需要告知类型
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void inStack(Map.Entry<Integer, Integer> entry, Map<Integer, Integer> map, Deque<Integer> stack, int k) {
        if (stack.isEmpty()) {
            //如果比最大的还大，那就会递归到底部，在这里把元素进栈再重新返回
            stack.push(entry.getKey());
            return;
        }
        if (map.get(stack.peek()) >= entry.getValue()) {
            //这个判断是为了之后最小次数存在相同的时候判断是否要加入
            //因为只有唯一解，因此最终结果不存在最小的次数相同的次数过多
            //比如要获取前三个，现在0，1位置都是比2位置大的，这时候又来和2位置一样大的，就需要舍去
            //如果栈还没满，可以加入，防止错漏，因为两个次数一样的也可以会存在
            if (stack.size() < k) {
                stack.push(entry.getKey());
            }

        } else {
            int temp = stack.pop();
            //将当前元素与出栈后的新的栈顶元素对比，进入递归后，如果新的栈的大于当前元素，直接入栈
            //回到这层后再把刚刚出栈的元素放回去
            inStack(entry, map, stack, k);
            //这里是防止递归到底部，如果栈的元素大于k，就不再放入栈，即栈里进入了新的元素，那栈里就得出来一个元素
            if (stack.size() < k) {
                stack.push(temp);
            }
        }
    }
}

