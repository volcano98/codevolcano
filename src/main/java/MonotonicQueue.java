

import java.util.*;
import java.util.concurrent.CountDownLatch;


public class MonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //单调递减队列，1 3 1 2 3 2
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque();
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);
            if (!queue.isEmpty() && queue.peekFirst() < left) {
                queue.removeFirst();
            }
            if (i - left + 1 == k) {
                res[i - k + 1] = nums[queue.peekFirst()];
                left++;
            }
        }
        return res;
    }

    String[] index = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ans = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        dfs(digits, 0, "");
        return ans;
    }

    void dfs(String digits, int i, String res) {
        if (res.length() == digits.length()) {
            ans.add(res);
            return;
        }
        for (int j = i; j < digits.length(); j++) {
            int temp = digits.charAt(j) - '0';
            for (int k = 0; k < index[temp].length(); k++) {
                dfs(digits,j+1,res+index[temp].charAt(k));
            }
        }
    }

    public int largestRectangleArea(int[] heights) {


        //对于每一个元素，自己面积是1*height[i],想大，就要高度尽可能高的情况下，增加宽度，
        //所以维护单调递增栈，栈顶永远是最高的，小于栈顶，pop栈顶计算面积然后pop出去
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        if (heights.length == 1) return heights[0];
        for (int i = 0; i <= heights.length; i++) {
            int count = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && count < heights[stack.peek()]) {
                int temp = stack.pop();
                int w = i - 1 - (stack.isEmpty() ? -1 : stack.peek());
                res = Math.max(res, w * heights[temp]);
            }
            stack.push(i);
        }
        return res;
    }

    public boolean isMatch(String s, String p) {
        //only p contains * and .
        //dp ij symbol s[i-1]t[j-1] is or not match.
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (i > 0 && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    if (j >= 2) {
                        if (i > 0 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                            // * 用1次，0次，多次。ab a*
                            dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                        }
                        if (i > 0 && p.charAt(j - 2) != s.charAt(i - 1)) {
                            dp[i][j] = dp[i][j - 2];
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String temp = strs[i];
            char[] chars = temp.toCharArray();
            Arrays.sort(chars);
            String charStr = Arrays.toString(chars);
            List<String> list = new ArrayList<>();
            if (map.containsKey(charStr)) {
                list = map.get(charStr);
            }
            list.add(temp);
            map.put(charStr, list);
        }

        return new ArrayList<>(map.values());
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            if (intervals[i][1] >= (i == intervals.length - 1 ? Integer.MAX_VALUE : intervals[i + 1][0])) {
                left = Math.min(intervals[i][0], intervals[i + 1][0]);
            }
            res.add(new int[]{left, intervals[i][1]});
        }
        return res.toArray(new int[res.size()][]);
    }

    public int longestValidParentheses(String s) {
        //
        int[] num=new int[s.length()];
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='('){
                stack.push(i);
            }else {
                if(stack.isEmpty()){
                    num[i]=1;
                }else stack.pop();
            }
        }
        while (!stack.isEmpty()){
            num[stack.pop()]=1;
        }
        // 0110001
        //num 1 就是不合法的，0就是合法长度，求连续为0的最大长度。
        int max=0;
        for (int i = 0; i < num.length; i++) {
            int count=0;
            while (num[i]==0){
                i++;
                count++;
            }
            max=Math.max(count,max);
        }
        return max;
    }

    public static void main(String[] args) {

    }

}
