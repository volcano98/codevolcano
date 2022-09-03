package SourceCode;

import java.util.Arrays;

public class 背包01 {
    //给你一个整数数组 nums 和一个整数 target 。
    //
    //向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
    //
    //例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
    //返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
    //来源：力扣（LeetCode）
    //链接：https://leetcode.cn/problems/target-sum
    public int findTargetSumWays(int[] nums, int target) {
        //对于每个构成每个target，有一个正数集合和一个负数集合。
        // a+b=3.a-b=sum.
        // 2a=sum+target; a=(sum+target)/2;
        //a所有所有正数集合的和。
        //所以问题就变成：给定nums[],找到所有和为 a 的集合，并返回种类，每个元素只能使用一次。
        //用01背包来回答就是，dp[i]表示能够凑成i有多少种。
        //dp[i][j]代表前i个数，在容量为j的情况下产生的最大价值。
        // dp[i][j]=math.max(dp[i-1][j]+dp[i-1][j-weight[i]]+values[i]);
        //简化：dp[j]表示容量为j能放入的最大价值，
        // dp[j]=Math.max(dp[j],dp[j-weight[i]]+values[i]);
        //到本题dp[j]可改为能凑成最大的数。但是我要凑成最大数有多少种。
        //dp[j]=dp[j-num[i]]+dp[j]; i从0-nums.length-1;
        //dp[0]=1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int a = (sum + target) / 2;
        if (a > sum || (sum + target) % 2 != 0) {
            return 0;
        }
        if (a < 0) {
            a = -a;
        }
        int dp[] = new int[a + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = a; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        //dp[4]=0,dp[3]=0,dp[2]=1;
        return dp[dp.length - 1];
    }

    //给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
    //
    //请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
    //
    //如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
    //
    //
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode.cn/problems/ones-and-zeroes
    //输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
    //输出：4
    //解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
    //其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
    public int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j]表示容量为i，j能凑成的最大子集的长度。
        //dp[i][j]=Math.max(dp[i][j],dp[i-strs[k].get(0).size][j-strs[k].get(1).size]+1);
        //i j要分开遍历，虽然是同一维，但是二维数组要迭代数据。
        int dp[][] = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (String str : strs) {
            int k0 = 0, k1 = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    k0++;
                } else {
                    k1++;
                }
            }
            for (int i = m; i >= k0; i--) {
                for (int j = n; j >= k1; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - k0][j - k1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    //给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
    //
    //请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
    //
    //链接：https://leetcode.cn/problems/coin-change-2
    public int change(int amount, int[] coins) {
        //dp[i] 表示凑成i有dp[i]种方法。
        //dp[j] = dp[j-nums[i]]+dp[j].
        int dp[] = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) dp[j] = dp[j - coins[i]] + dp[j];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[amount];
    }

    //给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    //
    //计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，return -1；
    //你可以认为每种硬币的数量是无限的。

    //来源：力扣（LeetCode）
    //链接：https://leetcode.cn/problems/coin-change
    public int coinChange(int[] coins, int amount) {
        //dp[i] 表示凑成i的最少数量，dp[j]=dp[j-coins[i]]+1;
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                //这里要判断dp[j-coins[i]]是否能凑成，不能凑成要忽略。
                if (j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }


    public int numSquares(int n) {
        //给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
        //完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是
        //dp[i] 表示构成i需要的最少完全平方数的数量。
        // 当i是完全平方数的时候：dp[j]=Math.min(dp[j],dp[j-i]+1)；
        // 当i不是完全平方数的时候： dp[j]=Math.min(dp[j],dp[j]+dp[i]);
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i * i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j >= i * i && dp[j - i * i] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];//0 -n-2;
        int[] dp1 = new int[nums.length];//1 - n-1;
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        dp1[1]=nums[1];
        dp1[2]=Math.max(nums[1],nums[2]);
        for (int i = 3; i < nums.length; i++) {
            dp1[i]=Math.max(dp1[i-1],dp[i-2]+nums[i]);

        }
        return Math.max(dp[dp.length-1],dp1[dp1.length-1]);
    }

    public static void main(String[] args) {
        new 背包01().numSquares(4);
    }
}
