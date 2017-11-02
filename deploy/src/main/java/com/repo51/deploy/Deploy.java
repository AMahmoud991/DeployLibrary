package com.repo51.deploy;

import android.graphics.Bitmap;

import com.repo51.deploy.Builder.DeployBuilder;
import com.repo51.deploy.ImageManager.ImageDownloader;
import com.repo51.deploy.Request.DeployQueue;
import com.repo51.deploy.Request.Request;
import com.repo51.deploy.cashe.ImageCacher;
import com.repo51.deploy.cashe.RequestCacher;


/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class Deploy {
    private static Deploy instance;
    private DeployQueue deployQueue;
    private ImageDownloader imageDownloader;
    private ImageCacher imageCacher;
    private RequestCacher requestCacher;

    public Deploy(DeployBuilder deployBuilder) {

    }


    public static synchronized Deploy getInstance(DeployBuilder deployBuilder){
        if (instance == null) {
            instance = new Deploy(deployBuilder);
        }
        return instance;
    }

    public static Deploy getInstance() {
        return instance;
    }

    public static void setInstance(Deploy instance) {
        Deploy.instance = instance;
    }

    public DeployQueue getDeployQueue() {
        return deployQueue;
    }

    public void setDeployQueue(DeployQueue deployQueue) {
        this.deployQueue = deployQueue;
    }

    public ImageDownloader getImageDownloader() {
        return imageDownloader;
    }

    public void setImageDownloader(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    public ImageCacher getImageCacher() {
        return imageCacher;
    }

    public void setImageCacher(ImageCacher imageCacher) {
        this.imageCacher = imageCacher;
    }

    public RequestCacher getRequestCacher() {
        return requestCacher;
    }

    public void setRequestCacher(RequestCacher requestCacher) {
        this.requestCacher = requestCacher;
    }
}
