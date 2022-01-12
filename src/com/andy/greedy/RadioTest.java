package com.andy.greedy;

import java.util.*;

/**
 *
 * @author： <a href="mailto:wangfei@tianyancha.com">wangfei</a>
 * @date: 2022/1/12
 * @version: 1.0.0
 */
public class RadioTest {//贪心算法-电台覆盖问题

    public static void main(String[] args) {
        //创建广播电台,放入到 Map
        Map<String, Set<String>> allRadioMap = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到 map
        allRadioMap.put("K1", hashSet1);
        allRadioMap.put("K2", hashSet2);
        allRadioMap.put("K3", hashSet3);
        allRadioMap.put("K4", hashSet4);
        allRadioMap.put("K5", hashSet5);
        //needCoverAreaSet 存放所有的地区
        HashSet<String> needCoverAreaSet = new HashSet<String>();
        needCoverAreaSet.add("北京");
        needCoverAreaSet.add("上海");
        needCoverAreaSet.add("天津");
        needCoverAreaSet.add("广州");
        needCoverAreaSet.add("深圳");
        needCoverAreaSet.add("成都");
        needCoverAreaSet.add("杭州");
        needCoverAreaSet.add("大连");

        System.out.println(radioGreedy(allRadioMap, needCoverAreaSet));
    }

    public static List<String> radioGreedy(Map<String, Set<String>> allRadioMap, Set<String> needCoverAreaSet) {
        List<String> result = new ArrayList<>();

        //覆盖未覆盖地区最多 地区数
        int maxCount = 0;
        //对应电台
        String maxKey = null;
        //需要被覆盖地区得到清空才结束
        while (needCoverAreaSet.size() != 0) {
            //每次找一个最优解
            //贪心算法最优条件：每次找一个能覆盖最多  当前还未覆盖地区的  电台 。
            for (String key : allRadioMap.keySet()) {
                //求每个电台地区与 当前未覆盖地区交集数
                HashSet<String> temp = new HashSet<>();
                temp.addAll(allRadioMap.get(key));
                temp.retainAll(needCoverAreaSet);
                //求极值，极值入选
                if (temp.size() > maxCount) {
                    maxCount = temp.size();
                    maxKey = key;
                }
            }

            //最大值入选 、入选电台涉及地区从未覆盖地区中移除
            if (maxKey != null && maxCount > 0) {
                result.add(maxKey);
                needCoverAreaSet.removeAll(allRadioMap.get(maxKey));
            }

            //清空辅助变量，开始下一轮
            maxCount = 0;
            maxKey = null;
        }
        return result;
    }

}
