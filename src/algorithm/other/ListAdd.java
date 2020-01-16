package other;

import algorithm.list.ListNode;

/**
 * @author dengjia
 * @date 2019/8/14 18:26
 * 题目要求：
 * <p>
 * 用单向链表表示十进制整数，求两个正整数的和。
 * 示例一：9999+999=10998，链表表示为：9->9->9->9->null和9->9->9->null的结果为1->0->9->9->8->null
 * 示例二：12+34=46。，链表表示为：1->2->null和3->4->null的结果为4->6->null
 * 注意单项链表的方向从前向后，不允许使用其他数据结构。
 * <p>
 * 思路：
 * 1. 如果有空链表，直接返回
 * 2. 判断两个链表是否等长，不等长则左补0使之等长
 * 3. 十进制加法需要进位，反转链表再相加更方便
 * 4. 相加后，再次反转链表即为结果
 */
public class ListAdd {
    public ListNode addTwoNumbers(ListNode listNode1, ListNode listNode2) {
        // 如果有空链表，直接返回
        if (listNode1 == null) {
            return listNode2;
        } else if (listNode2 == null) {
            return listNode1;
        }

        // 判断两个链表是否等长，不等长则左补0使之相等
        if (size(listNode1) > size(listNode2)) {
            listNode2 = complete(listNode2, size(listNode1) - size(listNode2));
        } else if (size(listNode1) < size(listNode2)) {
            listNode1 = complete(listNode1, size(listNode2) - size(listNode1));
        }

        // 反转链表
        listNode1 = reserveLink(listNode1);
        listNode2 = reserveLink(listNode2);

        ListNode resultListNode = new ListNode(0);
        ListNode listNode = resultListNode;
        // 相加总和
        int sum;
        // 进位值
        int prog = 0;
        while (listNode1 != null && listNode2 != null) {
            sum = listNode1.val + listNode2.val + prog;
            prog = sum / 10;
            sum = sum % 10;
            // 尾插法
            listNode.next = new ListNode(sum);
            // 后移
            listNode = listNode.next;
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
        }
        // 最高位还有进位
        if (prog != 0) {
            listNode.next = new ListNode(prog);
        } else {
            // 去掉最前面的0,防止反转后数值不对
            resultListNode = resultListNode.next;
        }

        return reserveLink(resultListNode);
    }

    public int size(ListNode node) {
        if (node == null) {
            return 0;
        }
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }

        return size;
    }

    public ListNode complete(ListNode node, int num) {
        if (node == null) {
            return node;
        }
        if (num <= 0) {
            return node;
        }
        for (int i = 0; i < num; i++) {
            ListNode n0 = new ListNode(0);
            n0.next = node;
            node = n0;
        }

        return node;
    }

    private ListNode reserveLink(ListNode head) {
        ListNode preNode = null;
        while (head != null) {
            // 保留下一个结点
            ListNode nextNode = head.next;
            //指针反转
            head.next = preNode;
            //前结点后移
            preNode = head;
            //当前结点后移
            head = nextNode;
        }
        return preNode;
    }

    public static void main(String[] args) {
        ListAdd listAdd = new ListAdd();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(3);
        listNode2.next = new ListNode(4);
        System.err.println(listAdd.addTwoNumbers(listNode1, listNode2));
    }

}
