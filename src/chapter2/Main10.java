package chapter2;

/**
 * Created by hao.yan on 2015/11/16.
 */
public class Main10 {
    //找出一个数组中的最大和最小值，使比较次数尽量少
    //可以扫一遍，比较次数为  2n

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    //也可以先拿两个比较，得出min和max，然后再两个两个一组，拿大的和max比
    // 小的和min比，更新min,max,次数为 n/2 + n = 1.5n
    static int[] getMinAndMax1(int[] num) {
        int[] result = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        if (num.length == 0)
            return null;
        if (num.length == 1) {
            result[0] = result[1] = num[0];
            return result;
        }
        for (int i = 0; i < num.length; i++) {
            if (i + 1 < num.length) {
                if (num[i] < num[i + 1]) {
                    result[1] = Math.max(num[i + 1], result[1]);
                    result[0] = Math.min(num[i], result[0]);
                } else {
                    result[1] = Math.max(num[i], result[1]);
                    result[0] = Math.min(num[i + 1], result[0]);
                }
            } else if (i < num.length) {
                result[1] = Math.max(num[i], result[1]);
                result[0] = Math.min(num[i], result[0]);
            }
        }
        return result;
    }

    //也可以用分治法，分成左右两部分，求出minLeft, maxLeft, minRight, maxRight
    //然后比较minLeft,minRight得min，比较maxLeft, maxRight得max

    static int[] getMinAndMax2(int[] num, int low, int high) {
        if (num == null || num.length <= 0 || low > high || high >= num.length || low < 0)
            return null;

        int[] result = new int[2];
        if (num.length == 1) {
            result[0] = result[1] = num[0];
            return result;
        }
        if (low >= high - 1) {
            if (num[low] > num[high]) {
                result[0] = num[high];
                result[1] = num[low];
            } else {
                result[0] = num[low];
                result[1] = num[high];
            }
            return result;
        }
        if (low < high) {
            int mid = low + (high - low) / 2;
            int[] left = getMinAndMax2(num, low, mid);
            int[] right = getMinAndMax2(num, mid + 1, high);
            result[0] = Math.min(left[0], right[0]);
            result[1] = Math.max(left[1], right[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] result1 = getMinAndMax1(num);
        int[] result2 = getMinAndMax2(num, 0, num.length - 1);
        System.out.println("min: " + result1[0] + " max: " + result1[1]);
        System.out.println("min: " + result2[0] + " max: " + result2[1]);
    }
}
