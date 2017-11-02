package com.repo51.deploy.Builder;

import com.repo51.deploy.Deploy;
import com.repo51.deploy.ImageManager.ImageDownloader;
import com.repo51.deploy.Request.DeployQueue;

/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class DeployBuilder {
    private DeployQueue deployQueue;
    private ImageDownloader imageDownloader;

    public Deploy build(){

            return new Deploy(this);
        }

}
