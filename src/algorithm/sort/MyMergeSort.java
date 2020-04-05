package algorithm.sort;

import java.util.Arrays;

public class MyMergeSort {

    public static void mergeSort(int[] arr, int n) {
        internalMergeSort(arr, 0, n - 1);
    }

    /**
     * 递归使用归并排序，对 arr[l,r] 的范围进行排序
     *
     * @param arr 数组
     * @param l   左边界索引
     * @param r   右边界索引
     */
    private static void internalMergeSort(int[] arr, int l, int r) {
        // 说明元素只有一个，或一个都没有了，这是递归到底的情况
        if (l >= r) {
            return;
        }
        // 利用二分查找的思想。小心溢出
        int mid = (l + r) / 2;
        internalMergeSort(arr, l, mid);
        internalMergeSort(arr, mid + 1, r);
        // merge完成之后，实现了整个排序
        merge(arr, l, mid, r);
    }

    /**
     * 将 arr[l,mid] 和 arr[mid+1,r] 两部分进行归并
     *
     * @param arr 数组
     * @param l   左边界索引
     * @param mid 二分位置的索引
     * @param r   右边界索引
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        // 首先需要开辟一个新空间
        int[] aux = new int[r - l + 1];
        // 将所有元素复制到新空间
        for (int i = l; i <= r; i++) {
            // l 是一个偏移量，得减去
            aux[i - l] = arr[i];
        }
        // 首先设置两个移动索引，第一个索引指向左边数组，第二个索引指向右边索引
        int i = l, j = mid + 1;
        // 索引从 l 开始
        for (int k = l; k <= r; k++) {
            // 首先需要解决越界问题。因为有可能左边数组全部有序，右边数组还有未排序的，左边数组索引可能会越界
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 11, 3, 9, 8};
        mergeSort(a, a.length);
        System.out.println("a = " + Arrays.toString(a));
    }


}
