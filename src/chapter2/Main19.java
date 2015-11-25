package chapter2;

import java.util.*;

/**
 * Created by hao.yan on 2015/11/24.
 */
public class Main19 {
    //判断一个源区间，是否在几个区间覆盖的区域内，
    //如[1,6]和[1,2],[3,6],[2,4],则[1, 6]在给定的区间内
    //所给的区间都是第一个数小于第二个数
    public static void main(String[] args) {
        int[] target = {1, 6};
        int[][] nums = {{1, 2}, {4, 6}, {2, 3}};
        System.out.println(isInside1(target, nums));
        System.out.println(isInside2(target, nums));
    }


    //解法一：将已知的区间按第一个数排序 O(nlogn)
    //然后合并这些区间 O(n)
    //然后用二分查找，判断源区间是否在合并之后的区间之内 O(nlogn)
    //总时间复杂度: O(nlogn)
    private static Stack<int[]> stack = new Stack<int[]>();

    private static boolean isInside1(int[] target, int[][] nums) {
        if (target == null || target.length <= 0)
            return true;
        if (nums == null || nums.length <= 0 || nums[0].length <= 0)
            return false;
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        for (int i = 0; i < nums.length; i++)
            stack.push(nums[i]);

        List<int[]> finalList = new ArrayList<int[]>();
        while (stack.size() > 0) {
            int[] first = stack.pop();
            if (stack.size() == 0) {
                finalList.add(first);
                break;
            }
            int[] second = stack.pop();
            if (canMerge(first, second)) {
                int[] temp = new int[2];
                temp[0] = Math.min(first[0], second[0]);
                temp[1] = Math.max(first[1], second[1]);
                stack.push(temp);
            } else {
                finalList.add(first);
                stack.push(second);
            }
        }
        for (int[] arr : finalList) {
            System.out.println("[" + arr[0] + "," + arr[1] + "]");
            if (target[0] >= arr[0] && target[1] <= arr[1])
                return true;
        }
        return false;

    }

    private static boolean canMerge(int[] num1, int[] num2) {
        if (num1[0] <= num2[0]) {
            if (num1[1] < num2[0])
                return false;
            return true;
        }
        return canMerge(num2, num1);
    }


    //用并查集的思想，（1，4）则表示 1-2-3-4，即1到4之间有连通线路
    //最后只要判断给所给的源区间，从x到y是否有线路直达即可
    //这里用了数组，所以给定的区间的数不可以是负数
    //最后的时间复杂度，为O(n*m),其中m表示给定的区间中，y与x的最大值
    private static int[] father = new int[100];


    private static boolean isInside2(int[] target, int[][] nums) {
        for (int i = 0; i < father.length; i++)
            father[i] = i;//每个点一开始都是独立的，不与其他人连通

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i][0];
            for (int y = x + 1; y <= nums[i][1]; y++) { //主要的时间复杂度，如果x,y之间相差太大，则复杂度很大
                union(x, y);
            }
        }
        return isConnected(target[0], target[1]);
    }

    private static void union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
        if (xRoot != yRoot) {
            father[xRoot] = yRoot;
        }
    }

    private static int findRoot(int x) {
        int root = father[x];
        while (root != x) {
            x = root;
            root = father[x];
        }
        //压缩路径， 全部指向root
        while (x != root) {
            int temp = x;
            father[x] = root;
            x = temp;
        }
        return root;
    }

    private static boolean isConnected(int x, int y) {
        return findRoot(x) == findRoot(y);
    }

}
