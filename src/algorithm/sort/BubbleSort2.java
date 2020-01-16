package sort;

/**
 * @author dengjia
 * @date 2019/7/19 15:42
 */
public class BubbleSort2 {
    /**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较.
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void sort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        // 最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界,每次只需要比较到这里即可退出
        int sortBorder = n - 1;
        for (int i = 0; i < n; i++) {
            // 提前退出标志位
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    // 此次冒泡有数据交换
                    flag = true;
                    // 更新最后一次交换的位置
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            // // 没有数据交换，提前退出
            if (!flag) {
                break;
            }
        }
    }
}
