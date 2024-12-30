package hust.edu;

public class test2 {
    public static void main(String[] args) {
        /*你可以将一个数组 arr 称为 山脉数组 当且仅当：

        arr.length >= 3
        存在一些 0 < i < arr.length - 1 的 i 使得：
        arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
        给定一个山脉数组 mountainArr ，返回 最小 的 index 使得 mountainArr.get(index) == target。如果不存在这样的 index，返回 -1 。

        无法直接访问山脉数组，只能使用 MountainArray 接口来访问数组：

        MountainArray.get(k) 返回数组中下标为 k 的元素（从 0 开始）。
        MountainArray.length() 返回数组的长度。
        调用 MountainArray.get 超过 100 次的提交会被判定为错误答案。此外，任何试图绕过在线评测的解决方案都将导致取消资格。
        * */

        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 3, 1};
        MountainArrayImpl arr = new MountainArrayImpl(arr1);

        int peak = findIndex(arr);
        int target = 10;
        int left = findLeft(arr, peak, target);
        int right = findRight(arr, peak, target);
        if (left != -1 && right != -1) {
            System.out.println(Math.min(left, right));
        } else if ((left != -1 || right != -1)) {
            System.out.println(left == -1 ? right : left);
        } else {
            System.out.println(-1);
        }

    }

    private static int findRight(MountainArrayImpl arr, int left, int i) {
        int right = arr.length();
        while (left != right) {
            int mid = (left + right) >>> 1;
            if (mid + 1 < arr.length() && arr.get(mid) > i) {
                //mid比i大，说明i在mid右边，那左边界移动到mid + 1，为什么加一，因为mid比i大，mid不是i，所以mid + 1是可能的i
                left = mid + 1;
            } else {
                //否则在mid左边或者等于mid，右边界移动到mid，为什么不减一，因为mid比i大，mid可能是i
                right = mid;
            }
        }
        //如果最后停留位置不是i，返回-1
        return arr.get(left) == i ? left : -1;
    }

    private static int findLeft(MountainArrayImpl arr, int right, int i) {
        int left = 0;
        int length = right - left + 1;
        while (left != right) {
            int mid = (left + right) >>> 1;
            if (mid + 1 < length && arr.get(mid) < i) {
                //mid比i小，说明i在mid右边，那左边界移动到mid + 1，为什么加一，因为mid比i小，mid不是i，所以mid + 1是可能的i
                left = mid + 1;
            } else {
                //否则在mid左边或者等于mid，右边界移动到mid，为什么不减一，因为mid比i大，mid可能是i
                right = mid;
            }
        }
        //如果最后停留位置不是i，返回-1
        return arr.get(left) == i ? left : -1;
    }

    private static int findIndex(MountainArrayImpl arr) {

        int max = 0;
        //定义左右边界
        int left = 0, right = arr.length() - 1;
        //二分查找
        while (left != right) {
            //防止整数溢出
            int mid = (right + left) >>> 1;
            //如果mid比mid + 1小，说明在上升区间，峰值肯定还在mid右边，因此左边界移动到mid + 1，为什么加一，因此此时还有比mid大的值
            //那mid就不是峰值，所以mid + 1是可能的峰值
            if (mid + 1 < arr.length() && arr.get(mid) < arr.get(mid + 1)) {
                left = mid + 1;
            } else {
                //否则在下降区间，峰值在mid左边，因此右边界移动到mid，为什么不减一，因为mid比下一位大，mid可能是峰值
                right = mid;
            }
        }
        return right;
    }
}

interface MountainArray {
      public int get(int index);
      public int length();
  }

class MountainArrayImpl implements MountainArray {
    private int[] arr;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public int length() {
        return arr.length;
    }
}
