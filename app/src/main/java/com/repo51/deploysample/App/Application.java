package com.repo51.deploysample.app;

import com.repo51.deploy.cashe.BaseCache;
import com.repo51.deploy.deploy.DeployBuilder;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new DeployBuilder().build();
    }
}
