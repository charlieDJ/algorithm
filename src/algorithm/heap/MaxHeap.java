package algorithm.heap;

import algorithm.array.Array;

/**
 * 最大堆实现
 *
 * @param <E> 泛型，支持多种对象
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        this.data = new Array<>(capacity);
    }

    public MaxHeap() {
        this.data = new Array<>();
    }


    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 数组索引从 0 开始
     *
     * @param index 当前索引
     * @return 父亲节点索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * @param index 当前索引
     * @return 左孩子的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * @param index 当前索引
     * @return 右孩子的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 增加元素
     *
     * @param e e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮操作
     *
     * @param index 当前索引
     */
    private void siftUp(int index) {
        // index == 0，说明已经到了根节点了
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            // 更新当前节点，继续上浮
            index = parent(index);
        }
    }

    /**
     * @return 堆顶元素
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not find Max when heap is empty");
        }
        return data.get(0);
    }

    /**
     * 返回堆顶元素，并维护二叉堆的特性
     *
     * @return 返回堆顶元素
     */
    private E extractMax() {
        final E max = findMax();
        // 第一个元素和最后一个元素互换位置
        data.swap(0, data.getSize() - 1);
        // 删除最后一个元素
        data.removeLast();
        // 下沉操作，从堆顶开始
        siftDown(0);
        return max;
    }

    /**
     * 下沉操作
     *
     * @param k 索引
     */
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 比较左右孩子，谁大谁小
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            // 比较父节点与子节点，父节点大于最大的子节点，满足二叉堆的特性，退出循环
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }


}
