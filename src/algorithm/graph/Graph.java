package algorithm.graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 只处理简单图
 */
public class Graph {
    /**
     * 顶点
     */
    private int V;
    /**
     * 边
     */
    private int E;
    /**
     * 用红黑树表示图
     */
    private TreeSet<Integer>[] adj;

    /**
     * 从文件中读取，填充到数组中
     *
     * @param filename 文件名称
     */
    @SuppressWarnings("unchecked")
    public Graph(String filename) {

        final File file = new File(filename);

        try (final Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("vertex must be non-negative!");
            }
            adj = new TreeSet[V];
            // 每一个顶点对应一条链表
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }
            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("edges must be non-negative!");
            }
            for (int i = 0; i < E; i++) {
                final int a = scanner.nextInt();
                validateVertex(a);
                final int b = scanner.nextInt();
                validateVertex(b);
                // 去除自环边
                if (a == b) {
                    throw new IllegalArgumentException("Self loop is detected");
                }
                // 去除平行边
                // 修改为红黑树自后，contains和add方法的时间复杂度变为了 O(logN)
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel edge is detected");
                }
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 两个顶点是否有边
     *
     * @param v 顶点v
     * @param w 顶点w
     * @return true：有边
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * @param v 顶点v
     * @return 与顶点v相邻的顶点
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * @param v 顶点v
     * @return 顶点v的度（相邻边）
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 验证顶点是否合法，小于0或大于最大的顶点数，都是不合法的
     *
     * @param v 顶点
     */
    private void validateVertex(int v) {
        if (v < 0 || v > V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    /**
     * 重写toString
     *
     * @return 自定义toString
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d ", v));
            for (Integer w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final Graph graph = new Graph("g1.txt");
        System.out.println(graph);
    }
}
