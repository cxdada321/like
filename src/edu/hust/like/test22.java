package edu.hust.like;

import java.util.Arrays;
import java.util.TreeSet;

public class test22 {
    public static void main(String[] args) {
        /**
         * 一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，
         * 其中 rooms[i] = [roomIdi, sizei] 表示有一个房间号为 roomIdi 的房间且它的面积为 sizei 。
         * 每一个房间号 roomIdi 保证是 独一无二 的。
         * 同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。
         * 第 j 个查询的答案是满足如下条件的房间 id ：
         * 房间的面积 至少 为 minSizej ，且
         * abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
         * 如果差的绝对值有 相等 的，选择 最小 的 id 。如果 没有满足条件的房间 ，答案为 -1 。
         * 请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
         */
        int[][] rooms = {{2,2},{1,2},{3,2}};/*{{7, 14},
                {11, 6},
                {3, 1},
                {9, 4},
                {14, 14},
                {17, 11},
                {22, 13},
                {6, 25},
                {12, 22},
                {21, 9}};*/
        int[][] queries = {{3,1},{3,3},{5,2}};/*{{21, 17},
                {4, 6},
                {17, 25},
                {15, 18},
                {17, 16},
                {18, 16},
                {8, 17},
                {6, 7},
                {9, 22},
                {17, 18}};*/
        int[] res = closestRoom(rooms, queries);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public static int[] closestRoom(int[][] rooms, int[][] queries) {
        //存在两个排序的时候可以考虑采用离线集合来做，即将一组操作或查询提前收集，进行统一处理，而不是在操作发生的当下实时处理
        //对两个数组先进行排列，按照size降序排列，并且保存索引

        //保存索引位置，新加一个空间是为了把对应的queries位置保存下来
        //因为后面排序位置就变了，需要保存原来的位置，这样res中对应位置才能与queries对应上
        int[][] queriesIndex = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            queriesIndex[i][0] = queries[i][0];
            queriesIndex[i][1] = queries[i][1];
            queriesIndex[i][2] = i;
        }

        //对roomsize降序排列,用Integer可以避免溢出
        Arrays.sort(rooms, (a , b) -> Integer.compare(b[1], a[1]));

        //继续排序，为什么降序排序，因为之后遍历查找的时候，能保证放入set中的roomsize都是满足当前queries要求的
        //因为前面放入的roomsize比开始最大的querissize大，那后面的queriessize就也能满足了
        Arrays.sort(queriesIndex, (a, b) -> Integer.compare(b[1], a[1]));

        int[] res = new int[queries.length];
        //保存有效的roomid，为了方便查询可以采用有序集合，可以动态维护有序数据并且快速查找,TreeSet会自动对元素升序排列
        //涉及有序集合和查找上下界问题采用TreeSet,不用用HashSet
        //用来加入有效房间的id
        TreeSet<Integer> set = new TreeSet<>();
        int roomIndex = 0;

        for (int i = 0; i < queriesIndex.length; i++) {
            int originaIndex = queriesIndex[i][2];
            //把有效房间放入集合中
            while (roomIndex < rooms.length && queriesIndex[i][1] <= rooms[roomIndex][1] ) {
                set.add(rooms[roomIndex][0]);
                roomIndex++;
            }
            //如果没有有效房间则直接返回-1
            if(set.isEmpty()) {
                res[originaIndex] = -1;
            } else {
                //查找queryid附近的有效room房间号
                Integer higher = set.ceiling(queriesIndex[i][0]);
                Integer lower = set.floor(queriesIndex[i][0]);

                res[originaIndex] = higher == null && lower == null ? -1 : higher == null ? lower : lower == null ?
                        higher : Math.abs((higher - queriesIndex[i][0])) >= Math.abs((lower - queriesIndex[i][0])) ? lower : higher;
            }

        }
        return res;

        /*//排序的方法可以做，但是会超出时间限制
        //写一个快速排序方法，queries第二个值如果比排序后的最大值还大，就直接返回-1，小的话就用二分查找找到最接近的值
        int[] res = new int[queries.length];
        //对rooms按照size排序
        quickSortSize(rooms, 0, rooms.length - 1);
        for (int i = 0; i < queries.length; i++) {
            //如果要求的size比最大的还大，直接返回-1，如果size存在满足条件的再去找id
            if (queries[i][1] > rooms[rooms.length - 1][1]) {
                res[i] = -1;
            } else {
                int index = rooms.length - 1;
                int id;
                int min = Integer.MAX_VALUE;
                while (queries[i][1] <= rooms[index][1]) {
                    if (min > Math.abs(rooms[index][0] - queries[i][0])) {
                        min = Math.abs(rooms[index][0] - queries[i][0]);
                        id = rooms[index][0];
                        res[i] = id;
                    } else if (min == Math.abs(rooms[index][0] - queries[i][0])) {
                        res[i] = Math.min(res[i], rooms[index][0]);
                    }
                    index--;
                    if (index < 0) {
                        break;
                    }
                }
            }
        }
        return res;*/
        
    }



    private static void quickSortSize(int[][] rooms, int i, int j) {
        int left = i, right = j;
        //递归出口
        if (left >= right) {
            return;
        }
        
        while (left < right) {
            //找到右边一个小于左边的值就停止从左边开始找
            if (rooms[i][1] > rooms[right][1]) {
                left++;
                if (rooms[i][1] < rooms[left][1]) {
                    //左边找到一个比第一位大的数，右边找到一个比第一位小的数，交换
                    swap(rooms, left, right);
                }
            } else {
                right--;
            }
        }

        //最后交换第一位和left的值
        swap(rooms, i, left);

        //递归左边
        quickSortSize(rooms, i, left - 1);

        //递归右边
        quickSortSize(rooms, left + 1, j);
    }

    private static void swap(int[][] rooms, int left, int right) {
        int[] temp = rooms[left];
        rooms[left] = rooms[right];
        rooms[right] = temp;
    }


}
