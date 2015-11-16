package chapter2;

/**
 * Created by hao.yan on 2015/11/16.
 */
public class Main6 {
    //求某个小数的最简分子表示形式,0.3 = 3/10, 0.3(3) = 1/3

    //对于非循环小数 a.b1b2..bn = a + b1b2..bn/10^n，然后分子分母除于最小公约数，即可得结果
    //对于循环小数 x = a.b1b2..bn(e1e2..em) = a + 0.b1b2..bn(e1e2..em) = a + b1b2..bn.(e1e2..em)/10^n
    // = a + b1b2..bn/10^n + 0.(e1e2..em)/10^n
    //令 y = 0.(e1e2..em)则 y * 10^m = e1e2..em.(e1e2..em) = e1e2..em + 0.(e1e2..em) = e1e2..em + y
    // 所以 y = e1e2..em/(10^m - 1)
    //所以 x = a + b1b2..bn/10^n + e1e2..em/(10^n * (10^m - 1))
    //       = [a(10^n * (10^m - 1)) + b1b2..bn(10^m - 1) + e1e2..em ] / (10^n * (10^m - 1))
    public static void main(String[] args) {
        String str = "2.333(345)";
        System.out.println(fraction(str));


    }

    static String fraction(String str) {
        String[] divide = str.split("\\.");
        int a = 0, b = 0, e = 0;
        int n = 0, m = 0;
        if (divide.length == 2) {
            String[] loop = divide[1].split("\\(");
            if (!loop[0].equals("")) {
                b = Integer.parseInt(loop[0]);
                n = loop[0].length();
            }
            loop[1] = loop[1].replace(")", "");
            m = loop[1].length();
            e = Integer.parseInt(loop[1]);
        }
        a = Integer.parseInt(divide[0]);

        int temp1 = (int)Math.pow(10, m) - 1;
        int temp2 = (int)Math.pow(10, n);
        int numerator = e + b * temp1 + a * temp2 * temp1;
        int denominator = temp1 * temp2;
        int gcd = gcd(numerator, denominator);
        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    static int gcd(int n, int m) {
        if (n < m)
            return gcd(m, n);
        int b = n % m;
        if (b == 0)
            return m;
        return gcd(m, b);
    }
}
