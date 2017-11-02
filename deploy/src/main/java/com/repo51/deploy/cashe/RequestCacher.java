package com.repo51.deploy.cashe;

import android.util.LruCache;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public class RequestCacher implements Cacher<String> {
    private static RequestCacher instance;
    private LruCache<String, String> requestCacherData;
    private int cachLimit = 20;

    public static synchronized RequestCacher getInstance() {
        if (instance == null) {
            instance = new RequestCacher();
        }
        return instance;
    }

    @Override
    public void setCachLimit(int cachLimit) {
        this.cachLimit=cachLimit;
    }

    @Override
    public void initCacher() {
        requestCacherData = new LruCache<>(cachLimit);

    }

    @Override
    public String get(String key) {
        return requestCacherData.get(key);
    }

    @Override
    public void put(String key, String data) {
requestCacherData.put(key, data);
    }

    @Override
    public void remove(String key) {
requestCacherData.remove(key);
    }

    @Override
    public void removeAll() {
requestCacherData.evictAll();
    }
}
