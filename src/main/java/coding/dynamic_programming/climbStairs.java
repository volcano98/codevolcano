package coding.dynamic_programming;

import java.util.HashMap;

public class climbStairs {
    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？n个台阶？
    //dp[n]=dp[n-1]+dp[n-2]. dp[n]表示n个台阶有dp[n]种方法爬到楼梯。
    // for()
    static int function(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
//        dp[1]=1;
//        dp[2]=2;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= 2; j++) {
                if (i - j >= 0) {
                    dp[i] = dp[i - j] + dp[i];
                }
            }
        }
        return dp[n];
    }
    //和为k的连续子数组个数。
    //1  2  3 ,5,4   k=3
//sum=0, 1,3,6,11，15
    //sum[i] 前i-1个数的和。

    public int subarraySum(int[] nums, int k) {
        int[] sum=new int[nums.length+1];
        sum[0]=0;
        int sum1=0;
        int res=0;
        HashMap<Integer,Integer> map=new HashMap();
        map.put(0,1);
        for (int i =0; i <nums.length; i++) {
//            sum1=sum1+nums[i];
            if(i==0){
                sum[i]=nums[i];
            }else sum[i]=sum[i-1]+nums[i];
            // 1 2 3
            if(map.containsKey(sum[i]-k)){
                res = map.get(sum[i]-k)+res;
            }
            map.put(sum[i],map.getOrDefault(sum[i],0)+1);
        }

        return res;
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        Boolean[] dp = new Boolean[target];
        //d[i][j]表示前i个数，能够组成和j的元素。
        //dp[i][j]=Math.
        return true;
    }


    public static void main(String[] args) {
      int s=  new climbStairs().subarraySum(new int[]{1,1,1},2);
        System.out.println(s);
    }
}
