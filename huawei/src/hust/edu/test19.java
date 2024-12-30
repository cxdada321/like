package hust.edu;

public class test19 {
    public static void main(String[] args) {
        /**
         * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用原地算法。
         * 原地算法，在处理数据时，不需要额外的存储空间来保存副本，而是直接在输入数据上进行操作。
         */
        int[][] matrix = {{1,0,1},{1,0,1},{1,1,1}};
        setZeroes(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }

    }
    public static void setZeroes(int[][] matrix) {
        //对于使用原地算法的，如果需要存储什么内容，可以利用本地空间存储就不需要更多的值
        //这个题目可以先遍历一遍矩阵把0的位置存储下来，放到第一行和第一列，然后再遍历一遍矩阵把0的位置所在行列都置为0
        //由于其他位置有0，会把对应的第一列和第一行置为0，因此需要判断第一行第一列是否有0，如果有0，再把第一行第一列置为0
        //并且最后遍历的时候可以从尾部开始，这样就不用再去管第一行第一列数字会变化的问题


        //只用一个布尔值的时候，是因为如果第一行存在0，会让第一个数字变为0，之后再重新补0的时候就会把第一行都换为0，
        //但是从最底部往上走，就会在最后才覆盖之前第一行的值，就不会影响其他行

        int row = matrix.length, col = matrix[0].length;
        boolean col0 = false;
        for (int i = 0; i < row; i++) {
            //为什么是第一列不是第一行，因为遍历开始的时候会从每行头开始，遍历过去后即便使头改变了，但是头部原本的数已经验证过了
            if (matrix[i][0] == 0) {
                col0 = true;
            }
            //如果是0，就把对应的行列的第一个数置为0
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //从底部开始遍历，因为如果从头部开始，会把第一行第一列的值改变，但是从底部开始，最后才会改变第一行第一列的值
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //最后处理第一列
        if (col0) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
