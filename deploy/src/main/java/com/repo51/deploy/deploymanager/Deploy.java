package com.repo51.deploy.deploymanager;

import com.repo51.deploy.cashe.BaseCache;

/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class Deploy {
    private static Deploy instance;
    private DeployQueue deployQueue;
    private BaseCache requestCache;

    public Deploy(DeployBuilder deployBuilder) {
        this.deployQueue = deployBuilder.getDeployQueue();
        this.requestCache = deployBuilder.getRequestCache();

    }

    private Deploy() {
    throw  new NullPointerException();
    }


    public static synchronized Deploy getInstance(DeployBuilder deployBuilder) {
        if (instance == null) {
            instance = new Deploy(deployBuilder);
        }
        return instance;
    }
    public static synchronized Deploy getInstance() {
        if (instance == null) {
            instance = new Deploy();
        }
        return instance;
    }


    public DeployQueue getDeployQueue() {
        return deployQueue;
    }





    public BaseCache getRequestCache() {
        return requestCache;
    }


}
