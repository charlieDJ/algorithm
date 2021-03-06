package algorithm.list;

/**
 * @author dengjia
 * @date 2019/7/16 14:58
 */
public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 获取链表中的元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表的index(0-based)位置添加新的元素e
     * 在链表中不是一个常用的操作，练习用：）
     *
     * @param index 索引
     * @param e     元素
     */
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            // 找到添加元素e的prev
            prev = prev.next;
        }
        // 创建当前节点e，并指定e的下一个节点为 prev.next。最后用e节点覆盖以前的prev.next
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加新的元素e
     *
     * @param e 元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表末尾添加新的元素e
     *
     * @param e 元素
     */
    public void addLast(E e) {
        add(size, e);
    }


    /**
     * 获得链表的第index(0-based)个位置的元素
     * 在链表中不是一个常用的操作，练习用：）
     *
     * @param index 索引位置
     * @return
     */
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        // 遍历至索引位置
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获得链表的第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得链表的最后一个元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表的第index(0-based)个位置的元素为e
     * 在链表中不是一个常用的操作，练习用：）
     *
     * @param index 索引
     * @param e     元素
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否有元素e
     *
     * @param e 元素
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            // 向后推移
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur).append("->");
        }
        res.append("NULL");
        return res.toString();
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 暂存以前的 prev.next
        Node retNode = prev.next;
        // 获取 prev.next.next 节点，赋值为 prev.next
        prev.next = retNode.next;
        // 赋值 prev.next.next 为null
        retNode.next = null;
        size--;

        return retNode.e;
    }

    /**
     * 从链表中删除第一个元素, 返回删除的元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从链表中删除最后一个元素, 返回删除的元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素(第一个)
     *
     * @param e 需要删除的元素
     */
    public void removeElement(E e) {

        Node prev = dummyHead;
        while (prev.next != null) {
            // prev.next等于传入参数时为目标节点
            if (prev.next.e.equals(e)) {
                break;
            }
            // 向后推进
            prev = prev.next;
        }

        if (prev.next != null) {
            // 暂存 prev.next
            Node delNode = prev.next;
            // 把 prev.next.next 节点赋值给 prev.next
            prev.next = delNode.next;
            // 删除以前的 prev.next
            delNode.next = null;
            size--;
        }
    }
}
