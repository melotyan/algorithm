package chapter2;

/**
 * Created by hao.yan on 2015/11/16.
 */
public class Main9 {
    //求斐波那契数列
    // f(n) = f(n-1) + f(n-2)
    public static void main(String[] args) {
        int n = 20;
        System.out.println(fibonacci1(n));
        System.out.println(fibonacci2(n));
    }
    // 用两个变量表示f(n-1),f(n-2)，每次求出 f(n)之后，更新f(n-1), f(n-2)
    //时间复杂度o(n),空间复杂度o(1)
    static int fibonacci1(int n) {
        if (n <= 1)
            return n;

        int first = 0;
        int second = 1;
        int result = second;

        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    // (Fn, Fn-1) = (Fn-1, Fn-2) * A
    //求得矩阵A=(1, 1; 1, 0)
    // (Fn, Fn-1) = (Fn-1, Fn-2)*A = (Fn-2, Fn-3)*A^2 = ... = (F1, F0)A^(n-1)
    // 现在我们来求 A^n
    // 举例：A^5 = A^(1*2^2 + 0*2^1 + 1*2^0) = A^(2^2) * A^(2^0)
    // 因为：A^(2^1) = (A^(2^0))^2, A^(2^2) = (A^(2^1))^2
    //时间复杂度o(logn)
    static int fibonacci2(int n) {
        int[][] A = {{1, 1},{ 1, 0}};
        int[][] initF = {{1, 0}, {0, 0}};
        int[][] result = multiply(initF, matrixPow(A, n - 1));
        return result[0][0];
    }

    //求矩阵A的n次方
    static int[][] matrixPow(int[][] num, int n) {
        int[][] result = {{1, 0},{0, 1}};

        while (n > 0) {
            if ((n & 1) > 0)
                result = multiply(result, num); //A^5 = A^(1*2^2 + 0*2^1 + 1*2^0) = A^(2^2) * A^(2^0)
            num = multiply(num, num); //A^(2^1) = (A^(2^0))^2, A^(2^2) = (A^(2^1))^2
            n >>= 1;
        }
        return result;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        result[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        result[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        result[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        result[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return result;
    }
}
