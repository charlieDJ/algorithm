package algorithm.graph;

import java.util.*;


public class MyDijkstra {
    /**
     * 图的表示
     */
    private MyWeightedGraph G;
    /**
     * 源点，起始点
     */
    private int s;
    /**
     * 距离数组
     */
    private int[] dis;
    /**
     * 顶点是否被访问
     */
    private boolean[] visited;
    /**
     * 保存当前顶点的上一个顶点
     */
    private int[] pre;

    /**
     * 顶点v索引值与dis数组的对应值
     */
    private class Node implements Comparable<Node> {

        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    /**
     * Dijkstra 算法逻辑
     *
     * @param G 图
     * @param s 起点
     */
    public MyDijkstra(MyWeightedGraph G, int s) {

        this.G = G;
        final Map<Integer, Integer> sourceMap = G.sourceMap();
        s = sourceMap.get(s);
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dis[s] = 0;
        pre[s] = s;
        visited = new boolean[G.V()];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            // 寻找权值最小的顶点
            int cur = pq.remove().v;

            if (visited[cur]) {
                continue;
            }

            visited[cur] = true;
            for (int w : G.adj(cur)) {
                if (!visited[w]) {
                    // 可能溢出，考虑修改dis类型
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
                }
            }
        }
    }

    /**
     * @param v 顶点v
     * @return 源点与顶点v是否联通
     */
    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    /**
     * @param v 顶点v
     * @return 源点到顶点v的距离
     */
    public int distTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

    /**
     * @param t 目标点
     * @return 源点到目标点的路径
     */
    public Iterable<Integer> path(int t) {
        final Integer target = G.sourceMap().get(t);
        ArrayList<Integer> res = new ArrayList<>();

        if (!isConnectedTo(target)) {
            return res;
        }
        // 从后往前找到路径
        int cur = target;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);

        final Map<Integer, Integer> indexMap = G.indexMap();
        final ArrayList<Integer> newRes = new ArrayList<>();
        for (Integer re : res) {
            newRes.add(indexMap.get(re));
        }
        return newRes;
    }

    static public void main(String[] args) {
        LinkedList<MyWeightedGraph.Edge> edges = new LinkedList<>();

        edges.add(new MyWeightedGraph.Edge(10, 11, 4));
        edges.add(new MyWeightedGraph.Edge(10, 12, 2));
        edges.add(new MyWeightedGraph.Edge(11, 12, 1));
        edges.add(new MyWeightedGraph.Edge(11, 13, 2));
        edges.add(new MyWeightedGraph.Edge(11, 14, 3));

        edges.add(new MyWeightedGraph.Edge(12, 13, 4));
        edges.add(new MyWeightedGraph.Edge(12, 14, 5));
        edges.add(new MyWeightedGraph.Edge(13, 14, 1));


        MyWeightedGraph g = new MyWeightedGraph(edges);
        MyDijkstra dij = new MyDijkstra(g, 13);
        for (int v = 0; v < g.V(); v++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();
        final Iterable<Integer> path = dij.path(10);
        System.out.println(path);
    }
}
