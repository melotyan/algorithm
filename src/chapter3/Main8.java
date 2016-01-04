package chapter3;

/**
 * Created by hao.yan on 2015/12/31.
 */
public class Main8 {
    //求一棵二叉树中，最远的两个节点之间的距离（连线数）
    private static int maxLen = 0;

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
        findMaxLen(root);
        System.out.println(maxLen);
    }

    //即求所有的节点的左孩子的最远距离加右孩子的最远距离
    static void findMaxLen(Node root) {
        if (root == null)
            return;
        if (root.left == null)
            root.leftMax = 0;
        if (root.right == null)
            root.rightMax = 0;
        if (root.left != null)
            findMaxLen(root.left);
        if (root.right != null)
            findMaxLen(root.right);

        if (root.left != null) {
            root.leftMax = 1 + Math.max(root.left.leftMax, root.left.rightMax);
        }
        if (root.right != null) {
            root.rightMax = 1 + Math.max(root.right.leftMax, root.right.rightMax);
        }

        if (root.leftMax + root.rightMax > maxLen)
            maxLen = root.leftMax + root.rightMax;
    }

    static class Node {
        int value;
        Node left;
        Node right;
        int leftMax;
        int rightMax;

        Node(int value) {
            this.value = value;
            leftMax = 0;
            rightMax = 0;
            left = null;
            right = null;
        }
    }

}
