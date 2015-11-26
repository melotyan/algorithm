package chapter2;

/**
 * Created by hao.yan on 2015/11/24.
 */
public class Main18Unfinished {
    //有一个数组num,包含2n个数，其和为sum，求其中的n个数，使得其和最接近sum/2

    //解法一：动态规划
    //把任务分成2N步，Sk代表第K步，即前K个元素中，任意i个元素的和构成的集合
    //则第K步可以分为第K-1步+一步，vi表示Sk-1中的任意一个数，即得  Sk = Sk-1 U {vi + num[k]}

}
