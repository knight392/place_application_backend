package com.place_application.demo.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集合工具类
 */
public class MySet {
    /**
     *  集合 A - 集合 B
     * @param listA
     * @param listB
     * @return 差值
     */
    public List<Integer> subtract(List<Integer> listA, List<Integer> listB) {
        Set<Integer> setB = new HashSet<>(listB);
        List<Integer> res = new ArrayList<>();
        for (Integer item : listA) {
            if(!setB.contains(item)) res.add(item);
        }
        return res;
    }
}
