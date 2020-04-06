package algorithm.graph;

/**
 * 深度优先搜索，检测是否有环
 */
public class CycleDetection {

    /**
     * 记录索引处的顶点是否被访问
     */
    private boolean[] visited;
    /**
     * 图
     */
    private Graph G;
    /**
     * 这张图是否有环
     */
    private boolean hasCycle;

    public CycleDetection(Graph G) {
        this.G = G;
        // 空间等于图的顶点数
        visited = new boolean[G.V()];
        // 针对联通分量做出的改进，每个顶点都需要遍历
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        //遍历与此顶点相邻的顶点
        for (Integer w : G.adj(v)) {
            // 如果遍历过，不再重复遍历。因为这些节点可能存在环
            if (!visited[w]) {
                // 遍历顶点w到顶点v
                // 从顶点w开始，到v顶点，发现有环了，提前返回
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                // w顶点被访问过，而且不是v的上一个顶点，必然有环
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }


    public static void main(String[] args) {
        final Graph G = new Graph("g1.txt");
        final CycleDetection cycleDetection = new CycleDetection(G);
        System.out.println(cycleDetection.hasCycle());

        final Graph G2 = new Graph("g2.txt");
        final CycleDetection cycleDetection2 = new CycleDetection(G2);
        System.out.println(cycleDetection2.hasCycle());
    }

}
