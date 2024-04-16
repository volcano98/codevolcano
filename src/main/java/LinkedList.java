package src.main.java;


import src.main.java.coding.onetoHundred.ListNode;

/**
 * @author : can
 * create at:  2023/7/22  22:27
 * @description: linkedList 相关题目
 */
public class LinkedList {
    // 相交链表
    ListNode res = null;

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode A = headA;
        ListNode B = headB;
        while (headA != headB) {
            if (headA != null) headA = headA.next;
            else headA = B;
            if (headB != null) headB = headB.next;
            else headB = A;
        }
        return headA;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int left = 0;
        int right = lists.length;
        return func(left, right, lists);
    }

    ListNode func(int left, int right, ListNode[] listNodes) {
//        if(left>right)return
        if (left == right) return listNodes[left];
        int mid = left + (right - left) / 2;
        ListNode l = func(left, mid, listNodes);
        ListNode r = func(mid + 1, right, listNodes);
        return merge(l, r);
    }

    ListNode merge(ListNode left, ListNode right) {
        if (left == null || right == null) return left == null ? right : left;
        if (left.val > right.val) {
            right.next = merge(left, right.next);
            return right;
        } else {
            left.next = merge(left.next, right);
            return left;
        }
    }
}
