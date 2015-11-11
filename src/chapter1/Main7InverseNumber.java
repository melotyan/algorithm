package chapter1;

import java.util.Arrays;

/**
 * Created by hao.yan on 2015/11/10.
 */
public class Main7InverseNumber {
    static int[] num = {2, 6, 3, 1, 4, 9, 7, 8, 0};
    static int count = 0;

    public static void main(String[] args) {
        divide(num, 0, num.length - 1);
        System.out.println(count);
    }

    static void divide(int[] num, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            divide(num, low, mid);
            divide(num, mid + 1, high);
            merge(num, low, mid, mid + 1, high);
        }
    }

    static void merge(int[] num, int start1, int end1, int start2, int end2) {
        int[] temp = new int[num.length];
        int cur = 0;
        int i = start1;
        int j = start2;
        while (i <= end1 && j <= end2) {
            if (num[i] <= num[j]) {
                temp[cur++] = num[i++];
            } else {
                temp[cur++] = num[j++];
                count += end1 - i + 1;
            }
        }
        while (i <= end1)
            temp[cur++] = num[i++];
        while (j <= end2)
            temp[cur++] = num[j++];
        cur = 0;
        i = start1;
        while (i <= end2)
            num[i++] = temp[cur++];
    }
}
