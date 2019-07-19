package algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insert {

    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            // 保存当前值
            int value = a[i];
            // 上个索引
            int j = i - 1;
            // 往前查找需要插入的位置
            for (; j >= 0; j--) {
                // 数据向后搬移，直到找到的值小于当前值
                if (a[j] > value) {
                    a[j + 1] = a[j];
                    // 退出循环后的j+1就是需要插入的位置
                } else {
                    break;
                }
            }
            // 替换当前值
            a[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] a = {11, 2, 9, 7, 8, 10};
        sort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

}
