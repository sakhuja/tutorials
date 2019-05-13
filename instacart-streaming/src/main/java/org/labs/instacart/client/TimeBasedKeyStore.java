package org.labs.instacart.client;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyStore {
    public static void main(String args[] ) throws Exception {
        KV kv = new KV();
        Long ts1 = kv.set("foo", "bar1");
        Long ts2 = kv.set("foo", "bar2");

        System.out.println(kv.get("foo", ts1));
        System.out.println(kv.get("foo", ts2));
        System.out.println(kv.get("foo"));
    }
}

class KV {
    Map<String, TreeMap<Long, String>> kVStore = new HashMap<>();

    public Long set(String key, String val) {
        Long currTS = System.currentTimeMillis();
        TreeMap<Long, String> timestampMap =  kVStore.get(key);
        if(timestampMap != null) {
            timestampMap.putIfAbsent(currTS, val);
        } else {
            timestampMap = new TreeMap<>();
            timestampMap.put(currTS, val);
            kVStore.put(key, timestampMap);
        }

        return currTS;
    }

    public String get(String key, Long timestamp) {
        TreeMap<Long, String> tsMap = kVStore.get(key);
        if(tsMap != null) {
            return tsMap.get(timestamp);
        }
        return null;
    }

    public String get(String key) {
        TreeMap<Long, String> tsMap = kVStore.get(key);
        if(tsMap != null) {
            return tsMap.get(tsMap.lastKey());
        }
        return null;
    }
}