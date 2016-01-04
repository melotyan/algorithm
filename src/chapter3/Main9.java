package chapter3;

/**
 * Created by hao.yan on 2016/1/4.
 */
public class Main9 {
    //给定二叉数的前序和中序遍历，重构出整个二叉树，

    public static void main(String[] args) {
        String preOrder = "12435";
        String inOrder = "42153";
        Node root = rebuild(preOrder, inOrder, 5);
        posOrder(root);
    }

    //后序验证
    static void posOrder(Node root) {
        if (root != null) {
            posOrder(root.left);
            posOrder(root.right);
            System.out.println(root.value);
        }
    }
    //递归即可，很简单
    static Node rebuild(String preOrder, String inOrder, int len) {
        if (len <= 0)
            return null;
        if (len == 1)
            return new Node(inOrder.charAt(0));
        char rootCh = preOrder.charAt(0);
        Node root = new Node(rootCh);
        int i = inOrder.indexOf(rootCh);
        root.left = rebuild(preOrder.substring(1, i + 1), inOrder.substring(0, i), i);
        root.right = rebuild(preOrder.substring(i + 1), inOrder.substring(i + 1), len - i - 1);
        return root;
    }

    static class Node {
        char value;
        Node left;
        Node right;

        Node(char value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
