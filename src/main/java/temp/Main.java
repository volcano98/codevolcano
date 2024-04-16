package src.main.java.temp;

import coding.onetoHundred.ListNode;
import coding.onetoHundred.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        firstMissingPositive(new int[]{1,2,1});
    }
    //根 左 右
    //左 根 右
    //左 右 根

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty() || root != null) {
            while (root.right != null) {
                list.add(0, root.val);
                stack.push(root);
                root = root.right;
            }
            TreeNode temp = stack.pop();
            root = temp.left;
        }
        return list;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        int k = lists.length;
        ListNode re = new ListNode(0);
        ListNode ans = re;
        re.next = lists[0];
        PriorityQueue<ListNode> pri = new PriorityQueue<>(k, Comparator.comparingInt(o -> o.val)
        );
        for (int i = 0; i < lists.length; i++) {
            pri.offer(lists[i]);
        }
        while (!pri.isEmpty()) {
            ListNode temp = pri.poll();
            re.next = temp;
            re = re.next;
            if (temp.next != null) {
                pri.offer(temp.next);
            }
        }
        return ans.next;

    }

    void func(int[] nums) {
        Arrays.sort(nums, 0, nums.length);
    }

    public static int firstMissingPositive(int[] nums) {
        boolean[] arr = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) continue;
            int j = i;
            while (j >= 0 && j < nums.length && nums[nums[j]] !=nums[j]) {
                int temp = nums[nums[j]];
                nums[nums[j]] = nums[j];
                j = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
            // if(nums[i]!=i)return i;
        }

        return nums.length;
    }
}
