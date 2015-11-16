package chapter2;

/**
 * Created by hao.yan on 2015/11/16.
 */
public class Main7 {

    //求两个数的最大公约数
    //辗转相除法：f(x, y) = s表示x, y的最大公约数 k = x / y; b = x % y，所以 x = ky + b
    //当b=0时，y即为最大公约数，然后如何将b转化为0？

    //由x = ky + b得 b = x - ky，则 b = ts -kus = (t -ku)s
    //所以b也可以被s整除,所以s是(y,b)的公约数之一，现在令f(y,b) = v;则 s<=v
    // 令 y = wv, b = av, 则x = ky + b = wvy + av = (wy+a)v，所以x可以被v整除，则v是(x,y)的公约数之一
    //所以 v<=f(x,y)，即  v<=s，又因为 s<=v，所以 s==v
    //所以 f(x, y) = f(y, b)


    static int gcd(int x, int y) {
        if (x < y)
            return gcd(y, x);

        int b = x % y;
        if (b == 0)
            return y;
        return gcd(y, b);
    }

    //最小公倍数等于两数之积除于最大公约数
    // x = a*gcd, y = b * gcd
    // lcm = x * t1 = y * t2
    // lcm = a * t1 * gcd = b * t2 * gcd
    //  x和y的某个公倍数 = x * y = a * b * gcd * gcd
    //因为gcd(a, b) = 1,所以 lcm = a * b * gcd = x * y / gcd
    static int lcm(int x, int y) {
        int gcd = gcd(x, y);
        return (x * y / gcd);
    }

    public static void main(String[] args) {
        int x = 60;
        int y = 100;

        System.out.println(gcd(x, y));
        System.out.println(lcm(x, y));
    }
}
