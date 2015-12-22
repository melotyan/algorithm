package chapter3;

/**
 * Created by hao.yan on 2015/12/22.
 */
public class Main4 {
    //给定一个链表的某一个结点，不是头结点也不是尾结点，要求删除这个结点
    //如 a->b->c->d
    //需要删除b，则先得到c，然后将c的值赋到b中，然后直接将b指向d即可

    public static void main(String[] args) {
        Node head = new Node(1);
        Node temp = head;
        for (int i = 2; i < 10; i++) {
            temp.next = new Node(i);
            temp = temp.next;
        }

        Node nodeD = head.next.next;
        System.out.println("delete node " + nodeD.value);
        deleteNode(nodeD);
        printList(head);

        System.out.println("reverse------------------");
        printList(reverse(head));

    }

    static void printList(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
    static void deleteNode(Node node) {
        Node cur = node.next;
        Node pos = cur.next;
        node.value = cur.value;
        node.next = pos;
    }

    static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node pre = head;
        Node cur = pre.next;
        pre.next = null;

        while (pre != null && cur != null) {
            Node pos = cur.next;
            cur.next = pre;
            pre = cur;
            cur = pos;
        }

        return pre;
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }


}
