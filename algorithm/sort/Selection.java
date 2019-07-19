package algorithm.sort;

import java.util.Arrays;

/**
 * @author dengjia
 * @date 2019/7/19 15:45
 */
public class Selection {
    /**
     * 选择排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            // 查找最小值对应的索引
            int minIndex = i;
            // 最小值的索引应该在i之后查找，因为选择排序就是把未排序的最小值放在已排序区域的后面，i之前的区域是已排序区域
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }


    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort(array, array.length);
        System.out.println(Arrays.toString(array));
    }
}
