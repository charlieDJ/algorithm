package queue;

/**
 * @author dengjia
 * @date 2019/7/22 16:59
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    /**
     * 添加元素
     * @param e 元素
     */
    void enqueue(E e);

    /**
     * 元素出队
     * @return 元素
     */
    E dequeue();

    /**
     * 队首元素
     * @return 元素
     */
    E getFront();
}
