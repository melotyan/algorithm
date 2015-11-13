package chapter2;

/**
 * Created by hao.yan on 2015/11/13.
 */

public class Main3 {
    private static int[] nums1 = {1, 2, 3, 1, 2, 3, 1, 4, 1, 1, 2, 1};
    private static int[] nums2 = {1, 2, 3, 1, 2, 3, 4};

    public static void main(String[] args) {
        if (nums1 == null || nums1.length <= 0 || nums2 == null || nums2.length <= 0)
            return;
        System.out.println(findMost(nums1));
        int[] result = findMostThree(nums2);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    //查找一个数组中出现次数超过一半的数
    static int findMost(int[] nums) {
        int count = 1;
        int n = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == n)
                count++;
            else {
                count--;
                if (count == 0) {
                    count = 1;
                    n = nums[i];
                }
            }
        }
        return n;
    }

    //查找数组中，出现次数超过总数四分之一的三个数
    static int[] findMostThree(int nums[]) {
        int[][] result = new int[2][3];
        for (int i = 0; i < nums.length; i++) {
            int index = findExit(result, nums[i]);
            if (index != -1) {
                result[1][index]++;
            } else {
                int free = findFree(result);
                if (free != -1) {
                    result[0][free] = nums[i];
                    result[1][free] = 1;
                } else {
                    result[1][0]--;
                    if (result[1][0] == 0) {
                        result[1][0] = 1;
                        result[0][0] = nums[i];
                    }
                }

            }

        }
        return result[0];

    }

    static int findFree(int[][] array) {
        for (int i = 0; i < array[0].length; i++) {
            if (array[1][i] == 0)
                return i;
        }
        return -1;
    }

    static int findExit(int[][] array, int n) {
        for (int i = 0; i < array[0].length; i++) {
            if (array[0][i] == n && array[1][i] != 0)
                return i;
        }
        return -1;
    }

}
