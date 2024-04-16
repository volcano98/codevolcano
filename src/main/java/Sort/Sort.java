package src.main.java.Sort;


import src.main.java.coding.onetoHundred.ListNode;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
     String res=  new Sort().minWindow("a",
               "a");
        System.out.println(res);
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        //dp[i][j]
        // 1[i]==2[j] dp[i][j]=dp[i-1][j-1]+1;
        // 1[i]!=2[j] dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                } else {
                    dp[i][j] = Math.max(i - 1 >= 0 ? dp[i - 1][j] : 0, j - 1 >= 0 ? dp[i][j - 1] : 0);

                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    public static boolean isSubsequence(String s, String t) {
        // dp[i][j] s的前i个元素和t的前j个元素最长相同元素。
        //dp[i-1][j-1]+1; dp[i][j-1]
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }

    public int numDistinct(String s, String t) {
        //dp[i][j] i == j dp[i][j]==dp[i-1][j-1]+dp[i-1][j].
        // i!=j dp[i][j]=dp[i-1][j].
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }
        return dp[s.length()][t.length()];
    }

    //连续递增子序列，不连续，dp表示当前符合条件的最大值。不需要全局变量。
    //子数组， 元素连续，dp表示以当前元素为底的最大值。需要全局变量。
    public static int findLength(int[] nums1, int[] nums2) {
        //dp[i][j]表示1 第i个元素，和 2 前j个元素为底。最长的子数组的长度。
        // 1[i]=2[j].  dp[i][j]=dp[i-1][j-1]+1
        // 1[i]!=2[j]. dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        int[][] dp = new int[nums1.length][nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp[nums1.length - 1][nums2.length - 1];
    }

    int[] mergeSort(int[] nums) {
        split(nums, 0, nums.length - 1);
        return nums;
    }

    void split(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        split(nums, left, mid);
        split(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            temp[index++] = Math.min(nums[i], nums[j]);
            i++;
            j++;
        }
        while (i <= mid) {
            temp[index++] = nums[i++];
        }
        while (j <= right) {
            temp[index++] = nums[j++];
        }
        for (int k = 0; k <= temp.length; k++) {
            nums[left + k] = temp[k];
        }
        return;
    }

    String func(String palindrome) {
        if (palindrome == null) return "";
        if (palindrome.length() < 2) return "";
        int mid = palindrome.length() / 2;
        for (int i = 0; i < mid; i++) {
            if (palindrome.charAt(i) != 'a') {
                String stringBuilder = palindrome.substring(0, i);
                stringBuilder = stringBuilder + "a";
                return stringBuilder + palindrome.substring(i + 1);
            }
        }
        String res = palindrome.substring(0, palindrome.length() - 1);
        return res + "b";
    }

    public static int totalFruit(int[] fruits) {
        List<Integer> list = new ArrayList<>();

        //  fruits[i]
        //求只包含2种元素的最长连续子序列。
        if (fruits.length <= 2) {
            return fruits.length;
        }
        int res = 2;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int count = 0;
        for (int i = 0; i < fruits.length; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
            while (map.size() > 2) {
                int temp = fruits[left];
                map.put(temp, map.get(temp) - 1);
                left++;
                if (map.get(temp) == 0) map.remove(temp);
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return split(lists, 0, lists.length - 1);
    }

    ListNode split(ListNode[] lists, int left, int right) {
        if (left >= right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l = split(lists, left, mid);
        ListNode r = split(lists, mid + 1, right);
        return merge(l, r);
    }

    ListNode merge(ListNode l, ListNode r) {
        ListNode res = new ListNode(0);
        ListNode now = res;
        while (l != null && r != null) {
            if (l.val > r.val) {
                now.next = r;
                r = r.next;
            } else {
                now.next = l;
                l = l.next;
            }
            now = now.next;
        }
        now.next = l != null ? l : r;
        return res;
    }

    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int temp = stack.pop();
                if (stack.isEmpty()) break;
                int weight = i - stack.peek() - 1;
                int h = Math.min(height[i], height[stack.peek()]) - height[temp];
                res = res + weight * h;
            }
            stack.push(i);
        }
        return res;
    }

    public int planStock(int[][] order, int balance) {
        // write code here
        int[] we = new int[order.length];
        for (int i = 0; i < we.length; i++) {
            we[i] = order[i][0] * order[i][1];
        }
        int[] va = new int[order.length];
        for (int i = 0; i < va.length; i++) {
            va[i] = order[i][1];
        }
        int[][] dp = new int[we.length + 1][balance + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j < we[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - we[i - 1]] + va[i - 1]);
                }
            }
        }
        return dp[order.length][balance];
    }

    public String minWindow(String s, String t) {
        //找到复合t的最小字串
        //滑动窗口，找到符合条件的字串，记录长度。左指针右移动，
        if(s.length()<t.length())return "";
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> targetWindow = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            targetWindow.put(t.charAt(i), targetWindow.getOrDefault(t.charAt(i), 0) + 1);
        }
        int count = 0;

        String res ="";
        int left = 0;
        for (int i = left; i < s.length(); i++) {
            char temp = s.charAt(i);
            window.put(s.charAt(i), window.getOrDefault(s.charAt(i), 0) + 1);
            if (targetWindow.containsKey(temp) && Objects.equals(window.get(temp), targetWindow.get(temp))) {
                count++;
            }
            //配对成功。
            while (count == targetWindow.size()) {
                char leftTemp=s.charAt(left);
                if(res.equals("")){
                    res=s.substring(left,i+1);
                }else{
                    res = (res.length() > i - left + 1) ? s.substring(left, i + 1) : res;
                }
                window.put(leftTemp, window.get(leftTemp) - 1);
                if (targetWindow.containsKey(leftTemp) && window.get(leftTemp) < targetWindow.get(leftTemp)) {
                    count--;
                }
                left++;
            }
        }

        return res;
    }


}
