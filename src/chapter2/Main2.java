package chapter2;

/**
 * Created by hao.yan on 2015/11/12.
 */

//求N！中最后0的个数，如10! = 3828800，结果为2
    //求 N！的二进制中，最后一个1的位置
public class Main2 {
    private static int n = 3;

    public static void main(String[] args) {
        System.out.println(getZeroCount1(n));
        System.out.println(getZeroCount2(n));
        System.out.println(getLastPosOfOne(n));
    }

    //N! = M * 10^k = 2^x + 3^y + 5^z，10 = 2 * 5, 所以求Min(x, z)，即z
    static int getZeroCount1(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int j = i;
            while (j % 5 == 0) {
                count++;
                j /= 5;
            }
        }
        return count;
    }

    //同样求5的个数, = n/5 + n/5^2 + n/5^3 + ...
    //5位数贡献一个5，5^2的位数再贡献一个5
    static int getZeroCount2(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    //将N!每次右移一位，当最右为0时，可整除2，移动。为1时，不可整除2，不移动
    //所以等于求N!中2的数量 + 1，即（x + 1), 与求5的数量方法相同
    static int getLastPosOfOne(int n) {
        int count = 0;
        while (n > 0) {
            n >>= 1;
            count += n;
        }
        return count + 1;
    }

}
