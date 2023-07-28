package com.leetcode;

/**
 * @author ChenYP
 * @date 2023/7/6 21:21
 * @describe 链表的反转
 * 1 next——>2 next——>3 next——> 4 next ——> 5 next (将此链表进行反转使其倒叙)
 * 对于节点本身，并不知道指向上一个节点的指针，我们需要将本节点的next指向上一个节点
 */
public class ReverseList {

    static class ListNode {

        int val;

        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 第一种方法，迭代链表
     *
     * @param head
     * @return 反转后的链表
     */
    public static ListNode iterate(ListNode head) {
        ListNode prev = null, next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = recursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        /*while (node1 !=null){
            System.out.print(node1.val + ",");
            node1 = node1.next;
        }
*/
       /* ListNode iterate = iterate(node1);
        while (iterate != null) {
            System.out.print(iterate.val + ",");
            iterate = iterate.next;
        }*/
        ListNode recursion = recursion(node1);
        while (recursion != null) {
            System.out.print(recursion.val + ",");
            recursion = recursion.next;
        }
    }
}
