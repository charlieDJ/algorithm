package algorithm.sort;

import java.util.Arrays;

public class Insert {

    public static void insertSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 1; i <= n; i++) {
            int value = a[i];
            int j = i - 1;
            //查找插入的位置
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    // 移动数据
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            // 插入数据
            a[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] a = {11, 2, 9, 7, 8, 10};
        insertSort(a, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

}
