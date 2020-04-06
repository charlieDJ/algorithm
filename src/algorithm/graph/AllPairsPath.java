package algorithm.graph;

/**
 * 所有点路径问题，对应英文 All Pairs(Vertices) Path (Problem)
 */
public class AllPairsPath {

    private Graph G;
    /**
     * 定义 SingleSourcePath 数组，存储每一个顶点到另外顶点之间的路径
     */
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph G) {
        this.G = G;
        // 图中有 V 个顶点，就有 V 个SingleSourcePath
        this.paths = new SingleSourcePath[G.V()];

        for (int v = 0; v < G.V(); v++) {
            final SingleSourcePath path = new SingleSourcePath(G, v);
            this.paths[v] = path;
        }

    }


}
