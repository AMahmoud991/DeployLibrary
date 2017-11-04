package com.repo51.deploy.deploy;

import android.graphics.Bitmap;

import com.repo51.deploy.ImageManager.ImageDownloader;
import com.repo51.deploy.cashe.BaseCache;

/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class DeployBuilder {
    private DeployQueue deployQueue;
    private ImageDownloader imageDownloader;
    private BaseCache requestCache;

    public DeployBuilder() {
        this.deployQueue = DeployQueue.getInstance();
        this.imageDownloader = ImageDownloader.getInstance();
    }



    public DeployBuilder rquestCache(BaseCache customRequestCache) {
        requestCache = customRequestCache;
        return this;
    }

    public Deploy build() {
        checkCache();
        return new Deploy(this);
    }

    private void checkCache() {

        if (requestCache == null) {
            requestCache = new BaseCache<String>();
        }
    }

    public DeployQueue getDeployQueue() {
        return deployQueue;
    }

    public ImageDownloader getImageDownloader() {
        return imageDownloader;
    }





    public BaseCache getRequestCache() {
        return requestCache;
    }

}
