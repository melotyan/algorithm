package chapter2;

/**
 * Created by hao.yan on 2015/11/12.
 */

//求一个数的二进制中1的个数
public class Main1 {
    private  static int n = 5;

    public static void main(String[] args) {
        System.out.println(bitOperation(n));
        System.out.println(bitOperation2(n));
    }

    //位操作, O(N), N为二进制的位数
    static int bitOperation(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    //进阶的位操作， O(M)， M为二进制中1的个数
    static int bitOperation2(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
