package com.andy.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/13
 * @version: 1.0.0
 */
public class PrimMinTreeTest {//prim算法-最小生成树问题

    public static void main(String[] args) {
        //创建问题原始图 == 把所有顶点全部连通构成的一张图
        List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        GraphByArr graphByArr = new GraphByArr(list.size());
        //添加节点
        for (String item : list) {
            graphByArr.addVertex(item);
        }
        //添加边，带权值，默认值0表示不能直接连通
        graphByArr.addEdge(0, 1, 5);
        graphByArr.addEdge(0, 2, 7);
        graphByArr.addEdge(0, 6, 2);
        graphByArr.addEdge(1, 0, 5);
        graphByArr.addEdge(1, 3, 9);
        graphByArr.addEdge(1, 6, 3);
        graphByArr.addEdge(2, 0, 7);
        graphByArr.addEdge(2, 4, 8);
        graphByArr.addEdge(3, 1, 9);
        graphByArr.addEdge(3, 5, 4);
        graphByArr.addEdge(4, 2, 8);
        graphByArr.addEdge(4, 5, 5);
        graphByArr.addEdge(4, 6, 4);
        graphByArr.addEdge(5, 3, 4);
        graphByArr.addEdge(5, 4, 5);
        graphByArr.addEdge(5, 6, 6);
        graphByArr.addEdge(6, 0, 2);
        graphByArr.addEdge(6, 1, 3);
        graphByArr.addEdge(6, 4, 4);
        graphByArr.addEdge(6, 5, 6);
        //输出看一眼
        graphByArr.show();
        System.out.println("---------");

        //prim算法求解最小生成树方案图，最小总权值=25  实测可行
        GraphByArr minTreeGraph = oriGraph2minTreeGraphPrim(graphByArr);
        minTreeGraph.show();
    }

    /**
     * prim算法求解最小生成树方案图
     * @param oriGraph 问题全连通原始图
     * @return
     */
    public static GraphByArr oriGraph2minTreeGraphPrim(GraphByArr oriGraph) {
        //图的原始节点集合
        List<String> oriVertexList = oriGraph.getVertexList();
        //图的原始边集合
        int[][] oriEdgeArr = oriGraph.getEdgeArr();
        //图的原始节点被访问情况集合
        boolean[] oriIsVisited = oriGraph.getIsVisited();

        //最小生成树对应图对象
        GraphByArr minTreeGraph = new GraphByArr(oriVertexList.size());
        //辅助入选边集合  存放入选的所有边
        int[][] minEdgeArr = new int[oriEdgeArr.length][oriEdgeArr[0].length];

        //辅助变量 求极值选边
        int minWeight = Integer.MAX_VALUE;
        int h1 = -1;
        int h2 = -1;

        //从第一个节点开始
        oriIsVisited[0] = true;

        //执行选边操作n-1次
        for (int k = 0; k < oriVertexList.size() - 1; k++) {
            //所有已访问节点  和  所有未访问节点 之间尝试连接，在获得的所有边中求最小值，入选
            for (int i = 0; i < oriVertexList.size(); i++) {
                for (int j = 0; j < oriVertexList.size(); j++) {
                    //已访问到未访问、直接连通、权更小
                    if (oriIsVisited[i] == true && oriIsVisited[j] == false
                        && oriEdgeArr[i][j] != 0 && oriEdgeArr[i][j] < minWeight) {
                        minWeight = oriEdgeArr[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //一条边选出来了
            //在邻接矩阵中表示两个节点连接，横竖都要处理
            minEdgeArr[h1][h2] = minWeight;
            minEdgeArr[h2][h1] = minWeight;
            //将当前这个结点标记为已经访问
            oriIsVisited[h2] = true;
            //minWeight重新设置用于下一轮求极值
            minWeight = Integer.MAX_VALUE;
        }

        //选边完成，n-1条边都选出来了，构建最小生成树方案图对象
        minTreeGraph.setVertexList(new ArrayList<>(oriVertexList));
        minTreeGraph.setEdgeArr(minEdgeArr);
        return minTreeGraph;
    }

}

//图对象，邻接矩阵实现
class GraphByArr {
    //存放节点
    private List<String> vertexList;
    //存放节点关系
    private int[][] edgeArr;
    //边数量
    private int edgeNum;
    //标志位数组 标记每个节点是否已被遍历过
    private boolean[] isVisited;

    public GraphByArr(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edgeArr = new int[n][n];
        this.isVisited = new boolean[n];
    }

    //添加节点
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    public void addEdge(int i, int j, int weight) {
        //无向图 传进来两个点则两个方向都标记上
        edgeArr[i][j] = weight;
        edgeArr[j][i] = weight;
        edgeNum++;
    }

    //打印邻接矩阵
    public void show() {
        //二维数组遍历
        for (int[] arr : edgeArr) {
            System.out.println(Arrays.toString(arr));
        }
    }

    //get、set
    public List<String> getVertexList() {
        return vertexList;
    }

    public int[][] getEdgeArr() {
        return edgeArr;
    }

    public boolean[] getIsVisited() {
        return isVisited;
    }

    public void setVertexList(List<String> vertexList) {
        this.vertexList = vertexList;
    }

    public void setEdgeArr(int[][] edgeArr) {
        this.edgeArr = edgeArr;
    }
}
