package com.repo51.deploy.cash;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public class ImageCacher implements Cacher<Bitmap> {
    private static ImageCacher instance;
    private LruCache<String, Bitmap> imageCacherData;
    private int cachLimit = 20;

    public static synchronized ImageCacher getInstance() {
        if (instance == null) {
            instance = new ImageCacher();
        }
        return instance;
    }

    @Override
    public void setCachLimit(int cachLimit) {
        this.cachLimit = cachLimit;
    }

    @Override
    public void initCacher() {
        imageCacherData = new LruCache<>(cachLimit);
    }

    @Override
    public Bitmap get(String key) {
        return imageCacherData.get(key);
    }

    @Override
    public void put(String key, Bitmap data) {
        imageCacherData.put(key, data);
    }

    @Override
    public void remove(String key) {
        imageCacherData.remove(key);
    }

    @Override
    public void removeAll() {
        imageCacherData.evictAll();
    }


}
