package chapter1;

/**
 * Created by hao.yan on 2015/11/10.
 */
public class Main8MinDistance {
    //楼层从1到9
    static int[] num = {1, 2, 3, 1, 2, 5, 4, 5, 4, 6, 4, 7, 5, 4, 3, 3, 6, 7, 8, 6, 9, 9, 9, 9, 9}; //下电梯的人
    final static int MIN = 1; //最低楼
    final static int MAX = 9; //最高楼
    static int n1, n2, n3; //n2：第i层下的人数， n1:i层以下的人数, n3:i层以上的人数
    static int[] count = new int[MAX + 1]; //每层楼对应的人数
    static int result;

    public static void main(String[] args) {
        for (int i = 0; i < num.length; i++) {
            count[num[i]]++;
        }

        n2 = count[1];
        for (int i = MIN + 1; i < count.length; i++) {
            result += (i - MIN) * count[i];
            n3 += count[i];
        }

        int target = 1;
        for (int i = MIN + 1; i <= MAX; i++) {
            if(n1 + n2 - n3 < 0) {
                result += n1 + n2 - n3;
                n1 += n2;
                n2 = count[i];
                n3 -= n2;
                target = i;
            } else {
                break;
            }
        }
        System.out.println(result + "   " + target);
    }

}
