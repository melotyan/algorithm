package chapter2;

import java.util.Arrays;

/**
 * Created by hao.yan on 2015/11/18.
 */
public class Main12 {
    //在一个数组中找出两个数，使其相加等于一个给定的数
    //假定给定的数组满足要求
    //先排序，然后令 i = 0, j = n - 1
    //当 num[i] + num[j] > n时，j--，当num[i] + num[j] < n时，i++

    public static void main(String[] args) {
        int[] num = {3, 5, 1, 87, 5, 43, 76, 34, 54, 22, 13, 34, 53, 23, 11, 30};
        int n = 110;

        Arrays.sort(num);
        int i = 0;
        int j = num.length - 1;

        while (i < j) {
            if (num[i] + num[j] == n) {
                System.out.println("num1: " + num[i] + " num2: " + num[j]);
                break;
            } else if (num[i] + num[j] < n) {
                i++;
            } else {
                j--;
            }
        }
    }
}
