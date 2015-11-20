package chapter2;

/**
 * Created by hao.yan on 2015/11/20.
 */
public class Main17 {
    //将一个数组，循环右移K位，要求时间复杂度为O(n), 最多使用两个额外的变量
    //考虑abcd123,则循环右移三位之后，得123abcd，所以可以将这个数组看成两段，然后交换两段的位置
    //则问题转换成将两个字符串交换
    //先将abcd颠倒，得dcba123，再将123颠倒，得dcba321,再将整个字符串颠倒，得123abcd

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c', 'd', '1', '2', '3'};
        moveKStep(chars, -7);
        for (char ch : chars)
            System.out.print(ch + " ");
    }

    private static void moveKStep(char[] chars, int k) {
        k %= chars.length;
        if (k == 0)
            return;
        if (k < 0) {
            moveKStep(chars, chars.length + k);
        } else {
            reverse(chars, 0, chars.length - k - 1);
            reverse(chars, chars.length - k , chars.length - 1);
            reverse(chars, 0, chars.length - 1);
        }
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = temp;
        }
    }



}
