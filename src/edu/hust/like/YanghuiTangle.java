package edu.hust.like;

import java.util.ArrayList;
import java.util.List;

public class YanghuiTangle {
    public static void main(String[] args) {
        /**
         * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
         * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
         */
        int numRows = 5;
        generate(numRows).forEach(System.out::println);
    }
    public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
        //清空list，用于保存新的一行数据
        list.clear();
        //每一行的第一个都是1
        list.add(1);
        for (int j = 1; j <= i; j++) {
            //每一行最后一个都是1
            if (j == i) {
                list.add(1);
            } else {
                //中间的数是上一行的j-1和j的和
                list.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
        }
        //将这一行的数据保存到res中,这里不能直接保存list，因为list是引用类型，会随着list的改变而改变
        res.add(new ArrayList<>(list));
    }
    return res;
    }
}
