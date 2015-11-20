package chapter2;

/**
 * Created by hao.yan on 2015/11/19.
 */
public class Main15 {
    //在一个N*M的二维数组中，找出一个子二维数组，使得其中的元素之和最大
    //转换成一维的形式为做，先确定上下边界，然后确定左右边界
    //确定上下边界，需要枚举，时间复杂度为o(N^2)
    //然后确定左右边界，这时候，按一维的方式，只需要O(M)的时间复杂度
    //也可以枚举左右边界，然后再确定上下边界
    // 最后总的复杂度为o(N*M* min(N, M));

    public static void main(String[] args) {
        int[][] num = { {1, 2, 4, 5, 2, 3, 4},
                        {3, -1, 3, 2, 5, 1, 3},
                        {1, -9, -3, 2, 1, -3, 2},
                        {2, 3, 12, -3, 5, 2, 1}};
        System.out.println(getMaxSubValArray(num));
        System.out.println(getMaxSubArrayValByCol(num));
        System.out.println(getMaxSubArrayValByRow(num));
    }
    static int getMaxSubValArray(int[][] num) {
        if (num == null || num.length <= 0 || num[0].length <= 0)
            return 0;
        if (num.length < num[0].length)
            return getMaxSubArrayValByRow(num);
        return getMaxSubArrayValByCol(num);
    }

    static int[][] initSumArray(int[][] num) {
        int[][] tempSum = new int[num.length][num[0].length];
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[0].length; j++) {
                if (i == 0 && j == 0)
                    tempSum[0][0] = num[0][0];
                else if (i == 0)
                    tempSum[i][j] = tempSum[i][j - 1] + num[i][j];
                else if (j == 0)
                    tempSum[i][j] = tempSum[i - 1][j] + num[i][j];
                else
                    tempSum[i][j] = tempSum[i - 1][j] + tempSum[i][j - 1] - tempSum[i - 1][j - 1] + num[i][j];
            }
        }
        return tempSum;
    }

    static int getMaxSubArrayValByRow(int[][] num) {
        int max = Integer.MIN_VALUE;
        int[][] tempSum = initSumArray(num);

        for (int upRow = 0; upRow < num.length; upRow++) {
            for (int downRow = upRow; downRow < num.length; downRow++) {
                int sum = 0;
                for (int i = 0; i < num[0].length; i++) {
                    if (i == 0 && upRow == 0)
                        sum += tempSum[downRow][i];
                    else if (i == 0)
                        sum += tempSum[downRow][i] - tempSum[upRow - 1][i];
                    else if (upRow == 0)
                        sum += tempSum[downRow][i] - tempSum[downRow][i - 1];
                    else
                        sum += tempSum[downRow][i] - tempSum[downRow][i - 1] - tempSum[upRow - 1][i] + tempSum[upRow - 1][i - 1];
                    if (sum > max)
                        max = sum;
                    if (sum < 0)
                        sum = 0;
                }
            }
        }
        return max;
    }

    static int getMaxSubArrayValByCol(int[][] num) {
        int max = Integer.MIN_VALUE;
        int[][] tempSum = initSumArray(num);

        for (int leftCol = 0; leftCol < num[0].length; leftCol++) {
            for (int rightCol = leftCol; rightCol < num[0].length; rightCol++) {
                int sum = 0;
                for (int i = 0; i < num.length; i++) {
                    if (i == 0 && leftCol == 0)
                        sum += tempSum[i][rightCol];
                    else if (i == 0)
                        sum += tempSum[i][rightCol] - tempSum[i][leftCol - 1];
                    else if (leftCol == 0)
                        sum += tempSum[i][rightCol] - tempSum[i - 1][rightCol];
                    else
                        sum += tempSum[i][rightCol] - tempSum[i][leftCol - 1] - tempSum[i - 1][rightCol] + tempSum[i - 1][leftCol - 1];

                    if (sum > max)
                        max = sum;
                    if (sum < 0)
                        sum = 0;
                }
            }
        }
        return max;
    }
}
