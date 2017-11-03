package com.repo51.deploy.Request;



/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class DeployQueue {
    private static DeployQueue instance;


    public static synchronized DeployQueue getInstance() {
        if (instance == null) {
            instance = new DeployQueue();
        }
        return instance;
    }
}
