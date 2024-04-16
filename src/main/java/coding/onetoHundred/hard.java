package coding.onetoHundred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class hard {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else if (s.charAt(i - 1) == '(') {
                if (i > 2) dp[i] = dp[i - 2] + 2;
                else dp[i] = 2;
            } else if ((i - dp[i - 1] - 1 >= 0) && (s.charAt(i - dp[i - 1] - 1) == '(')) {
                dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.equals(s2)){
            int[] arr=new int[26];
            for (int i = 0; i < s1.length(); i++) {
                arr[s1.charAt(i)-'a']++;
            }
            for (int i = 0; i < arr.length; i++) {
                if(arr[i]>=2)return true;
            }
            return false;
        }
        List list=new ArrayList();
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i)!=s2.charAt(i)){
                list.add(s1.charAt(i));
                list.add(s2.charAt(i));
            }
        }
        if(list.size()!=4)return false;
        if(list.get(0)==list.get(3)&&list.get(1)==list.get(2))return true;
        return false;
    }

}

