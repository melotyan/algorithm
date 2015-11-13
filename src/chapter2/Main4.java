package chapter2;

/**
 * Created by hao.yan on 2015/11/13.
 */
public class Main4 {
    //求一个数N中含1的个数，如13: 1, 2, 3...10, 11, 12, 13, 共6个
    //按个位，十位，百位这样来求，13：个位：01，11，十位：10，11，12，13，共六个
    //abcde，以百倍数举例，百位数上1出现的个数由三种情况组成
    //1:百位数是0，如12034, 则100-199，1100-1199，2100-2199....11100-11199，=12*100个刚好是大于百位的数 *100
    //2:百倍数是1，如12134，则100-199,1100-1199,2100-2199,...,11100-11199 + 12100-12134
    //  其中12100-12134是低于百位的，总共有35个，所以总共有35+12*100个
    //  所以百位数是1时，结果为：高于百位数的数 * 100 + 低于百倍数的数 +1
    //3:百位数大于1：如：12234，则100-199,1100-1199,..., 11100-11199,12100-12199，总共13*100个
    //  所以结果为：（高于百位数的数 + 1）* 100

    public static void main(String[] args) {
        long n = 999999999;
        int count = 0;
        long cur;
        long high;
        long low;
        long factor = 1; //从个位数开始处理

        while (n / factor > 0) {
            cur = (n % (factor * 10)) / factor;
            low = n % factor;
            high = n / (factor * 10);

            System.out.print("位数：" + factor);
            if (cur == 0) {
                count += high * factor;
                System.out.println(" 数量：" + high * factor);
            } else if (cur == 1) {
                count += high * factor + low + 1;
                System.out.println(" 数量：" + (high * factor + low + 1));
            } else {
                count += (high + 1) * factor;
                System.out.println(" 数量：" + (high + 1) * factor);
            }
            factor *= 10;
        }
        System.out.println(count);
    }
}
