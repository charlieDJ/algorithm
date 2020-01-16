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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 创建User类对象
     */
    public static void alloc() {
        User u = new User();
        u.setId(5);
        u.setName("geym");
    }

    /**
     * 开启逃逸分析
     * -Xmx5m -Xms5m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+DoEscapeAnalysis -XX:+UseTLAB -XX:+EliminateAllocations
     * -Xmx5m -Xms5m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+DoEscapeAnalysis -XX:+UseTLAB -XX:+EliminateAllocations
     * 关闭逃逸分析
     * -Xmx5m -Xms5m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-DoEscapeAnalysis -XX:-UseTLAB -XX:-EliminateAllocations
     * -Xmx5m -Xms5m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-DoEscapeAnalysis -XX:-UseTLAB -XX:-EliminateAllocations
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
        System.err.println(String.format("执行时间：%d ms", e - b));
    }
}
