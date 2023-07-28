package com.leetcode;

/**
 * @author：江南
 * @Classname AddTwoNumbers
 * @Description 两数相加 https://leetcode-cn.com/problems/add-two-numbers/
 * @Date 2022/4/11 22:59
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);


        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //初始化一个空链表
        ListNode pre = new ListNode();

        //定义一个可以移动的指针，用来存储两个数和的位置
        ListNode cur = pre;

        //定义一个进位数
        int carry = 0;

        while (l1 != null || l2 != null) {
            //如果l1不为空，取出l1的值，否则l1.val赋值0
            int x = l1 == null ? 0 : l1.val;

            //如果l2不为空，取出l2的值，否则l2.val赋值0
            int y = l2 == null ? 0 : l2.val;

            //计算两位数的和
            int sum = x + y + carry;

            carry = sum / 10;

            //计算两个数的和，如果大于10，则取余
            sum = sum % 10;
            cur.next = new ListNode(sum);

            //将新的链表往后移
            cur = cur.next;

            //当l1不等于null时，将l1往后移
            if (l1 != null) {
                l1 = l1.next;
            }

            //当l2不等于null时，将l2往后移
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //如果最后两位数相加有进位，将进位数赋予新的节点
        if (carry >= 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}


class ListNode {

    int val;

    ListNode next;

    ListNode() {
    }

    ;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
