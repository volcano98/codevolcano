package src.main.java.du;

import src.main.java.coding.onetoHundred.ListNode;

/**
 * @author : can
 * create at:  2023/3/14  19:31
 * @description: baidu
 *
 */
public class demo {

    public static void main(String[] args) {

    }
    // 大型项目设计，源码阅读.
    // 手百小程序.
    // 开源小程序，平台赋能.
    // 本地生活.
    // zongguowei
    public static ListNode func(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode left = null;
        ListNode right = node;
        while (right != null) {
            ListNode temp = right.next;
            right.next = left;
            left = right;
            right = temp;
        }
        return left;
    }
}
