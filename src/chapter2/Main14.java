package chapter2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by hao.yan on 2015/11/19.
 */
public class Main14 {
    //求数组中子数组和的最大值
    //从第一个数开始加，记录最大值，只要和是正数，就时时记录最大值
    //当和为负数时，证明当前的数是一个负数，全部抛弃，从下一个数开始加，并时时记录最大在值
    //时间复杂度 o(n), 空间复杂度 o(1)

    static int getMaxSubArray(int[] num) {
        if (num == null || num.length <= 0)
            return 0;
        int max = num[0];
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
            if (sum > max)
                max = sum;
            if (sum < 0)
                sum = 0;
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int[] num = {2, -9, 3, 2, 5, -6, 3, -1, 10};
        System.out.println(getMaxSubArray(num));
//        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\hao.yan\\Downloads\\getCustomProjects_resp1")));
//        String str1 = br1.readLine();
//        br1.close();
//        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\hao.yan\\Downloads\\getCustomProjects_resp2")));
//        String str2 = br2.readLine();
//        br2.close();
//        System.out.println(str1);
//        System.out.println(str2);
//
//        String[] arr1 = str1.split(",");
//        String[] arr2 = str2.split(",");
//        for (int i = 0; i < arr1.length; i++) {
//            if (!arr1[i].equals(arr2[i]))
//                System.out.println(arr1[i] + "    vs   " + arr2[i]);
//        }
//        System.out.println(str1.equals(str2));
    }
}
