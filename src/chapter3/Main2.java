package chapter3;

/**
 * Created by hao.yan on 2015/11/27.
 */
public class Main2 {
    //根据手机的9格拼音键盘，给手机号码，生成对应的所有的英文组合
    //如果用for循环的话，数字每增加一个，就会多一层for循环，不够灵活优美
    //所以此
    private static char[][] chars = {{' '}, {' '}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},{'j', 'k', 'l'},
            {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
    private static int[] len = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4}; //表示每个数字能代表的字母数

    private final static String PHONE_NUMBER = "23";
    public static void main(String[] args) {
        generateWord1(new StringBuilder(), 0);
        System.out.println("循环法");
        generateWord2();
    }

    //递归实现
    private static void generateWord1(StringBuilder sb, int start) {
        if (start == PHONE_NUMBER.length()) {
            System.out.println(sb.toString());
        } else {
            int num = Integer.parseInt(PHONE_NUMBER.charAt(start) + "");
            for (int j = 0; j < chars[num].length; j++) {
                if (chars[num][j] != ' ') {
                    sb.append(chars[num][j]);
                    generateWord1(sb, start + 1);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    //循环实现, 如
    //abc
    //def
    //每行抽一个出来组合，最后打印顺序为 ad, ae, af, bd, be, bf, cd, ce, cf

    private static void generateWord2() {
        int[] number = new int[PHONE_NUMBER.length()]; //用来装输入的字符串的数字形式
        int[] answer = new int[PHONE_NUMBER.length()]; //初始全部为0， 代表开始时每行都是取第一个
        //number[0] = 4即代表当前号码的第一位为4， answer[0] = 2代表当前4所代表的字符是下标为2的字符：i
        //即chars[number[0]][answer[0]] = chars[4][2] = i;

        for (int i = 0; i < PHONE_NUMBER.length(); i++) {
            number[i] = PHONE_NUMBER.charAt(i) - 48;
        }

        while (true) {
            for (int i = 0; i < PHONE_NUMBER.length(); i++) {
                System.out.print(chars[number[i]][answer[i]]);
            }
            System.out.println();

            int k = PHONE_NUMBER.length() - 1;
            while (k >= 0) {
                if (answer[k] < len[number[k]] - 1) { //当前行还未走完
                    answer[k]++;
                    break;
                } else {
                    answer[k] = 0;
                    k--;
                }
            }
            if (k < 0)
                break;
        }

    }

}
