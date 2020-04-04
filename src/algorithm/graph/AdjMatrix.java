package algorithm.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 只处理简单图
 */
public class AdjMatrix {
    /**
     * 顶点
     */
    private int V;
    /**
     * 边
     */
    private int E;
    /**
     * 矩阵，用二维数组表示
     */
    private int[][] adj;

    /**
     * 从文件中读取，填充到数组中
     *
     * @param filename 文件名称
     */
    public AdjMatrix(String filename) {

        final File file = new File(filename);

        try (final Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("vertex must be non-negative!");
            }
            adj = new int[V][V];
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
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel edge is detected");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
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
        return adj[v][w] == 1;
    }

    /**
     * @param v 顶点v
     * @return 与顶点v相邻的顶点
     */
    public List<Integer> adj(int v) {
        validateVertex(v);
        List<Integer> vertexes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                vertexes.add(i);
            }
        }
        return vertexes;
    }

    /**
     * @param v 顶点v
     * @return 顶点v的度（相邻边）
     */
    public int degree(int v) {
        return adj(v).size();
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
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
