package algorithm.sort;

import java.util.Arrays;

public class Quick {

    public static void main(String[] args) {
        int[] a = {11, 2, 9, 7, 8, 10};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }


    public static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
            while (end > start && a[end] >= key){
                end--;
            }
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
            while (end > start && a[start] <= key){
                start++;
            }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) {
            //左边序列。第一个索引位置到关键值索引-1
            sort(a, low, start - 1);
        }
        if (end < high) {
            //右边序列。从关键值索引+1到最后一个
            sort(a, end + 1, high);
        }
    }
}
