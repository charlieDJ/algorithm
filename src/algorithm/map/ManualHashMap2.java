package map;

import java.util.LinkedList;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ManualHashMap2 {
    /**
     * 数组长度
     */
    private LinkedList[] array = new LinkedList[999];
    /**
     * 元素大小
     */
    private int size = 0;

    public ManualHashMap2() {
    }

    public void put(Object key, Object value) {
        // 新建一个Entry结点
        Entry node = new Entry(key, value);
        // 计算hash
        int hash = node.key.hashCode() % array.length;
        hash = hash < 0 ? -hash : hash;
        // 此索引位置没有链表元素
        if (array[hash] == null) {
            // 新建一个链表，用于存放entry元素
            LinkedList<Entry> linkedList = new LinkedList<>();
            // 添加entry元素到链表中
            linkedList.add(node);
            // 将链表元素放在数组中
            array[hash] = linkedList;
            // 维护链表元素大小
            size++;
            // 索引冲突
        } else {
            // 非空
            LinkedList<Entry> linkedList = array[hash];
            boolean flag = false;
            // 链表元素中是否存在entry元素
            for (Entry entry : linkedList) {
                if (entry.key == key || entry.key.equals(key)) {
                    // 存在则替换原来的值
                    entry.value = value;
                    flag = true;
                }
            }
            // 如果存在，加入链表中，链表加入数组，维护大小
            if (!flag) {
                linkedList.add(node);
                array[hash] = linkedList;
                size++;
            }
        }
    }

    public Object get(Object key) {
        // 计算hash
        int hash = key.hashCode() % array.length;
        hash = hash < 0 ? -hash : hash;
        LinkedList<Entry> linkedList = array[hash];
        if (linkedList != null) {
            // 遍历链表元素，查看链表中是否有entry元素
            for (Entry entry : linkedList) {
                if (entry.key == key || entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public Object remove(Object key) {
        // 计算hash
        int hash = key.hashCode() % array.length;
        hash = hash < 0 ? -hash : hash;
        LinkedList<Entry> linkedList = array[hash];
        if (linkedList != null) {
            // 遍历链表元素，查看链表中是否有entry元素
            for (Entry entry : linkedList) {
                if (entry.key == key || entry.key.equals(key)) {
                    Object deleteValue = entry.value;
                    linkedList.remove(entry);
                    // 维护元素大小
                    size--;
                    return deleteValue;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ManualHashMap2 hashMap2 = new ManualHashMap2();
        hashMap2.put("2", "2");
        System.out.println(hashMap2.get("2"));
        hashMap2.put("3", "3");
        hashMap2.remove("2");
        System.out.println(hashMap2.toString());
    }

    @Override
    public String toString() {
        LinkedList<Object> objects = new LinkedList<>();
        for (LinkedList linkedList : array) {
            if (linkedList != null && !linkedList.isEmpty()) {
                objects.add(linkedList);
            }
        }
        return "algorithm.map.ManualHashMap2{" +
                "array=" + objects.toString() +
                ", size=" + size +
                '}';
    }
}

class Entry {
    Object key;
    Object value;

    public Entry(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) &&
                Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "algorithm.map.Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
