package com.repo51.deploy.cash;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public class ImageCasher implements Casher<Bitmap> {
    private static ImageCasher instance;
    private LruCache<String, Bitmap> imageCasherData;
    private int cashLimit = 20;

    public static synchronized ImageCasher getInstance() {
        if (instance == null) {
            instance = new ImageCasher();
        }
        return instance;
    }

    public void setCashLimit(int cashLimit) {
        this.cashLimit = cashLimit;
    }

    @Override
    public void initCasher() {
        imageCasherData = new LruCache<>(cashLimit);
    }

    @Override
    public Bitmap get(String key) {
        return imageCasherData.get(key);
    }

    @Override
    public void put(String key, Bitmap data) {
        imageCasherData.put(key, data);
    }

    @Override
    public void remove(String key) {
        imageCasherData.remove(key);
    }

    @Override
    public void removeAll() {
        imageCasherData.evictAll();
    }


}
