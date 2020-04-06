package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 单源路径
 */
public class SingleSourcePath {

    /**
     * 记录索引处的顶点是否被访问
     */
    private boolean[] visited;
    /**
     * 保存当前顶点的上一个顶点是哪个顶点
     */
    private int[] pre;

    /**
     * 图
     */
    private Graph G;
    /**
     * 我们定义的开始顶点
     */
    private int s;

    /**
     * @param G 图
     * @param s 开始顶点
     */
    public SingleSourcePath(Graph G, int s) {
        this.G = G;
        this.s = s;
        // 空间等于图的顶点数
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        // 初始化每个顶点的值
        for (int i = 0; i < G.V(); i++) {
            pre[i] = -1;
        }
        // s 作为开始顶点，它没有上一个顶点
        dfs(s, s);
    }

    /**
     * @param v      当前顶点
     * @param parent 父顶点
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        //遍历与此顶点相邻的顶点
        for (Integer w : G.adj(v)) {
            // 如果遍历过，不再重复遍历。因为这些节点可能存在环
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    /**
     * 目标顶点与源顶点是否相连
     *
     * @param t 目标顶点
     * @return true: 相连
     */
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    /**
     * 单源路径
     * @param t 目标顶点
     * @return 开始顶点到目标顶点的路径
     */
    public Iterable<Integer> path(int t) {
        final ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return res;
        }
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {
        final Graph G = new Graph("g1.txt");
        final SingleSourcePath sspath = new SingleSourcePath(G, 0);
        System.out.println("sspath.path(0 -> 6) = " + sspath.path(6));
        System.out.println("sspath.path(0 -> 5) = " + sspath.path(5));
    }

}
