package exam.huawei;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] starEnd = sc.nextLine().split(" ");
        String start = starEnd[0];
        String end = starEnd[1];

        Map<String, Map<String, Integer>> graph = new HashMap<>();

        String line;
        while (!(line = sc.nextLine()).equals("0000")) {
            String[] parts = line.split(" ");
            String str1 = parts[0];
            String str2 = parts[1];
            int time = Integer.parseInt(parts[2]);

            graph.computeIfAbsent(str1, v -> new HashMap<>()).put(str2, time);
            graph.computeIfAbsent(str2, v -> new HashMap<>()).put(str1, time);
        }

        Map<String , Integer> distances = new HashMap<>();
        Map<String , String> pre = new HashMap<>();
        Set<String> visit = new HashSet<>();

        for (String s : graph.keySet()) {
            distances.put(s, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        while (visit.size() < graph.size()) {
            String cur = null;
            int min = Integer.MAX_VALUE;
            for (String s : graph.keySet()) {
                if (!visit.contains(s) && distances.get(s) < min) {
                    min = distances.get(s);
                    cur = s;
                }
            }

            if (cur == null) {
                break;
            }

            visit.add(cur);

            for (Map.Entry<String, Integer> entry : graph.get(cur).entrySet()) {
                String neighbor = entry.getKey();
                int time = entry.getValue();
                int newDistance = distances.get(cur) + time;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    pre.put(neighbor, cur);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String cur = end;
        while (cur != null) {
            path.add(0, cur);
            cur = pre.get(cur);
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}
