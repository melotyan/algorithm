package chapter2;

/**
 * Created by hao.yan on 2015/11/13.
 */
public class Main5 {
    //求一个数组（n个数，不存在相同的数）中最大的k个数,
    //思想：求第（n-k-1)大的数，然后大于等于它的就刚好K个数
    //以下是求第（n-k-1)大的数
    static int[] num1 = {11, 12, 34, 4, 15, 6, 17, 8, 9, 10};
    static int[] num2 = {11, 12, 35, 4, 15, 66, 73, 8, 9, 10};
    static int[] num3 = {11, 12, 35, 4, 15, 66, 73, 8, 9, 10};
    static int k = 4;
    final static int INDEX = num1.length - k;
    public static void main(String[] args) {

        getK1(num1, k);
        System.out.println();
        getK2(num2, k);
        System.out.println();
        getK3(num3, k);
    }
    //快排思想，分割，将所求之数移到下标为（n-k)的地方，然后右边的都是最大的k个数
    static void getK1(int[] num, int k) {
        if (num == null || num.length <= 0 || num.length < k)
            return;
        int low = 0;
        int high = num.length - 1;
        while (low < high) {
            int i = divide(num, low, high);
            if (i == INDEX) {
                low = i;
                break;
            } else if (i > INDEX) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        //low就是那个数的下标,打印结果集
        for (int i = low; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
    }

    //拿一个数，对数组进行划分
    static int divide(int[] num, int low, int high) {
        if (num == null || num.length <= 0 || low > high)
            return -1;

        int temp = num[low];
        while (low < high) {
            while (low < high && num[high] >= temp) {
                high--;
            }
            num[low] = num[high];
            while (low < high && num[low] <= temp) {
                low++;
            }
            num[high] = num[low];
        }
        num[low] = temp;
        return low;
    }

    //分配一个数组，count[i]表示i出现的次数，适合num中数组为连续的情况， 有重复最好，不过这里没有
    //要求num中的数为大于等于0的整数
    static void getK2(int[] num, int k) {
        if (num == null || num.length <= 0 || num.length < k)
            return;
        int max = num[0];
        for (int i = 1; i < num.length; i++)  {
            if (num[i] > max)
                max = num[i];
        }

        int[] count = new int[max + 1];
        for (int i = 0; i < num.length; i++) {
            count[num[i]]++;
        }

        //keyValue就是原数组中第 n-k-1大的数
        int c = 0;
        int keyValue = 0;
        for (int i = 0; i < count.length; i++) {
            c += count[i];
            if (c >= INDEX + 1) {
                keyValue = i;
                break;
            }
        }

        //打印结果集
        for (int i = 0; i < num.length; i++) {
            if (num[i] >= keyValue) {
                System.out.print(num[i] + " ");
            }
        }
    }

    //与方法二类似，先求num里面的min,max，然后在min,max之间二分，每次取中间的数，
    //判断这个数是不是第n-k+1个数
    static void getK3(int[] num, int k) {
        if (num == null || num.length <= 0 || num.length < k)
            return;
        int min = num[0], max = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] > max)
                max = num[i];
            else if (num[i] < min)
                min = num[i];
        }

        int mid = 0;
        while (max > min) {
            mid = min + (max - min) / 2;
            int val = isKth(num, mid, k);
            if (val == 0)
                break;
            else if (val > 0)
                max = mid - 1;
            else
                min = mid + 1;
        }
        System.out.println("mid is " + mid);
        //打印结果集
        for (int i = 0; i < num.length; i++) {
            if (num[i] >= mid) {
                System.out.print(num[i] + " ");
            }
        }

    }

    static int isKth(int[] num, int n, int k) {
        int count = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] >= n) {
                count++;
            }
        }
        if (count == k)
            return 0;
        if (count > k)
            return -1;
        return 1;
    }

}
