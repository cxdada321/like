package edu.hust.like;

/**
 * 最小栈
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class MinStack {
    //用deque队列代替stack，因为这是双向队列，只操作一端可以视为栈，如果是queue队列的话，就是先入先出
    private Deque<Integer> data;
    private Deque<Integer> min;

    public MinStack() {
        //初始化两个栈，一个栈用来存储数据，一个栈用来存储最小值
        this.data = new LinkedList<>();
        this.min = new LinkedList<>();
        this.min.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        this.data.push(val);
        this.min.push(Math.min(this.min.peek(), val));
    }

    public void pop() {
        this.data.pop();
        this.min.pop();
    }

    public int top() {
        return this.data.peek();
    }

    public int getMin() {
        return this.min.peek();
    }
}
