package chapter2;

/**
 * Created by hao.yan on 2015/11/18.
 */
public class Main13 {
    //在一个数组中，抽出 n -1 个数，使得它们的乘积最大， 不可以用除法
    public static void main(String[] args) {
        int[] num = {24, 35, 56, 12, 235, 563, 1223, 34, 2, 546, 23};
        System.out.println(getMaxProduct1(num));
        System.out.println(getMaxProduct2(num));
    }

    //用s[i]表示从0到i的数组的乘积，用t[i]表示从i到n-1的乘积
    //则踢除第i个数时，其余 n-1个数相乘得到的结果为 s[i-1] * t[i+1]
    //则时间复杂度为o(n), 空间复杂度为o(n)
    static int getMaxProduct1(int[] num) {
        if (num == null || num.length <= 1)
            return 0;

        int[] pre = new int[num.length];
        int[] post = new int[num.length];

        pre[0] = num[0];
        for (int i = 1; i < num.length; i++) {
            pre[i] = pre[i - 1] * num[i];
        }

        post[num.length - 1] = num[num.length - 1];
        for (int i = num.length - 2; i >= 0; i--) {
            post[i] = post[i + 1] * num[i];
        }

        int result = post[1];
        for (int i = 1; i < num.length - 1; i++) {
            result = Math.max(pre[i - 1] * post[i + 1], result);
        }
        result = Math.max(pre[num.length - 2], result);

        return result;
    }

    //令N个数的乘积为P
    //1. P为0，至少有一个0，去掉那个0，得到N-1个数的乘积Q
    //1.1 Q为0，则至少含有两个0，最后答案肯定是0
    //1.2 Q为正数，直接返回Q
    //1.3 Q为负数，则有奇数个负数，所以用0去替换其中一个负数，答案为 0
    //2. P为负数，则有奇数个负数，则删除绝对值最小的那个负数，得答案
    //3. P为正数，删除绝对值最小的正数，得答案
    //时间复杂度为o(n), 空间复杂度为o(1)，比1方案好
    //但是N个数相乘，有可能会溢出，这也是题目不让用除法的目的
    //但是我们只需要知道P的属性，所以直接扫一遍数组，根据0的数量，负数的数量，就可以知道P的正负属性了
    static int getMaxProduct2(int[] num) {
        if (num == null || num.length <= 1)
            return 0;

        int zeroCount = 0;
        int negCount = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0)
                zeroCount++;
            else if (num[i] < 0)
                negCount++;
        }

        if (zeroCount > 0) { //所有数乘积为0
            int zeroIndex = 0;
            for (int i = 0; i < num.length; i++) {
                if (num[i] == 0) {
                    zeroIndex = i;
                    break;
                }
            }
            int Q = getProductExceptN(num, zeroIndex);
            if (Q >= 0)
                return Q;
            return 0;
        } else if ((negCount & 1) > 0) { //所有数乘积为负数
            int minusIndex = 0;
            int maxNeg = Integer.MIN_VALUE;
            for (int i = 0; i < num.length; i++) {
                if (num[i] < 0 && num[i] > maxNeg) {
                    minusIndex = i;
                    maxNeg = num[i];
                }
            }
            return getProductExceptN(num, minusIndex);
        } else { //所有数的乘积为正数
            int posIndex = 0;
            int minPos = Integer.MAX_VALUE;
            for (int i = 0; i < num.length; i++) {
                if (num[i] > 0 && num[i] < minPos) {
                    posIndex = i;
                    minPos = num[i];
                }
            }

            return getProductExceptN(num, posIndex);
        }
    }

    static int getProductExceptN(int[] num, int n) {
        int result = 1;
        for (int i = 0; i < num.length; i++) {
            if (i == n)
                continue;
            result *= num[i];
        }
        return result;
    }

}
