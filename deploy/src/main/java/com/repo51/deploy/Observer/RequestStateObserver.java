package com.repo51.deploy.Observer;


import com.repo51.deploy.Error.DeployError;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public interface RequestStateObserver<T> {

    void onStartLoadingRequest();
    void onSuccess(T data);
    void onError(DeployError error);
}
