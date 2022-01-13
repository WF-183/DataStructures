package com.andy.graph;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/12
 * @version: 1.0.0
 */
public class GraphByArrTest {//邻接矩阵创建图

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        GraphByArr graphByArr = new GraphByArr(list.size());
        //添加节点
        for (String item : list) {
            graphByArr.addVertex(item);
        }
        //添加边
        //A-B A-C 、B-C B-A B-D B-E 、C-B C-A、D-B、E-B ，无向图，两个方向留一个就行
        //默认值0表示不能直接连通
        graphByArr.addEdge(0, 1, 1);
        graphByArr.addEdge(0, 2, 1);
        graphByArr.addEdge(1, 2, 1);
        graphByArr.addEdge(1, 3, 1);
        graphByArr.addEdge(1, 4, 1);

        //输出看一眼
        graphByArr.show();
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

    //获取节点数量
    public int countVertex() {
        return vertexList.size();
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


    //打印邻接矩阵
    public void show() {
        //二维数组遍历
        for (int[] arr : edgeArr) {
            System.out.println(Arrays.toString(arr));
        }
    }

    //返回直接下标节点i数据
    public String getVertexValByIndex(int i) {
        return vertexList.get(i);
    }

    //返回节点i和节点j间边的权值
    public int getWeight(int i, int j) {
        return edgeArr[i][j];
    }

    //-------------------

    //深度优先遍历
    public void dfs() {
        //每个节点都作为初始节点过一遍
        for (int i = 0; i < vertexList.size(); i++) {
            dfsRound(isVisited, i);
        }
    }

    //深度优先遍历 - 一个初始节点的一轮，深度遍历需要递归
    public void dfsRound(boolean[] isVisited, int i) {
        if (!isVisited[i]) {
            //输出
            System.out.println(getVertexValByIndex(i));
            //标记
            isVisited[i] = true;
        }

        //本行第一个连通节点
        int next = getFirstNeighborIndex(i);
        //下个连通节点存在一直往下找
        while (next != -1) {
            //此节点还没输出过
            if (!isVisited[next]) {
                //输出
                System.out.println(getVertexValByIndex(next));
                //标记
                isVisited[next] = true;
                //每找到一个可输出节点就往下进行此节点的递归
                dfsRound(isVisited, next);
            }
            //此节点已经输出过，往本层右移继续找连通节点
            next = getNextNeighborIndex(i, next);
        }
    }

    //辅助方法 获取指定节点本层的第一个连通节点下标
    public int getFirstNeighborIndex(int i) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edgeArr[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //辅助方法 获取指定节点+本层指定连通节点 的下一个连通接点下标
    public int getNextNeighborIndex(int i, int next) {
        //注意从next+1开始再往右找
        for (int j = next + 1; j < vertexList.size(); j++) {
            if (edgeArr[i][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //-------------------

    //广度优先遍历
    public void bfs() {
        //每个节点都作为初始节点过一遍
        for (int i = 0; i < vertexList.size(); i++) {
            bfsRound(isVisited, i);
        }
    }

    //广度优先遍历 - 一个初始节点的一轮，广度遍历不需要递归
    public void bfsRound(boolean[] isVisited, int i) {
        //辅助队列
        LinkedList<Integer> queue = new LinkedList<>();

        if (!isVisited[i]) {
            //输出
            System.out.println(getVertexValByIndex(i));
            //标记
            isVisited[i] = true;
            //入队列
            queue.addLast(i);
        }

        while (!queue.isEmpty()) {
            //出队列 队首取，借助队列完成每层各个节点对下一层的进入
            i = queue.removeFirst();
            //本行第一个连通节点
            int next = getFirstNeighborIndex(i);
            //下个连通节点存在一直往下找
            while (next != -1) {
                //此节点还没输出过
                if (!isVisited[next]) {
                    //输出
                    System.out.println(getVertexValByIndex(next));
                    //标记
                    isVisited[next] = true;
                    //入队列 队尾加
                    queue.addLast(next);
                }
                //此节点已经输出过，往本层右移继续找连通节点
                next = getNextNeighborIndex(i, next);
            }
        }
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