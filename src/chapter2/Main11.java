package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hao.yan on 2015/11/16.
 */
public class Main11 {
    //求出一个平面中距距离最近的两个点的距离
    //暴力解决的情况下，两两相连，复杂度o(n^2)
    //由一维的情况去延伸，分治法o(nlgn)
    //分支法，从中线点D分成两部分，则最小距离要么在左边，要么在右边，要么左右各一个点
    //左边为minLeft, 右边为minRight,则暂时min = Min(minLeft, minRight)
    //则判断中间时，只需要在d-min到d+min中的点去找即可，超出这个范围的点不可能是最短距离的两点
    //在判断中间时，先固定左边的一个点，则右边符合的点的Y坐标的范围为【 leftY - min, leftY + min】,只要
    //查找这些符合的点即可

    public static void main(String[] args) {
        List<Point> list = new ArrayList<Point>();
        list.add(new Point(82, 35));
        list.add(new Point(43, 67));
        list.add(new Point(70, 98));
        list.add(new Point(71, 94));
        list.add(new Point(24, 61));
        list.add(new Point(21, 34));
        list.add(new Point(5, 2));
        list.add(new Point(81, 37));
        list.add(new Point(67, 29));
        list.add(new Point(42, 76));
        list.add(new Point(6, 3));
        System.out.println("min distance: " + getMin(list, 5, 82));
    }

    static double getMin(List<Point> list, int low, int high) {
        if (list == null || list.size() <= 1 || low > high) {
            return Double.MAX_VALUE;
        }

        if (list.size() == 2) {
            return Point.getDistance(list.get(0), list.get(1));
        }

        int mid = low + (high - low) / 2;
        List<Point> leftList = new ArrayList<Point>();
        List<Point> rightList = new ArrayList<Point>();
        for (Point point : list) {
            if (point.x > mid)
                rightList.add(point);
            else
                leftList.add(point);
        }
        double leftMin = getMin(leftList, low, mid);
        double rightMin = getMin(rightList, mid, high);
        double minDist = Math.min(leftMin, rightMin);
        double result = minDist;

        leftList.clear();
        rightList.clear();
        for (Point point : list) {
            if (point.x > mid && point.x <= mid + minDist)
                rightList.add(point);
            else if (point.x <= mid && point.x >= mid - minDist)
                leftList.add(point);
        }
        for (Point left : leftList) {
            for (Point right : rightList) {
                if (right.y <= left.y + minDist && right.y >= left.y - minDist) {
                    result = Math.min(result, Point.getDistance(left, right));
                }
            }
        }

        return result;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static double getDistance(Point p1, Point p2) {
            double xd = Math.pow(p1.x - p2.x, 2);
            double yd = Math.pow(p1.y - p2.y, 2);
            return Math.sqrt(xd + yd);
        }
    }
}
