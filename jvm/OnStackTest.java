package jvm;

/**
 * @author dengjia
 */
public class OnStackTest {
    /**
     * User类
     */
    public static class User {
        public int id = 0;
        public String name = "";
    }

    /**
     * 创建User类对象
     */
    public static void alloc() {
        User u = new User();
        u.id = 5;
        u.name = "geym";
    }

    /**
     * 开启逃逸分析
     * -Xmx5m -Xms5m -XX:+PrintGC -XX:+DoEscapeAnalysis -XX:+UseTLAB -XX:+EliminateAllocations
     * 关闭逃逸分析
     * -Xmx5m -Xms5m -XX:+PrintGC -XX:-DoEscapeAnalysis -XX:-UseTLAB -XX:-EliminateAllocations
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long b = System.currentTimeMillis();
        // 创建大量的对象
        int length = 99999999;
        for (int i = 0; i < length; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        // 打印执行时间
        System.out.println(String.format("执行时间：%d ms", e - b));
    }
}
