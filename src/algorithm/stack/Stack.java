package stack;

/**
 * @author dengjia
 * @date 2019/7/16 15:27
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();

}
