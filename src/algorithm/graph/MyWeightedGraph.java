package algorithm.graph;

import java.util.*;

/**
 * @author dengjia on 2020/4/8
 */
public class MyWeightedGraph implements Cloneable {

    /**
     * 顶点个数
     */
    private int V;
    /**
     * 边数
     */
    private int E;
    /**
     * 邻接表
     */
    private TreeMap<Integer, Integer>[] adj;

    /**
     * 自定义数字与索引值对应
     */
    private Map<Integer, Integer> sourceMap;
    /**
     * 索引值与自定义数字对应
     */
    private Map<Integer, Integer> indexMap;

    /**
     * 边
     */
    public static class Edge {
        int source;
        int target;
        int weight;

        public Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    public Map<Integer, Integer> indexMap() {
        return indexMap;
    }

    public Map<Integer, Integer> sourceMap() {
        return sourceMap;
    }

    public MyWeightedGraph(List<Edge> edges) {
        // 有序set
        Set<Integer> set = new TreeSet<>();
        for (Edge edge : edges) {
            set.add(edge.source);
            set.add(edge.target);
        }
        // 顶点数
        final int size = set.size();
        Integer[] vertexes = set.toArray(new Integer[size]);
        sourceMap = new HashMap<>(size);
        indexMap = new HashMap<>(size);
        for (int i = 0; i < vertexes.length; i++) {
            final Integer x = vertexes[i];
            sourceMap.put(x, i);
            indexMap.put(i, x);
        }

        this.V = size;
        this.E = edges.size();


        if (V < 0) {
            throw new IllegalArgumentException("V must be non-negative");
        }
        adj = new TreeMap[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new TreeMap<>();
        }

        if (E < 0) {
            throw new IllegalArgumentException("E must be non-negative");
        }
        for (int i = 0; i < E; i++) {
            Edge node = edges.get(i);
            int a = sourceMap.get(node.source);
            validateVertex(a);
            int b = sourceMap.get(node.target);
            validateVertex(b);
            int v = node.weight;
            // 不处理自环边
            if (a == b) throw new IllegalArgumentException("Self Loop is Detected!");
            // 不处理平兴边
            if (adj[a].containsKey(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

            adj[a].put(b, v);
            adj[b].put(a, v);
        }
    }

    /**
     * 校验顶点
     *
     * @param v 顶点
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * @param v 顶点v
     * @param w 顶点w
     * @return 两顶点是否有边，true：有边
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    /**
     * @param v 顶点v
     * @param w 顶点w
     * @return 两顶点之间的权值
     */
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    /**
     * @param v 顶点v
     * @return 顶点v相邻的所有顶点
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    /**
     * @param v 顶点v
     * @return 顶点v所有的度
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 删边
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].containsKey(w)) E--;
        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Object clone() {

        try {
            MyWeightedGraph cloned = (MyWeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for (int v = 0; v < V; v++) {
                cloned.adj[v] = new TreeMap<Integer, Integer>();
                for (Map.Entry<Integer, Integer> entry : adj[v].entrySet())
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet())
                sb.append(String.format("(%d: %d) ", entry.getKey(), entry.getValue()));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final List<Edge> edges = new LinkedList<>();

        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 2));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 3));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(2, 4, 5));
        edges.add(new Edge(3, 4, 1));

        MyWeightedGraph g = new MyWeightedGraph(edges);
        System.out.print(g);
    }
}
