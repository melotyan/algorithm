package chapter3;

import java.util.List;
import java.util.Map;

/**
 * Created by hao.yan on 2015/12/8.
 */
public class Main3 {

    //计算两个字符串的距离，距离的定义为
    //str1和str2,修改一个字符需要一步，删一个字符需要一步，增加一个字符需要一步
    //直到两个字符串相等，所走过的步数即为他们的距离
    //
    //解决方案1：从两个字符串的第一个字符开始比较，如果相等，则比较第二个字符
    //如果不相等，则有三种解决方案
    //1.删除str1的第一个字符，再进行比较
    //2.删除str2的第一个字符，再进行比较
    //3.将str1的第一个字符，再进行比较
    //以此递归，然后取三种方案中的最短距离即可
    static int dfs(String str1, String str2) {
        if (str1.length() == 0)
            return str2.length();
        if (str2.length() == 0)
            return str1.length();

        if (str1.charAt(0) == str2.charAt(0))
            return dfs(str1.substring(1), str2.substring(1));
        int d1 = dfs(str1.substring(1), str2);
        int d2 = dfs(str1, str2.substring(1));
        int d3 = dfs(str1.substring(1), str2.substring(1));

        return Math.min(d1, Math.min(d2, d3)) + 1;
    }


    //解决方案2：由方案1的代码可以知，dfs(str1.substring(1), str2.substring(1))
    //计算了两次，所以可以先将这个结果保存下来，结省一次计算
    static int getDistance(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        // dp[i][j]表示字符串A从位置i(不包含）开始的后缀与
        // 字符串B从位置j（不包含）开始的后缀的距离
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化,若一个序列为空,则字符串的距离为
        // 另一个字符串所取的后缀的长度
        for (int i = 0; i <= len1; i++)
            dp[i][len2] = len1 - i;
        for (int i = 0; i <= len2; i++)
            dp[len1][i] = len2 - i;

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    // 若相等，则字符串A的字符i与字符串B的字符j并没有增加距离，
                    // 仍等于A从字符i+1开始的后缀与B从字符j+1开始的后缀的距离
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    // 若不相等,则A和B的距离加1，且取相应后缀组中三个距离中最小的那个
                    dp[i][j] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i + 1][j + 1])) + 1;
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String str1 = "abcdefsgf";
        String str2 = "bbdefgds";
        System.out.println(dfs(str1, str2));
        System.out.println(getDistance(str1, str2));
    }


}
