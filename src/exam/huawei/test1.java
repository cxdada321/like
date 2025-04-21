package exam.huawei;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        Map<String, String> preMap = new HashMap<>();
        Map<String, List<String>> curMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            String cur = parts[0];
            String pre = parts[1];
            preMap.put(cur, pre);

            if ("NA".equals(pre)) {
                list.add(cur);
            } else {
                curMap.computeIfAbsent(pre, v -> new ArrayList<>()).add(cur);
            }
        }

        Map<String, Integer> depth = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        for (String str : list) {
            depth.put(str, 0);
            queue.add(str);
        }

        while (!queue.isEmpty()) {
            String str = queue.poll();
            List<String> cur = curMap.get(str);
            if (cur != null) {
                for (String s : cur) {
                    depth.put(s, depth.get(str) + 1);
                    queue.add(s);
                }
            }
        }

        int maxDepth = 0;
        for (int value : depth.values()) {
            if (value > maxDepth) {
                maxDepth = value;
            }
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : depth.entrySet()) {
            if (entry.getValue() == maxDepth) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);
        System.out.println(String.join(" ", result));
    }
}
