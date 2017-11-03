package com.repo51.deploy.ImageManager;

import android.graphics.Bitmap;
import android.util.LruCache;


/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class ImageDownloader {
    private static ImageDownloader instance;

    public static synchronized ImageDownloader getInstance() {
        if (instance == null) {
            instance = new ImageDownloader();
        }
        return instance;
    }
}
