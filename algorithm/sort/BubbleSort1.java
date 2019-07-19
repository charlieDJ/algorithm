package algorithm.sort;

import java.util.Arrays;

/**
 * @author dengjia
 * @date 2019/7/19 15:41
 */
public class BubbleSort1 {
    /**
     * 冒泡排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; ++i) {
            // 提前退出标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                // 交换
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    // 此次冒泡有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort(array, array.length);
        System.out.println(Arrays.toString(array));
    }
}
