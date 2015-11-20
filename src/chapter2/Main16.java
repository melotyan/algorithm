package chapter2;

import java.nio.ByteBuffer;

/**
 * Created by hao.yan on 2015/11/19.
 */
public class Main16 {
    //求一个数组中递增数列的最大长度
    //如2, 1, 3, -6, 4   最大长度为3, (2, 3, 4) 或 (1, 3, 4)
    public static void main(String[] args) {
        int[] num = {1,-1,2,-3,4,-5,6,-7};
        System.out.println(getMaxLenOfIncremental1(num));
        System.out.println(getMaxLenOfIncremental2(num));
    }

    //解法1，令s[i] = 从num[0]到num[i]的数组中的递增序列的最大长度
    //则当num[i] 大于 num[k]时，s[i] = max(1, s[k] + 1), 其中k从0到 i-1
    //时间复杂度 o(n^2)
    private static int getMaxLenOfIncremental1(int[] num) {
        if (num == null || num.length <= 0)
            return 0;
        int[] maxLen = new int[num.length];
        maxLen[0] = 1;
        for (int i = 1; i < num.length; i++) {
            maxLen[i] = 1;
            for (int j = 0; j < i; j++) {
                if (num[i] > num[j] && maxLen[j] + 1 > maxLen[i])
                    maxLen[i] = maxLen[j] + 1;
            }
        }

        int result = maxLen[0];
        for (int i = 1; i < maxLen.length; i++) {
            if (maxLen[i] > result)
                result = maxLen[i];
        }
        return result;
    }

    //解法二：在解法一的基础上改进
    // minVal[i]表示长度为1的递增序列中，,最后一个数的最小值.如(-1, 2, 1)中，minVal[1] = -1, minVal[2] = 1
    // 1. 若 num[i+1] > minVal[k]时，此时minVal[k+1] = num[i+1]
    // 2. 当 num[i+1] <= minVal[k]时，
    // 需要找到一个j,使得 minVal[j-1]<num[i+1]<minVal[j]
    // 此时修改minVal[j]=num[i+1],
    //即查找一个最小的j，使得minVal[j] > num[i+1],如果所有的minVal[j]都小于num[i+1]，则返回j+1
    //所以最后的解决方案是，拿 到num[i+1]之后，通过二分查找，找出一个符合条件的j
    //然后minVal[j] = num[i+1]

    private static int getMaxLenOfIncremental2(int[] num) {
        if (num == null || num.length <= 0)
            return 0;
        if (num.length == 1)
            return 1;
        int[] minVal = new int[num.length + 1];
        minVal[1] = num[0];
        int max = 1;

        for (int i = 2; i < minVal.length; i++)
            minVal[i] = Integer.MAX_VALUE; //这一步很重要，它保证了minVal数组的递增性，可以用二分查找

        for (int i = 1; i < num.length; i++) {
            int index = binarySearch(minVal, num[i]);
            if (index != -1) {
                minVal[index] = num[i];
                max = Math.max(max, index);
            }
        }
        return max;
    }

    private static int binarySearch(int[] num, int n) {
        if (num == null || num.length <=0)
            return -1;
        int low = 0;
        int high = num.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid > 0 && num[mid] > n && num[mid - 1] < n)
                return mid;
            else if (mid == 0 && num[mid] > n)
                return mid;
            else if (num[mid] < n)
                low = mid + 1;
            else if (num[mid] > n)
                high = mid - 1;
        }
        if (n > num[high])
            return high + 1;  //不用担心越界，因为high+1最大也只可能是最后一个数
        return high;
    }
}
