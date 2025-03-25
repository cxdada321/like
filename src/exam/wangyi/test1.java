package exam.wangyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 金融大亨
 */
public class test1 {
    public static void main(String[] args) {
        /**
         * 知道n天中m支股票每天的股价
         * 每天的选择：1、买入一支股票 2、卖出一支股票 3、不操作
         * 最后一天只能有现金
         * 由于每天股价是透明的，每天都会把所有资金投入获利期望最大的一支股票，如果当天所有股票都无法获利，那么他会选择持有现金不买入
         * 现在手里有一些启动资金，希望n天后从可预知的m支股票中获得最大收益，计算一下n天后手中能有多少现金，并且列出n天中股票的买卖情况
         *
         * 输入：第一行是三个正整数，N、M、K分别代表天数，不同股票支数，启动资金
         * 接下来有n行数据，每行m个正实数wi表示当天每支股票的单价（规定买入的股票可以不为整数）
         *
         * 输出：第一行是正实数表示最后手中的现金，保留四位小数
         * 接下来n行输出买卖情况，每行输出两个整数x、y，分别是抛售了哪只股票的股份和买入了哪只股票，规定如果当天做出了买、卖操作，那么两个整数范围是0<= x, y < m
         * 如当天没有买或者卖的操作，则对应位置输出-1
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //记录初始现金
        double cash = sc.nextDouble();
        double[][] stock = new double[n][m];
        //获取每天股票的价格
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                stock[i][j] = sc.nextDouble();
            }
        }

        List<int[]> operation = new ArrayList<>();

        //每支股票的股数
        double[] stockNum = new double[m + 1];
        for (int day = 0; day < n; day++) {
            int sell = -1;
            int buy = -1;

            //可卖出的股票，并计算收益
            double maxSellProfit = 0;
            for (int i = 0; i < m; i++) {
                if (stockNum[i] > 0) {
                    double profit = stockNum[i] * stock[day][i];
                    if (profit > maxSellProfit) {
                        maxSellProfit = profit;
                        sell = i;
                    }
                }
            }

            //可买入的股票，并计算收益
            double maxBuyProfit = 0;
            for (int i = 0; i < m; i++) {
                //只有不是最后一天才会买入
                if (day < n - 1) {
                    double profit = (stock[day + 1][i] - stock[day][i]) / stock[day][i];
                    if (profit > maxBuyProfit) {
                        maxBuyProfit = profit;
                        buy = i;
                    }
                }
            }

            //卖出，买入
            if (sell != -1) {
                cash += stockNum[sell] * stock[day][sell];
                stockNum[sell] = 0;
            }

            //只有在不是最后一天并且有期望获利的时候才买入
            if (day < n - 1 && maxBuyProfit > 0) {
                double buyAmount = cash / stock[day][buy];
                stockNum[buy] += buyAmount;
                cash = 0;
            }

            operation.add(new int[]{sell, buy});
        }

        //最后一天卖出所有股票
        for (int i = 0; i < m; i++) {
            if (stockNum[i] > 0) {
                cash += stockNum[i] * stock[n - 1][i];
                stockNum[i] = 0;
            }
        }

        System.out.printf("%.4f\n", cash);
        for (int[] arr: operation) {
            System.out.println(arr[0] + " " + arr[1]);
        }
        
        
        
        
//        //dp[i][j] 表示第 i 天，持有股票 j (j = 0 to m-1) 或现金 (j = m) 的最大收益
//        double[][] dp = new double[n][m + 1];
//        //记录每天的操作
//        int[][][] operations = new int[n][m + 1][2];
//
//        //初始化第一天
//        for (int j = 0; j < m; j++) {
//            dp[0][j] = cash / stock[0][j];
//            //持有股票 j 的股数
//            operations[0][j][0] = -1;
//            operations[0][j][1] = j;
//        }
//        //持有现金
//        dp[0][m] = cash;
//        operations[0][m][0] = -1;
//        operations[0][m][1] = -1;
//
//        //动态规划
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < m + 1; j++) {
//                dp[i][j] = -1;
//
//                //前一天持有的现金转移过来
//                if (dp[i - 1][m] >= 0) {
//                    if (j < m) {
//                        //买入股票j
//                        double newStockNum = dp[i - 1][m] / stock[i][j];
//                        if (newStockNum > dp[i][j]) {
//                            dp[i][j] = newStockNum;
//                            operations[i][j][0] = -1;
//                            operations[i][j][1] = j;
//                        }
//                    } else {
//                        //继续持有现金
//                        if (dp[i - 1][m] > dp[i][j]) {
//                            dp[i][j] = dp[i - 1][m];
//                            operations[i][j][0] = -1;
//                            operations[i][j][1] = -1;
//                        }
//                    }
//                }
//
//                //从前一天持有的股票转移过来
//                for (int k = 0; k < m; k++) {
//                    if (dp[i - 1][k] >= 0) {
//                        if (j < m) {
//                            //换成股票j
//                            double cashFromK = dp[i - 1][k] * stock[i - 1][k];
//                            double newStockNum = cashFromK / stock[i][j];
//                            if (newStockNum > dp[i][j]) {
//                                dp[i][j] = newStockNum;
//                                operations[i][j][0] = k;
//                                operations[i][j][1] = j;
//                            }
//                        } else {
//                            //卖出股票 k，持有现金
//                            double cashFromK = dp[i - 1][k] * stock[i - 1][k];
//                            if (cashFromK > dp[i][j]) {
//                                dp[i][j] = cashFromK;
//                                operations[i][j][0] = k;
//                                operations[i][j][1] = -1;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        //找到最后一天持有现金的最大收益
//        double maxCash = -1;
//        int index = -1;
//        for (int j = 0; j < m + 1; j++) {
//            if (j < m) {
//                double temp = dp[n - 1][j] * stock[n - 1][j];
//                if (temp > maxCash) {
//                    maxCash = temp;
//                    index = j;
//                }
//            } else {
//                if (dp[n - 1][j] > maxCash) {
//                    maxCash = dp[n - 1][j];
//                    index = j;
//                }
//            }
//        }
//        System.out.printf("%.4f\n", maxCash);

    }
}

