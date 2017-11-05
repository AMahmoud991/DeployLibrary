package com.repo51.deploy.deploymanager;

import com.repo51.deploy.cashe.BaseCache;

/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class DeployBuilder {
    private DeployQueue deployQueue;
    private BaseCache requestCache;

    public DeployBuilder() {
        this.deployQueue = DeployQueue.getInstance();
    }



    public DeployBuilder rquestCache(BaseCache customRequestCache) {
        requestCache = customRequestCache;
        return this;
    }

    public Deploy build() {
        checkCache();
        return Deploy.getInstance(this);
    }

    private void checkCache() {

        if (requestCache == null) {
            requestCache = new BaseCache<>();
        }
    }

    public DeployQueue getDeployQueue() {
        return deployQueue;
    }






    public BaseCache getRequestCache() {
        return requestCache;
    }

}
