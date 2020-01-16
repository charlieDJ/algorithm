package sort;

import java.util.Arrays;

/**
 * Created by wangzheng on 2018/10/16.
 */
public class QuickSort {

    /**
     * 快速排序
     *
     * @param a 数组
     * @param n 数组的大小
     */
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    /**
     * 快速排序递归函数
     *
     * @param a 数组
     * @param p 下标
     * @param r 下标
     */
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        // 获取分区点
        int q = partition(a, p, r);
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    /**
     * 原地分区
     * @param a 数组
     * @param p 下标
     * @param r 下标
     * @return 分区点
     */
    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                // 第一个元素位置不变，推进i的位置
                if (i == j) {
                    ++i;
                    // a[j]放在左侧数组最后，相当于a[i]和a[j]互换位置
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }
        // 推进后的a[i]和pivot互换
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        System.out.println("i=" + i);
        // 返回推进后的索引i
        return i;
    }

    public static void main(String[] args) {
        int[] a = {6, 11, 3, 9, 8};
        quickSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
