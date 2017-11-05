package com.repo51.deploy.cashe;

import android.util.LruCache;

import java.util.ArrayList;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public class BaseCache<T> implements Cache<T> {
    private LruCache<String, T> cacheData;
    private int cachLimit=20;

    public BaseCache() {
    }

    public BaseCache(int cachLimit) {
        this.cachLimit = cachLimit;
    }

    @Override
    public void setCachLimit(int cachLimit) {
        this.cachLimit = cachLimit;
    }

    @Override
    public void initCacher() {
cacheData=new LruCache<>(cachLimit);
    }


    @Override
    public T get(String key) {
if(cacheData==null){
    initCacher();
}
        return cacheData.get(key);
    }

    @Override
    public void put(String key, T data) {
        if(cacheData==null){
            initCacher();
        }
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
