package hust.edu;

import java.util.*;

public class test6 {
    public static void main(String[] args) {
        /*给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
        字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        for (List<String> strings : reSort(strs)) {
            for (String string : strings) {
                System.out.printf(string);
            }
        }
    }

    private static List<List<String>> reSort(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            //把每个字符串转为字符后排序再转回字符串，即指定一个统一标准
            char[] array = str.toCharArray();
            Arrays.sort(array);

            //如果有相同的键，就把值加到list里
            //使用computeIfAbsent方法，如果key不存在，就创建一个新的list，
            //如果存在就直接返回与键关联的值的引用，即可以直接在上面添加值
            //map.computeIfAbsent(new String(array), v -> new ArrayList<>()).add(str);


            List<String> newstr = map.getOrDefault(new String(array), new ArrayList<>());
            newstr.add(str);
            map.put(new String(array), newstr);
        }
        System.out.println(map);
        return new ArrayList<>(map.values());
    }
}
