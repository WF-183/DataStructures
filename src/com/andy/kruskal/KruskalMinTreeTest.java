package com.andy.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/13
 * @version: 1.0.0
 */
public class KruskalMinTreeTest {//克鲁斯卡尔算法-最小生成树问题

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

        //克鲁斯卡尔算法求解最小生成树方案图，最小总权值=25  实测可行
        //[Edge{startVal='A', endVal='G', weight=2}, Edge{startVal='B', endVal='G', weight=3},
        //Edge{startVal='D', endVal='F', weight=4}, Edge{startVal='E', endVal='G', weight=4},
        //Edge{startVal='E', endVal='F', weight=5}, Edge{startVal='A', endVal='C', weight=7}]
        Edge[] edges = oriGraph2minTreeGraphKruskal(graphByArr);
        System.out.println(Arrays.toString(edges));
    }

    /**
     * 克鲁斯卡尔算法求解最小生成树方案
     * @param oriGraph 问题全连通原始图
     * @return
     */
    public static Edge[] oriGraph2minTreeGraphKruskal(GraphByArr oriGraph) {
        //获取原始图所有边对象
        Edge[] edges = oriGraph.getAllEdge();
        //排序 从小到大，任选一种排序算法
        bubbleSort(edges);

        //入选边结果
        Edge[] result = new Edge[edges.length];

        //辅助变量 放置入选边
        int index = 0;
        //辅助变量 保存在"已有最小生成树" 中的每个顶点在其中的终点，i = 节点i， ends[i] = 节点i的终点index值，ends随着处理动态变化
        int[] ends = new int[edges.length];

        for (int i = 0; i < edges.length; i++) {
            //获取当前边的两端点 节点下标
            int p1 = oriGraph.getIndexByVal(edges[i].getStartVal());
            int p2 = oriGraph.getIndexByVal(edges[i].getEndVal());
            //获取两端点各自 在已有最小生成树中的终点
            int m = getEndIndex(ends, p1);
            int n = getEndIndex(ends, p2);
            //终点不同入选，否则舍弃
            if (m != n) {
                result[index++] = edges[i];
                ends[m] = n;
            }
        }
        return result;
    }

    /**
     * 关键辅助方法 获取下标为 i 的顶点的终点
     * @param ends
     * @param i
     * @return
     */
    private static int getEndIndex(int[] ends, int i) {
        //潜在条件，ends数组元素默认值都是0，如果end[i]=0，则可能是这个点还没加入最小生成树，也可能是这个点本身就是终点。
        if (ends[i] == 0) {
            return i;
        }
        //一个点终点是一个点，则这个点换成终点，再往下找，直到找到终点本身结束，返回终点
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //辅助方法 冒泡排序
    public static void bubbleSort(Edge[] arr) {
        boolean swapFlag = false;
        Edge temp = null;
        //总轮数
        for (int i = 0; i < arr.length - 1; i++) {
            //每轮比较次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //交换 从左到右，两两比较，大的放后面；要大到小还是小到大改一个符号即可；
                if (arr[j].getWeight() > arr[j + 1].getWeight()) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapFlag = true;
                }
            }
            //优化
            if (swapFlag) {
                //本轮存在交换则进入下一轮 清空标志位
                swapFlag = false;
            } else {
                //本轮不存在任何交换则直接全部结束
                break;
            }
        }
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

    //获取边数量
    public int countEdge() {
        int count = 0;
        for (int i = 0; i < vertexList.size(); i++) {
            //注意，j = i+1，实现不重复统计效果，A-C、C-A算1条边
            for (int j = i + 1; j < vertexList.size(); j++) {
                if (edgeArr[i][j] != 0) {
                    count++;
                }
            }
        }
        edgeNum = count;
        return edgeNum;
    }

    //获取所有边对象
    public Edge[] getAllEdge() {
        Edge[] result = new Edge[countEdge()];
        int index = 0;
        for (int i = 0; i < vertexList.size(); i++) {
            //注意，j = i+1，实现不重复统计效果，A-C、C-A算1条边
            for (int j = i + 1; j < vertexList.size(); j++) {
                if (edgeArr[i][j] != 0) {
                    result[index++] = new Edge(vertexList.get(i), vertexList.get(j), edgeArr[i][j]);
                }
            }
        }
        return result;
    }

    //通过节点值获取其下标
    //隐含前提：节点间是有先后顺序的，且每个节点有自己下标。A-G就对应节点下标0-6 。
    public int getIndexByVal(String val) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).equals(val)) {
                return i;
            }
        }
        return -1;
    }

}

//边对象
class Edge {

    private String startVal;
    private String endVal;
    private int weight;

    public Edge(String startVal, String endVal, int weight) {
        this.startVal = startVal;
        this.endVal = endVal;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" + "startVal='" + startVal + '\'' + ", endVal='" + endVal + '\'' + ", weight=" + weight + '}';
    }

    public int getWeight() {
        return weight;
    }

    public String getStartVal() {
        return startVal;
    }

    public String getEndVal() {
        return endVal;
    }
}