package com.repo51.deploy.cashe;

import android.util.LruCache;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public class BaseCache<T> implements Cache<T> {
    private LruCache<String, T> cacheData;
    private int cachLimit = 20;


    @Override
    public void setCachLimit(int cachLimit) {
        this.cachLimit = cachLimit;
    }

    @Override
    public void initCacher() {

    }


    @Override
    public T get(String key) {
        return cacheData.get(key);
    }

    @Override
    public void put(String key, T data) {
        cacheData.put(key,data);
    }

    @Override
    public void remove(String key) {
        cacheData.remove(key);

    }

    @Override
    public void removeAll() {
        cacheData.evictAll();
    }


}
