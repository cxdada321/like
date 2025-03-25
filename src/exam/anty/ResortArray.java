package exam.anty;

import java.util.HashSet;
import java.util.Set;

public class ResortArray {
    public static void main(String[] args) {
        String input = "113"; // 输入字符串
        Set<String> results = new HashSet<>(); // 使用 Set 来存储唯一的排列结果
        permute(input.toCharArray(), 0, results);

        // 输出结果
        for (String result : results) {
            System.out.println(result);
        }
    }

    // 生成所有排列的递归方法
    private static void permute(char[] arr, int index, Set<String> results) {
        if (index == arr.length - 1) {
            String permuted = new String(arr);
            // 检查是否以零开头
            if (!(permuted.charAt(0) == '0')) {
                results.add(permuted); // 添加到结果集
            }
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (i != index && arr[i] == arr[index]) {
                continue; // 重复字符，跳过
            }
            //这段递归的思想就是确定每个位置的字符，然后递归第二个位置的字符，直到最后一个位置
            //到了最后之后再递归回来，把每个位置的数重新换一遍，这样就能得到所有的排列
            //比如三个数，第一个数先确定，第二个位置就可以放剩下两个数的其中一个，剩下一个就是第三个数
            //第一个递归进去，然后第二个位置变了两次后，又递归回第一层，第一个位置又换一个数，然后再进去让第二个位置变两次，这样就相当于把所有的排列都找到了
            //3个数的排列就是3*2*1=6种，递归每一层的数量也是这样的
            swap(arr, index, i); // 交换字符
            permute(arr, index + 1, results); // 递归生成排列
            //递归回来之后要复原交换，这样才能继续下一个位置的排列
            swap(arr, index, i); // 还原交换
        }
    }

    // 交换数组中的两个元素
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
