package org.labs.instacart.client;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {

    Map<String, TreeMap<Integer, String>> M;

    public TimeMap() {
        M = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        if (!M.containsKey(key)) {
            M.put(key, new TreeMap());
        }
        M.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!M.containsKey(key)) return "";

        TreeMap<Integer, String> tree = M.get(key);
        Integer t = tree.floorKey(timestamp);
        return t != null ? tree.get(t) : "";
    }

    public static void main(String[] args) {
        TimeMap tm = new TimeMap();
        tm.set("1", "1", 1);
        System.out.println(tm.get("1", 1));
    }
}