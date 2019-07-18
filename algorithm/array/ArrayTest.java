package algorithm.array;

/**
 * @author dengjia
 * @date 2019/7/17 11:31
 */
public class ArrayTest {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        array.addFirst(51);
        System.out.println(array);
    }
}
