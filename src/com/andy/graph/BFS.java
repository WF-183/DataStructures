package com.andy.graph;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/12
 * @version: 1.0.0
 */
public class BFS {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
        GraphByArr graphByArr = new GraphByArr(list.size());
        //添加节点
        for (String item : list) {
            graphByArr.addVertex(item);
        }
        //添加边
        graphByArr.addEdge(0, 1, 1);
        graphByArr.addEdge(0, 2, 1);
        graphByArr.addEdge(1, 3, 1);
        graphByArr.addEdge(1, 4, 1);
        graphByArr.addEdge(3, 7, 1);
        graphByArr.addEdge(4, 7, 1);
        graphByArr.addEdge(2, 5, 1);
        graphByArr.addEdge(2, 6, 1);
        graphByArr.addEdge(5, 6, 1);
        //输出看一眼
        graphByArr.show();
        //广度优先遍历结果： 1 2 3 4 5 6 7 8
        graphByArr.bfs();
    }
}
