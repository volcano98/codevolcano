package SourceCode;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class Cache<K, V> {
    void function() throws ExecutionException {
        // 第一种：loader方式
        LoadingCache<Object, Object> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<Object, Object>() {
                    @Override
                    public Object load(Object name) {
                        // 在cache找不到就从load()方法中取数据
                        return String.format("重新load（%s）：%s", System.currentTimeMillis(), name);
                    }
                });
        cache.get("a");
//
    }

    private final ConcurrentHashMap<K, V> cacheMap = new ConcurrentHashMap<>();
    HashMap map=new HashMap();
    public  Object getCache(Object keyValue, String ThreadName) {
        Object value = null;
        //从缓存获取数据
        value = cacheMap.get(keyValue);
        //如果没有的话，把数据放到缓存
        if (value == null) {
            return putCache(keyValue, ThreadName);
        }
        return value;
    }

    public Object putCache(Object keyValue, String ThreadName) {
        System.out.println("excute once");
        @SuppressWarnings("unchecked")
        String value = ThreadName;
        //把数据放到缓存
        cacheMap.putIfAbsent((K) keyValue, (V) value);
        return value;
    }

    public static void main(String[] args) {
        String key="key";
        final Cache<String, String> cache = new Cache<String, String>();

        new Thread(() -> {
            System.out.println("T1======start========");
            Object value = cache.getCache(key, "T1");
            System.out.println("T1 value==============" + value);
            System.out.println("T1======end========");
        }).start();

        new Thread(() -> {
            System.out.println("T2======start========");
            Object value = cache.getCache(key, "T2");
            System.out.println("T2 value==============" + value);
            System.out.println("T2======end========");

        }).start();

        new Thread(() -> {
            System.out.println("T3======start========");
            Object value = cache.getCache(key, "T3");
            System.out.println("T3 value==============" + value);
            System.out.println("T3======end========");

        }).start();
    }

}
