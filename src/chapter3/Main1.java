package chapter3;

/**
 * Created by hao.yan on 2015/11/26.
 */
public class Main1 {
    //给定两个字符串s1,s2，判断s2是否在s1的某个右移变体中
    //如:s1=abcde, s2=dea，则s2在s1的右移变体（deabc)中
    //解法：如果s2在s1的某个右移变体中，则s2必定在2*s1中，如dea必在abcdeabcde中
    //此时用kmp算法判断，则时间复杂度为o(n+m), 空间复杂度为o(2n)
    //再优化一下，当s2比较到s1的末尾时，用剩下的字符串去s1的开头去比，时间复杂度不变，空间复杂度o(1)
    //附上一个KMP算法讲解得不错的网址：http://www.cnblogs.com/goagent/archive/2013/05/16/3068442.html
    public static void main(String[] args) {
        String source = "abcdabcde";
        String target = "bcdeabcda";
        System.out.println(contains(source, target));
    }

    private static boolean contains(String source, String target) {
        if (source == null || target == null || target.length() > source.length())
            return false;
        int i = 0;
        int j = 0;
        int[] next = initNext(source);

        while (i < source.length() && j < target.length()) {
            if (j == -1 || source.charAt(i) == target.charAt(j)) {
                i++;
                j++;
                if (j == target.length())
                    return true;
                if (i == source.length()) {
                    while (source.charAt(i % source.length()) == target.charAt(j)) {//优化步骤，避免增加空间复杂度，判断到最后了，回到前面判断
                        i++;
                        j++;
                        if (j == target.length())
                            return true;
                    }
                    return false;
                }
            } else {
                j = next[j];
            }

        }
        return false;
    }

    private static int[] initNext(String str) {
        if (str == null || str.length() <= 0)
            return null;

        int len = str.length();
        int[] next = new int[len];
        next[0] = -1;

        int i = 1;
        int j = 0;
        while (i < len - 1) {
            if (j == -1 || str.charAt(i) == str.charAt(j)) {
                i++;
                j++;
                next[i] = j;//用 next[i] = str.charAt(i) == str.charAt(j) ? next[j] : j; 更优
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
