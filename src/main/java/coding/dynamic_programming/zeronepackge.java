package coding.dynamic_programming;

import java.util.*;

/**
 * @author : can
 * create at:  2022/4/30  18:36
 * @description: 01背包问题
 */
public class zeronepackge {


    private static final int[] weight = new int[]{2, 1};
    //背包容量，


    //确定递推式，初始化状态。run

    /**
     * @param weight 物品重量，
     * @param value  物品价值
     */
    void packge(int[] weight, int[] value, int bagesize) {
        int w = 10;//背包的size

        // 有n件物品和一个最多能背重量为w 的背包。
        // 第i件物品的重量是weight[i]，得到的价值是value[i] 。每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大
        //dp[物品下标][背包容量]=dp[i][j]
        // 从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
        //不放下标为i的物品 dp[i][j]=dp[i-1][j],放物品dp[i][j]=dp[i-1][j-weight[i]]+values[i].
        int[][] dp = new int[][]{};
        //初始化状态，在dp[0][j]的情况下， 如果j<weight[0],dp[0][j]=0 else dp[0][j]=value[0]
        for (int j = 0; j < weight[0]; j++) {
            dp[0][j] = 0;
        }

        // 先遍历物品，再遍历背包。
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j < bagesize; j++) {
//                if (weight[i-1] > j) dp[i][j] = dp[i - 1][j];
//                else dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-weight[i]]+value[i]);
            }
        }

    }


    Map<String, String> as = new HashMap<>();

    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String a = function(s, i, i);
            String b = function(s, i, i + 1);
            res = res.length() > a.length() ? res : a;
            res = res.length() > b.length() ? res : b;
        }
        return res;
    }

    public String function(String s, int i, int j) {
        if (as.containsKey(i + "," + j)) {
            return as.get(i + "," + j);
        }
        while (i <= j && i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;

        }
        as.put(i + "," + j, s.substring(i + 1, j));
        return as.get(i + "," + j);
    }

    class MyCalendar {
        private List<int[]> list;

        public MyCalendar() {
            list = new ArrayList<>();
        }

        public boolean book(int start, int end) {

            if (list.isEmpty()) {
                return true;
            }
            boolean res = false;
            for (int[] ints : list) {
                int l = ints[0];
                int r = ints[1];
                if (start <= r || end >= l) {
                    res = false;
                } else {
                    list.add(new int[]{start, end});
                    res = true;
                }
            }

            return res;
        }
    }

    //给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
    //dp[i] 表示长度为i+1的上升子序列的尾部最小值。而不是最长上升。
    //dp[2]表示长度为2的上声子序列的尾部最小值.
    public int findNumberOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        dp[0] = 1;
        int len = 0;
        int res = 1;
        // [1,2,4,3,5,4,7,2]
        // [1 2 3 3 4 3 5 2 ]
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        System.out.print("\n" + "[");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(+dp[i] + " ");
            if (dp[i] == len) {
                //找到以i为根节点是最长的递增子序列。
                res++;
            }
        }
        System.out.print("]");
        return res;
    }

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    //输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

    public static int trap(int[] height) {
        // stack 小于栈顶放进去，大于栈顶，就
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur=stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int w = i - 1 - left;
                int h = Math.min(height[i], height[left])-height[cur];
                res = w * h + res;
            }
            stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
        int res = trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(res);
    }


}
