package Stack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleStack {
    //最大矩形
    //找到只包含1的最大矩形
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = new int[matrix[0].length];
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = i;
                int v = 0;
                while (temp >= 0 && matrix[temp][j] == '1') {
                    v++;
                    temp--;
                }
                arr[j] = v;
            }
            res = Math.max(res, maxfind(arr));
        }
        return res;
    }

    int maxfind(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int h = nums[stack.pop()];
                int w = i - (stack.isEmpty() ? 0 : stack.peek());
                res = Math.max(res, w * h);
            }
            stack.push(i);
        }
        return res;
    }

    public int singleNumber(int[] nums) {


        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        AtomicInteger res = new AtomicInteger();
        map.forEach((k, v) -> {
            if (v.equals(1)) {
                res.set(v);
            }
        });
        return res.get();
    }

    //三数之和。
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0 || nums[0] >= 0) return new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1 && nums[i] == nums[i + 1]) continue;
            // 目标就是 a+b=-nums[i];
            int target = -nums[i];
            //数组已经排序好了，用双指针吧。
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (target == nums[left] + nums[right]) {
                    List<Integer> sums = new ArrayList<>();
                    sums.add(nums[i]);
                    sums.add(nums[left]);
                    sums.add(nums[right]);
                    //防止相同解。
                    while (left < right && (target == nums[++left] + nums[right])) ;
                    while (left < right && (target == nums[left] + nums[--right])) ;
                } else if (target < nums[left] + nums[right]) {
                    left++;
                } else {
                    right--;
                }

            }

        }
        return res;
    }

    int func(String s, int i, int j) {
        int res = 1;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            res++;
        }
        return res;
    }

    public int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] < nums[stack.peek()]) {
                stack.push(i);
            }
        }
        int res = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }

    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            map.put(character, map.getOrDefault(character, 0) + 1);
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            map.put(temp, map.get(temp) - 1);
            if (stack.contains(temp)) continue;
            while (!stack.isEmpty() && temp < stack.peek() && map.get(stack.peek()) > 0) {
                stack.pop();
            }
            stack.push(temp);
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }
        return res.toString();

    }

    public static void main(String[] args) {
        boolean[] s = new boolean[2];
        System.out.println(s[0]);
    }

}
