package edu.hust.like;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 课程表
 */
public class CourseSchedule {

    static List<List<Integer>> edges;
    static int[] visited;
    static int[] indeg;

    public static void main(String[] args) {
        /**
         * 这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
         * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
         * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，需要先完成课程 1 。
         * 请判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
         */
        int[][] prerequisites = {{1, 0},{2, 4},{3,1},{3,2}};
        int numCourses = 5;
//        boolean res = canFinish(numCourses, prerequisites);
        boolean res = canFinishBFS(numCourses, prerequisites);
        System.out.println(res);
    }


    //也可以采用bfs，考虑拓扑排序中最前面的节点，该节点一定不会有任何入边，也就是它没有任何的先修课程要求
    //将一个节点加入答案中后，就可以移除它的所有出边，代表着它的相邻节点少了一门先修课程的要求
    //如果某个相邻节点变成了「没有任何入边的节点」，那么就代表着这门课可以开始学习了
    //按照这样的流程，不断地将没有入边的节点加入答案，直到答案中包含所有的节点（得到了一种拓扑排序）或者不存在没有入边的节点（图中包含环）
    private static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        //创建一个对应课程数大小的课程表；
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //定义每个节点入边数量
        indeg = new int[numCourses];
        //遍历关系数组，把对应邻居放到邻接表；
        for (int[] info : prerequisites) {
            //表示info[0]节点依赖于节点info[1]，即info[1]节点指向info[0]节点
            edges.get(info[1]).add(info[0]);
            //info[0]节点入边增加
            indeg[info[0]]++;
        }
        //把所有没有入边的节点保存到队列，按着队列里的节点去掉别的节点的入边，直到队列中节点为空，即所有节点的入边都已经去掉
        //核心思想就是去掉所有节点入边，如果存在环是不能全部去掉的
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        //记录访问节点的数量
        int count = 0;
        //队列不为空，即存在入度为0的节点，根据其出边移除别的节点的入边
        while (!queue.isEmpty()) {
            count++;
            int i = queue.poll();
            for (int j : edges.get(i)) {
                //移去入边
                indeg[j]--;
                if (indeg[j] == 0) {
                    queue.offer(j);
                }
            }
        }
        return count == numCourses;
    }

    //利用拓扑排序，检查图中是否存在环，利用dfs遍历每个未曾搜索的节点，搜索到底部即递归回去，并将递归回去的节点入栈，这样就会保证栈顶的节点是最后一个节点，即没有依赖的节点，构成拓扑排序
    //如果递归回去后，再进入别的入口的时候发现状态是搜索中，说明有环
    //dfs需要维护节点状态；0表示未搜索，1表示搜索中，2表示已搜索

    //核心是构建课程的先修关系图（使用邻接表），用一个列表维护每个节点的邻居，列表索引表示头节点，对应索引里的列表表示头节点指向的邻居节点，表示邻居依赖该节点
    //如果是bfs，需要维护每个节点的入度，如果一个节点入度为0，即没有依赖节点，就可以学习，将其加入队列，然后将其指向的节点的入度减1，如果减到0，就加入队列
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        //创建一个对应课程数大小的课程表；
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //遍历关系数组，把对应邻居放到邻接表；
        for (int[] info : prerequisites) {
            //表示info[0]节点依赖于节点info[1]，即info[1]节点指向info[0]节点
            edges.get(info[1]).add(info[0]);
        }
        //每个节点状态；
        visited = new int[numCourses];
        //遍历课程表
        for (int i = 0; i < numCourses; i++) {
            //如果当前节点是没有搜索过的，即进入搜索，根据搜索返回的结果判断是否有环，如果当前搜索没有环则继续搜索后续节点
            if (visited[i] == 0 && !dfs(i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(int i) {
        //说明该节点没有指向的节点为底部，返回
        if (edges.get(i).size() == 0) {
            //表示搜索完毕
            visited[i] = 2;
            return true;
        }
        //表示搜索中
        visited[i] = 1;
        for (int j : edges.get(i)) {
            //表示邻居可以搜索
            if (visited[j] == 0 && !dfs(j)) {
                return false;
            } else if (visited[j] == 1) {
                //表示有环
                return false;
            }
        }
        //表示搜索完毕
        visited[i] = 2;
        return true;
    }

}
