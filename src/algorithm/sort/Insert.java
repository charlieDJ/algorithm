package algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insert {

    public static void sort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        // 第一个元素不动，从第二个元素开始，对于插入排序来说，第一个元素本身就有序，第二个元素向前比较
        for (int i = 1; i < n; i++) {
            // 往前查找需要插入的位置，寻找数组合适的插入位置
            // j 为什么不等于 0 呢？ 这是因为 j 这个索引的元素需要与索引 0 对应的元素做比较，所以不能等于 0，最多考察到 j=1
            for (int j = i; j > 0; j--) {
                // 如果当前元素比前一个元素小，需要交换
                if (arr[j] < arr[j - 1]) {
                    // 交换元素
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {11, 2, 9, 7, 8, 10};
//        int[] a = {2, 1};
        sort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

}
