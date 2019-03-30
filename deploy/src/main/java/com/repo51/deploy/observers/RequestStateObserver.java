package com.repo51.deploy.observers;


import com.repo51.deploy.errors.DeployError;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public interface RequestStateObserver<T> {
    void onSuccess(T data);
    void onError(DeployError error);
}
