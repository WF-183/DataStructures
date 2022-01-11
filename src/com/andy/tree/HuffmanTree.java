package com.andy.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/10
 * @version: 1.0.0
 */
public class HuffmanTree {

    public static void main(String[] args) {
        //一 赫夫曼树
        //int[] arr = new int[] {13, 7, 8, 3, 29, 6, 1};
        //TreeNode root = buildHuffmanTree(arr);
        //root.preOrder();
        //System.out.println("-----------");

        //二 赫夫曼编码
        String input = "i like like like java do you like a java";
        //{1,1,1,2,2,2,4,4,4,5,5,9};
        List<TreeNode> nodeList = getCharNodeList(input);
        TreeNode root = buildHuffmanTree(nodeList);
        root.preOrder();
        //{ =01, a=100, d=11000, u=11001, e=1110, v=11011, i=101, y=11010, j=0010, k=1111, l=000, o=0011}
        Map<String, String> huffmanDic = getHuffmanDic(root);
        System.out.println(huffmanDic);

    }

    public static TreeNode buildHuffmanTree(int[] arr) {
        //先借助list排序，从小到大
        List<TreeNode> list = Arrays.stream(arr).mapToObj(item -> new TreeNode(item)).collect(Collectors.toList());
        list.sort((o1, o2) -> o1.getId() - o2.getId());

        //循环往复，直到处理到最后一个根节点结束，得到的树就是最优解赫夫曼树
        while (list.size() > 1) {
            //取出最小两个元素，组成一颗三节点子树
            TreeNode leftNode = list.get(0);
            TreeNode rightNode = list.get(1);
            TreeNode tempNode = new TreeNode(leftNode.getId() + rightNode.getId());
            tempNode.setLeftNode(leftNode);
            tempNode.setRightNode(rightNode);

            //两元素排除，辅助根节点加入，重新排序
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(tempNode);
            list.sort((o1, o2) -> o1.getId() - o2.getId());
        }
        return list.get(0);
    }

    //-----------------------------------------

    //intput字符串转char数组，统计每个char出现次数，构建节点node集合
    public static List<TreeNode> getCharNodeList(String input) {
        List<TreeNode> result = new ArrayList<>();
        char[] chars = input.toCharArray();

        Map<Character, Integer> countMap = new HashMap<>();
        for (char item : chars) {
            if (!countMap.containsKey(item)) {
                countMap.put(item, 0);
            }
            countMap.put(item, countMap.get(item) + 1);
        }

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            result.add(new TreeNode(entry.getValue(), String.valueOf(entry.getKey())));
        }
        return result;
    }

    //叶子节点构建霍夫曼树
    public static TreeNode buildHuffmanTree(List<TreeNode> list) {
        //先借助list排序，从小到大
        list.sort((o1, o2) -> o1.getId() - o2.getId());

        //循环往复，直到处理到最后一个根节点结束，得到的树就是最优解赫夫曼树
        while (list.size() > 1) {
            //取出最小两个元素，组成一颗三节点子树
            TreeNode leftNode = list.get(0);
            TreeNode rightNode = list.get(1);
            TreeNode tempNode = new TreeNode(leftNode.getId() + rightNode.getId());
            tempNode.setLeftNode(leftNode);
            tempNode.setRightNode(rightNode);

            //两元素排除，辅助根节点加入，重新排序
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(tempNode);
            list.sort((o1, o2) -> o1.getId() - o2.getId());
        }
        return list.get(0);
    }

    //按照左0右1约定，获取霍夫曼树各个叶子节点字典
    public static Map<String, String> getHuffmanDic(TreeNode root) {
        Map<String, String> result = new HashMap<>();
        if (root == null) {
            return result;
        }
        //root左半部分
        getLeafNodeCode(root.getLeftNode(), "0", new StringBuilder(), result);
        //root右半部分
        getLeafNodeCode(root.getRightNode(), "1", new StringBuilder(), result);
        return result;
    }

    //递归处理
    //将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到result集合
    public static void getLeafNodeCode(TreeNode node, String flagStr, StringBuilder sb, Map<String, String> result) {
        //必须的
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(flagStr);
        if (node == null) {
            return;
        }
        //非叶子节点 只有叶子节点才在构建NodeList填充了name
        if (node.getName() == null) {
            getLeafNodeCode(node.getLeftNode(), "0", sb2, result);
            getLeafNodeCode(node.getRightNode(), "1", sb2, result);
        } else {//叶子节点
            result.put(node.getName() + "", sb2.toString());
        }
    }

}
