package algorithm.map;

import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class ManualHashMap {

    LinkedList[] arr = new LinkedList[999]; // 键值对集合！ Map底层结构是:数组 + 链表
    int size = 0; // HashMap的容量

    // 构造方法
    public ManualHashMap() {
    }

    /*
     * 向HashMap中存入键值对
     */
    public void put(Object key, Object value) {
        MapEntry node = new MapEntry(key, value);
        /*
         * 获取该键值对在数组中的索引位置(0~998);
         * 重写HashCode()方法就是为了让具有相同属性对象具有相同的HashCode值(地址码);
         * 由于重写HashCode()方法在任何种程度上，都会出现一定的Bug，使得具有不同属性值都会有相同的HashCode码值；
         * 此时就需要重写equals()方法，进行二次比较key值是否相同，就可做到万无一失了！
         */
        int hash = node.key.hashCode() % arr.length;
        hash = hash < 0 ? -hash : hash;
        if (arr[hash] == null) { // 此索引位置为空
            LinkedList<MapEntry> list = new LinkedList<>();    //创建一个双向链表
            arr[hash] = list;
            list.add(node);
            size++;
        } else { // 该位置有元素
            LinkedList<MapEntry> list = arr[hash]; // 取出该索引处的链表
            // 判断有没有键值重复
            boolean flag = false;    //判断此链表中，是否存在重复的键值
            for (MapEntry temp : list) {
                if (temp.key == key || temp.key.equals(key)) { // 键值有重复
                    temp.value = value; // value值覆盖
                    flag = true;
                }
            }
            if (!flag) {    //不存在重复的key，需添加此元素
                list.add(node);
                size++;
            }
        }
    }

    /*
     * 获取键值对中某个键值对对象
     */
    public Object get(Object key) {
        int hash = key.hashCode() % arr.length;
        hash = hash < 0 ? -hash : hash;
        if (arr[hash] != null) {
            LinkedList<MapEntry> list = arr[hash];
            for (MapEntry temp : list) {
                if (temp.key == key || temp.key.equals(key)) {
                    return temp.value;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ManualHashMap map = new ManualHashMap();
        map.put("6", "b");
        map.put("6", "a");
        map.put("5", "c");
        map.put("4", "d");
        System.out.println(map.size);    //3
        System.out.println(map.get("6"));    //a
    }
}

class MapEntry {
    Object key;
    Object value;

    public MapEntry(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }
}