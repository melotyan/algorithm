package chapter2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hao.yan on 2015/11/25.
 */
public class Main21 {
    public static void main(String[] args) {
        int n = 35;
        Map<Integer, Integer> map = getSerialNum(n);
        for (Integer key : map.keySet()) {
            int count = map.get(key);
            for (int i = key; i < count + key; i++)
                System.out.print(i + " ");
            System.out.println();
        }

        findUnsuitableNum();
    }

    //1：给一个正整数，如果它可以由多个连续的数相加相得，则输出所有可能的组合情况,如 6=1+2+3
    //给定n，则令 n=j + (j+1) + (j+2) +...+ (j+i-1)共i个连续的数相加
    //则 n=(2j+i-1)i/2=ij+ i(i-1)/2所以 j= (n-i(i-1)/2) / i
    //所以只要j为整数，即可求出解，所以将i从2(至少两个数）开始递增,一直到 n/2 + 1，如果j是整数，则满足
    private static Map<Integer, Integer> getSerialNum(int n) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 2; n > i * (i - 1) / 2; i++) {
            int temp = n - i * (i - 1) / 2;
            if (temp % i == 0)
                map.put(temp / i, i);
        }
        return map;
    }

    //2：有一些数是无法找出符合条件的组合的，如32，这些数字都有什么规律
    //因为 n=(2j+i-1)i/2，2j为偶数，若i为奇数，令(2j+i-1)/2=x，则n=x*奇数
    //若i为偶数，则令i/2=x，则n=x*奇数，所以n的质因子中至少含有一个奇数
    //反证法：若n中不含有奇数的质因子，令n=a*b，则a,b都是偶数
    //设 a==2j+i-1,b=i/2， 由a得i为奇数，此时b=i/2，无法整除，不符合
    //设 a = (2j+i-1)/2, b=i, 由b得i为偶数，此时(2j+i-1)为奇数，无法整除2，不符合
    //结论：n人质因子中不含奇数，则n必定是2的n次方
    //用程序验证一下
    private static void findUnsuitableNum() {
        int n = 64;
        for (int i = 1; i <= n; i++) {
            if (getSerialNum(i).size() == 0)
                System.out.println(i + " 无法找到合适的组合");
        }
    }

    //求64位整数中，组合最长的那个数
    //组合最长，则一定是从1开始加，
    //即 n=1+2+...+k = (1+k)k/2
    //求解 (1+k)k/2 < 0xFFFF FFFF FFFF FFFF

}
