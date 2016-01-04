package chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hao.yan on 2016/1/4.
 */
public class Main10 {
    //分层遍历二叉树
    public static void main(String[] args) {
        Node root = new Node(0);
        Node temp = root;
        for (int i = 1; i < 5; i++) {
            temp.left = new Node(i);
            temp = temp.left;
        }
        temp = root;
        for (int i = 5; i < 10; i++) {
            temp.right = new Node(i);
            temp = temp.right;
        }
        wfs(root);

    }
    //广度优先，用队列即可，很简单
    static void wfs(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<Node>();
        Queue<Node> innerQueue = new LinkedList<Node>();
        queue.offer(root);
        while (queue.size() > 0) {
            Node top = queue.poll();
            System.out.print(top.value + " ");
            if (top.left != null)
                innerQueue.offer(top.left);
            if (top.right != null)
                innerQueue.offer(top.right);

            if (queue.isEmpty()) {
                System.out.println();
                queue.addAll(new LinkedList<Node>(innerQueue));
                innerQueue.clear();
            }
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node (int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
