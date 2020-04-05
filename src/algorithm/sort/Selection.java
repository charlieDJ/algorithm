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
        // 将整个数组从小到大排序
        for (int i = 0; i < n - 1; i++) {
            // 寻找 [i,n) 区间的最小元素，初始化在 i 这个位置
            int minIndex = i;
            // 最小元素的索引应该在 i 之后查找，因为选择排序就是把未排序的最小元素放在已排序区域的后面，i 之前的区域是已排序区域
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            // for 循环后寻找到了 [i,n) 这个区间的最小元素的索引
            // 交换 swap
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
