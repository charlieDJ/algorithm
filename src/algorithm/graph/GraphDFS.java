package algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先搜索
 */
public class GraphDFS {

    /**
     * 先序遍历存放顶点的列表
     */
    private List<Integer> pre;
    /**
     * 后序遍历存放顶点的列表
     */
    private List<Integer> post;
    /**
     * 记录索引处的顶点是否被访问
     */
    private boolean[] visited;
    /**
     * 图
     */
    private Graph G;

    public GraphDFS(Graph G) {
        this.G = G;
        pre = new ArrayList<>();
        post = new ArrayList<>();
        // 空间等于图的顶点数
        visited = new boolean[G.V()];
        // 针对联通分量做出的改进，每个顶点都需要遍历
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        //遍历与此顶点相邻的顶点
        for (Integer w : G.adj(v)) {
            // 如果遍历过，不再重复遍历。因为这些节点可能存在环
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    /**
     * @return 返回遍历到的顶点
     */
    public Iterable<Integer> pre() {
        return pre;
    }

    /**
     * @return 返回后序遍历到的顶点
     */
    public Iterable<Integer> post() {
        return post;
    }

    public static void main(String[] args) {
        final Graph G = new Graph("g1.txt");
        final GraphDFS graphDFS = new GraphDFS(G);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }

}
