package Stack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static Stack.SimpleStack.Solution.secondGreaterElement;

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
        int[] s = secondGreaterElement(new int[]{1, 17, 18, 0, 18, 10, 20, 0});
    }

    static class Solution {
        public static int[] secondGreaterElement(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();//记录第一大元素。key:元素i， value：元素i的下一个最大元素。
            Deque<Integer> stack = new ArrayDeque<>();
            Deque<Integer> stack2 = new ArrayDeque<>();
            Deque<Integer> stack3 = new ArrayDeque<>();
            int[] res = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                    //比栈顶元素大，则是第一大元素。 value存的j。意味着针对i，从i～j，nums[j]，是第一个大于>nums[i]的元素。
                    //只需要从j出发，再找一次比i大的元素即可。
                    int j = stack.pop(); // nums[i] 是 nums[j]  的第一大元素。
                    //太复杂
//                    for (int k = i+1; k < nums.length; k++) {
//                        if(nums[k]>nums[j]){//
//                            map.put(nums[j],nums[k]);
//                            break;//找到第一个元素直接返回。
//                        }
//                    }
                    res[j] = nums[i];
                }
                //维护最近的第一大的值，单调递减栈。
                while (!stack2.isEmpty() && nums[i] >= nums[stack2.peek()]) {
                    stack3.push(stack2.pop());
                }
                while (!stack3.isEmpty()) {
                    stack.push(stack3.pop());
                }

                stack2.push(i);
            }
            // map已经维护了第二大值。
//            for (int i = 0; i < nums.length; i++) {
//                res[i] = map.getOrDefault(nums[i],-1);
//            }
            return res;
        }
    }

}
